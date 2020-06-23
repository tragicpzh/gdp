package org.t01.gdp.security.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;
import org.t01.gdp.service.MyLogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class TeacherLoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        MyLogService.warn("设备[" + request.getRemoteAddr() + "] 尝试登录教师平台");
        request.getSession(true).setAttribute("loginFailure",true);
        response.sendRedirect("/teacher/login");
    }
}