package com.cdfive.sentinel.demo.user.service;

import com.cdfive.sentinel.demo.user.vo.QueryUserDetailReqVo;
import com.cdfive.sentinel.demo.user.vo.QueryUserDetailRespVo;

/**
 * @author cdfive
 */
public interface UserService {

    QueryUserDetailRespVo queryUserDetail(QueryUserDetailReqVo reqVo);
}
