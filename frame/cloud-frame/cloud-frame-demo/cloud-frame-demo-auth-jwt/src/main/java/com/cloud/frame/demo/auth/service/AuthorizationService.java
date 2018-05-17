package com.cloud.frame.demo.auth.service;

import com.cloud.frame.demo.auth.config.JwtConfig;
import com.cloud.frame.demo.auth.dao.entity.AccountInfoEntity;
import com.cloud.frame.demo.auth.dto.AuthToken;
import com.cloud.frame.demo.auth.util.JwtUtils;
import com.cloud.frame.demo.base.Result;
import com.cloud.frame.demo.constant.AuthCodeConstant;
import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wd on 2018/5/16.
 */
@Service
@Slf4j
public class AuthorizationService {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private AccountInfoService accountInfoService;

    /**
     * 验证JWT的签名，返回Result对象
     *
     * @param token
     * @return
     */
    public Result<Void> authorization(String token) {
        Result<Void> result = Result.build();
        if (Strings.isNullOrEmpty(token)) {
            result.setSuccess(false);
            result.setCode(AuthCodeConstant.JWT_ERRCODE_NULL + "");
            result.setMessage("签名验证不存在");
            return result;
        }
        Result<Claims> checkResult = JwtUtils.validateJWT(token, jwtConfig.getAccessSecret());
        if (checkResult.getSuccess()) {
            return result;
        } else {
            this.handleClaims(checkResult, result);
            return result;
        }
    }

    /**
     * token刷新
     *
     * @param refreshToken
     * @return
     */
    public Result<AuthToken> refresh(String refreshToken) {
        Result<AuthToken> result = Result.build();
        Result<Claims> checkResult = JwtUtils.validateJWT(refreshToken, jwtConfig.getRefreshSecret());
        if (checkResult.getSuccess()) {
            Integer id = Integer.valueOf(checkResult.getBody().getId());
            AccountInfoEntity user = accountInfoService.getEntity(id);
            AuthToken authToken = handleAuthToken(user);
            result.setBody(authToken);
        } else {
            this.handleClaims(checkResult, result);
        }
        return result;
    }

    /**
     * 生成token
     * @param entity
     * @return
     */
    public AuthToken handleAuthToken(AccountInfoEntity entity) {
        String accessJWT = JwtUtils.createJWT(entity.getId().toString(), entity.getUserName(), jwtConfig.getAccessSecret(), Integer.valueOf(jwtConfig.getAccessExpiration()));
        String refreshJWT = JwtUtils.createJWT(entity.getId().toString(), entity.getUserName(), jwtConfig.getRefreshSecret(), Integer.valueOf(jwtConfig.getRefreshExpiration()));
        return new AuthToken(accessJWT, refreshJWT);
    }

    private void handleClaims(Result<Claims> checkResult, Result<? extends Object> result) {
        result.setSuccess(false);
        int code = Integer.valueOf(checkResult.getCode());
        switch (code) {
            // 签名验证不通过
            case AuthCodeConstant.JWT_ERRCODE_FAIL:
                result.setCode(checkResult.getCode() + "");
                result.setMessage("签名验证不通过");
                break;
            // 签名过期，返回过期提示码
            case AuthCodeConstant.JWT_ERRCODE_EXPIRE:
                result.setCode(checkResult.getCode() + "");
                result.setMessage("签名过期");
                break;
            default:
                break;
        }
    }
}
