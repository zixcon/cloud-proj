package com.cloud.frame.demo.auth.user.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by wd on 2018/3/30.
 */
@Data
public class RcPrivilegeEntity {

    private Integer roleId;
    private String menuId;
    private Date createTime;
}
