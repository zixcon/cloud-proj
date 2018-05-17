package com.cloud.frame.demo.auth.api;

import com.cloud.frame.demo.auth.config.JwtConfig;
import com.cloud.frame.demo.auth.dao.entity.AccountInfoEntity;
import com.cloud.frame.demo.auth.dto.AuthToken;
import com.cloud.frame.demo.auth.service.AccountInfoService;
import com.cloud.frame.demo.auth.service.AuthorizationService;
import com.cloud.frame.demo.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wd on 2018/5/3.
 */
@Api(value = "权限验证", tags = {"权限验证接口"})
@RestController
@RequestMapping("/auth/token")
public class LoginController {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private AccountInfoService accountInfoService;

    @Autowired
    private AuthorizationService authorizationService;

    @ApiOperation("用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true)
    })
    @ResponseBody
    @GetMapping(value = "/login")
    public Result<AuthToken> login(String username, String password) {
        Result<AuthToken> result = Result.build();
        result.setSuccess(false);
        AccountInfoEntity user = accountInfoService.getEntity(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                // 把token返回给客户端-->客户端保存至cookie-->客户端每次请求附带token参数
                AuthToken authToken = authorizationService.handleAuthToken(user);
                result.setBody(authToken);
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

    @ApiOperation("token验证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @ResponseBody
    @GetMapping(value = "/verify")
    public Result<Void> verify(String token) {
        return authorizationService.authorization(token);
    }

    @ApiOperation("token刷新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "refreshToken", value = "refreshToken", required = true)
    })
    @ResponseBody
    @GetMapping(value = "/refresh")
    public Result<AuthToken> refresh(String refreshToken) {
        return authorizationService.refresh(refreshToken);
    }

}
