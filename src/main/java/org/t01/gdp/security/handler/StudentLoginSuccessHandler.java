package org.t01.gdp.security.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.t01.gdp.domain.Student;
import org.t01.gdp.domain.StudentExample;
import org.t01.gdp.domain.UserInfo;
import org.t01.gdp.mapper.StudentMapper;
import org.t01.gdp.service.MyLogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service
public class StudentLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String studentId = authentication.getName();

        MyLogService.info("学生用户[" + studentId +"]成功登录学生平台");

        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andStudentIdEqualTo(studentId);
        Student student = studentMapper.selectByExample(studentExample).get(0);
        UserInfo userInfo = new UserInfo(student.getId(),student.getStudentId(),student.getName(),student.getPhoneNumber(),student.getEmail(),student.getHeadPortrait(),student.getCreateTime());

        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("USER_INFO", userInfo);
        session.setAttribute("loginFailure",false);

        String basePath = httpServletRequest.getScheme()+"://"+httpServletRequest.getServerName()+":"+httpServletRequest.getServerPort()+"/";
        httpServletResponse.sendRedirect(basePath+"student/mainPage");
    }
}
