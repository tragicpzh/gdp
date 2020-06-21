package org.t01.gdp.controller.administration;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.t01.gdp.service.UserService;

import java.util.*;

@Controller
@RequestMapping("/administrator")
@RequiredArgsConstructor
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/addSingleUser")
    public String addSingleUser(@RequestParam(name = "name") String name,
                                @RequestParam(name = "phoneNumber") String phoneNumber,
                                @RequestParam(name = "email") String email,
                                @RequestParam(name = "role") String role,
                                @RequestParam(name = "otherInfo") String otherInfo) {
        return userService.addUser(name, phoneNumber, email, role, otherInfo);
    }

    @ResponseBody
    @RequestMapping("/updateUser")
    public String updateUser(@RequestParam(name = "id") String id,
                             @RequestParam(name = "name") String name,
                             @RequestParam(name = "phoneNumber") String phoneNumber,
                             @RequestParam(name = "email") String email,
                             @RequestParam(name = "role") String role,
                             @RequestParam(name = "otherInfo") String otherInfo) {
        return userService.updateUser(id, name, phoneNumber, email, role, otherInfo);
    }

    @ResponseBody
    @RequestMapping(value = "/batchImportUsers", method = RequestMethod.POST)
    public List<String[]> batchImportUsers(@RequestBody JSONObject inputJsonObject) {
        JSONArray jsonArray = inputJsonObject.getJSONArray("data");
        String name = "";
        String role = "";
        String phoneNumber = "";
        String email = "";
        List<String[]> duplicateIds = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String otherInfo = "";
            for (String key : jsonObject.keySet()) {
                String value = jsonObject.getString(key);
                if (key.equals("学院Id") || key.equals("专业Id")) {
                    otherInfo = value;
                    continue;
                }
                switch (key) {
                    case "用户类型":
                        role = value;
                        break;
                    case "姓名":
                        name = value;
                        break;
                    case "手机号码":
                        phoneNumber = value;
                        break;
                    case "Email":
                        email = value;
                        break;
                    default:
                        LOG.warn("未知的字段名称:" + key);
                }
            }
            String message = userService.addUser(name, phoneNumber, email, role, otherInfo);
            if (!message.equals("success")) {
                duplicateIds.add(new String[]{name, message, otherInfo});
            }
        }
        return duplicateIds;
    }

    @ResponseBody
    @RequestMapping(value = "/getUsersByRole", method = RequestMethod.POST)
    public List<Map<String, Object>> getUsersByRole(@RequestParam(name = "role") String role) {
        return userService.getUserByRole(role);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteUserById", method = RequestMethod.DELETE)
    public boolean deleteUserById(@RequestParam(name = "id") String id,
                                  @RequestParam(name = "role") String role) {
        return userService.deleteUserById(id, role);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteUsersById", method = RequestMethod.DELETE)
    public boolean deleteUsersById(@RequestBody List<String> ids) {
        int count = ids.size();
        String role = ids.get(0).length() == 10 ? "TEA" : "STU";
        for (String id : ids) {
            if (userService.deleteUserById(id, role)) {
                count--;
            }
        }
        return count == 0;
    }
}
