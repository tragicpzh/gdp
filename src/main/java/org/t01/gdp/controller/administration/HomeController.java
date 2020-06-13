package org.t01.gdp.controller.administration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.t01.gdp.domain.TimeAxis;
import org.t01.gdp.domain.TimePoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/administrator")
public class HomeController {
    @GetMapping("/home")
    public String getHome(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        map.addAttribute("position", "home");

        return "administrator/mainPage";
    }

    @GetMapping("/timeAxis")
    public String getTimeAxis(ModelMap map) {
        map.addAttribute("TimePoints", TimeAxis.getTimePoints());

        map.addAttribute("position", "timeAxis");

        return "administrator/mainPage";
    }

    @GetMapping("/accountInfo")
    public String getAccountInfo(ModelMap map) {
        map.addAttribute("position", "accountInfo");

        return "administrator/mainPage";
    }

    @PostMapping("/timeAxis")
    @ResponseBody
    public String changeTimeAxis(TimePoint timePoint, String dateTimeString) throws ParseException {
        Date date = TimeAxis.getFormat().parse(dateTimeString);
        timePoint.setDateTime(date);

        System.out.println(timePoint);

        int result = TimeAxis.setTimePoint(timePoint.getIndex(), timePoint);
        switch (result) {
            case 0:
                return "保存成功";
            case -1:
                return "时间应在前一事件的时间之后";
            case 1:
                return "时间应在后一事件的时间之前";
            default:
                return "发生未知错误";
        }

    }
}
