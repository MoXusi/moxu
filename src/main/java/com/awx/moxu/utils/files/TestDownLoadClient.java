package com.awx.moxu.utils.files;

import com.awx.moxu.filter.BizException;

import java.io.File;

public class TestDownLoadClient {

    public static void main(String arg[]) throws Exception{
        testStop();
//        testContinue();
    }

    //测试停止方法
    public static void testStop() throws Exception{
        String filePath = "E:/demo/old/1.mp4";
        String fileSavePath = "E:/demo/new/";
        File file = new File(filePath);
        DownThreadClient dtc = new DownThreadClient(fileSavePath, file);
        dtc.downLoad();
        //显示下载进度
        dtc.getDownLoadPercent(dtc);
        Thread.sleep(1000);
        //停止下载
        PauseContinueUtil.stopDownLoad(dtc);
    }

    //测试继续方法
    public static void testContinue() throws Exception{
        //继续下载，参数为停止下载后生成的属性文件路径
        DownThreadClient dtc = PauseContinueUtil.startDownload("E:/demo/new/5a2f8fd0-ffd0-47e8-ac0f-687974a2f442.properties");
        dtc.downLoad();
        //显示下载进度
        dtc.getDownLoadPercent(dtc);
    }
}