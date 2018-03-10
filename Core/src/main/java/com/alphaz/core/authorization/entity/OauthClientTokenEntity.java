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
@Table(name = "oauth_client_token", schema = "alphaz", catalog = "")
public class OauthClientTokenEntity {
    private String authenticationId;
    private String tokenId;
    private byte[] token;
    private String userName;
    private String clientId;

    @Id
    @Column(name = "authentication_id")
    public String getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
    }

    @Basic
    @Column(name = "token_id")
    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    @Basic
    @Column(name = "token")
    public byte[] getToken() {
        return token;
    }

    public void setToken(byte[] token) {
        this.token = token;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "client_id")
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OauthClientTokenEntity that = (OauthClientTokenEntity) o;
        return Objects.equals(authenticationId, that.authenticationId) &&
                Objects.equals(tokenId, that.tokenId) &&
                Arrays.equals(token, that.token) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(authenticationId, tokenId, userName, clientId);
        result = 31 * result + Arrays.hashCode(token);
        return result;
    }
}
