package com.cdfive.sentinel.demo.stock.service.impl;

import com.alibaba.fastjson.JSON;
import com.cdfive.sentinel.demo.base.util.CommonUtil;
import com.cdfive.sentinel.demo.stock.service.StockService;
import com.cdfive.sentinel.demo.stock.vo.LockProductStockReqVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cdfive
 */
@Slf4j
@Service("stockService")
public class StockServiceImpl implements StockService {

    @Override
    public void lockProductStock(List<LockProductStockReqVo> reqVos) {
        if (log.isInfoEnabled()) {
            log.info("lockProductStock reqVo={}", JSON.toJSONString(reqVos));
        }

        CommonUtil.sleepRandomMs(10, 50);
    }
}
