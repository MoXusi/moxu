package com.awx.moxu.filter;

import com.awx.moxu.utils.exception.ApiCode;

/**
 * @description: 自定义异常类
 * @author: DT
 * @date: 2021/4/19 21:44
 * @version: v1.0
 */
public class BizException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected int code;
    /**
     * 错误信息
     */
    protected String msg;

    public BizException() {
        super();
    }

    public BizException(ApiCode apiCode) {
        super(String.valueOf(apiCode.getCode()));
        this.code = apiCode.getCode();
        this.msg = apiCode.getMsg();
    }

    public BizException(ApiCode apiCode, Throwable cause) {
        super(String.valueOf(apiCode.getCode()), cause);
        this.code = apiCode.getCode();
        this.msg = apiCode.getMsg();
    }

    public BizException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BizException(int code, String msg) {
        super(String.valueOf(code));
        this.code = code;
        this.msg = msg;
    }

    public BizException(int code, String msg, Throwable cause) {
        super(String.valueOf(code), cause);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
