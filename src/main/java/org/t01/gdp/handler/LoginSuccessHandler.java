package org.t01.gdp.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.t01.gdp.domain.UserInfo;
import org.t01.gdp.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

@Service
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String id = authentication.getName();
        UserInfo userInfo = userService.getUserInfo(authentication.getName());

        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("USER_INFO", userInfo);

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        String basePath = httpServletRequest.getScheme()+"://"+httpServletRequest.getServerName()+":"+httpServletRequest.getServerPort()+"/";
        if (roles.contains("ROLE_ADM")){
            httpServletResponse.sendRedirect(basePath+"administrator/home");
            return;
        }else if(roles.contains("ROLE_TEA")){
            httpServletResponse.sendRedirect(basePath+"teacher/home");
            return;
        }else if(roles.contains("ROLE_STU")){
            httpServletResponse.sendRedirect(basePath+"student/home");
            return;
        }else{
            httpServletResponse.sendRedirect(basePath+"error");
            return;
        }
    }
}
