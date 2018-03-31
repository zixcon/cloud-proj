package com.cloud.frame.demo.auth.user.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by wd on 2018/3/30.
 */
@Data
public class RcMenuEntity {

    private String id;
    private String code;
    private String pCode;
    private String pId;
    private String name;
    private String url;
    private Integer isMenu;
    private Integer level;
    private Integer sort;
    private Integer status;
    private String icon;
    private Date createTime;
    private Date updateTime;
}
