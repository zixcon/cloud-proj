package com.cloud.frame.demo.reactive.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by wd on 2018/2/2.
 */
@Data
public class WxAppInfo {

    private Long id;
    private String appid;
    private String secret;
    private Boolean enable;
    private Date createTime;
    private Date updateTime;
}
