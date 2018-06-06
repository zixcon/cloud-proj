package com.cloud.frame.demo.auth.dao.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 */
@ApiModel()
public class AccountInfoEntity implements Serializable {
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
     * 表名：type
     * 类型：0：最高权限，1：普通权限
     */
    @ApiModelProperty( name="type" ,required=true ,example="0" ,value="类型：0：最高权限，1：普通权限")
    private Integer type;

    /**
     * 默认值：0
     * 最大长度：3
     * 表名：status
     * 状态：0：正常，1：普通权限
     */
    @ApiModelProperty( name="status" ,required=true ,example="0" ,value="状态：0：正常，1：普通权限")
    private Integer status;

    /**
     * 默认值：0
     * 最大长度：0
     * 表名：locked
     * 锁定：0：正常，1：锁定
     */
    @ApiModelProperty( name="locked" ,required=true ,example="0" ,value="锁定：0：正常，1：锁定")
    private Boolean locked;

    /**
     * 默认值：0
     * 最大长度：0
     * 表名：deleted
     * 删除：0：正常，1：删除
     */
    @ApiModelProperty( name="deleted" ,required=true ,example="0" ,value="删除：0：正常，1：删除")
    private Boolean deleted;

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

    private static final long serialVersionUID = 1L;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AccountInfoEntity other = (AccountInfoEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getSalt() == null ? other.getSalt() == null : this.getSalt().equals(other.getSalt()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getLocked() == null ? other.getLocked() == null : this.getLocked().equals(other.getLocked()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getSalt() == null) ? 0 : getSalt().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getLocked() == null) ? 0 : getLocked().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userName=").append(userName);
        sb.append(", password=").append(password);
        sb.append(", salt=").append(salt);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", locked=").append(locked);
        sb.append(", deleted=").append(deleted);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}