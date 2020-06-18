package org.t01.gdp.controller;

import lombok.RequiredArgsConstructor;
import org.omg.CORBA.UNKNOWN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.t01.gdp.domain.TimeAxis;
import org.t01.gdp.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class CommonController {
    @Autowired
    private FileService fileService;
    @Autowired
    private VerificationService verificationService;
    @Autowired
    private SMSService smsService;
    @Autowired
    private MailService mailService;
    @Autowired
    private UserService userService;
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
    public void getDownload(HttpServletRequest request, HttpServletResponse response) {
        fileService.downloadFile(request, response);
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

    @PostMapping("/retrievePassword")
    public String retrievePassword(String username, String role, String smsVerifyCode, String emailVerifyCode, String newPassword){
        if(!smsVerifyCode.equals("") && smsVerifyCode != null){
            String phoneNumber = userService.getPhoneNumber(username, role);
            if(!verificationService.smsVerify(smsVerifyCode,phoneNumber)){
                return "index";
            }
        }else if(!emailVerifyCode.equals("") && emailVerifyCode != null){
            String email = userService.getEmail(username, role);
            if(!verificationService.emailVerify(emailVerifyCode,email)){
                return "index";
            }
        }else{
            return "index";
        }

        if(userService.setPassword(username,newPassword,role)){
            switch (role){
                case "STU":
                    return "student/login";
                case "TEA":
                    return "teacher/login";
                case "ADM":
                    return "administrator/login";
            }
        }
        return "index";
    }

    @PostMapping("/sendVerifyCode")
    @ResponseBody
    public String sendVerifyCode(String method, String username, String role){
        if(username==null || username.equals("")){
            return "NO_USER_NAME";
        }

        if(method.equals("sms")){
            String phoneNumber = userService.getPhoneNumber(username, role);
            if(phoneNumber==null || phoneNumber.equals("")){
                return "PHONE_NUMBER_ERROR";
            }
            verificationService.sendSMSVerificationCode(phoneNumber,smsService);
            return "OK";
        }else if(method.equals("email")){
            String email = userService.getEmail(username, role);
            if(email==null || email.equals("")){
                return "EMAIL_ADDR_ERROR";
            }
            verificationService.sendEmailVerificationCode(email,mailService);
            return "OK";
        }
        
        return "UNKNOWN";
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
