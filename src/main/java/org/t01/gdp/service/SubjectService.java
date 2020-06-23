package org.t01.gdp.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.t01.gdp.domain.*;
import org.t01.gdp.mapper.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {
    @Autowired
    SubjectMapper subjectMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    MajorMapper majorMapper;

    @Autowired
    SqlMapper sqlMapper;

    @Autowired
    ReviewMapper reviewMapper;

    @Autowired
    SubjectForTeacher subjectForTeacher;

    @Autowired
    StudentAndSubjectMapper studentAndSubjectMapper;

    @Autowired
    FileService fileService;

    public PageInfo searchSubjects(SubjectSearchCase subjectSearchCase,int pageNo, int pageSize){

        SubjectExample subjectExample = new SubjectExample();
        SubjectExample.Criteria criteria = subjectExample.createCriteria();
        if(subjectSearchCase.getId()!=null){
            criteria.andIdEqualTo(subjectSearchCase.getId());
        }
        if(subjectSearchCase.getName()!=null){
            criteria.andNameLike(subjectSearchCase.getName());
        }
        if(subjectSearchCase.getDifficulty()!=null){
            criteria.andDifficultyEqualTo(subjectSearchCase.getDifficulty());
        }
        if(subjectSearchCase.getMajorName()!=null){
            MajorExample majorExample = new MajorExample();
            majorExample.createCriteria().andNameEqualTo(subjectSearchCase.getMajorName());
            List<Major> majors = majorMapper.selectByExample(majorExample);
            if(!majors.isEmpty()){
                criteria.andMajorIdEqualTo(majors.get(0).getId());
            }else{
                return new PageInfo(new ArrayList<>());
            }
        }
        if(subjectSearchCase.getDirection()!=null){
            criteria.andDirectionEqualTo(subjectSearchCase.getDirection());
        }
        if(subjectSearchCase.getState()!=null){
            criteria.andStateIn(subjectSearchCase.getState());
        }
        if(subjectSearchCase.getCreateTeacherId()!=null){
            criteria.andCreateTeacherIdEqualTo(subjectSearchCase.getCreateTeacherId());
        }
        if(subjectSearchCase.getOrderByClause()!=null){
            subjectExample.setOrderByClause(subjectSearchCase.getOrderByClause());
        }
        if(subjectSearchCase.getMajorId()!=null){
            criteria.andMajorIdEqualTo(subjectSearchCase.getMajorId());
        }

        PageHelper.startPage(pageNo,pageSize);

        List<Subject> subjects = subjectMapper.selectByExample(subjectExample);
        List<Subject> result = new ArrayList<>();
        for(Subject subject:subjects){
            result.add(subject);
        }

        PageInfo<Subject> subjectPageInfo = new PageInfo<>(result);
        subjectPageInfo.setTotal(((Page)subjects).getTotal());
        return subjectPageInfo;
    }

    public PageInfo<StudentAndSubject> searchReview(ReviewSearchCase reviewSearchCase,int pageNo, int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        List<StudentAndSubject> studentAndSubjects;

        if(reviewSearchCase.getPaperReviewTeacherId()!=null){
            studentAndSubjects = studentAndSubjectMapper.selectByPaperReviewTeacherId(reviewSearchCase.getPaperReviewTeacherId());
        }else if(reviewSearchCase.getCreateTeacherId()!=null){
            studentAndSubjects = studentAndSubjectMapper.selectByCreateTeacherId(reviewSearchCase.getCreateTeacherId());
        }else if(reviewSearchCase.getCrossReviewTeacherId()!=null){
            studentAndSubjects = studentAndSubjectMapper.selectByCrossReviewTeacherId(reviewSearchCase.getCrossReviewTeacherId());
        }else if(reviewSearchCase.getOpenReviewTeacherId()!=null){
            studentAndSubjects = studentAndSubjectMapper.selectByOpenReviewTeacherId(reviewSearchCase.getOpenReviewTeacherId());
        }else if(reviewSearchCase.getMiddleReviewTeacherId()!=null){
            studentAndSubjects = studentAndSubjectMapper.selectByMiddleReviewTeacherId(reviewSearchCase.getMiddleReviewTeacherId());
        }else if(reviewSearchCase.getConclusionReviewTeacherId()!=null){
            studentAndSubjects = studentAndSubjectMapper.selectByConclusionReviewTeacherId(reviewSearchCase.getConclusionReviewTeacherId());
        }else{
            return new PageInfo<>(new ArrayList<>());
        }

        List<StudentAndSubject> result = new ArrayList<>();
        for(StudentAndSubject studentAndSubject:studentAndSubjects){
            result.add(studentAndSubject);
        }

        PageInfo studentAndSubjectPageInfo = new PageInfo(result);
        studentAndSubjectPageInfo.setTotal(((Page)studentAndSubjects).getTotal());
        return studentAndSubjectPageInfo;
    }

    public void updateById(Subject subject, String examineFlag) {
            subject.setState(examineFlag);
            subjectMapper.updateByPrimaryKeySelective(subject);
    }

    public int updateWithSubject(Subject subject){
        return subjectMapper.updateByPrimaryKey(subject);
    }

    public int updateSubjectSelective(Subject subject, long teacherId){
        if(subjectMapper.selectByPrimaryKey(subject.getId()).getCreateTeacherId().equals(teacherId)){
            return subjectMapper.updateByPrimaryKeySelective(subject);
        }
        return -1;
    }

    public boolean chooseSubjectByStudent(Long studentId, String subjectId){
        Student student=new Student();
        student.setSubjectId(Long.valueOf(subjectId));
        student.setId(studentId);
        studentMapper.updateByPrimaryKeySelective(student);
        return true;
    }

    public PageInfo<SubjectBrief> getSubjectsByTeacherId(int pageNo, int pageSize, String id) {
        PageHelper.startPage(pageNo, pageSize);
        return new PageInfo<>(subjectForTeacher.selectByTeacherId(id));
    }

    public Subject getSubjectById(long id){
        return subjectMapper.selectByPrimaryKey(id);
    }

    public int subjectExamination(String state, long subjectId){
        Subject subject = new Subject();
        subject.setId(subjectId);
        subject.setState(state);

        return subjectMapper.updateByPrimaryKeySelective(subject);
    }

    public int deleteSubject(long subjectId){
        Subject subject = subjectMapper.selectByPrimaryKey(subjectId);
        if (subject.getDocument() != null && subject.getDocument() != "") {
            fileService.deleteFile(subject.getDocument());
        }
        return subjectMapper.deleteByPrimaryKey(subjectId);
    }

    public SubjectInfo selectSubjectByStudent(String subjectId){
        SubjectInfo subjectInfo;
        subjectInfo=sqlMapper.selectSubjectByPrimaryKey(Long.valueOf(subjectId));
        return subjectInfo;
    }

    public PageInfo<SubjectInfo> listSubjectByCondition(int pageNo,
                                                      int pageSize,
                                                      String subject_name,
                                                      String subject_teacher,
                                                      String subject_major,
                                                      String subject_ID,
                                                      String subject_direction,
                                                      String difficult_mn,
                                                      String difficult_mx,
                                                      String state,
                                                      String sort,
                                                      String type) {
        Integer difficult_min=(difficult_mn!=""?new Integer(difficult_mn):null);
        Integer difficult_max=(difficult_mx!=""?new Integer(difficult_mx):null);
        Long subject_id=(subject_ID!=""?new Long(subject_ID):null);
        PageHelper.startPage(pageNo,pageSize);
        List<SubjectInfo> list=sqlMapper.selectBycondition(
                subject_name,
                subject_teacher,
                subject_major,
                subject_id,
                subject_direction,
                difficult_min,
                difficult_max,
                state,
                sort,
                type);

        return new PageInfo<>(list);
    }
}
