package com.cdfive.sentinel.demo.product.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author cdfive
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QueryProductDetailReqVo implements Serializable {

    private Long userId;
    
    private Long productId;
}
