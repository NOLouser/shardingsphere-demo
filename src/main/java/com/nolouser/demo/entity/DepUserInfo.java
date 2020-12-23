package com.nolouser.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 用户基本信息表
 * </p>
 *
 * @author nolouser
 * @since 2020-12-16
 */
@TableName("dep_user_info")
public class DepUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 无业务意义主键
     */
    @TableId("id_")
    private String id;

    /**
     * 登陆帐号
     */
    @TableField("account_")
    private String account;

    /**
     * 姓名
     */
    @TableField("name_")
    private String name;

    /**
     * 状态:0=启用，1=禁用
     */
    @TableField("status_")
    private Boolean status;

    /**
     * 角色ID
     */
    @TableField("role_id_")
    private String roleId;

    /**
     * 创建日期
     */
    @TableField("create_date_")
    private LocalDateTime createDate;

    /**
     * 密码
     */
    @TableField("password_")
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DepUserInfo{" +
            "id=" + id +
            ", account=" + account +
            ", name=" + name +
            ", status=" + status +
            ", roleId=" + roleId +
            ", createDate=" + createDate +
            ", password=" + password +
        "}";
    }
}
