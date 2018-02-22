package com.alphaz.core.authorization.pojo.entity;

import com.alphaz.core.pojo.entity.BaseDO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.entity
 * User: C0dEr
 * Date: 2017/6/13
 * Time: 下午2:40
 * Description:This is a class of com.alphaz.core.pojo.entity
 */
@Entity
@Table(name = "alphaz_user", schema = "alphaz", catalog = "")
public class AlphazUserEntity extends BaseDO implements UserDetails {
    private Long avatar;
    private String note;
    private String password;
    private String username;
    private String name;
    private String phone;
    private String address;
    private Timestamp birthday;
    private String gender;
    private String qq;
    private String weibo;
    private String wechat;
    private String email;
    private boolean isEnabled;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private String grantType;
    private String scope;
    private String redirectUri;


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
    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
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
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Basic
    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    @Basic
    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
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

    @Override
    @javax.persistence.Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Basic
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Basic
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    @Basic
    @Column(columnDefinition = "tinyint default 1",length = 1)
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    @Basic
    @Column(columnDefinition = "tinyint default 1",length = 1)
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    @Basic
    @Column(columnDefinition = "tinyint default 1",length = 1)
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Basic
    @Override
    @Column(columnDefinition = "tinyint default 1",length = 1)
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

    @Basic
    @Column(name = "grant_type", columnDefinition = "varchar(70) default 'client_credentials,password,authorization_code,refresh_token'")
    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Basic
    @Column(name = "redirect_uri", columnDefinition = "varchar(300) default 'http://localhost'")
    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
}
