package com.awx.moxu.constant;

public interface BladeConstant {
    java.lang.String UTF_8 = "UTF-8";
    java.lang.String CONTENT_TYPE_NAME = "Content-type";
    java.lang.String CONTENT_TYPE = "application/json;charset=utf-8";
    java.lang.String SECURITY_ROLE_PREFIX = "ROLE_";
    java.lang.String DB_PRIMARY_KEY = "id";
    int DB_STATUS_NORMAL = 1;
    int DB_NOT_DELETED = 0;
    int DB_IS_DELETED = 1;
    int DB_ADMIN_NON_LOCKED = 0;
    int DB_ADMIN_LOCKED = 1;
    java.lang.String ADMIN_TENANT_ID = "000000";
    java.lang.String LOG_NORMAL_TYPE = "1";
    java.lang.String DEFAULT_NULL_MESSAGE = "暂无承载数据";
    java.lang.String DEFAULT_SUCCESS_MESSAGE = "操作成功";
    java.lang.String DEFAULT_FAILURE_MESSAGE = "操作失败";
    java.lang.String DEFAULT_UNAUTHORIZED_MESSAGE = "签名认证失败";
}