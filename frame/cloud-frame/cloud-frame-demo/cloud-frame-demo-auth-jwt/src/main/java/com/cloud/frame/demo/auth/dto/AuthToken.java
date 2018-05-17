package com.cloud.frame.demo.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by wd on 2018/5/16.
 */
@Data
@AllArgsConstructor
public class AuthToken {

    private String accessToken;

    private String refreshToken;


}
