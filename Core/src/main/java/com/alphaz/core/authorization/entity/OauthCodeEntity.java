package com.alphaz.core.authorization.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

/**
 * @Author: c0der
 * @Date: 下午1:26 2018/2/22
 * @Description:
 */
@Entity
@Table(name = "oauth_code", catalog = "")
public class OauthCodeEntity {
    private String code;
    private byte[] authentication;
    private Long id;

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "authentication")
    public byte[] getAuthentication() {
        return authentication;
    }

    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OauthCodeEntity that = (OauthCodeEntity) o;
        return Objects.equals(code, that.code) &&
                Arrays.equals(authentication, that.authentication);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(code);
        result = 31 * result + Arrays.hashCode(authentication);
        return result;
    }
}
