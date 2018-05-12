package com.cloud.frame.gateway.client;

import com.cloud.frame.demo.base.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by wd on 2018/5/10.
 */
@FeignClient(value = "ucenter")
public interface AuthorizationClient {

    @GetMapping(value = "/auth/token/verify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result<Void> verify(@RequestParam(value = "token", defaultValue = "") String token);

}
