package com.cdfive.sentinel.demo.stock.vo;

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
public class LockProductStockReqVo implements Serializable {

    private Long productId;

    private Integer quantity;
}
