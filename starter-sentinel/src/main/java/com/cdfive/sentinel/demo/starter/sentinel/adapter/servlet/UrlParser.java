package com.cdfive.sentinel.demo.starter.sentinel.adapter.servlet;

import java.util.List;

/**
 * @author cdfive
 */
public interface UrlParser {

    List<String> getUrls();

    String parseUrl(String originUrl);
}
