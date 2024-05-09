package com.template.server.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class SystemUserInfo implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 账号
     */
    private String userCode;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别(1：男 2：女 0：未知)
     */
    private Integer sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 账号状态（1：启用 2：禁用）
     */
    private Byte userStatus;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 部门编码
     */
    private String deptCode;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 职位
     */
    private String userPosition;

    /**
     * 是否删除 0未删除 1已删除
     */
    private Boolean delFlag;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建人名字
     */
    private String creator;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新人名字
     */
    private String updater;

    /**
     * 主键
     * @return id 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 账号
     * @return user_code 账号
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 账号
     * @param userCode 账号
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * 密码
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 盐值
     * @return salt 盐值
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 盐值
     * @param salt 盐值
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * 昵称
     * @return nickname 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 昵称
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 性别(1：男 2：女 0：未知)
     * @return sex 性别(1：男 2：女 0：未知)
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 性别(1：男 2：女 0：未知)
     * @param sex 性别(1：男 2：女 0：未知)
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 邮箱
     * @return email 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 手机号
     * @return mobile 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 手机号
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 账号状态（1：启用 2：禁用）
     * @return user_status 账号状态（1：启用 2：禁用）
     */
    public Byte getUserStatus() {
        return userStatus;
    }

    /**
     * 账号状态（1：启用 2：禁用）
     * @param userStatus 账号状态（1：启用 2：禁用）
     */
    public void setUserStatus(Byte userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * 发布时间
     * @return publish_time 发布时间
     */
    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    /**
     * 发布时间
     * @param publishTime 发布时间
     */
    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * 部门编码
     * @return dept_code 部门编码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 部门编码
     * @param deptCode 部门编码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 部门名称
     * @return dept_name 部门名称
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * 部门名称
     * @param deptName 部门名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    /**
     * 职位
     * @return user_position 职位
     */
    public String getUserPosition() {
        return userPosition;
    }

    /**
     * 职位
     * @param userPosition 职位
     */
    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition == null ? null : userPosition.trim();
    }

    /**
     * 是否删除 0未删除 1已删除
     * @return del_flag 是否删除 0未删除 1已删除
     */
    public Boolean getDelFlag() {
        return delFlag;
    }

    /**
     * 是否删除 0未删除 1已删除
     * @param delFlag 是否删除 0未删除 1已删除
     */
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建人
     * @return create_by 创建人
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 创建人
     * @param createBy 创建人
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * 创建人名字
     * @return creator 创建人名字
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人名字
     * @param creator 创建人名字
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 更新人
     * @return update_by 更新人
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 更新人
     * @param updateBy 更新人
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * 更新人名字
     * @return updater 更新人名字
     */
    public String getUpdater() {
        return updater;
    }

    /**
     * 更新人名字
     * @param updater 更新人名字
     */
    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }
}