package org.t01.gdp.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.t01.gdp.service.MailService;
import org.t01.gdp.service.SMSService;
import org.t01.gdp.service.UserService;
import org.t01.gdp.service.VerificationService;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AccountController {
    private final UserService userService;
    private final VerificationService verificationService;

    @ResponseBody
    @RequestMapping("/getAccountInfo")
    public Map<String, String> getAccountInfo(@RequestParam(name = "id") String id) {
        return userService.getAccountInfoById(id);
    }

    @ResponseBody
    @RequestMapping("/checkPassword")
    public boolean checkPassword(@RequestParam(name = "id") String id,
                                 @RequestParam(name = "oldPassword") String oldPassword) {
        return userService.getUserById(id).getPassword().equals(oldPassword);
    }

    @ResponseBody
    @RequestMapping("/updatePassword")
    public boolean updatePassword(@RequestParam(name = "id") String id,
                                  @RequestParam(name = "newPassword") String newPassword) {
        return userService.updateUserPassword(id, newPassword);
    }

    @ResponseBody
    @RequestMapping("/sendNoteCode")
    public String sendNoteCode(@RequestParam(name = "phoneNumber") String phoneNumber) {
        verificationService.sendSMSVerificationCode(phoneNumber);
        return "success";
    }

    @ResponseBody
    @RequestMapping("/checkNoteCode")
    public boolean checkNoteCode(@RequestParam(name = "phoneNumber") String phoneNumber,
                                 @RequestParam(name = "code") String code) {
        return verificationService.smsVerify(code, phoneNumber);
    }

    @ResponseBody
    @RequestMapping("/updatePhoneNumber")
    public boolean updatePhoneNumber(@RequestParam(name = "id") String id,
                                     @RequestParam(name = "role") String role,
                                     @RequestParam(name = "phoneNumber") String phoneNumber) {
        return userService.updatePhoneNumber(id, role, phoneNumber);
    }

    @ResponseBody
    @RequestMapping("/sendEmailCode")
    public String sendEmailCode(@RequestParam(name = "email") String email) {
        verificationService.sendEmailVerificationCode(email);
        return "success";
    }

    @ResponseBody
    @RequestMapping("/checkEmailCode")
    public boolean checkEmailCode(@RequestParam(name = "email") String email,
                                  @RequestParam(name = "code") String code) {
        return verificationService.emailVerify(code, email);
    }

    @ResponseBody
    @RequestMapping("/updateEmail")
    public boolean updateEmail(@RequestParam(name = "id") String id,
                               @RequestParam(name = "role") String role,
                               @RequestParam(name = "email") String email) {
        return userService.updateEmail(id, role, email);
    }
}
