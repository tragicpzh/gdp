package org.t01.gdp.controller.administration;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.t01.gdp.domain.User;
import org.t01.gdp.service.UserService;

import java.util.*;

@Controller
@RequestMapping("/administrator")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @RequestMapping("/manageTeachers")
    public String ToTeachersPage(Model model) {
        model.addAttribute("role", "TEA");
        return "/administrator/manageUsers";
    }

    @RequestMapping("/manageStudents")
    public String ToStudentsPage(Model model) {
        model.addAttribute("role", "STU");
        return "/administrator/manageUsers";
    }

    @ResponseBody
    @RequestMapping(value = "/addSingleUser", method = RequestMethod.POST)
    public boolean addSingleUser(User user) {
        /*System.out.println("Id: " + user.getId() + ", Password: " + user.getPassword() + ", Role: "
                + user.getRole() + ", Name: " + user.getName() + ", PhoneNum: "
                + user.getPhoneNumber() + ", Email: " + user.getEmail());*/
        return userService.addUser(user) == 1;
    }

    @ResponseBody
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public boolean updateUser(User user) {
        /*System.out.println("Id: " + user.getId() + ", Password: " + user.getPassword() + ", Role: "
                + user.getRole() + ", Name: " + user.getName() + ", PhoneNum: "
                + user.getPhoneNumber() + ", Email: " + user.getEmail());*/
        return userService.updateUser(user) == 1;
    }

    @ResponseBody
    @RequestMapping(value = "/batchImportUsers", method = RequestMethod.POST)
    public List<String> batchImportUsers(@RequestBody JSONObject inputJsonObject) {
        JSONArray jsonArray = inputJsonObject.getJSONArray("data");
        List<String> duplicateIds = new ArrayList<>();
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            User user = new User();
            Set<String> hashSet = jsonObject.keySet();
            for (String key : hashSet) {
                String value = jsonObject.getString(key);
                switch (key) {
                    case "学号":
                        user.setId(value);
                        break;
                    case "密码":
                        user.setPassword(value);
                        break;
                    case "用户类型":
                        user.setRole(value);
                        break;
                    case "姓名":
                        user.setName(value);
                        break;
                    case "手机号码":
                        user.setPhoneNumber(value);
                        break;
                    case "Email":
                        user.setEmail(value);
                        break;
                }
            }
            if (userService.addUser(user) == 0) {
                duplicateIds.add(user.getId());
            }
        }
        return duplicateIds;
    }

    @ResponseBody
    @RequestMapping(value = "/getUsersByRole", method = RequestMethod.POST)
    public List<User> getUsersByRole(@RequestParam(name = "role") String role) {
        return userService.getUserByRole(role);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteUsersById", method = RequestMethod.DELETE)
    public boolean deleteUsersById(@RequestParam(name = "id") String id) {
        //System.out.println(id);
        return userService.deleteUserById(id) == 1;
    }
}
