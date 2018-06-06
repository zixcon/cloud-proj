package com.cloud.frame.demo.auth.api;

import com.cloud.frame.demo.auth.config.JwtConfig;
import com.cloud.frame.demo.auth.dao.entity.AccountInfoEntity;
import com.cloud.frame.demo.auth.dto.AuthToken;
import com.cloud.frame.demo.auth.service.AccountInfoService;
import com.cloud.frame.demo.auth.service.AuthorizationService;
import com.cloud.frame.demo.auth.shiro.pass.UserPassToken;
import com.cloud.frame.demo.auth.shiro.stateless.JwtStatelessToken;
import com.cloud.frame.demo.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wd on 2018/5/25.
 */
@Api(value = "权限验证", tags = {"权限验证接口"})
@RestController
@RequestMapping("/auth/shiro")
public class ShiroLoginController {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private AccountInfoService accountInfoService;

    @ApiOperation("用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true)
    })
    @ResponseBody
    @GetMapping(value = "/apply")
    public Result<AuthToken> login(String username, String password) {
        Result<AuthToken> result = Result.build();
        result.setSuccess(false);
        AccountInfoEntity user = accountInfoService.getEntity(username);
        if (user != null) {
            AuthToken authToken = authorizationService.handleAuthToken(user);
            //调用shiro判断当前用户是否是系统用户
            Subject subject = SecurityUtils.getSubject();   //得到当前用户
            //shiro是将用户录入的登录名和密码（未加密）封装到token对象中
            JwtStatelessToken token = new JwtStatelessToken(authToken.getAccessToken(), password);
//            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            subject.login(token);   //自动调用AuthRealm.doGetAuthenticationInfo

//            String userPass = ShiroUtils.md5Hash(user.getPassword(), user.getSalt());
//            if (userPass.equals(password)) {
            if (user.getPassword().equals(password)) {
                // 把token返回给客户端-->客户端保存至cookie-->客户端每次请求附带token参数
//                AuthToken authToken = authorizationService.handleAuthToken(user);
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

    @ApiOperation("用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true)
    })
    @ResponseBody
    @GetMapping(value = "/apply1")
    public Result<AuthToken> login1(String username, String password) {
        Result<AuthToken> result = Result.build();
        result.setSuccess(false);
        AccountInfoEntity user = accountInfoService.getEntity(username);
        if (user != null) {
            AuthToken authToken = authorizationService.handleAuthToken(user);
            //调用shiro判断当前用户是否是系统用户
            Subject subject = SecurityUtils.getSubject();   //得到当前用户
            //shiro是将用户录入的登录名和密码（未加密）封装到token对象中
            UserPassToken token = new UserPassToken(username, password);
//            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            subject.login(token);   //自动调用AuthRealm.doGetAuthenticationInfo

//            String userPass = ShiroUtils.md5Hash(user.getPassword(), user.getSalt());
//            if (user.getPassword().equals(password)) {
//            if (user.getPassword().equals(password)) {
                // 把token返回给客户端-->客户端保存至cookie-->客户端每次请求附带token参数
//                AuthToken authToken = authorizationService.handleAuthToken(user);
                result.setBody(authToken);
                result.setSuccess(true);
                return result;
//            } else {
//                result.setMessage("用户名或密码错误");
//                return result;
//            }
        } else {
            result.setMessage("用户名或密码错误");
            return result;
        }
    }
}
