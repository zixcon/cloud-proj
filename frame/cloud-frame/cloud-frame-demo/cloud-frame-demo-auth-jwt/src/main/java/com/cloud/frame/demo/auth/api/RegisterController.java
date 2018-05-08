package com.cloud.frame.demo.auth.api;

import com.cloud.frame.demo.auth.service.AccountInfoService;
import com.cloud.frame.demo.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wd on 2018/5/8.
 */
@Api("用户注册")
@RestController
@RequestMapping("/auth/register")
public class RegisterController {

    @Autowired
    private AccountInfoService accountInfoService;

    @ApiOperation("手机验证")
    @ResponseBody
    @RequestMapping(value = "/valid/telphone", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<String> validTelphone(String telphone, String code) {
        Result<String> result = Result.build();
        String realCode = "123456";
        if (realCode.equalsIgnoreCase(code)) {
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
        }
        return result;
    }


    @ApiOperation("用户注册")
    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<Void> userRegister(String username, String password) {
        Result<Void> result = Result.build();
        accountInfoService.register(username, password);
        return result;
    }

}
