package com.cdfive.sentinel.demo.base.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author cdfive
 */
public abstract class CommonUtil {

    public static void sleepMs(long timeoutMs) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeoutMs);
        } catch (InterruptedException e) {
            throw new RuntimeException("sleepMs error", e);
        }
    }

    public static int randomInt(int minInclusive, int maxInclusive) {
        return ThreadLocalRandom.current().nextInt(minInclusive, maxInclusive + 1);
    }

    public static void sleepRandomMs(int minInclusive, int maxInclusive) {
        sleepMs(randomInt(minInclusive, maxInclusive));
    }
}
