package com.cdfive.sentinel.demo.product.service.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author cdfive
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductVo implements Serializable {

    private Long productId;

    private String name;

    private String logo;

    private String mainImage;

    private BigDecimal price;

    private Date createTime;
}
