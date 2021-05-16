package com.cdfive.sentinel.demo.promotion.service.impl;

import com.alibaba.fastjson.JSON;
import com.cdfive.sentinel.demo.base.util.CommonUtil;
import com.cdfive.sentinel.demo.promotion.service.PromotionService;
import com.cdfive.sentinel.demo.promotion.vo.UserPromotionReqVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author cdfive
 */
@Slf4j
@Service("promotionService")
public class PromotionServiceImpl implements PromotionService {

    @Override
    public void usePromotion(UserPromotionReqVo reqVo) {
        if (log.isInfoEnabled()) {
            log.info("usePromotion reqVo={}", JSON.toJSONString(reqVo));
        }

        CommonUtil.sleepRandomMs(1000, 5000);
    }
}
