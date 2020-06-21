package org.t01.gdp.service;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.t01.gdp.domain.TimePoint;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * 时间轴对象，记录了时间安排，并标记了系统当前处于时间轴的哪个位置
 * 大部分情况只需要调用静态函数getTimeAxisState
 */

public class TimeAxisService {
    private static final Logger LOG = LoggerFactory.getLogger(TimeAxisService.class);

    private static ArrayList<TimePoint> timePoints = new ArrayList<>();
    private static int timeAxisState = -1;
    private static final String[] name = new String[]{"出题","选题","开题答辩","项目中前期","项目中期","项目中后期","论文提交","结题答辩","结束"};
    private static final int NUMOFTIMEPOINTS = 9;
    private static final String DATE_FORMAT_YMDHMS_WITH_T="yyyy-MM-dd'T'HH:mm";
    private static final String savePath = System.getProperty("user.dir") + "\\src\\main\\resources\\timeAxis.save";

    private static Map<String,List<Integer>> pageConfig = new HashMap<>();

    private static void initPageConfig(){
        pageConfig.put("/teacher/subjectManagement/addSubjectFragment",Arrays.asList(new Integer[]{0}));
        pageConfig.put("/teacher/review/openReviewFragment",Arrays.asList(new Integer[]{2}));
        pageConfig.put("/teacher/review/middleReviewFragment",Arrays.asList(new Integer[]{4}));
        pageConfig.put("/teacher/review/paperReviewFragment",Arrays.asList(new Integer[]{6}));
        pageConfig.put("/teacher/review/crossReviewFragment",Arrays.asList(new Integer[]{6}));
        pageConfig.put("/teacher/review/conclusionReviewFragment",Arrays.asList(new Integer[]{7}));
        pageConfig.put("/student/selectSubjectFragment",Arrays.asList(new Integer[]{1}));
    }

    public static boolean isAccessible(String uri){
        if(uri.contains("/administrator/") || uri.contains("/homeFragment") || uri.contains("/accountInfoFragment") || uri.equals("/teacher/subjectManagement/subjectFragment") || uri.equals("/student/subjectProcessFragment")){
            return true;
        }

        List<Integer> accessibleList = pageConfig.get(uri);

        if(accessibleList == null){
            return false;
        }

        return accessibleList.contains(timeAxisState);
    }

    public static TimePoint getNowTimePoint(){
        if(timeAxisState>=0 && timeAxisState < NUMOFTIMEPOINTS){
            return timePoints.get(timeAxisState);
        }
        return null;
    }

    //获取系统时间轴状态的函数，返回值为-1~8，分别依次表示毕业设计活动开始前，以及name数组中列出的状态
    public static int getTimeAxisState() {
        return timeAxisState;
    }

    public static boolean checkTimeAxis(){
        int oldState = timeAxisState;

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

        if(timeAxisState == oldState + 1){
            return true;
        }
        return false;
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
                if(!file.createNewFile()){
                    LOG.warn("创建时间轴配置保存文件TimeAxis.save失败");
                }
            } catch (IOException e) {
                LOG.error(e.getMessage());
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
            LOG.error(e.getMessage());
            return false;
        } catch (IOException e) {
            LOG.error(e.getMessage());
            return false;
        }finally {
            if(bos!=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    LOG.error(e.getMessage());
                }
            }
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    LOG.error(e.getMessage());
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
                if(!file.createNewFile()){
                    LOG.warn("创建时间轴配置保存文件TimeAxis.save失败");
                }
            } catch (IOException e) {
                LOG.error(e.getMessage());
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
                LOG.error(e.getMessage());
                return false;
            }
        } catch (FileNotFoundException e) {
            LOG.error(e.getMessage());
            return false;
        } catch (IOException e) {
            LOG.error(e.getMessage());
            return false;
        }finally {
            if(bis!=null){
                try {
                    bis.close();
                } catch (IOException e) {
                    LOG.error(e.getMessage());
                }
            }
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    LOG.error(e.getMessage());
                }
            }
        }
    }

    public static void initialTime(){
        initPageConfig();

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
