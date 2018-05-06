package com.cloud.frame.demo.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by wd on 2018/5/2.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO implements Serializable {
    /**
     * token
     */
    private String token;
    /**
     * 有效时间：单位：秒
     */
    private Integer expire;
}
