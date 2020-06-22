package org.t01.gdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.t01.gdp.domain.*;
import org.t01.gdp.mapper.*;
import sun.awt.geom.AreaOp;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class AdministratorService {
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

    private MonitorRecord monitorRecord;

    public void getUserInfo(long id){
        administratorMapper.selectByPrimaryKey(id);
    }

    public void uploadNewUserImage(HttpServletRequest request, MultipartFile multipartFile) {
        long administratorId = ((UserInfo) request.getSession(true).getAttribute("USER_INFO")).getId();
        String path = "userImage/administrator/" + administratorId + "/";
        fileService.uploadUserImage(path, multipartFile);
    }

    public Map<String,Object> simpleSelect(Long admin_id){
        Map<String,Object> map=new HashMap<String,Object>();
        Administrator administrator=administratorMapper.selectByPrimaryKey(admin_id);
        map.put("name",administrator.getName());
        map.put("email",administrator.getEmail());
        map.put("telephone",administrator.getPhoneNumber());
        return map;
    }

    public Map<String,Object> ToDoList(Long admin_id){
        Map<String,Object> map=new HashMap<String,Object>();
        Administrator administrator=administratorMapper.selectByPrimaryKey(admin_id);
        map.put("emailexsit",(administrator.getEmail()!=null)?true:false);
        map.put("headexsit",(administrator.getHeadPortrait()!=null)?true:false);
        map.put("telephoneexsit",(administrator.getPhoneNumber()!=null)?true:false);
        SubjectExample subjectExample=new SubjectExample();
        subjectExample.or().andStateEqualTo("NEW");
        subjectExample.or().andStateEqualTo("MODIFIED");
        List<Subject> subjectList=subjectMapper.selectByExample(subjectExample);
        map.put("subjectExamine",(subjectList.size()==0)?true:false);
        return  map;
    }

    public Map<String,Object> getLable(){
        Map<String,Object> map=new HashMap<String, Object>();
        int cnt=0;
        List<Subject>subjects=subjectMapper.selectByExample(null);
        Calendar calendar_now=Calendar.getInstance();
        for (Subject subject : subjects) {
            Calendar calendar_tmp=Calendar.getInstance();
            calendar_tmp.setTime(subject.getCreateTime());
            if(calendar_now.get(Calendar.YEAR)==calendar_tmp.get(Calendar.YEAR)
            &&calendar_now.get(Calendar.MONTH)==calendar_tmp.get(Calendar.MONTH)
            &&calendar_now.get(Calendar.DAY_OF_MONTH)==calendar_tmp.get(Calendar.DAY_OF_MONTH)
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
        }
        map.put("subjectState",subjectState);

        int sum=0;
        List<Administrator>administrators=administratorMapper.selectByExample(null);
        List<Teacher> teachers=teacherMapper.selectByExample(null);
        List<Student> students=studentMapper.selectByExample(null);
        for (Administrator administrator : administrators) {
            Calendar calendar_tmp=Calendar.getInstance();
            calendar_tmp.setTime(administrator.getCreateTime());
            if(calendar_now.get(Calendar.YEAR)==calendar_tmp.get(Calendar.YEAR)
                    &&calendar_now.get(Calendar.MONTH)==calendar_tmp.get(Calendar.MONTH)
                    &&calendar_now.get(Calendar.DAY_OF_MONTH)==calendar_tmp.get(Calendar.DAY_OF_MONTH)
            )sum++;
        }
        for (Teacher teacher : teachers) {
            Calendar calendar_tmp=Calendar.getInstance();
            calendar_tmp.setTime(teacher.getCreateTime());
            if(calendar_now.get(Calendar.YEAR)==calendar_tmp.get(Calendar.YEAR)
                    &&calendar_now.get(Calendar.MONTH)==calendar_tmp.get(Calendar.MONTH)
                    &&calendar_now.get(Calendar.DAY_OF_MONTH)==calendar_tmp.get(Calendar.DAY_OF_MONTH)
            )sum++;
        }
        for (Student student : students) {
            Calendar calendar_tmp=Calendar.getInstance();
            calendar_tmp.setTime(student.getCreateTime());
            if(calendar_now.get(Calendar.YEAR)==calendar_tmp.get(Calendar.YEAR)
                    &&calendar_now.get(Calendar.MONTH)==calendar_tmp.get(Calendar.MONTH)
                    &&calendar_now.get(Calendar.DAY_OF_MONTH)==calendar_tmp.get(Calendar.DAY_OF_MONTH)
            )sum++;
        }
        map.put("newUser",sum);

        int MEM;
        monitorRecord = monitorService.getMonitorRecord();
        if(monitorRecord.getTotalMemory()!=null){
            MEM= (int) ((monitorRecord.getUsedMemory()) *100/ monitorRecord.getTotalMemory());
        }
        else MEM=0;
        String MEMS=String.valueOf(MEM);
        MEMS+="%";
        map.put("memoryCount",MEMS);
        return map;
    }
}
