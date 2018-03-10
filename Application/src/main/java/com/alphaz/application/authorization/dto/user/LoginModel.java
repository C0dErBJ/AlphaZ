package com.alphaz.application.authorization.dto.user;

/**
 * @Author: c0der
 * @Date: 下午2:12 2018/1/12
 * @Description:
 */
public class LoginModel {
    private String username;
    private String password;
    private Boolean remember;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRemember() {
        return remember;
    }

    public void setRemember(Boolean remember) {
        this.remember = remember;
    }
}
