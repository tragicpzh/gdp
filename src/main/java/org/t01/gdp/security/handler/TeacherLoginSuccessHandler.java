package org.t01.gdp.security.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.t01.gdp.domain.*;
import org.t01.gdp.mapper.TeacherMapper;
import org.t01.gdp.service.MyLogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service
public class TeacherLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String teacherId = authentication.getName();

        MyLogService.info("教师用户[" + teacherId +"]成功登录管理员平台");

        TeacherExample teacherExample = new TeacherExample();
        teacherExample.createCriteria().andTeacherIdEqualTo(teacherId);
        Teacher teacher = teacherMapper.selectByExample(teacherExample).get(0);
        UserInfo userInfo = new UserInfo(teacher.getId(),teacher.getTeacherId(),teacher.getName(),teacher.getPhoneNumber(),teacher.getEmail(),teacher.getHeadPortrait(),teacher.getCreateTime());

        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("USER_INFO", userInfo);

        String basePath = httpServletRequest.getScheme()+"://"+httpServletRequest.getServerName()+":"+httpServletRequest.getServerPort()+"/";
        httpServletResponse.sendRedirect(basePath+"teacher/mainPage");
    }
}