package com.cloud.frame.demo.auth.user.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by wd on 2018/3/30.
 */
@Data
public class RcUserRoleEntity {

    private Long id;
    private Integer userId;
    private Integer roleId;
    private Date createTime;
    private String createBy;
}
