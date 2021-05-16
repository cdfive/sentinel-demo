package com.cdfive.sentinel.demo.base.controller;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.status.Status;
import org.apache.dubbo.common.threadpool.manager.DefaultExecutorRepository;
import org.apache.dubbo.common.threadpool.manager.ExecutorRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static org.apache.dubbo.common.extension.ExtensionLoader.getExtensionLoader;

/**
 * @author cdfive
 */
@RequestMapping("/dubbo")
@RestController
public class DubboThreadPoolStatusController {

    @RequestMapping("/threadPoolStatus")
    public Map<String, Object> threadPoolStatus() {
        ExecutorRepository executorRepository = getExtensionLoader(ExecutorRepository.class).getDefaultExtension();
        DefaultExecutorRepository defaultExecutorRepository = (DefaultExecutorRepository) executorRepository;
        ConcurrentMap<String, ConcurrentMap<Integer, ExecutorService>> data = null;
        try {
            Field field = defaultExecutorRepository.getClass().getDeclaredField("data");
            field.setAccessible(true);
            data = (ConcurrentMap<String, ConcurrentMap<Integer, ExecutorService>>) field.get(defaultExecutorRepository);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        if (data == null) {
            return Collections.emptyMap();
        }

        Map<String, Object> map = new HashMap<>();

        ConcurrentMap<Integer, ExecutorService> executors = data.get(CommonConstants.EXECUTOR_SERVICE_COMPONENT_KEY);
        if (executors == null || executors.size() == 0) {
            return Collections.emptyMap();
        }

        StringBuilder msg = new StringBuilder();
        Status.Level level = Status.Level.OK;
        for (Map.Entry<Integer, ExecutorService> entry : executors.entrySet()) {
            Integer port = entry.getKey();
            ExecutorService executor = (ExecutorService) entry.getValue();

            if (executor instanceof ThreadPoolExecutor) {
                ThreadPoolExecutor tp = (ThreadPoolExecutor) executor;
                boolean ok = tp.getActiveCount() < tp.getMaximumPoolSize() - 1;
                Status.Level lvl = Status.Level.OK;
                if (!ok) {
                    level = Status.Level.WARN;
                    lvl = Status.Level.WARN;
                }

                map.put("status", lvl);
                map.put("max", tp.getMaximumPoolSize());
                map.put("core", tp.getCorePoolSize());
                map.put("largest", tp.getLargestPoolSize());
                map.put("active", tp.getActiveCount());
                map.put("task", tp.getTaskCount());
                map.put("completedTask", tp.getCompletedTaskCount());
                map.put("queueSize", tp.getQueue().size());
                break;
            }
        }

        return map;
    }
}
