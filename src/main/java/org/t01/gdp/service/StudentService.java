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
import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class StudentService {
    private static final Logger LOG = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private SubjectMapper subjectMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SqlMapper sqlMapper;
    @Autowired
    private MajorMapper majorMapper;
    @Autowired
    private CollegeMapper collegeMapper;
    @Autowired
    private FileService fileService;

    public void updateFinalScore(){
        StudentExample studentExample = new StudentExample();
        List<Student> students = studentMapper.selectByExample(studentExample);

        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()){
            Student studentFinalScore = new Student();
            Student next = iterator.next();

            if(next.getOpenScore1() == null){
                next.setOpenScore1(0);
            }
            if(next.getOpenScore2() == null){
                next.setOpenScore2(0);
            }
            if(next.getOpenScore3() == null){
                next.setOpenScore3(0);
            }
            if(next.getMiddleScore1() == null){
                next.setMiddleScore1(0);
            }
            if(next.getMiddleScore2() == null){
                next.setMiddleScore2(0);
            }
            if(next.getMiddleScore3() == null){
                next.setMiddleScore3(0);
            }
            if(next.getConclusionScore1() == null){
                next.setConclusionScore1(0);
            }
            if(next.getConclusionScore2() == null){
                next.setConclusionScore2(0);
            }
            if(next.getConclusionScore3() == null){
                next.setConclusionScore3(0);
            }
            if(next.getTeacherPaperScore() == null){
                next.setTeacherPaperScore(0);
            }
            if(next.getCrossPaperScore() == null){
                next.setCrossPaperScore(0);
            }

            studentFinalScore.setId(next.getId());

            float finalScore = ((float)next.getOpenScore1() + next.getOpenScore2() + next.getOpenScore3()) / 15
                    + ((float)next.getMiddleScore1() + next.getMiddleScore2() + next.getMiddleScore3()) / 15
                    + ((float)next.getConclusionScore1() + next.getConclusionScore2() + next.getConclusionScore3()) / 15
                    + (float)next.getTeacherPaperScore() / 5
                    + (float)next.getCrossPaperScore() / 5;
            studentFinalScore.setFinalScore(BigDecimal.valueOf(finalScore));

            studentMapper.updateByPrimaryKeySelective(studentFinalScore);
        }
    }

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
        List<Subject> list=sqlMapper.selectfiveById(majorId);
        return list;
    }

    public void uploadNewUserImage(HttpServletRequest request, MultipartFile multipartFile) {
        long studentId = ((UserInfo) request.getSession(true).getAttribute("USER_INFO")).getId();
        String path = "userImage/student/" + studentId + '/';
        fileService.uploadUserImage(path, multipartFile);
    }

    public List<String> getMajorlist(Long student_id){
        MajorService majorService=new MajorService();
        Student student=studentMapper.selectByPrimaryKey(student_id);
        Major major=majorMapper.selectByPrimaryKey(student.getMajorId());
        MajorExample majorExample = new MajorExample();
        majorExample.createCriteria().andCollegeIdEqualTo(major.getCollegeId());
        List<Major> majors=majorMapper.selectByExample(majorExample);
        List<String> list=new ArrayList<String>();
        for (Major major1 : majors) {
            list.add(major1.getName());
        }
        return list;
    }
}
