package com.cdfive.sentinel.demo.product.service.impl;

import com.cdfive.sentinel.demo.base.util.CommonUtil;
import com.cdfive.sentinel.demo.product.service.ProductService;
import com.cdfive.sentinel.demo.product.service.vo.ProductVo;
import com.cdfive.sentinel.demo.product.vo.*;
import com.cdfive.sentinel.demo.user.service.UserService;
import com.cdfive.sentinel.demo.user.vo.QueryUserDetailReqVo;
import com.cdfive.sentinel.demo.user.vo.QueryUserDetailRespVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author cdfive
 */
@Slf4j
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private UserService userService;

    @Override
    public List<QueryProductListRespVo> queryProductList(QueryProductListReqVo reqVo) {
        CommonUtil.sleepRandomMs(10, 50);

        Long userId = reqVo.getUserId();
        if (userId != null) {
            QueryUserDetailRespVo userDetailRespVo = userService.queryUserDetail(new QueryUserDetailReqVo(reqVo.getUserId()));
        }

        List<QueryProductListRespVo> list = PRODUCT_LIST.stream()
                .map(o -> new QueryProductListRespVo(o.getProductId(), o.getName(), o.getLogo(), o.getMainImage(), o.getPrice()))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public QueryProductDetailRespVo queryProductDetail(QueryProductDetailReqVo reqVo) {
        CommonUtil.sleepRandomMs(10, 50);

        Long userId = reqVo.getUserId();
        if (userId != null) {
            QueryUserDetailRespVo userDetailRespVo = userService.queryUserDetail(new QueryUserDetailReqVo(reqVo.getUserId()));
        }

        ProductVo productVo = PRODUCT_MAP.get(reqVo.getProductId());
        QueryProductDetailRespVo respVo = new QueryProductDetailRespVo(productVo.getProductId(), productVo.getName()
                , productVo.getLogo(), productVo.getMainImage(), productVo.getPrice());
        return respVo;
    }

    @Override
    public List<QueryOrderProductListRespVo> queryOrderProductList(QueryOrderProductListReqVo reqVo) {
        if (log.isInfoEnabled()) {
            log.info("queryOrderProductList reqVo={}", reqVo);
        }

        CommonUtil.sleepRandomMs(10, 50);

        List<QueryOrderProductListRespVo> respVos = reqVo.getProductIds().stream().map(o -> {
            ProductVo productVo = PRODUCT_MAP.get(o);
            QueryOrderProductListRespVo respVo = new QueryOrderProductListRespVo();
            return respVo;
        }).collect(Collectors.toList());
        return respVos;
    }

    @Override
    public boolean isHotProduct(Long productId) {
        return P_HOT.getProductId().equals(productId);
    }

    private static final ProductVo P1 = new ProductVo(1001L, "商品1", "logo1.jpg", "主图1.jpg", new BigDecimal("11.11"), new Date());
    private static final ProductVo P2 = new ProductVo(1001L, "商品2", "logo2.jpg", "主图2.jpg", new BigDecimal("12.11"), new Date());
    private static final ProductVo P3 = new ProductVo(1001L, "商品3", "logo3.jpg", "主图3.jpg", new BigDecimal("13.11"), new Date());
    private static final ProductVo P4 = new ProductVo(1001L, "商品4", "logo4.jpg", "主图4.jpg", new BigDecimal("14.11"), new Date());
    private static final ProductVo P5 = new ProductVo(1001L, "商品5", "logo5.jpg", "主图5.jpg", new BigDecimal("15.11"), new Date());
    private static final ProductVo P_HOT = new ProductVo(2001L, "热点商品", "热点商品logo.jpg", "热点商品主图.jpg", new BigDecimal("66.66"), new Date());
    private static final ProductVo P_NORMAL = new ProductVo(2002L, "普通商品", "普通商品logo.jpg", "普通商品主图.jpg", new BigDecimal("77.77"), new Date());

    private static final List<ProductVo> PRODUCT_LIST = new ArrayList<ProductVo>(Arrays.asList(P1, P2, P3, P4, P5, P_HOT, P_NORMAL));
    private static final Map<Long, ProductVo> PRODUCT_MAP = PRODUCT_LIST.stream().collect(Collectors.toMap(ProductVo::getProductId, o -> o, (o1, o2) -> o2));
}
