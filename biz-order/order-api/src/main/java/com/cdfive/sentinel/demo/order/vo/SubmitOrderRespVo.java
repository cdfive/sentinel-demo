package com.cdfive.sentinel.demo.order.vo;

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
public class SubmitOrderRespVo implements Serializable {

    private Boolean success;
}
