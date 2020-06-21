package org.t01.gdp.controller.administration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.t01.gdp.domain.MonitorRecord;
import org.t01.gdp.service.MonitorService;

import java.util.ArrayList;

@Controller
@RequestMapping("/administrator")
public class platformMonitorController {
    @Autowired
    MonitorService monitorService;
    private MonitorRecord monitorRecord;

    @GetMapping("/updateMonitorRecord")
    @ResponseBody
    public void updateMonitorRecord() {
        monitorRecord = monitorService.getMonitorRecord();
    }

    @GetMapping("/getJVM")
    @ResponseBody
    public double getJvm() {
        return ((double) monitorRecord.getJvmUsedMemory()) / monitorRecord.getJvmTotalMemory();
    }

    @GetMapping("/getMEM")
    @ResponseBody
    public double getMemory() {
        return (double) (monitorRecord.getUsedMemory()) / monitorRecord.getTotalMemory();
    }

    @GetMapping("/getFileSystem")
    @ResponseBody
    public double getFileSystem() {
        return (double) (monitorRecord.getFileSystemUsed()) / monitorRecord.getFileSystemTotal();
    }

    @GetMapping("/getCPU")
    @ResponseBody
    public ArrayList<Double> getCPU() {
        return monitorRecord.getCpuUsage();
    }
}
