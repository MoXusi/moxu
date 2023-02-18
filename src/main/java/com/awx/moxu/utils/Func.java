package com.awx.moxu.utils;

import cn.hutool.core.collection.ListUtil;

import java.util.LinkedList;
import java.util.List;

public class Func {

    public static List<String>  toStringList(String str){
        return ListUtil.toLinkedList(str.split(","));
    }



    public static void main(String[] args) {
        String str = "854a5s4ad5asd,aasd2as57465a41d,as5d6a4s65dasdas";
        List<String> strings = Func.toStringList(str);
        System.out.println(strings);
    }

}
