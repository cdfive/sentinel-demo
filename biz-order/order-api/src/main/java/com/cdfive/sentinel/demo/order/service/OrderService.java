package com.cdfive.sentinel.demo.order.service;

import com.cdfive.sentinel.demo.order.vo.SubmitOrderReqVo;
import com.cdfive.sentinel.demo.order.vo.SubmitOrderRespVo;

/**
 * @author cdfive
 */
public interface OrderService {

    SubmitOrderRespVo submitOrder(SubmitOrderReqVo reqVo);
}
