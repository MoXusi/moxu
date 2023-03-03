package com.awx.moxu.utils.files;

import com.alibaba.excel.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Properties;


/**
 * @author 沫须水
 */
public class PauseContinueUtil {

    /**
     * 保存数据，停止下载
     * @param dtc DownThreadClient对象
     * @throws Exception
     */
    public static void stopDownLoad(DownThreadClient dtc) throws Exception{
        //停止线程、IO、定时器
        stopThreadIO(dtc);
        //组装必要数据保存到文件中：即初始化DownThreadClient需要的变量以及下载起始点和结束点
        //以键值对的方式存储下载信息，方便以后读取
        ArrayList<String> writeString = new ArrayList<String>();
        writeString.add("fileSavePath="+dtc.getFileSavePath());
        writeString.add("newFileName="+dtc.getNewFileName());
        writeString.add("oldFilePath="+dtc.getFile().getAbsolutePath().replaceAll("\\\\", "/"));
        writeString.add("bakFilePath="+dtc.getBakFilePath());
        writeString.add("threadAccount="+dtc.getThreadAccount());
        String arrayString = "positionArray=";
        for(int i=0;i<dtc.getStarts().size();i++){
            arrayString += dtc.getStarts().get(i)+","+dtc.getEnds().get(i)+";";
        }
        writeString.add(arrayString);
        String tempFilePath = dtc.getFilePath("xx.properties");
        OutputStreamWriter outputStream = new FileWriter(tempFilePath);
        for(String s:writeString){
            outputStream.write(s+"\n");
        }
        outputStream.close();
    }

    /**
     * 从文件中读取数据，开始下载
     * @param tempFilePath 存放下载信息的配置文件路径
     * @return 返回DownThreadClient对象
     * @throws Exception
     */
    public static DownThreadClient startDownload(String tempFilePath) throws Exception{
        //以Properties方式读取文件
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream(tempFilePath);
        properties.load(inputStream);
        //读取文件必要信息
        String fileSavePath = properties.getProperty("fileSavePath");
        String newFileName = properties.getProperty("newFileName");
        String oldFilePath = properties.getProperty("oldFilePath");
        String bakFilePath = properties.getProperty("bakFilePath");
        int threadAccount = Integer.valueOf(properties.getProperty("threadAccount"));
        ArrayList<ArrayList<Long>> arrayLists = getPositonArray(properties.getProperty("positionArray"));
        DownThreadClient dtc = new DownThreadClient(threadAccount, fileSavePath, new File(oldFilePath),
                bakFilePath, arrayLists.get(0), arrayLists.get(1), newFileName);
        inputStream.close();//关闭输入流
        File file = new File(tempFilePath);
        file.delete();//删除配置文件
        return dtc;
    }

    /**
     * 组装下载起始点、结束点的数组
     * @param positionArray 下载起始点结束点的字符串
     * @return
     */
    public static ArrayList<ArrayList<Long>> getPositonArray(String positionArray){
        ArrayList<ArrayList<Long>> arrayList = new ArrayList<ArrayList<Long>>();
        ArrayList<Long> arrayListStarts = new ArrayList<Long>();
        ArrayList<Long> arrayListEnds = new ArrayList<Long>();
        //以分号分隔出单组起始结束点
        String[] array1 = positionArray.split(";");
        for(String element:array1){
            if(!StringUtils.isEmpty(element)){
                //以逗号分隔出每组的起始点和结束点
                String[] array2 = element.split(",");
                arrayListStarts.add(Long.valueOf(array2[0]));
                arrayListEnds.add(Long.valueOf(array2[1]));
            }
        }
        arrayList.add(arrayListStarts);
        arrayList.add(arrayListEnds);
        return arrayList;
    }

    /**
     * 停止多线程、定时器、收集下载点的位置、关闭输入输出流
     * @param dtc
     * @throws Exception
     */
    public static void stopThreadIO(DownThreadClient dtc) throws Exception{
        dtc.stopThread();
        dtc.getTimer().cancel();
        //收集下载点的位置
        ArrayList<Long> starts = new ArrayList<Long>();
        for(int i=0;i<dtc.getThreadAccount();i++){
            long startPositon = dtc.getFile().length() - dtc.getInputStreams()[i].available();
            starts.add(startPositon);
        }
        dtc.setStarts(starts);
        dtc.closeIs();
        System.out.println("下载已暂停");
    }

}