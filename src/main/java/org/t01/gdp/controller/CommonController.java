package org.t01.gdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.t01.gdp.service.TimeAxisService;
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
    private UserService userService;

    private static final String RETIVEACCOUNT = "forgotPassword";

    @RequestMapping({"/index","/"})
    public String getIndex(HttpServletRequest request){
        request.getSession(true).setAttribute("loginFailure",false);
        return "index";
    }

    @RequestMapping("/forgotPassword")
    public String getForgetPassword(HttpServletRequest request){
        request.getSession(true).setAttribute("loginFailure",false);
        request.getSession(true).setAttribute("verifyFailure",false);
        return RETIVEACCOUNT;
    }

    @GetMapping("/download/**")
    @ResponseBody
    public void getDownload(HttpServletRequest request, HttpServletResponse response) {
        fileService.downloadFile(request, response);
    }

    @RequestMapping("/**/*Fragment")
    public String getFragment(HttpServletRequest request) {
        String uri = request.getRequestURI();
        if(TimeAxisService.isAccessible(uri) || true){
            return uri;
        }else{
            return "notAccessible";
        }
    }

    @PostMapping("/retrievePassword")
    public String retrievePassword(String username, String role,String newPassword, HttpServletRequest request){

        if(userService.setPassword(username,newPassword,role)){
            switch (role){
                case "STU":
                    return "student/login";
                case "TEA":
                    return "teacher/login";
                case "ADM":
                    return "administrator/login";
                default:
                    request.getSession(true).setAttribute("verifyFailure",true);
                    return RETIVEACCOUNT;
            }
        }
        request.getSession(true).setAttribute("verifyFailure",true);
        return RETIVEACCOUNT;
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
            verificationService.sendSMSVerificationCode(phoneNumber);
            return "OK";
        }else if(method.equals("email")){
            String email = userService.getEmail(username, role);
            if(email==null || email.equals("")){
                return "EMAIL_ADDR_ERROR";
            }
            verificationService.sendEmailVerificationCode(email);
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
