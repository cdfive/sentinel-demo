package com.cdfive.sentinel.demo.promotion.service;

import com.cdfive.sentinel.demo.promotion.vo.UserPromotionReqVo;

/**
 * @author cdfive
 */
public interface PromotionService {

    void usePromotion(UserPromotionReqVo reqVo);
}
