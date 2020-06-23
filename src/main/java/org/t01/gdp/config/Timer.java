package org.t01.gdp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.t01.gdp.service.*;

@Component
public class Timer {
    @Autowired
    NoticeService noticeService;
    @Autowired
    MonitorService monitorService;
    @Autowired
    AdministratorService administratorService;

    @Scheduled(cron = "${time.cron-for-time-axis}")
    public void checkTimeAxis(){
        if(TimeAxisService.checkTimeAxis()){
            noticeService.sendMessageNotice();
        }
        if(TimeAxisService.getTimeAxisState() == 1){
//            administratorService.createReview();
//            administratorService.crossReviewCreate();
        }
    }

    @Scheduled(cron = "${time.cron-for-clear-verifivation-code}")
    public void clearVerificationCode(){
        VerificationService.clearOverdueCode();
    }

    @Scheduled(cron = "${time.cron-for-monitor}")
    public void takeOnceMonitor(){
        monitorService.onceMonitor();
    }
}
