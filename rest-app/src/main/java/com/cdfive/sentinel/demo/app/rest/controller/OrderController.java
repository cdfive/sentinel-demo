package com.cdfive.sentinel.demo.app.rest.controller;

import com.cdfive.sentinel.demo.order.service.OrderService;
import com.cdfive.sentinel.demo.order.vo.SubmitOrderReqVo;
import com.cdfive.sentinel.demo.order.vo.SubmitOrderRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cdfive
 */
@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/submit")
    public Boolean submit(@RequestBody SubmitOrderReqVo reqVo) throws Exception {
        SubmitOrderRespVo submitOrderRespVo = orderService.submitOrder(reqVo);
        return submitOrderRespVo.getSuccess();
    }
}
