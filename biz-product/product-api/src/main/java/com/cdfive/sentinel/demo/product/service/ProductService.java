package com.cdfive.sentinel.demo.product.service;

import com.cdfive.sentinel.demo.product.vo.*;

import java.util.List;

/**
 * @author cdfive
 */
public interface ProductService {

    List<QueryProductListRespVo> queryProductList(QueryProductListReqVo reqVo);

    QueryProductDetailRespVo queryProductDetail(QueryProductDetailReqVo reqVo);

    List<QueryOrderProductListRespVo> queryOrderProductList(QueryOrderProductListReqVo reqVo);

    boolean isHotProduct(Long productId);
}
