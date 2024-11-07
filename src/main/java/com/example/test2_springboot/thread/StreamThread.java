package com.example.test2_springboot.thread;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理流线程
 *
 * @author weijs
 * @version 1.0
 * @date 2018年9月28日 下午1:31:59
 * @since JDK1.8
 */
public class StreamThread extends Thread {


    // 输入流
    private InputStream inputStream;
    // 流类型
    private String streamType;
    // 是否运行完成
    private volatile boolean inFinish = false;
    // 需要的返回结果数据
    private StringBuffer resultMap;
    private Boolean isShowLog = false;

    /**
     * 构造器
     *
     * @param inputStream 输入流
     * @param streamType  流类型
     */
    public StreamThread(InputStream inputStream, String streamType) {
        this.inputStream = inputStream;
        this.streamType = streamType;
        this.inFinish = false;
        this.resultMap = new StringBuffer();
    }

    /**
     * 构造器
     *
     * @param inputStream 输入流
     * @param streamType  流类型
     * @param isShowLog   是否打印日志
     */
    public StreamThread(InputStream inputStream, String streamType, Boolean isShowLog) {
        this.inputStream = inputStream;
        this.streamType = streamType;
        this.inFinish = false;
        this.resultMap = new StringBuffer();
        this.isShowLog = isShowLog;
    }


    /**
     * 重写run()方法
     */
    public void run() {
        try {
            InputStreamReader isr = new InputStreamReader(inputStream, "GBK");
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            while (( line = bufferedReader.readLine() ) != null) {
//                if ( streamType.equals("Error") ) {
//                    System.out.println(">>>>>>Error :" + line);
//                } else {
//                    System.out.println("info:" + line);
//
//                }
                if ( isShowLog ) {
                    if ( streamType.equals("Error") ) {
                        System.err.println(line);
                    } else {
                        System.out.println(line);

                    }
                }
                resultMap.append(line + "\r\n");
            }
        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            this.inFinish = true;
            synchronized (this) {
                notify();
            }
        }
    }

    /**
     * 返回结果
     *
     * @return
     * @author weijs
     * @date 2018年9月28日 下午1:37:42
     */
    public String getContent() {
        if ( !this.inFinish ) {
            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException ignore) {
                    ignore.printStackTrace();
                }
            }
        }
//        System.out.println(resultMap);
        return this.resultMap.toString();
    }

}
