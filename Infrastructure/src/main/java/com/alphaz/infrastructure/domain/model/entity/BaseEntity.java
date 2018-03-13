package com.alphaz.infrastructure.domain.model.entity;

import com.alphaz.infrastructure.domain.constant.common.State;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 持久化对象的基类,所有要持久化到数据库的对象继承该类或其子类
 *
 * @author HuangJian
 * @since May 19, 2010
 */
@MappedSuperclass
@FilterDef(name = "dataStateCondition", parameters = {@ParamDef(name = "state", type = "int")})
@Filter(name = "dataStateCondition", condition = "state = :state")
public abstract class BaseEntity {

    /**
     * 主键，UUID
     */
    private Long id;

    /**
     * 逻辑删除
     */
    private State state;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    private Long createBy;
    /**
     * 更新人
     */
    private Long updateBy;

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "state", nullable = true, columnDefinition = "tinyint default 1", length = 1)
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Basic
    @Column(name = "create_time", columnDefinition = "datetime default CURRENT_TIMESTAMP")
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time", columnDefinition = "datetime on update CURRENT_TIMESTAMP")
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
    @Basic
    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }
    @Basic
    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    @Version
    protected Integer version;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Basic
    @Column(name = "version", nullable = false, columnDefinition = "int default 1", length = 1)
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}