package org.t01.gdp.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.t01.gdp.service.SMSService;
import org.t01.gdp.service.UserService;
import org.t01.gdp.service.VerificationService;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AccountController {
    private final UserService userService;

    @ResponseBody
    @RequestMapping(value = "/getAccountInfo", method = RequestMethod.GET)
    public Map<String, String> getAccountInfo(@RequestParam(name = "id") String id) {
        return userService.getAccountInfoById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/updateAccountInfo", method = RequestMethod.POST)
    public Map<String, String> updateAccountInfo(@RequestBody JSONObject jsonObject) {
        return userService.updateAccountInfo(
                jsonObject.getString("id"),
                jsonObject.getString("role"),
                jsonObject.getString("phoneNumber"),
                jsonObject.getString("email"
                ));
    }

    @ResponseBody
    @RequestMapping("/sendCode")
    public String sendCode(@RequestParam(name = "phoneNumber") String phoneNumber) {
        VerificationService.sendSMSVerificationCode(phoneNumber);
        return "success";
    }

    @ResponseBody
    @RequestMapping("/checkCode")
    public boolean checkCode(@RequestParam(name = "phoneNumber") String phoneNumber,
                             @RequestParam(name = "code") String code) {
        return VerificationService.smsVerify(code, phoneNumber);
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
}
