package com.cdfive.sentinel.demo.starter.sentinel.adapter.servlet;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlCleaner;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cdfive
 */
@Slf4j
public class CustomUrlCleaner implements UrlCleaner {

    private List<UrlParser> urlParsers = new ArrayList<>();

    @Override
    public String clean(String originUrl) {
        if (urlParsers.isEmpty()) {
            return originUrl;
        }

        for (UrlParser urlParser : urlParsers) {
            if (urlParser.getUrls() != null && urlParser.getUrls().contains(originUrl)) {
                try {
                    return urlParser.parseUrl(originUrl);
                } catch (Exception e) {
                    log.error("urlParser[{}] parse url[{}] error", urlParser.getClass().getSimpleName(), originUrl, e);
                    return originUrl;
                }
            }
        }

        return originUrl;
    }

    public List<UrlParser> getUrlParsers() {
        return urlParsers;
    }

    public void setUrlParsers(List<UrlParser> urlParsers) {
        this.urlParsers = urlParsers;
    }
}