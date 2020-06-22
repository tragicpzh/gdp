package org.t01.gdp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.t01.gdp.domain.*;
import org.t01.gdp.mapper.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class AdministratorService {
    private final Logger LOG = LoggerFactory.getLogger(AdministratorService.class);

    @Autowired
    AdministratorMapper administratorMapper;
    @Autowired
    FileService fileService;
    @Autowired
    SubjectMapper subjectMapper;
    @Autowired
    SqlMapper sqlMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    MonitorService monitorService;

    public void getUserInfo(long id){
        administratorMapper.selectByPrimaryKey(id);
    }

    public void uploadNewUserImage(HttpServletRequest request, MultipartFile multipartFile) {
        long administratorId = ((UserInfo) request.getSession(true).getAttribute("USER_INFO")).getId();
        String path = "userImage/administrator/" + administratorId + '/';
        fileService.uploadUserImage(path, multipartFile);
    }

    public Map<String,Object> simpleSelect(Long adminId){
        Map<String,Object> map=new HashMap<>();
        Administrator administrator=administratorMapper.selectByPrimaryKey(adminId);
        map.put("name",administrator.getName());
        map.put("email",administrator.getEmail());
        map.put("telephone",administrator.getPhoneNumber());
        return map;
    }

    public Map<String,Object> toDoList(Long adminId){
        Map<String,Object> map=new HashMap<>();
        Administrator administrator=administratorMapper.selectByPrimaryKey(adminId);
        map.put("emailexsit",administrator.getEmail()!=null);
        map.put("headexsit",administrator.getHeadPortrait()!=null);
        map.put("telephoneexsit",administrator.getPhoneNumber()!=null);
        SubjectExample subjectExample=new SubjectExample();
        subjectExample.or().andStateEqualTo("NEW");
        subjectExample.or().andStateEqualTo("MODIFIED");
        List<Subject> subjectList=subjectMapper.selectByExample(subjectExample);
        map.put("subjectExamine",subjectList.isEmpty());
        return  map;
    }

    public Map<String,Object> getLable(){
        Map<String,Object> map=new HashMap<>();
        int cnt=0;
        List<Subject>subjects=subjectMapper.selectByExample(null);
        Calendar calendarNow=Calendar.getInstance();
        for (Subject subject : subjects) {
            Calendar calendarTmp=Calendar.getInstance();
            calendarTmp.setTime(subject.getCreateTime());
            if(calendarNow.get(Calendar.YEAR)==calendarTmp.get(Calendar.YEAR)
            &&calendarNow.get(Calendar.MONTH)==calendarTmp.get(Calendar.MONTH)
            &&calendarNow.get(Calendar.DAY_OF_MONTH)==calendarTmp.get(Calendar.DAY_OF_MONTH)
            )cnt++;
        }
        map.put("newSubject",cnt);

        String state=sqlMapper.selectone();
        String subjectState="";
        switch (state) {
            case "NO_SELECTION":
                subjectState+= "选题阶段";
                break;
            case "SELECT_COMPLETE":
                subjectState+= "选题阶段";
                break;
            case "NoOpenDoc":
                subjectState+= "开题阶段";
                break;
            case "WaitOpenScore":
                subjectState+= "开题阶段";
                break;
            case "OpenComplete":
                subjectState+= "开题阶段";
                break;
            case "Working":
                subjectState+= "论文编写阶段";
                break;
            case "NoMidDoc":
                subjectState+= "中期答辩阶段";
                break;
            case "WaitMidScore":
                subjectState+= "中期答辩阶段";
                break;
            case "MidComplete":
                subjectState+= "中期答辩阶段";
                break;
            case "NoPaperDoc":
                subjectState+= "论文评阅阶段";
                break;
            case "WaitPaperScore":
                subjectState+= "论文评阅阶段";
                break;
            case "PaperComplete":
                subjectState+= "论文评阅阶段";
                break;
            case "NoConDoc":
                subjectState+= "毕业答辩阶段";
                break;
            case "WaitConScore":
                subjectState+= "毕业答辩阶段";
                break;
            case "ConComplete":
                subjectState+= "毕业答辩阶段";
                break;
            case "WaitFinalScore":
                subjectState+= "最终阶段";
                break;
            case "Finished":
                subjectState+= "最终阶段";
                break;
            default:
                LOG.warn("未知阶段:{}",state);
        }
        map.put("subjectState",subjectState);

        int sum=0;
        List<Administrator>administrators=administratorMapper.selectByExample(null);
        List<Teacher> teachers=teacherMapper.selectByExample(null);
        List<Student> students=studentMapper.selectByExample(null);
        for (Administrator administrator : administrators) {
            Calendar calendarTmp=Calendar.getInstance();
            calendarTmp.setTime(administrator.getCreateTime());
            if(calendarNow.get(Calendar.YEAR)==calendarTmp.get(Calendar.YEAR)
                    &&calendarNow.get(Calendar.MONTH)==calendarTmp.get(Calendar.MONTH)
                    &&calendarNow.get(Calendar.DAY_OF_MONTH)==calendarTmp.get(Calendar.DAY_OF_MONTH)
            )sum++;
        }
        for (Teacher teacher : teachers) {
            Calendar calendarTmp=Calendar.getInstance();
            calendarTmp.setTime(teacher.getCreateTime());
            if(calendarNow.get(Calendar.YEAR)==calendarTmp.get(Calendar.YEAR)
                    &&calendarNow.get(Calendar.MONTH)==calendarTmp.get(Calendar.MONTH)
                    &&calendarNow.get(Calendar.DAY_OF_MONTH)==calendarTmp.get(Calendar.DAY_OF_MONTH)
            )sum++;
        }
        for (Student student : students) {
            Calendar calendarTmp=Calendar.getInstance();
            calendarTmp.setTime(student.getCreateTime());
            if(calendarNow.get(Calendar.YEAR)==calendarTmp.get(Calendar.YEAR)
                    &&calendarNow.get(Calendar.MONTH)==calendarTmp.get(Calendar.MONTH)
                    &&calendarNow.get(Calendar.DAY_OF_MONTH)==calendarTmp.get(Calendar.DAY_OF_MONTH)
            )sum++;
        }
        map.put("newUser",sum);

        int mem;
        MonitorRecord monitorRecord = monitorService.getMonitorRecord();
        if(monitorRecord.getTotalMemory()!=null){
            mem= (int) ((monitorRecord.getUsedMemory()) *100/ monitorRecord.getTotalMemory());
        }
        else mem=0;
        String mems=String.valueOf(mem);
        mems+="%";
        map.put("memoryCount",mems);
        return map;
    }
}
