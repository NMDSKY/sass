package com.bage.finance.biz.domain;

import java.util.Date;

/**
 * （表：member）
 *
 * @author bage
 */
public class Member {
    /**
     * 
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 是否禁用
     */
    private Boolean disable;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 姓名
     */
    private String name;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 系统角色id，1表示管理员
     */
    private Integer sysRoleId;

    /**
     * 租户id
     */
    private Long tenantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Integer sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public void initDefault() {
        if (this.getNickName() == null) {
            this.setNickName("");
        }
        if (this.getDisable() == null) {
            this.setDisable(false);
        }
        if (this.getCreateTime() == null) {
            this.setCreateTime(new Date());
        }
        if (this.getUpdateTime() == null) {
            this.setUpdateTime(new Date());
        }
        if (this.getName() == null) {
            this.setName("");
        }
        if (this.getAvatarUrl() == null) {
            this.setAvatarUrl("");
        }
        if (this.getSysRoleId() == null) {
            this.setSysRoleId(0);
        }
        if (this.getTenantId() == null) {
            this.setTenantId(0L);
        }
    }

    public void initUpdate() {
    }
}