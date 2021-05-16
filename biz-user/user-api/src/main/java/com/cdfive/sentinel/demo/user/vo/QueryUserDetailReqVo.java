package com.cdfive.sentinel.demo.user.vo;

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
public class QueryUserDetailReqVo implements Serializable {

    private Long userId;
}
