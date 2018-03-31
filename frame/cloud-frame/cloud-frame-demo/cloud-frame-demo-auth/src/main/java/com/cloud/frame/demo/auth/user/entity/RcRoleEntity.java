package com.cloud.frame.demo.auth.user.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by wd on 2018/3/30.
 */
@Data
public class RcRoleEntity {

    private Long id;
    private String name;
    private String value;
    private String tips;
    private Integer status;
    private Date createTime;
    private Date updateTime;
}
