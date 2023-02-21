package com.awx.moxu.utils;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.lang.Nullable;

import java.util.LinkedList;
import java.util.List;

public class Func {

    public static List<String>  toStringList(String str){
        return ListUtil.toLinkedList(str.split(","));
    }

    public static int toInt(final Object value, final int defaultValue) {
        if (value == null) {
            return defaultValue;
        } else {
            try {
                return Integer.valueOf((Integer) value);
            } catch (NumberFormatException var3) {
                return defaultValue;
            }
        }
    }
    public static String[] toStrArray(String str) {
        return toStrArray(",", str);
    }
    public static String[] toStrArray(String split, String str) {
        return StrUtil.isBlank(str) ? new String[0] : str.split(split);
    }
    public static void main(String[] args) {
        String str = "854a5s4ad5asd,aasd2as57465a41d,as5d6a4s65dasdas";
        List<String> strings = Func.toStringList(str);
        System.out.println(strings);
    }

}
