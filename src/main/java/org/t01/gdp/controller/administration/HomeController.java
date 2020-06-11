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
    public String getHome(HttpServletRequest request, HttpServletResponse response, ModelMap map){
        map.addAttribute("position","home");

        return "administrator/mainPage";
    }

    @GetMapping("/timeAxis")
    public String getTimeAxis(ModelMap map){
        map.addAttribute("TimePoints",TimeAxis.getTimePoints());

        map.addAttribute("position","timeAxis");

        return "administrator/mainPage";
    }

    @GetMapping("/accountManagement/accountInfo")
    public String getAccountInfo(ModelMap map){
        map.addAttribute("position","accountManagement/accountInfo");

        return "administrator/mainPage";
    }

    @PostMapping("/timeAxis")
    @ResponseBody
    public String changeTimeAxis(TimePoint timePoint, String dateTimeString) throws ParseException {
        String DATE_FORMAT_YMDHMS_WITH_T="yyyy-MM-dd'T'HH:mm";
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YMDHMS_WITH_T);
        Date date = format.parse(dateTimeString);
        timePoint.setDateTime(date);

        System.out.println(timePoint);

        TimeAxis.setTimePoint(timePoint.getIndex(),timePoint);

        return timePoint.toString();
    }
}
