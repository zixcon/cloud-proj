package com.cloud.frame.demo.reactive.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by wd on 2018/1/25.
 */
@Data
public class AccountInfo {

    private Long id;
    private String userName;
    private String password;
    private String salt;
    private Integer type;
    private Integer status;
    private Date createTime;
    private Date updateTime;
}
