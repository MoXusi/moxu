//package com.awx.moxu.utils;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//
//import java.io.Serializable;
//import java.util.Date;
//
//@Data
//@Builder
//@AllArgsConstructor
//public class R<T> implements Serializable {
//    private static final long serialVersionUID = 1L;
//
//    private int code;
//    private boolean success;
//    private String message;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private Date time;
//    private T data;
//
//    public R() {
//        time = new Date();
//    }
//
//    public static <T> R<T> ok() {
//        return R.<T>builder()
//                .code(ApiCode.SUCCESS.getCode())
//                .message(ApiCode.SUCCESS.getMsg())
//                .data(null)
//                .success(true)
//                .time(new Date())
//                .build();
//    }
//
//    public static <T> R<T> ok(T data) {
//        return R.<T>builder()
//                .code(0)
//                .message(null)
//                .data(data)
//                .success(true)
//                .time(new Date())
//                .build();
//    }
//
//    public static <T> R<T> fail(String message) {
//        return R.<T>builder()
//                .code(1)
//                .message(message)
//                .data(null)
//                .success(false)
//                .time(new Date())
//                .build();
//    }
//
//    public static <T> R<T> fail(ApiCode apiCode) {
//        return R.<T>builder()
//                .code(apiCode.getCode())
//                .message(apiCode.getMsg())
//                .data(null)
//                .success(false)
//                .time(new Date())
//                .build();
//    }
//
//}
