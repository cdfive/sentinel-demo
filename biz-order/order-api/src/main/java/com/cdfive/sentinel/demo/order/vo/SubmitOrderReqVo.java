package com.cdfive.sentinel.demo.order.vo;

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
public class SubmitOrderReqVo implements Serializable {

    private Long userId;

    private List<Long> promotionIds;

    private List<ProductVo> products;

    @Data
    public static class ProductVo implements Serializable{

        private Long productId;

        private Integer quantity;
    }
}
