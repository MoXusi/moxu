package com.awx.moxu.utils;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.awx.moxu.utils.blade.NumberUtil;
import org.springframework.lang.Nullable;

import java.util.*;

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
    public static boolean isEmpty(@Nullable Object obj) {
        return ObjectUtil.isEmpty(obj);
    }
    public static boolean isNotEmpty(@Nullable Object obj) {
        return !ObjectUtil.isEmpty(obj);
    }


    public static boolean hasEmpty(Object... os) {
        Object[] var1 = os;
        int var2 = os.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Object o = var1[var3];
            if (isEmpty(o)) {
                return true;
            }
        }

        return false;
    }
    public static boolean equals(Object obj1, Object obj2) {
        return Objects.equals(obj1, obj2);
    }

    public static String toStr(Object str) {
        return toStr(str, "");
    }

    public static String toStr(Object str, String defaultValue) {
        return null == str ? defaultValue : String.valueOf(str);
    }
    public static int toInt(final Object value) {
        return NumberUtil.toInt(String.valueOf(value));
    }
    public static String join(List list){
        return ArrayUtil.join(list.toArray(),",");
    }
}
