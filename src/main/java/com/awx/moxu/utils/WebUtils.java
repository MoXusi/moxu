package com.awx.moxu.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author : Serendipity
 * @desc :  Web工具类
 * @create : 2019/12/17 10:25
 */
public class WebUtils {

    /**
     * 获取请求
     */
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        //获取一个请求
        return requestAttributes.getRequest();
    }

    /**
     * 获取会话
     */
    public static HttpSession getSession(){
        return getRequest().getSession();
    }


}

