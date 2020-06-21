package org.t01.gdp.service;

import com.github.pagehelper.PageInfo;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.t01.gdp.domain.*;
import org.t01.gdp.mapper.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class TeacherService {
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    SubjectMapper subjectMapper;
    @Autowired
    MajorMapper majorMapper;
    @Autowired
    SubjectService subjectService;

    public Teacher selectTeacherById(long teacherId){
        return teacherMapper.selectByPrimaryKey(teacherId);
    }

    public int addSubject(Subject subject)
    {
        return subjectMapper.insert(subject);
    }

    public String create_review(Long college_id,int start,int end,List<CrossreviewInfo> list){
        String msg="";
        Random random=new Random();
        TeacherExample teacherExample=new TeacherExample();
        teacherExample.createCriteria()
                .andCollegeIdEqualTo(college_id);
        List<Teacher> teachers=teacherMapper.selectByExample(teacherExample);
        for(int i=start;i<=end;i++) {
            int cnt = 0;
            while (cnt >= 0) {
                cnt++;
                if (cnt > 20) {
                    msg += "无法为" + String.valueOf(teachers.get(i).getId()) + "号课题分配交叉评阅老师";
                    break;
                }
                int cas = random.nextInt(teachers.size());
                if (teachers.get(cas).getId().equals(list.get(i).getCreateTeacherId())) continue;
                else {
                    list.get(i).setCrossReviewTeacher(Long.valueOf(cas));
                    break;
                }
            }
        }
        return msg;
    }//分配中的子方法

    public String cross_review_create(List<Subject> subjects){
        if(subjects.size()<=1)return "课题数量太少";

        List<CrossreviewInfo> list=new ArrayList<CrossreviewInfo>();        //创建CrossreviewInfo数组，为了加入学院id属性。
        for (Subject subject : subjects) {
            MajorExample majorExample=new MajorExample();
            majorExample.createCriteria()
                    .andIdEqualTo(subject.getMajorId());
            List<Major> majors=majorMapper.selectByExample(majorExample);
            Long college_id=(majors.size()>0?majors.get(0).getCollegeId():null);        //查询获得学院id

            CrossreviewInfo temp=new CrossreviewInfo();
            temp.setId(subject.getId());
            temp.setCreateTeacherId(subject.getCreateTeacherId());
            temp.setCrossReviewTeacher(null);
            temp.setCollegeId(college_id);
            list.add(temp);
        }
        Collections.sort(list, new Comparator<CrossreviewInfo>() {
            @Override
            public int compare(CrossreviewInfo o1, CrossreviewInfo o2) {
                return o1.getCollegeId().intValue()-o2.getCollegeId().intValue();
            }
        });     //根据学院ID排序

        int start_id=-1;
        int end_id=-1;
        String msg="";

        for (int i=0;i<list.size();i++){
            CrossreviewInfo crossreviewInfo=list.get(i);
            if(i>0){
                CrossreviewInfo crossreviewInfo1=list.get(i-1);
                if(crossreviewInfo.getCollegeId().equals(crossreviewInfo1.getCollegeId())){
                    continue;
                }
                else {
                    end_id=i-1;
                    Long college_id=crossreviewInfo1.getCollegeId();
                    msg+=create_review(college_id,start_id,end_id,list);
                    start_id=i;
                }
            }
           else {
                start_id=0;
            }
        }                                 //自动分配交叉评审老师，满足同学院非开题老师

        end_id=list.size()-1;                                               //为最后一组学院的课题分配交叉评审老师
        Long college_id=list.get(start_id).getCollegeId();
        msg+=create_review(college_id,start_id,end_id,list);

        for (CrossreviewInfo crossreviewInfo : list) {
            Subject subject=new Subject();
            subject.setCrossReviewTeacher(crossreviewInfo.getCrossReviewTeacher());
            SubjectExample subjectExample=new SubjectExample();
            subjectExample.createCriteria()
                    .andIdEqualTo(crossreviewInfo.getId());
            subjectMapper.updateByExampleSelective(subject,subjectExample);
        }                   //更新subject表中的数据
       return msg;
    }//自动分配交叉评阅老师

    public PageInfo<StudentAndSubject> cross_review_select(ReviewSearchCase reviewSearchCase,int pageNo,int pageSize,Long teacher_id){
        return subjectService.searchReview(reviewSearchCase,pageNo,pageSize);
    }//查看交叉评阅列表

    public void uploadNewUserImage(HttpServletRequest request, MultipartFile multipartFile) {
        long teacherId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        String path = "userImage/teacher/" + teacherId + "/";
        File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\" + path + multipartFile.getOriginalFilename());
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thumbnails.of(file).size(160, 160).keepAspectRatio(false).toFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File fileRename = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\" + path + "userimage.jpg");
        if(fileRename.exists()){
            fileRename.delete();
        }
        file.renameTo(fileRename);
    }
}
