package org.t01.gdp.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.t01.gdp.domain.VerificationCode;
import org.t01.gdp.service.SMSService;
import org.t01.gdp.service.UserService;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AccountController {
    private final UserService userService;
    private final SMSService smsService;

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
        String code = VerificationCode.createCode(phoneNumber);
        smsService.sendVerificationCode(code, phoneNumber);
        System.out.println(code);
        return "success";
    }

    @ResponseBody
    @RequestMapping("/checkCode")
    public boolean checkCode(@RequestParam(name = "phoneNumber") String phoneNumber,
                             @RequestParam(name = "code") String code) {
        return VerificationCode.checkCode(phoneNumber, code);
    }
}
