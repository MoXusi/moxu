package com.awx.moxu.utils.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Timer;
import java.util.UUID;


/**
 * @author 沫须水
 */
public class DownThreadClient {

    //默认启动4个线程
    private int threadAccount = 4;
    private String fileSavePath = "C:/Users/Administrator/Desktop/";
    private InputStream[] inputStreams;//输入流
    private RandomAccessFile[] rdfs;//输出流
    private File file;//待下载文件，此处是下载本机的文件到本机，后面也可以 扩展为从服务器上下载
    private String bakFilePath;//临时的备份文件地址

    //为扩展续传功能，添加下载起始点的数组
    private ArrayList<Long> starts = new ArrayList<Long>();//下载开始点数组
    private ArrayList<Long> ends = new ArrayList<Long>();//下载结束点数组
    private String newFileName;//下载时生成新的文件名
    private ArrayList<DownThread> downThreads = new ArrayList<DownThread>();//线程数组
    private Timer timer;//定时器，用于进度显示

    public Timer getTimer() {
        return timer;
    }

    public ArrayList<Long> getStarts() {
        return starts;
    }

    public void setStarts(ArrayList<Long> starts) {
        this.starts = starts;
    }

    public ArrayList<Long> getEnds() {
        return ends;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public String getFileSavePath() {
        return fileSavePath;
    }

    public String getBakFilePath() {
        return bakFilePath;
    }

    public int getThreadAccount() {
        return threadAccount;
    }

    public InputStream[] getInputStreams() {
        return inputStreams;
    }

    public File getFile() {
        return file;
    }

    public DownThreadClient() {
        super();
    }

    /**
     * @param threadAccount 线程数
     * @param fileSavePath 存储文件目录
     * @param file 要下载的文件
     * @param bakFilePath 备份文件
     * @param starts 文件下载起点数组
     * @param ends 问价下载结束点数组
     * @param newFileName 新文件名
     */
    public DownThreadClient(int threadAccount, String fileSavePath, File file,
                            String bakFilePath, ArrayList<Long> starts,
                            ArrayList<Long> ends, String newFileName) {
        super();
        this.threadAccount = threadAccount;
        this.fileSavePath = fileSavePath;
        this.file = file;
        this.bakFilePath = bakFilePath;
        this.starts = starts;
        this.ends = ends;
        this.newFileName = newFileName;
        inputStreams = new InputStream[threadAccount];
        rdfs = new RandomAccessFile[threadAccount];
    }

    /**
     * @param threadAccount 线程数
     * @param fileSavePath 新文件存储目录
     * @param file 要下载的文件
     */
    public DownThreadClient(int threadAccount, String fileSavePath, File file) {
        this.threadAccount = threadAccount;
        this.fileSavePath = fileSavePath;
        this.file = file;
        inputStreams = new InputStream[threadAccount];
        rdfs = new RandomAccessFile[threadAccount];
    }

    /**
     * @param fileSavePath 新文件存储目录
     * @param file 要下载的文件
     */
    public DownThreadClient(String fileSavePath, File file) {
        this.fileSavePath = fileSavePath;
        this.file = file;
        inputStreams = new InputStream[threadAccount];
        rdfs = new RandomAccessFile[threadAccount];
    }

    /**
     * 拼接出存储文件的绝对路径，文件名随机生成
     * @param oldFileName 原始文件名
     * @return
     */
    public String getFilePath(String oldFileName){
        //获取原始文件名的后缀
        String suffix = oldFileName.substring(oldFileName.lastIndexOf("."));
        UUID uuid = UUID.randomUUID();
        String fileName = uuid.toString()+suffix;
        return fileSavePath+fileName;
    }

    //设置一个相同大小的空备份文件，避免磁盘空间不足
    public void creatBlankFile() throws Exception{
        String filePath = getFilePath("xx.bak");
        RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
        raf.setLength(file.length());
        raf.close();
        bakFilePath = filePath;
    }

    //删除备份文件
    public void deleteBlankFile(String filePath) throws Exception{
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
        }
    }

    //开始下载任务
    public void downLoad() throws Exception{
        //start无值则表示不是断点续传，则不生成备份文件
        if(starts.size()==0){
            creatBlankFile();
        }
        long fileLen = file.length();//文件总长度
        long partLen = fileLen/threadAccount;//分区长度
        String newFilePath = newFileName==null ?
                getFilePath(file.getName()):(fileSavePath+newFileName);//文件存储新路径
        newFileName = newFilePath.substring(newFilePath.lastIndexOf("/")+1);
        for(int i=0;i<threadAccount;i++){
            long start = 0;
            long end = 0;
            //b不是断点续传，按常规设置起始点、结束点
            if(starts.size()==0){
                start = i* partLen;
                end = (i+1)*partLen;
                ends.add(end);
            }else {//是断点续传，读取传过来的起始点、结束点
                start = starts.get(i);
                end = ends.get(i);
            }
            //初始化输入输出流
            inputStreams[i] = new FileInputStream(file);
            rdfs[i] = new RandomAccessFile(newFilePath, "rw");
            //如果是最后一段,并且不是续传,则设置下载结束位置为文件最末尾
            if(i==threadAccount-1 && starts.size()==0){
                end = file.length();
            }
            //初始化并开启下载线程
            DownThread downThread = new DownThread(start, end, inputStreams[i], rdfs[i], i);
            downThreads.add(downThread);
            downThread.start();
        }
    }

    /**
     * 获取下载进度
     * @param dtc DownThreadClient对象
     */
    public void getDownLoadPercent(DownThreadClient dtc){
        Timer timer = new Timer();
        ShowDownLoadPercentTask sdlp = new ShowDownLoadPercentTask(dtc, timer);
        //延迟1秒开启任务，每秒钟执行一次
        timer.schedule(sdlp, 1000, 1000);
        this.timer = timer;
    }

    //关闭输入输出流
    public void closeIs(){
        try {
            for(int i=0; i<threadAccount; i++){
                inputStreams[i].close();
                rdfs[i].close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //结束线程
    public void stopThread(){
        for(DownThread downThread:downThreads){
            downThread.stop();
        }
    }

}