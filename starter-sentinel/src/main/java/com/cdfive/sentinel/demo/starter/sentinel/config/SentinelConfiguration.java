package com.cdfive.sentinel.demo.starter.sentinel.config;

import com.alibaba.csp.sentinel.Constants;
import com.alibaba.csp.sentinel.adapter.dubbo.config.DubboAdapterGlobalConfig;
import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallback;
import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlCleaner;
import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import com.alibaba.csp.sentinel.config.SentinelConfig;
import com.alibaba.csp.sentinel.init.InitExecutor;
import com.alibaba.csp.sentinel.transport.config.TransportConfig;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.cdfive.sentinel.demo.starter.sentinel.adapter.servlet.CustomUrlCleaner;
import com.cdfive.sentinel.demo.starter.sentinel.adapter.servlet.UrlParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author cdfive
 */
@Slf4j
public class SentinelConfiguration {

    public static final String LOG_PRIFEX = "[starter-sentinel]SentinelConfiguration";

    @Autowired(required = false)
    private SentinelProperties sentinelProperties;

    @Autowired(required = false)
    private UrlCleaner urlCleaner;

    @Autowired(required = false)
    private List<UrlParser> urlParsers;

    @Autowired(required = false)
    private DubboFallback dubboFallback;

    @PostConstruct
    public void init() throws Exception {
        log.info("{} init", LOG_PRIFEX);

        if (sentinelProperties == null) {
            return;
        }

        // enable or disable sentinel
        Boolean enable = sentinelProperties.getEnable();
        log.info("{} enable={}", LOG_PRIFEX, enable);
        if (!enable) {
            Constants.ON = false;
            return;
        }

        // set application name as a sentinel client
        String appName = sentinelProperties.getAppName();
        if (StringUtil.isNotBlank(appName)) {
            log.info("{} appName={}", LOG_PRIFEX, appName);
            System.setProperty(SentinelConfig.APP_NAME_PROP_KEY, appName);
        }

        // set client port as a sentinel client
        Integer clientPort = sentinelProperties.getClientPort();
        if (clientPort != null) {
            log.info("{} clientPort={}", LOG_PRIFEX, clientPort);
            TransportConfig.setRuntimePort(clientPort);
        }

        // set dashboard url of sentinel
        String dashboardUrl = sentinelProperties.getDashboardUrl();
        if (StringUtil.isNotBlank(dashboardUrl)) {
            log.info("{} dashboardUrl={}", LOG_PRIFEX, dashboardUrl);
            SentinelConfig.setConfig(TransportConfig.CONSOLE_SERVER, dashboardUrl);
        }

        // register UrlCleaner
        if (urlCleaner != null) {
            log.info("{} register UrlCleaner", LOG_PRIFEX);
            WebCallbackManager.setUrlCleaner(urlCleaner);
        } else if (!CollectionUtils.isEmpty(urlParsers)) {
            log.info("{} register CustormUrlCleaner, UrlParsers size={}", LOG_PRIFEX, urlParsers.size());
            for (UrlParser urlParser : urlParsers) {
                log.info("{} UrlParser found: {}", LOG_PRIFEX, urlParser.getClass().getSimpleName());
            }
            CustomUrlCleaner urlCleaner = new CustomUrlCleaner();
            urlCleaner.setUrlParsers(urlParsers);
            WebCallbackManager.setUrlCleaner(urlCleaner);
        } else {
            log.info("{} use DefaultUrlCleaner", LOG_PRIFEX);
        }

        // register dubbo fallback
        if (dubboFallback != null) {
            DubboAdapterGlobalConfig.setProviderFallback(dubboFallback);
            DubboAdapterGlobalConfig.setConsumerFallback(dubboFallback);
        }

        // init sentinel if we need to init eagerly
        Boolean eager = sentinelProperties.getEager();
        log.info("{} eager={}", LOG_PRIFEX, eager);
        if (eager) {
            InitExecutor.doInit();
        }
    }
}
