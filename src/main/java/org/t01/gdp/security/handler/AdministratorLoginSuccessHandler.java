package org.t01.gdp.security.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.t01.gdp.domain.Administrator;
import org.t01.gdp.domain.AdministratorExample;
import org.t01.gdp.domain.UserInfo;
import org.t01.gdp.mapper.AdministratorMapper;
import org.t01.gdp.service.MyLogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service
public class AdministratorLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private AdministratorMapper administratorMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String adminId = authentication.getName();

        MyLogService.info("管理员用户[" + adminId +"]成功登录管理员平台");

        AdministratorExample administratorExample = new AdministratorExample();
        administratorExample.createCriteria().andAdminIdEqualTo(adminId);
        Administrator administrator = administratorMapper.selectByExample(administratorExample).get(0);
        UserInfo userInfo = new UserInfo(administrator.getId(),administrator.getAdminId(),administrator.getName(),administrator.getPhoneNumber(),administrator.getEmail(),administrator.getHeadPortrait(),administrator.getCreateTime());

        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("USER_INFO", userInfo);
        session.setAttribute("loginFailure",false);

        String basePath = httpServletRequest.getScheme()+"://"+httpServletRequest.getServerName()+":"+httpServletRequest.getServerPort()+"/";
        httpServletResponse.sendRedirect(basePath+"administrator/mainPage");
    }
}