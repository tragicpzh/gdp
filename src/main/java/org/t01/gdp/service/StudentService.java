package org.t01.gdp.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.t01.gdp.domain.*;
import org.t01.gdp.mapper.*;
import org.t01.gdp.mapper.StudentMapper;
import org.t01.gdp.mapper.SubjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
@RequiredArgsConstructor
public class StudentService {
    private static final Logger LOG = LoggerFactory.getLogger(StudentService.class);

    private final StudentMapper studentMapper;
    private final SubjectMapper subjectMapper;
    private final UserMapper userMapper;
    private final SqlMapper sqlMapper;
    private final MajorMapper majorMapper;
    private final CollegeMapper collegeMapper;
    @Autowired
    FileService fileService;

    public Student getStudentInfoById(String id) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andStudentIdEqualTo(id);
        return studentMapper.selectByExample(studentExample).get(0);
    }

    public Student getStudent(long id){
        return studentMapper.selectByPrimaryKey(id);
    }

    public String updateState(Student student) {
        String id = student.getStudentId();
        studentMapper.updateByPrimaryKeySelective(student);
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andStudentIdEqualTo(id);
        return studentMapper.selectByExample(studentExample).get(0).getState();
    }

    public Subject getSubjectById(long subjectId) {
        return subjectMapper.selectByPrimaryKey(subjectId);
    }

    public int updateStudentOpeningScore(long teacherId, long studentId, long subjectId, Integer score, String evaluation) {
        Subject subject = subjectMapper.selectByPrimaryKey(subjectId);
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andIdEqualTo(studentId);
        Student student = studentMapper.selectByExample(studentExample).get(0);
        if (subject.getReviewTeacherId1() == teacherId) {
            student.setOpenScore1(score);
            student.setOpenEvaluation1(evaluation);
            return studentMapper.updateByPrimaryKey(student);
        } else if (subject.getReviewTeacherId2() == teacherId) {
            student.setOpenScore2(score);
            student.setOpenEvaluation2(evaluation);
            return studentMapper.updateByPrimaryKey(student);
        } else if (subject.getReviewTeacherId3() == teacherId) {
            student.setOpenScore3(score);
            student.setOpenEvaluation3(evaluation);
            return studentMapper.updateByPrimaryKey(student);
        }
        return -1;
    }

    public int updateStudentMiddleScore(long teacherId, long studentId, Long subjectId, Integer score, String evaluation) {
        Subject subject = subjectMapper.selectByPrimaryKey(subjectId);
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andIdEqualTo(studentId);
        Student student = studentMapper.selectByExample(studentExample).get(0);
        if (subject.getReviewTeacherId1() == teacherId) {
            student.setMiddleScore1(score);
            student.setMiddleEvaluation1(evaluation);
            return studentMapper.updateByPrimaryKey(student);
        } else if (subject.getReviewTeacherId2() == teacherId) {
            student.setMiddleScore2(score);
            student.setMiddleEvaluation2(evaluation);
            return studentMapper.updateByPrimaryKey(student);
        } else if (subject.getReviewTeacherId3() == teacherId) {
            student.setMiddleScore3(score);
            student.setMiddleEvaluation3(evaluation);
            return studentMapper.updateByPrimaryKey(student);
        }
        return -1;
    }

    public int updateStudentConclusionScore(long teacherId, long studentId, Long subjectId,Integer score, String evaluation){
        Subject subject = subjectMapper.selectByPrimaryKey(subjectId);
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andIdEqualTo(studentId);
        Student student = studentMapper.selectByExample(studentExample).get(0);
        if(subject.getReviewTeacherId1() == teacherId){
            student.setConclusionScore1(score);
            student.setConclusionEvaluation1(evaluation);
            return studentMapper.updateByPrimaryKey(student);
        }else if(subject.getReviewTeacherId2() == teacherId){
            student.setConclusionScore2(score);
            student.setConclusionEvaluation2(evaluation);
            return studentMapper.updateByPrimaryKey(student);
        }else if(subject.getReviewTeacherId3() == teacherId){
            student.setConclusionScore3(score);
            student.setConclusionEvaluation3(evaluation);
            return studentMapper.updateByPrimaryKey(student);
        }
        return -1;
    }

    public int updateStudentCrossPaperScore(long teacherId, long studentId, long subjectId,Integer score, String evaluation){
        Subject subject = subjectMapper.selectByPrimaryKey(subjectId);
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andIdEqualTo(studentId);
        Student student = studentMapper.selectByExample(studentExample).get(0);
        if(subject.getCreateTeacherId().equals(teacherId) && student.getSubjectId().equals(subjectId)){
            student = new Student();
            student.setCrossPaperScore(score);
            student.setCrossPaperEvaluation(evaluation);
            student.setId(studentId);

            return studentMapper.updateByPrimaryKeySelective(student);
        }
        return -1;
    }

    public int updateStudentTeacherPaperScore(long teacherId, long studentId, long subjectId,Integer score, String evaluation){
        Subject subject = subjectMapper.selectByPrimaryKey(subjectId);
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andIdEqualTo(studentId);
        Student student = studentMapper.selectByExample(studentExample).get(0);
        if(subject.getCreateTeacherId().equals(teacherId) && student.getSubjectId().equals(subjectId)){
            student = new Student();
            student.setTeacherPaperScore(score);
            student.setTeacherPaperEvaluation(evaluation);
            student.setId(studentId);

            return studentMapper.updateByPrimaryKeySelective(student);
        }
        return -1;
    }

    public void updateStudentOpenReport(long studentId, String path, String originalFilename) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andIdEqualTo(studentId);
        Student student = studentMapper.selectByExample(studentExample).get(0);
        student.setOpenDocument(path+originalFilename);
        studentMapper.updateByPrimaryKeySelective(student);
    }

    public void updateStudentMiddleReport(long studentId, String path, String originalFilename) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andIdEqualTo(studentId);
        Student student = studentMapper.selectByExample(studentExample).get(0);
        student.setMiddleDocument(path+originalFilename);
        studentMapper.updateByPrimaryKeySelective(student);
    }

    public void updateStudentConclusionReport(long studentId, String path, String originalFilename) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andIdEqualTo(studentId);
        Student student = studentMapper.selectByExample(studentExample).get(0);
        student.setConclusionDocument(path+originalFilename);
        studentMapper.updateByPrimaryKeySelective(student);
    }

    public void updatePaper(Long studentId, String path, String originalFilename) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andIdEqualTo(studentId);
        Student student = studentMapper.selectByExample(studentExample).get(0);
        student.setPaperDocument(path+originalFilename);
        studentMapper.updateByPrimaryKeySelective(student);
    }

    public Map<String,Object> simpleSelect(Long studentId){
        Map<String,Object> map=new HashMap<>();
        Student student=studentMapper.selectByPrimaryKey(studentId);
        Major major=majorMapper.selectByPrimaryKey(student.getMajorId());
        College college=collegeMapper.selectByPrimaryKey(major.getCollegeId());
        Subject subject=subjectMapper.selectByPrimaryKey(student.getSubjectId());
        map.put("name",student.getName());
        map.put("college",college.getName());
        map.put("major",major.getName());
        map.put("email",student.getEmail());
        map.put("telephone",student.getPhoneNumber());
        map.put("subject",(subject!=null)?subject.getName():null);
        return  map;
    }

    public Map<String,Object> toDoList(Long studentId){
        Map<String,Object>map=new HashMap<>();
        Student student=studentMapper.selectByPrimaryKey(studentId);
        map.put("emailexsit",(student.getEmail()!=null)?true:false);
        map.put("headexsit",(student.getHeadPortrait()!=null)?true:false);
        map.put("telephoneexsit",(student.getPhoneNumber()!=null)?true:false);

        Boolean subject=true;
        Boolean open=true;
        Boolean mid=true;
        Boolean con=true;
        Boolean paper=true;
        String state=student.getState();
        switch(state){
            case "NO_SELECTION":
                subject=false;
                break;
            case "NoOpenDoc":
                open=false;
                break;
            case "NoMidDoc":
                mid=false;
                break;
            case "NoPaperDoc":
                paper=false;
                break;
            case "NoConDoc":
                con=false;
                break;
            default:
                LOG.warn("未知的状态：{}",state);
        }

        map.put("subjectDoc",subject);
        map.put("openDoc",open);
        map.put("midDoc",mid);
        map.put("conDoc",con);
        map.put("paperDoc",paper);
        return map;
    }

    public Map<String,Object> getScore(Long studentId){
        Map<String,Object>map=new HashMap<>();
        Student student=studentMapper.selectByPrimaryKey(studentId);
        map.put("openSc1",student.getOpenScore1());
        map.put("openSc2",student.getOpenScore2());
        map.put("openSc3",student.getOpenScore3());
        map.put("midSc1",student.getMiddleScore1());
        map.put("midSc2",student.getMiddleScore2());
        map.put("midSc3",student.getMiddleScore3());
        map.put("paperSc",student.getCrossPaperScore());
        map.put("crossSc",student.getCrossPaperScore());
        map.put("conSc1",student.getConclusionScore1());
        map.put("conSc2",student.getConclusionScore2());
        map.put("conSc3",student.getConclusionScore3());
        map.put("finalSc",student.getFinalScore());
        return map;
    }

    public List<Subject> getSubject(Long studentId){
        Student student=studentMapper.selectByPrimaryKey(studentId);
        Long majorId=student.getMajorId();
        SubjectExample subjectExample=new SubjectExample();
        subjectExample.createCriteria()
                .andMajorIdEqualTo(majorId);
        subjectExample.setOrderByClause("id DESC");
        List<Subject> list=subjectMapper.selectByExample(subjectExample);
        List<Subject> recent=new ArrayList<>();
        int size=list.size();
        for(int i=0;i<5&&i<size;i++){
            recent.add(list.get(i));
        }
        return recent;
    }

    public void uploadNewUserImage(HttpServletRequest request, MultipartFile multipartFile) {
        long studentId = ((UserInfo) request.getSession(true).getAttribute("USER_INFO")).getId();
        String path = "userImage/student/" + studentId + '/';
        fileService.uploadUserImage(path, multipartFile);
    }
}
