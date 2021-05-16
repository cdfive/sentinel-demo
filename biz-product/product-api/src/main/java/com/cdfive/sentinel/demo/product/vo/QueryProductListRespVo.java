package com.cdfive.sentinel.demo.product.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author cdfive
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QueryProductListRespVo implements Serializable {

    private Long productId;

    private String name;

    private String logo;

    private String mainImage;

    private BigDecimal price;
}
