package com.cdfive.sentinel.demo.stock.service;

import com.cdfive.sentinel.demo.stock.vo.LockProductStockReqVo;

import java.util.List;

/**
 * @author cdfive
 */
public interface StockService {

    void lockProductStock(List<LockProductStockReqVo> reqVos);
}
