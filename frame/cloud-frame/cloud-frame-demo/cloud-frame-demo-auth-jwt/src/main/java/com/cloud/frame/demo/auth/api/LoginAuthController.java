package com.cloud.frame.demo.auth.api;

import com.cloud.frame.demo.auth.config.JwtConfig;
import com.cloud.frame.demo.auth.dao.entity.AccountInfoEntity;
import com.cloud.frame.demo.auth.service.AccountInfoService;
import com.cloud.frame.demo.auth.util.JwtUtils;
import com.cloud.frame.demo.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by wd on 2018/5/3.
 */
@Api("权限管理")
@RestController
@RequestMapping("/api/auth")
public class LoginAuthController {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private AccountInfoService accountInfoService;

    /**
     * 用户登陆
     */
    @ApiOperation("用户登陆")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<String> login(String username, String password, HttpServletResponse response) {
        Result<String> result = Result.build();
        result.setSuccess(false);
        AccountInfoEntity user = accountInfoService.getEntity(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                // 把token返回给客户端-->客户端保存至cookie-->客户端每次请求附带token参数
                String JWT = JwtUtils.createJWT(user.getId().toString(), username, jwtConfig.getSecret(), Integer.valueOf(jwtConfig.getExpiration()));
                result.setBody(JWT);
                result.setSuccess(true);
                return result;
            } else {
                result.setMessage("用户名或密码错误");
                return result;
            }
        } else {
            result.setMessage("用户名或密码错误");
            return result;
        }
    }

    /**
     * 获取用户信息
     */
    @ApiOperation("获取用户信息")
    @RequestMapping(value = "/description", method = RequestMethod.POST)
    public Result<AccountInfoEntity> description(String username) {
        Result<AccountInfoEntity> result = Result.build();
        AccountInfoEntity user = accountInfoService.getEntity(username);
        result.setBody(user);
        return result;
    }
}
