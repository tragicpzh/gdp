package org.t01.gdp.service;

import org.hyperic.sigar.*;
import org.springframework.stereotype.Service;
import org.t01.gdp.domain.MonitorRecord;

import java.util.ArrayList;

@Service
public class MonitorService {
    private double jvmThreshold;
    private double memThreshold;
    private double cpuThreshold;

    public MonitorService() {
        jvmThreshold = 0.60;
        memThreshold = 0.60;
        cpuThreshold = 0.80;
    }

    public MonitorRecord getMonitorRecord() {
        Runtime runtime = Runtime.getRuntime();
        Sigar sigar = new Sigar();
        MonitorRecord monitorRecord = new MonitorRecord();

        monitorRecord.setJvmTotalMemory(runtime.totalMemory());
        monitorRecord.setJvmUsedMemory(runtime.totalMemory() - runtime.freeMemory());
        monitorRecord.setJvmProcessors((long) runtime.availableProcessors());

        try {
            Mem mem = sigar.getMem();
            monitorRecord.setTotalMemory(mem.getTotal());
            monitorRecord.setUsedMemory(mem.getUsed());
        } catch (SigarException e) {
            e.printStackTrace();
        }

        try {
            CpuPerc[] cpuPercList = sigar.getCpuPercList();
            for (CpuPerc cpuPerc : cpuPercList) {
                monitorRecord.addCpuUsage(cpuPerc.getCombined());
            }
        } catch (SigarException e) {
            e.printStackTrace();
        }

        try {
            FileSystemUsage fileSystemUsage = sigar.getFileSystemUsage(System.getProperty("user.dir").substring(0, 3));
            monitorRecord.setFileSystemTotal(fileSystemUsage.getTotal());
            monitorRecord.setFileSystemUsed(fileSystemUsage.getUsed());
        } catch (SigarException e) {
            e.printStackTrace();
        }

        return monitorRecord;
    }

    public void onceMonitor() {
        MonitorRecord monitorRecord = getMonitorRecord();

//        System.out.println(monitorRecord);

        if (monitorRecord.getJvmUsedMemory() != null && ((double) monitorRecord.getJvmUsedMemory()) / monitorRecord.getJvmTotalMemory() >= jvmThreshold) {
            MyLogService.info("jvm:" + monitorRecord.getJvmUsedMemory() + "/" + monitorRecord.getJvmTotalMemory());
        }
        if (monitorRecord.getUsedMemory() != null && ((double) monitorRecord.getUsedMemory()) / monitorRecord.getTotalMemory() >= memThreshold) {
            MyLogService.info("mem:" + monitorRecord.getUsedMemory() + "/" + monitorRecord.getTotalMemory());
        }
        ArrayList<Double> cpuUsages = monitorRecord.getCpuUsage();
        for (Double cpuUsage : cpuUsages) {
            if (cpuUsage >= cpuThreshold) {
                MyLogService.info("cpu:" + cpuUsage);
            }
        }
    }

    public double getJvmThreshold() {
        return jvmThreshold;
    }

    public void setJvmThreshold(double jvmThreshold) {
        this.jvmThreshold = jvmThreshold;
    }

    public double getMemThreshold() {
        return memThreshold;
    }

    public void setMemThreshold(double memThreshold) {
        this.memThreshold = memThreshold;
    }

    public double getCpuThreshold() {
        return cpuThreshold;
    }

    public void setCpuThreshold(double cpuThreshold) {
        this.cpuThreshold = cpuThreshold;
    }
}
