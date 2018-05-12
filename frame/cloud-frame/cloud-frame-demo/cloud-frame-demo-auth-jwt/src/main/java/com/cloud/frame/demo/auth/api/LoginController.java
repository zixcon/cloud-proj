package com.cloud.frame.demo.auth.api;

import com.cloud.frame.demo.auth.config.JwtConfig;
import com.cloud.frame.demo.auth.dao.entity.AccountInfoEntity;
import com.cloud.frame.demo.auth.service.AccountInfoService;
import com.cloud.frame.demo.auth.util.JwtUtils;
import com.cloud.frame.demo.base.Result;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @ApiOperation("用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true)
    })
    @ResponseBody
    @GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<String> login(String username, String password) {
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

    @ApiOperation("token验证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @ResponseBody
    @GetMapping(value = "/verify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<Void> verify(String token) {
        Result<Claims> claimsResult = JwtUtils.validateJWT(token, jwtConfig.getSecret());
        Result<Void> result = Result.build();
        result.setSuccess(claimsResult.getSuccess());
        result.setCode(claimsResult.getCode());
        result.setMessage(claimsResult.getMessage());
        return result;
    }

}
