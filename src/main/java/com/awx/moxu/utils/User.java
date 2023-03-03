package com.awx.moxu.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author anLiHang
 * @date 2023/3/1
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String clientId;
    
    private String userId;
    
    private String userName;
    
    private String account;
    
    private String roleId;
    
    private String roleName;

    public String getClientId() {
        return this.clientId;
    }

    public String getUserId() {
        return this.userId;
    }


    public String getUserName() {
        return this.userName;
    }

    public String getAccount() {
        return this.account;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setClientId(final String clientId) {
        this.clientId = clientId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public void setAccount(final String account) {
        this.account = account;
    }

    public void setRoleId(final String roleId) {
        this.roleId = roleId;
    }

    public void setRoleName(final String roleName) {
        this.roleName = roleName;
    }


    @Override
    public int hashCode() {
        return Objects.hash(clientId, userId, userName, account, roleId, roleName);
    }
}
