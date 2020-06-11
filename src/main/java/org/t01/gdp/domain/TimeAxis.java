package org.t01.gdp.domain;

import java.util.*;

public class TimeAxis {
    private static ArrayList<TimePoint> timePoints = new ArrayList<>();

    public static ArrayList<TimePoint> getTimePoints() {
        return timePoints;
    }

    public static void initialTime(){
        Date now = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);

        for(int i = 0; i < 6; ++i){
            calendar.add(Calendar.DAY_OF_MONTH, 5);
            TimePoint timePoint = new TimePoint();

            timePoint.setDateTime(calendar.getTime());
            timePoint.setIndex(i);

            timePoints.add(timePoint);
        }

        System.out.println(timePoints);
    }

    public static boolean setTimePoint(int index,TimePoint timePoint){
        timePoints.set(index,timePoint);
        return true;
    }

    public static boolean setDate(int index,Date date){
        return true;
    }
}
