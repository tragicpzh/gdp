package org.t01.gdp.controller.administration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/administrator")
@RequiredArgsConstructor
public class UserController {
//    private final UserService userService;

//    @ResponseBody
//    @RequestMapping(value = "/addSingleUser", method = RequestMethod.POST)
//    public boolean addSingleUser(User user, @RequestParam(name = "otherInfo") String otherInfo) {
//        return userService.addUser(user, otherInfo);
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
//    public boolean updateUser(User user, @RequestParam(name = "otherInfo") String otherInfo) {
//        return userService.updateUser(user, otherInfo);
//    }

//    @ResponseBody
//    @RequestMapping(value = "/batchImportUsers", method = RequestMethod.POST)
//    public List<String> batchImportUsers(@RequestBody JSONObject inputJsonObject) {
//        JSONArray jsonArray = inputJsonObject.getJSONArray("data");
//        List<String> duplicateIds = new ArrayList<>();
//        for (int i = 0; i < jsonArray.size(); i++) {
//            JSONObject jsonObject = jsonArray.getJSONObject(i);
//            User user = new User();
//            String otherInfo = "";
//            for (String key : jsonObject.keySet()) {
//                String value = jsonObject.getString(key);
//                //System.out.println(key + ": " + value);
//                if (key.equals("学院Id") || key.equals("专业Id")) {
//                    otherInfo = value;
//                    continue;
//                }
//                switch (key) {
//                    case "Id":
//                        user.setId(value);
//                        break;
//                    case "密码":
//                        user.setPassword(value);
//                        break;
//                    case "用户类型":
//                        user.setRole(value);
//                        break;
//                    case "姓名":
//                        user.setName(value);
//                        break;
//                    case "手机号码":
//                        user.setPhoneNumber(value);
//                        break;
//                    case "Email":
//                        user.setEmail(value);
//                        break;
//                }
//            }
//            if (!userService.addUser(user, otherInfo)) {
//                duplicateIds.add(user.getId());
//            }
//        }
//        return duplicateIds;
//    }

//    @ResponseBody
//    @RequestMapping(value = "/getUsersByRole", method = RequestMethod.POST)
//    public List<HashMap<String, Object>> getUsersByRole(@RequestParam(name = "role") String role) {
//        return userService.getUserByRole(role);
//    }

//    @ResponseBody
//    @RequestMapping(value = "/deleteUserById", method = RequestMethod.DELETE)
//    public boolean deleteUserById(@RequestParam(name = "id") String id) {
//        return userService.deleteUserById(id);
//    }

//    @ResponseBody
//    @RequestMapping(value = "/deleteUsersById", method = RequestMethod.DELETE)
//    public boolean deleteUsersById(@RequestBody List<Integer> ids) {
//        int count = ids.size();
//        for (int id : ids) {
//            if (userService.deleteUserById(String.valueOf(id))) {
//                count--;
//            }
//        }
//        return count == 0;
//    }
}
