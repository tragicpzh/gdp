package org.t01.gdp.domain;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * 时间轴对象，记录了时间安排，并标记了系统当前处于时间轴的哪个位置
 * 大部分情况只需要调用静态函数getTimeAxisState
 */

public class TimeAxis {
    private static ArrayList<TimePoint> timePoints = new ArrayList<>();
    private static int timeAxisState = -1;
    private static final String[] name = new String[]{"出题","选题","开题答辩","项目中前期","项目中期","项目中后期","论文提交","结题答辩","结束"};
    private static final int NUMOFTIMEPOINTS = 9;
    private static final String DATE_FORMAT_YMDHMS_WITH_T="yyyy-MM-dd'T'HH:mm";
    private static final String savePath = System.getProperty("user.dir") + "\\src\\main\\resources\\timeAxis.save";

    //获取系统时间轴状态的函数，返回值为-1~8，分别依次表示毕业设计活动开始前，以及name数组中列出的状态
    public static int getTimeAxisState() {
        return timeAxisState;
    }

    public static void setTimeAxisState(int timeAxisState) {
        TimeAxis.timeAxisState = timeAxisState;
    }

    public static void checkTimeAxis(){
        Date now = new Date();
        int index = -1;
        for(int i = 0; i < NUMOFTIMEPOINTS; ++i){
            Date dateTime = timePoints.get(i).getDateTime();
            if(now.compareTo(dateTime)<0){
                break;
            }
            index = timePoints.get(i).getIndex();
        }
        timeAxisState = index;
//        System.out.println(timeAxisState);
    }

    public static SimpleDateFormat getFormat(){
        return new SimpleDateFormat(DATE_FORMAT_YMDHMS_WITH_T);
    }

    public static ArrayList<TimePoint> getTimePoints() {
        return timePoints;
    }

    public static boolean saveTimeAxis(){
        File file = new File(savePath);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            String jsonString = JSON.toJSONString(timePoints);

            bos.write(jsonString.getBytes());
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally {
            if(bos!=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean loadTimeAxis(){
        File file = new File(savePath);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;

        try{
            String jsonString = "";
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);

            int i = bis.read(buffer);
            while(i != -1){
                jsonString += new String(buffer);
                i = bis.read(buffer);
            }

            try {
                timePoints = (ArrayList<TimePoint>) JSON.parseArray(jsonString,TimePoint.class);
                if(timePoints == null){
                    timePoints = new ArrayList<>();
                    return false;
                }

                checkTimeAxis();

                return true;
            } catch (Exception e) {
                return false;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally {
            if(bis!=null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void initialTime(){
        if(loadTimeAxis()){
            return;
        }

        Date now = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);

        for(int i = 0; i < NUMOFTIMEPOINTS; ++i){
            calendar.add(Calendar.DAY_OF_MONTH, 5);
            TimePoint timePoint = new TimePoint();

            timePoint.setIndex(i);
            timePoint.setName(name[i]);
            timePoint.setDateTime(calendar.getTime());

            timePoints.add(timePoint);
        }

        saveTimeAxis();
//        System.out.println(timePoints);
    }

    public static int setTimePoint(int index,TimePoint timePoint){
        Date newDateTime = timePoint.getDateTime();
        if(index>0){
            Date dateTimeBefore = timePoints.get(index-1).getDateTime();
            if(newDateTime.compareTo(dateTimeBefore) <= 0){
                return -1;
            }
        }
        if(index<NUMOFTIMEPOINTS-1){
            Date dateTimeAfter = timePoints.get(index+1).getDateTime();
            if(newDateTime.compareTo(dateTimeAfter) >= 0){
                return 1;
            }
        }

        timePoints.set(index,timePoint);
        saveTimeAxis();
        return 0;
    }

    public static boolean setDate(int index,Date date){
        return true;
    }
}
