package org.t01.gdp.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.t01.gdp.domain.TimeAxis;

@Component
public class Timer {

    @Scheduled(cron = "${time.cron-for-time-axis}")
    public void checkTimeAxis(){
        TimeAxis.checkTimeAxis();
    }
}
