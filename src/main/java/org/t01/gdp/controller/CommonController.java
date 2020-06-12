package org.t01.gdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.t01.gdp.service.DownloadService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CommonController {
    @Autowired
    DownloadService downloadService;

    @GetMapping("/download/**")
    @ResponseBody
    public boolean getDownload(HttpServletRequest request, HttpServletResponse response){
        return downloadService.downloadFile(request, response);
    }
}