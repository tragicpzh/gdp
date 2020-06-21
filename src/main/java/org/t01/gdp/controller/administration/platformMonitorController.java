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
        monitorRecord = monitorService.getMonitorRecord();
        return ((double) monitorRecord.getJvmUsedMemory()) / monitorRecord.getJvmTotalMemory();
    }

    @GetMapping("/getMEM")
    @ResponseBody
    public double getMemory() {
        monitorRecord = monitorService.getMonitorRecord();
        if(monitorRecord.getTotalMemory()!=null){
            return (double) (monitorRecord.getUsedMemory()) / monitorRecord.getTotalMemory();
        }
        return 0;
    }

    @GetMapping("/getFileSystem")
    @ResponseBody
    public double getFileSystem() {
        monitorRecord = monitorService.getMonitorRecord();
        if(monitorRecord.getFileSystemTotal()!=null){
            return (double) (monitorRecord.getFileSystemUsed()) / monitorRecord.getFileSystemTotal();
        }
        return 0;
    }

    @GetMapping("/getCPU")
    @ResponseBody
    public ArrayList<Double> getCPU() {
        monitorRecord = monitorService.getMonitorRecord();
        if(monitorRecord.getCpuUsage()!=null){
            return monitorRecord.getCpuUsage();
        }
        return new ArrayList<>();
    }
    @GetMapping("/getProcessor")
    @ResponseBody
    public Double getProcessor() {
        monitorRecord = monitorService.getMonitorRecord();
        if(monitorRecord.getCpuUsage()!=null){
            return monitorRecord.getCpuUsage();
        }
        return 0.1;
    }
}
