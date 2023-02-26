package com.awx.moxu.utils.files;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class DownThread extends Thread {

    private final int BUFF_LEN = 1024;
    private InputStream inputStream;
    private RandomAccessFile raf;
    private long start;
    private long end;
    private int flag = 1;

    /**
     * @param start 下载开始位置
     * @param end 下载结束位置
     * @param inputStream 输入流
     * @param raf 输出流
     * @param flag 第n个线程
     */
    public DownThread(long start,long end,InputStream inputStream,RandomAccessFile raf,int flag){
        this.start = start;
        this.end = end;
        this.inputStream = inputStream;
        this.raf = raf;
        this.flag = flag;
    }

    public void run(){
        //System.out.println("Thread "+ flag +" start!");
        try {
            //初始化输入输出流的位置
            inputStream.skip(start);
            raf.seek(start);
            byte[] buffer = new byte[BUFF_LEN];
            long contentLen = end - start;
            //设置读取界限，避免超过线程读取的文件分区范围，区间数为times+1
            int times = (int)(contentLen/BUFF_LEN);
            int hasRead = 0;
            //根据读取界限读取文件
            for(int i=0;i<=times;i++){
                hasRead = inputStream.read(buffer);
                if(hasRead == -1) break;
                if(i==times){
                    raf.write(buffer, 0, (int)(contentLen%BUFF_LEN));
                }else {
                    raf.write(buffer, 0, BUFF_LEN);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //统一由发起输入输出流的类关闭
            //inputStream.close();
            //raf.close();
        }
    }
}