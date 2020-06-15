package org.t01.gdp.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.t01.gdp.domain.TimeAxis;
import org.t01.gdp.service.DownloadService;
import org.t01.gdp.service.SMSService;
import org.t01.gdp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CommonController {
    private final DownloadService downloadService;

//    @Autowired
//    SMSService smsService;
//
//    @RequestMapping("/test")
//    public String test(){
//        String result = smsService.sendVerificationCode("1234", "17361040193");
//        String result = smsService.sendBatchSMS();
//        System.out.println(result);
//
//        return "test";
//    }

    @GetMapping("/download/**")
    @ResponseBody
    public boolean getDownload(HttpServletRequest request, HttpServletResponse response) {
        return downloadService.downloadFile(request, response);
    }

    @RequestMapping("/**/*Fragment")
    public String getFragment(HttpServletRequest request) {
        String uri = request.getRequestURI();
        if(TimeAxis.isAccessible(uri) || true){
            return uri;
        }else{
            return "notAccessible";
        }
    }

    @RequestMapping("/**/*Script")
    public String getScript(HttpServletRequest request) {
        return request.getRequestURI();
    }

    @RequestMapping("/*/mainPage")
    public String getMainPage(HttpServletRequest request) {
        return request.getRequestURI();
    }
}
