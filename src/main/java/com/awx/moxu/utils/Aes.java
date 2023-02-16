package com.awx.moxu.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Aes {

    private final static String key = "awx1030moxud8jud";
    /**
     * Aes对称加密
     * @param content
     * @return 加密后数据
     */
    public static String encryptHex(String content){
        AES aes = SecureUtil.aes(key.getBytes());
        return aes.encryptHex(content);
    }
    /**
     * Aes对称解密
     * @param content
     * @return 明文
     */
    public static String decryptStr(String content){
        AES aes = SecureUtil.aes(key.getBytes());
        return aes.decryptStr(content, CharsetUtil.CHARSET_UTF_8);
    }


    public static void main(String[] args) {
        Aes aes = new Aes();
        String aa = aes.encryptHex("admin");
        String s = aes.decryptStr(aa);
        System.out.println(aa);
        System.out.println(s);

    }
}
