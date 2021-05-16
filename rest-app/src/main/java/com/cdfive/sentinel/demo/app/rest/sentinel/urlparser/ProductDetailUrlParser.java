package com.cdfive.sentinel.demo.app.rest.sentinel.urlparser;

import com.alibaba.csp.sentinel.util.StringUtil;
import com.cdfive.sentinel.demo.product.service.ProductService;
import com.cdfive.sentinel.demo.starter.sentinel.adapter.servlet.AbstractUrlParser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom UrlParser for urls of product detail.
 *
 * @author cdfive
 */
@Component
public class ProductDetailUrlParser extends AbstractUrlParser {

    private static final String URL_PRODUCT_DETAIL = "/product/detail";
    private static final String URL_PRODUCT_DETAIL_PROMOTION = "/product/detail/promotion";
    private static final String URL_PRODUCT_DETAIL_EVALUTION = "/product/detail/evalution";

    private static final List<String> URLS = new ArrayList<String>() {
        {
            add(URL_PRODUCT_DETAIL);
            add(URL_PRODUCT_DETAIL_PROMOTION);
            add(URL_PRODUCT_DETAIL_EVALUTION);
        }
    };

    private static final String PARAM_NAME_PRODUCT_ID = "productId";
    private static final String PARAM_URL_PRODUCT_ID = "hotProductId";

    @Autowired
    private ProductService productService;

    @Override
    public List<String> getUrls() {
        return URLS;
    }

    @Override
    public String parseUrl(String originUrl) {
        HttpServletRequest request = super.getHttpServletRequest();
        if (request == null) {
            return originUrl;
        }

        String productIdStr = request.getParameter(PARAM_NAME_PRODUCT_ID);
        if (StringUtil.isBlank(productIdStr)) {
            log.error("ProductDetailUrlParser parameter productId is blank");
            return originUrl;
        }

        Long productId;
        try {
            productId = Long.parseLong(productIdStr);
        } catch (NumberFormatException e) {
            log.error("ProductDetailUrlParser parameter productId is invalid");
            return originUrl;
        }

        if (!productService.isHotProduct(productId)) {
            return originUrl;
        }

        return super.appendUrlParam(originUrl, PARAM_URL_PRODUCT_ID, String.valueOf(productId));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class IdVo implements Serializable {

        private String id;
    }
}
