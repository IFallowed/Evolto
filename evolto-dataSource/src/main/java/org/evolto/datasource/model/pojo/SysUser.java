package org.evolto.datasource.model.pojo;

import org.evolto.datasource.model.ISysUser;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Description：默认的登录用户实现<br>
 * Date：2021/2/10 23:56<br>
 * @author IFallowed
 * @version 1.0
 */
public class SysUser implements ISysUser, Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String avatar;

    private String description;

    private Integer sortIndex;

    private Long createUid;

    private LocalDateTime createTime;

    private Long updateUid;

    private LocalDateTime updateTime;

    private Integer delStatus;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public Long getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Long createUid) {
        this.createUid = createUid;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateUid() {
        return updateUid;
    }

    public void setUpdateUid(Long updateUid) {
        this.updateUid = updateUid;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }
}
