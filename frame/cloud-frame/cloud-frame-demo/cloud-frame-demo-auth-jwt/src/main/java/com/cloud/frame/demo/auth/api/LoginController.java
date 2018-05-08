package com.cloud.frame.demo.auth.api;

import com.cloud.frame.demo.auth.config.JwtConfig;
import com.cloud.frame.demo.auth.dao.entity.AccountInfoEntity;
import com.cloud.frame.demo.auth.service.AccountInfoService;
import com.cloud.frame.demo.auth.util.JwtUtils;
import com.cloud.frame.demo.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wd on 2018/5/3.
 */
@Api(value = "权限验证", tags = {"权限验证接口"})
@RestController
@RequestMapping("/auth/login")
public class LoginController {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private AccountInfoService accountInfoService;

    /**
     * 用户登陆
     */
    @ApiOperation("用户登陆")
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
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

    @ApiOperation(value = "获取用户信息", tags = {"获取用户信息"}, notes = "注意问题点")
    @RequestMapping(value = "/description", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<AccountInfoEntity> description(@ApiParam(name = "username", value = "用户", required = true) String username) {
        Result<AccountInfoEntity> result = Result.build();
        AccountInfoEntity user = accountInfoService.getEntity(username);
        result.setBody(user);
        return result;
    }

    @ApiOperation(value = "获取用户信息", tags = {"获取用户信息"}, notes = "注意问题点")
    @RequestMapping(value = "/desc", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<AccountInfoEntity> desc(@RequestBody @ApiParam(name = "用户对象", value = "传入json格式", required = true) AccountInfoEntity info) {
        Result<AccountInfoEntity> result = Result.build();
        result.setBody(info);
        return result;
    }

}
