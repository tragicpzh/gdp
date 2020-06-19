package org.t01.gdp.domain;

import java.util.ArrayList;

public class MonitorRecord {
    private Long jvmTotalMemory;
    private Long jvmUsedMemory;
    private Integer jvmProcessors;

    private Long totalMemory;
    private Long usedMemory;

    private ArrayList<Double> cpuUsages = new ArrayList<>();

    private Long fileSystemTotal;
    private Long fileSystemUsed;

    @Override
    public String toString() {
        return "{"
                + "\"jvmTotalMemory\":"
                + jvmTotalMemory
                + ",\"jvmUsedMemory\":"
                + jvmUsedMemory
                + ",\"jvmProcessors\":"
                + jvmProcessors
                + ",\"totalMemory\":"
                + totalMemory
                + ",\"usedMemory\":"
                + usedMemory
                + ",\"cpuUsages\":"
                + cpuUsages
                + ",\"fileSystemTotal\":"
                + fileSystemTotal
                + ",\"fileSystemUsed\":"
                + fileSystemUsed
                + "}";

    }

    public Long getJvmTotalMemory() {
        return jvmTotalMemory;
    }

    public void setJvmTotalMemory(Long jvmTotalMemory) {
        this.jvmTotalMemory = jvmTotalMemory;
    }

    public Long getJvmUsedMemory() {
        return jvmUsedMemory;
    }

    public void setJvmUsedMemory(Long jvmUsedMemory) {
        this.jvmUsedMemory = jvmUsedMemory;
    }

    public Integer getJvmProcessors() {
        return jvmProcessors;
    }

    public void setJvmProcessors(Integer jvmProcessors) {
        this.jvmProcessors = jvmProcessors;
    }

    public Long getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(Long totalMemory) {
        this.totalMemory = totalMemory;
    }

    public Long getUsedMemory() {
        return usedMemory;
    }

    public void setUsedMemory(Long usedMemory) {
        this.usedMemory = usedMemory;
    }

    public ArrayList<Double> getCpuUsage() {
        return cpuUsages;
    }

    public void addCpuUsage(Double cpuUsage){
        cpuUsages.add(cpuUsage);
    }

    public Long getFileSystemTotal() {
        return fileSystemTotal;
    }

    public void setFileSystemTotal(Long fileSystemTotal) {
        this.fileSystemTotal = fileSystemTotal;
    }

    public Long getFileSystemUsed() {
        return fileSystemUsed;
    }

    public void setFileSystemUsed(Long fileSystemUsed) {
        this.fileSystemUsed = fileSystemUsed;
    }
}
