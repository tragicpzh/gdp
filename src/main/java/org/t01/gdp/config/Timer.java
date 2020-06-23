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
    @Autowired
    StudentService studentService;

    @Scheduled(cron = "${time.cron-for-time-axis}")
    public void checkTimeAxis(){
        int oldTimeAxisState = TimeAxisService.getTimeAxisState();

        if(TimeAxisService.checkTimeAxis()){
            noticeService.sendMessageNotice();
        }
        if(TimeAxisService.getTimeAxisState() == 1 && oldTimeAxisState == 0){
            String review = administratorService.createReview();
            String crossReview = administratorService.crossReviewCreate();
            if(!review.equals("")){
                noticeService.sendMessageNoticeToAdministrators(review);
            }
            if(!crossReview.equals("")){
                noticeService.sendMessageNoticeToAdministrators(crossReview);
            }
        }else if(TimeAxisService.getTimeAxisState() == 8 && oldTimeAxisState == 7){
            studentService.updateFinalScore();
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
