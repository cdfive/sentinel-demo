package com.cdfive.sentinel.demo.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.cdfive.sentinel.demo.base.util.CommonUtil;
import com.cdfive.sentinel.demo.user.service.UserService;
import com.cdfive.sentinel.demo.user.vo.QueryUserDetailReqVo;
import com.cdfive.sentinel.demo.user.vo.QueryUserDetailRespVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author cdfive
 */
@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

    @Override
    public QueryUserDetailRespVo queryUserDetail(QueryUserDetailReqVo reqVo) {
        if (log.isInfoEnabled()) {
            log.info("queryUserDetail reqVo={}", JSON.toJSONString(reqVo));
        }

        CommonUtil.sleepRandomMs(10, 50);

        QueryUserDetailRespVo respVo = new QueryUserDetailRespVo();
        respVo.setUserId(1111L);
        respVo.setNickname("小张");
        respVo.setLevel(1);
        respVo.setPlus(false);
        return respVo;
    }
}
