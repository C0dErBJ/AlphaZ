package com.alphaz.core.authorization.entity;

import com.alphaz.infrastructure.domain.model.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.entity
 * User: C0dEr
 * Date: 2017/6/13
 * Time: 下午2:40
 * Description:This is a class of com.alphaz.core.pojo.entity
 */
@Entity
@Table(name = "alphaz_user", catalog = "")
public class AlphazUserEntity extends BaseEntity {
    private Long avatar;
    private String note;
    private String password;
    private String username;
    private String name;
    private String phone;
    private String address;
    private LocalDateTime birthday;
    private String gender;
    private String email;
    private boolean isEnabled;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private String resetCode;
    private boolean isTwoFactorEnabled;

    @Basic
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    @Basic
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    public Long getAvatar() {
        return avatar;
    }

    public void setAvatar(Long avatar) {
        this.avatar = avatar;
    }


    @Basic
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Basic
    public String getUsername() {
        return username;
    }

    @Basic
    @Column(columnDefinition = "tinyint default 1", length = 1)
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Basic
    @Column(columnDefinition = "tinyint default 1", length = 1)
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Basic
    @Column(columnDefinition = "tinyint default 1", length = 1)
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Basic
    @Column(columnDefinition = "tinyint default 1", length = 1)
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }
}
