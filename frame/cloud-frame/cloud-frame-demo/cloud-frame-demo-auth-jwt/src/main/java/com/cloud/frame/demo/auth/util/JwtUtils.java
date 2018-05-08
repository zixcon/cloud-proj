package com.cloud.frame.demo.auth.util;


import com.cloud.frame.demo.base.Result;
import com.cloud.frame.demo.constant.AuthCodeConstant;
import io.jsonwebtoken.*;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * Created by wd on 2018/5/3.
 */
public class JwtUtils {

    /**
     * 签发JWT
     *
     * @param id
     * @param subject   可以是JSON数据 尽可能少
     * @param secert
     * @param ttlMillis
     * @return String
     */
    public static String createJWT(String id, String subject, String secert, long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey secretKey = generalKey(secert);
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(subject)   // 主题
                .setIssuer("cloud-proj-demo")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey); // 签名算法以及密匙
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate); // 过期时间
        }
        return builder.compact();
    }

    /**
     * 验证JWT
     *
     * @param jwtStr
     * @return
     */
    public static Result<Claims> validateJWT(String jwtStr, String secert) {
        Result<Claims> checkResult = Result.build();
        Claims claims ;
        try {
            claims = parseJWT(jwtStr, secert);
            checkResult.setBody(claims);
        } catch (ExpiredJwtException e) {
            checkResult.setSuccess(false);
            checkResult.setCode(AuthCodeConstant.JWT_ERRCODE_EXPIRE + "");
        } catch (SignatureException e) {
            checkResult.setSuccess(false);
            checkResult.setCode(AuthCodeConstant.JWT_ERRCODE_FAIL + "");
        } catch (Exception e) {
            checkResult.setSuccess(false);
            checkResult.setCode(AuthCodeConstant.JWT_ERRCODE_FAIL + "");
        }
        return checkResult;
    }

    public static SecretKey generalKey(String secert) {
        byte[] encodedKey = Base64.decode(secert);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 解析JWT字符串
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt, String secert) throws Exception {
        SecretKey secretKey = generalKey(secert);
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static void main(String[] args) throws InterruptedException {
        //小明失效 10s
        String secert = "123456";
        String sc = createJWT("1", "小明", secert, 3000);
        System.out.println(sc);
        System.out.println(validateJWT(sc, secert).getCode());
        System.out.println(validateJWT(sc, secert).getBody().getId());
        //Thread.sleep(3000);
        System.out.println(validateJWT(sc, secert).getBody());
    }
}
