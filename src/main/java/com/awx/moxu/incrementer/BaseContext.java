package com.awx.moxu.incrementer;

import cn.hutool.db.Session;
import com.awx.moxu.entity.BladeUser;

/**
 * 基于ThreadLocal封装工具类，用户保存和获取当前登录用户id
 */
public class BaseContext {
    BaseContext(){}
    public static ThreadLocal<String> CONTEXT_INFO_HOLDER = new ThreadLocal<>();

    public static void setContext(String contextInfo) {
        CONTEXT_INFO_HOLDER.set(contextInfo);
    }
    public static String getContext() {
        return CONTEXT_INFO_HOLDER.get();
    }
    public static void clearContext() {
        CONTEXT_INFO_HOLDER.remove();
    }
}
