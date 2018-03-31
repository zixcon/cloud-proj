package com.cloud.frame.demo.auth.user.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by wd on 2018/3/30.
 */
@Data
public class RcUserEntity {

    private Long id;
    private String avatar;
    private String username;
    private String password;
    private String salt;
    private String name;
    private Date birthday;
    private Integer sex;
    private String email;
    private String phone;
    private Integer status;
    private Date createTime;
    private Date updateTime;
}
