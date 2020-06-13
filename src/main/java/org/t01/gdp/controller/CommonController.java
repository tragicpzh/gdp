package org.t01.gdp.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.t01.gdp.service.DownloadService;
import org.t01.gdp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CommonController {
    private final DownloadService downloadService;
    private final UserService userService;

    @GetMapping("/download/**")
    @ResponseBody
    public boolean getDownload(HttpServletRequest request, HttpServletResponse response) {
        return downloadService.downloadFile(request, response);
    }

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
}
