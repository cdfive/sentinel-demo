package com.cdfive.sentinel.demo.product.vo;

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
public class QueryOrderProductListReqVo implements Serializable {

    private Integer level;

    private Boolean plus;

    private List<Long> productIds;
}
