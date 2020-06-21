package org.t01.gdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.t01.gdp.domain.UserInfo;
import org.t01.gdp.mapper.AdministratorMapper;

import javax.servlet.http.HttpServletRequest;

@Service
public class AdministratorService {
    @Autowired
    AdministratorMapper administratorMapper;
    @Autowired
    FileService fileService;

    public void getUserInfo(long id){
        administratorMapper.selectByPrimaryKey(id);
    }

    public void uploadNewUserImage(HttpServletRequest request, MultipartFile multipartFile) {
        long administratorId = ((UserInfo) request.getSession(true).getAttribute("USER_INFO")).getId();
        String path = "userImage/administrator/" + administratorId + "/";
        fileService.uploadUserImage(path, multipartFile);
    }
}
