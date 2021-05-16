package com.cdfive.sentinel.demo.app.rest.controller;

import com.cdfive.sentinel.demo.product.service.ProductService;
import com.cdfive.sentinel.demo.product.vo.QueryProductDetailReqVo;
import com.cdfive.sentinel.demo.product.vo.QueryProductDetailRespVo;
import com.cdfive.sentinel.demo.product.vo.QueryProductListReqVo;
import com.cdfive.sentinel.demo.product.vo.QueryProductListRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author cdfive
 */
@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/list")
    public List<QueryProductListRespVo> queryProductList(QueryProductListReqVo reqVo) throws Exception {
        return productService.queryProductList(reqVo);
    }

    @RequestMapping("/detail")
    public QueryProductDetailRespVo queryProductDetail(QueryProductDetailReqVo reqVo) throws Exception {
        return productService.queryProductDetail(reqVo);
    }
}
