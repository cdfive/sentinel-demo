package com.cdfive.sentinel.demo.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.cdfive.sentinel.demo.base.util.CommonUtil;
import com.cdfive.sentinel.demo.order.service.OrderService;
import com.cdfive.sentinel.demo.order.vo.SubmitOrderReqVo;
import com.cdfive.sentinel.demo.order.vo.SubmitOrderRespVo;
import com.cdfive.sentinel.demo.product.service.ProductService;
import com.cdfive.sentinel.demo.product.vo.QueryOrderProductListReqVo;
import com.cdfive.sentinel.demo.product.vo.QueryOrderProductListRespVo;
import com.cdfive.sentinel.demo.promotion.service.PromotionService;
import com.cdfive.sentinel.demo.promotion.vo.UserPromotionReqVo;
import com.cdfive.sentinel.demo.stock.service.StockService;
import com.cdfive.sentinel.demo.stock.vo.LockProductStockReqVo;
import com.cdfive.sentinel.demo.user.service.UserService;
import com.cdfive.sentinel.demo.user.vo.QueryUserDetailReqVo;
import com.cdfive.sentinel.demo.user.vo.QueryUserDetailRespVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cdfive
 */
@Slf4j
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserService userService;
    @Autowired
    private StockService stockService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PromotionService promotionService;

    @Override
    public SubmitOrderRespVo submitOrder(SubmitOrderReqVo reqVo) {
        if (log.isInfoEnabled()) {
            log.info("submitOrder reqVo={}", JSON.toJSONString(reqVo));
        }

        /**Query user info*/
        Long userId = reqVo.getUserId();
        QueryUserDetailRespVo userDetailVo = userService.queryUserDetail(new QueryUserDetailReqVo(userId));

        /**Query products info in the order*/
        Integer level = userDetailVo.getLevel();
        Boolean plus = userDetailVo.getPlus();
        List<Long> productIds = reqVo.getProducts().stream().map(o -> o.getProductId()).collect(Collectors.toList());
        QueryOrderProductListReqVo queryOrderProductListReqVo = new QueryOrderProductListReqVo(level, plus, productIds);
        List<QueryOrderProductListRespVo> queryOrderProductListRespVos = productService.queryOrderProductList(queryOrderProductListReqVo);

        /**Lock stock of product in the order*/
        List<SubmitOrderReqVo.ProductVo> products = reqVo.getProducts();
        List<LockProductStockReqVo> lockProductStockReqVos = products.stream()
                .map(o -> new LockProductStockReqVo(o.getProductId(), o.getQuantity())).collect(Collectors.toList());
        stockService.lockProductStock(lockProductStockReqVos);

        /**Use promotions in the order*/
        List<Long> promotionIds = reqVo.getPromotionIds();
        promotionService.usePromotion(new UserPromotionReqVo(userId, promotionIds));

        CommonUtil.sleepRandomMs(10, 50);
        return new SubmitOrderRespVo(true);
    }
}
