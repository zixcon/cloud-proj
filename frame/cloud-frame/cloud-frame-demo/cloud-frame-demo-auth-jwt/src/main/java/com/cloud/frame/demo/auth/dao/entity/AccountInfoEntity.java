package com.cloud.frame.demo.auth.dao.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 */
@ApiModel()
public class AccountInfoEntity {
    /**
     * 默认值：null
     * 最大长度：10
     * 表名：id
     */
    @ApiModelProperty( name="id" ,required=true ,example="null")
    private Integer id;

    /**
     * 默认值：null
     * 最大长度：50
     * 表名：user_name
     */
    @ApiModelProperty( name="userName" ,required=true ,example="null")
    private String userName;

    /**
     * 默认值：null
     * 最大长度：100
     * 表名：password
     */
    @ApiModelProperty( name="password" ,required=true ,example="null")
    private String password;

    /**
     * 默认值：null
     * 最大长度：50
     * 表名：salt
     */
    @ApiModelProperty( name="salt" ,required=true ,example="null")
    private String salt;

    /**
     * 默认值：0
     * 最大长度：3
     * 表名：status
     */
    @ApiModelProperty( name="status" ,required=true ,example="0")
    private Integer status;

    /**
     * 默认值：0
     * 最大长度：3
     * 表名：type
     */
    @ApiModelProperty( name="type" ,required=true ,example="0")
    private Integer type;

    /**
     * 默认值：CURRENT_TIMESTAMP
     * 最大长度：19
     * 表名：create_time
     * 注册时间
     */
    @ApiModelProperty( name="createTime" ,required=true ,example="CURRENT_TIMESTAMP" ,value="注册时间" ,hidden=true)
    private Date createTime;

    /**
     * 默认值：CURRENT_TIMESTAMP
     * 最大长度：19
     * 表名：update_time
     * 更新时间
     */
    @ApiModelProperty( name="updateTime" ,required=true ,example="CURRENT_TIMESTAMP" ,value="更新时间" ,hidden=true)
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}