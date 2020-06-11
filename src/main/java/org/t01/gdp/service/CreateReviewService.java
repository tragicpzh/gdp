package org.t01.gdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.t01.gdp.domain.Subject;
import org.t01.gdp.domain.Teacher;
import org.t01.gdp.domain.TeacherExample;
import org.t01.gdp.mapper.SubjectMapper;
import org.t01.gdp.mapper.TeacherMapper;

import java.util.*;

@Service
public class CreateReviewService {
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    SubjectMapper subjectMapper;

    public void create_review(List<String> list) {
        for (String subject_id : list) {
            Subject subject=subjectMapper.selectByPrimaryKey(Long.valueOf(subject_id));       //获取课题信息
            Teacher teacher=teacherMapper.selectByPrimaryKey(subject.getCreateTeacherId());   //获得出题人信息
            String college_id=teacher.getCollegeId();                                         //获得大学id
            TeacherExample example=new TeacherExample();
            example.createCriteria()
                    .andCollegeIdEqualTo(college_id)
                    .andDirectionEqualTo(subject.getDirection())
                    .andIdNotEqualTo(subject.getCreateTeacherId());                           //根据大学和方向进行查询
            List<Teacher> teachers=teacherMapper.selectByExample(example);
            int size=teachers.size();

            if(size<3){
                TeacherExample example1=new TeacherExample();
                example.createCriteria()
                        .andCollegeIdEqualTo(college_id)
                        .andIdNotEqualTo(subject.getCreateTeacherId());                       //根据大学进行查询
                List<Teacher> teachers1=teacherMapper.selectByExample(example1);
                Set<String> set=new HashSet<>();
                Random r=new Random();
                int size2=teachers1.size();

                if(size==1){                                                                   //满足方向的老师优先选择
                    set.add(teachers.get(0).getId());
                }
                else if(size==2){
                    set.add(teachers.get(0).getId());
                    set.add(teachers.get(1).getId());
                }

                while(set.size()<3){                                                            //剩余的老师从同学校选出
                    set.add(teachers1.get(r.nextInt(size2)).getId());
                }

                Iterator<String> iterator = set.iterator();
                subject.setReviewTeacherId1(iterator.next());
                subject.setReviewTeacherId2(iterator.next());
                subject.setReviewTeacherId3(iterator.next());
            }
            else {
                Set<Integer> set=new HashSet<>();
                Random r=new Random();

                while(set.size()<3){                                                            //从满足方向的老师中随机选择
                    set.add(r.nextInt(size));
                }

                Iterator<Integer> iterator = set.iterator();
                subject.setReviewTeacherId1(teachers.get(iterator.next()).getId());
                subject.setReviewTeacherId2(teachers.get(iterator.next()).getId());
                subject.setReviewTeacherId3(teachers.get(iterator.next()).getId());
            }
            subjectMapper.updateByPrimaryKeySelective(subject);                                 //更新课题信息
        }
    }
}
