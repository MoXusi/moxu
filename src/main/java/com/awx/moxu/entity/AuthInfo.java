package com.awx.moxu.entity;

import lombok.Data;

@Data
public class AuthInfo {
    //令牌
    private String accessToken;
    //令牌类型
    private String tokenType;
    //刷新令牌
    private String refreshToken;
    //用户ID
    private String userId;
    //第三方系统ID
    private String oauthId;
    //头像
    private String avatar;
    //角色名
    private String authority;
    //用户名
    private String userName;
    //账号名
    private String account;
    //过期时间
    private long expiresIn;
    //许可证
    private String license;
}