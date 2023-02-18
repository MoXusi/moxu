package com.awx.moxu.utils.exception;

import com.awx.moxu.utils.R.IResultCode;
import lombok.Data;

@Data
public class ApiCode implements IResultCode {
    private int code;
    private String msg;
    public static ApiCode AUTHENTICATION_EXPIRED = new ApiCode(401, "身份验证已过期，请重新登录！");
    public static ApiCode SUCCESS = new ApiCode(0, "请求成功");
    public static ApiCode SERVER_ERROR = new ApiCode(1000, "服务异常");
    public static ApiCode MSG_PASSWORD_ERROR = new ApiCode(1001, "密码错误！");
    public static ApiCode LOGIN_USER_NOT_FOUND = new ApiCode(402, "登录用户错误");
    public static ApiCode PRIMARY_PASSWORD_ERROR = new ApiCode(1008, "原密码错误！");
    public static ApiCode USERNAME_PASSWORD_ERROR = new ApiCode(1002, "用户名或者密码错误！");
    public static ApiCode PARAMETER_ALREADY_EXISTS = new ApiCode(1003, "请求参数已存在");
    public static ApiCode NULL_REQUEST_PARAMETER = new ApiCode(1004, "请求对象为空！");
    public static ApiCode PARAMETER_HAVE_CHILD_LEVELS = new ApiCode(1005, "请求参数有子集");
    public static ApiCode PARAMETER_CANNOT_BE_NULL = new ApiCode(1006, "传入参数不可为空");
    public static ApiCode File_Already_Exists = new ApiCode(1007, "文件已经存在！");
    public static ApiCode DEPARTMENT_NOT_FOUND = new ApiCode(2000, "部门未找到！");
    public static ApiCode ENUM_ERROR = new ApiCode(2001, "枚举获取失败！");

    private ApiCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }
    @Override
    public int getCode() {
        return code;
    }
}
