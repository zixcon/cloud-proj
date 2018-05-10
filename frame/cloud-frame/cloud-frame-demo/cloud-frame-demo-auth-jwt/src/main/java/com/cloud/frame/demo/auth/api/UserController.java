package com.cloud.frame.demo.auth.api;

import com.cloud.frame.demo.auth.dao.entity.AccountInfoEntity;
import com.cloud.frame.demo.auth.service.AccountInfoService;
import com.cloud.frame.demo.base.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wd on 2018/5/9.
 */
@Api(value = "用户信息", tags = {"用户信息接口"})
@RestController
@RequestMapping("/auth/user")
public class UserController {

    @Autowired
    private AccountInfoService accountInfoService;

    @ApiOperation("获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true)
    })
    @GetMapping(value = "/description", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<AccountInfoEntity> description(String username) {
        Result<AccountInfoEntity> result = Result.build();
        AccountInfoEntity user = accountInfoService.getEntity(username);
        result.setBody(user);
        return result;
    }

    @ApiOperation("取用户信息")
    @PostMapping(value = "/desc", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<AccountInfoEntity> desc(@RequestBody @ApiParam(name = "用户对象", value = "传入json格式", required = true) AccountInfoEntity info) {
        Result<AccountInfoEntity> result = Result.build();
        result.setBody(info);
        return result;
    }
}
