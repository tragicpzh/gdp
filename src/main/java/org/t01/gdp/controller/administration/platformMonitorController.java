package org.t01.gdp.controller.administration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.t01.gdp.domain.TimePoint;
import org.t01.gdp.service.MonitorService;
import org.t01.gdp.service.TimeAxisService;

import java.util.ArrayList;

@Controller
@RequestMapping("/administrator")

public class platformMonitorController {
    @Autowired
    MonitorService monitorService;
    @GetMapping("/getLog")
    @ResponseBody
    public String getLog() {
        return monitorService.getLog();
    }
}
