package com.cloud.frame.demo.auth.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;

/**
 * Created by wd on 2018/5/17.
 */
public class ShiroUtils {

    private static SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();

    private ShiroUtils() {
    }

    /**
     * 盐
     *
     * @return
     */
    public static String salt() {
        //一个Byte占两个字节，此处生成的3字节，字符串长度为6
        return secureRandomNumberGenerator.nextBytes(3).toHex();
    }

    public static String md5Hash(String password, String salt) {
        return new Md5Hash(password, ByteSource.Util.bytes(salt), 3).toString();
    }
}
