package org.t01.gdp.controller.administration;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.t01.gdp.service.UserService;

import java.util.*;

@Controller
@RequestMapping("/administrator")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ResponseBody
    @RequestMapping("/addSingleUser")
    public String addSingleUser(@RequestParam(name = "name") String name,
                                @RequestParam(name = "phoneNumber") String phoneNumber,
                                @RequestParam(name = "email") String email,
                                @RequestParam(name = "role") String role,
                                @RequestParam(name = "otherInfo") String otherInfo) {
        return userService.addUser(name, phoneNumber, email, role, otherInfo);
    }

    /*@ResponseBody
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public boolean updateUser(User user, @RequestParam(name = "otherInfo") String otherInfo) {
        return userService.updateUser(user, otherInfo);
    }*/

    @ResponseBody
    @RequestMapping(value = "/batchImportUsers", method = RequestMethod.POST)
    public List<String> batchImportUsers(@RequestBody JSONObject inputJsonObject) {
        JSONArray jsonArray = inputJsonObject.getJSONArray("data");
        String name = "";
        String role = "";
        String phoneNumber = "";
        String email = "";
        List<String> duplicateIds = new ArrayList<>();
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
                }
            }
            if (!userService.addUser(name, phoneNumber, email, role, otherInfo).contains("success")) {
                duplicateIds.add(name);
            }
        }
        return duplicateIds;
    }

    @ResponseBody
    @RequestMapping(value = "/getUsersByRole", method = RequestMethod.POST)
    public List<Map<String, String>> getUsersByRole(@RequestParam(name = "role") String role) {
        return userService.getUserByRole(role);
    }

    /*@ResponseBody
    @RequestMapping(value = "/deleteUserById", method = RequestMethod.DELETE)
    public boolean deleteUserById(@RequestParam(name = "id") String id) {
        return userService.deleteUserById(id);
    }*/

    /*@ResponseBody
    @RequestMapping(value = "/deleteUsersById", method = RequestMethod.DELETE)
    public boolean deleteUsersById(@RequestBody List<Integer> ids) {
        int count = ids.size();
        for (int id : ids) {
            if (userService.deleteUserById(String.valueOf(id))) {
                count--;
            }
        }
        return count == 0;
    }*/
}
