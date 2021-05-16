package com.cdfive.sentinel.demo.promotion.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author cdfive
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserPromotionReqVo implements Serializable {

    private Long userId;

    private List<Long> promotionId;
}
