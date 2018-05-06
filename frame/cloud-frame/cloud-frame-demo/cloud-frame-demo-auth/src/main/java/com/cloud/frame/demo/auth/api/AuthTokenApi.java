package com.cloud.frame.demo.auth.api;

import com.cloud.frame.demo.auth.dto.TokenDTO;
import com.cloud.frame.demo.base.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by wd on 2018/5/2.
 */
//@FeignClient
public interface AuthTokenApi {

    /**
     * 登录获取token
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/client/login", method = RequestMethod.POST)
    Result<TokenDTO> login(@RequestParam(value = "userName") String userName);
}
