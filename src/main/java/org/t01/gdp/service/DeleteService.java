package org.t01.gdp.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Service
public class DeleteService {
    public void deleteFile(String subPath){
        try {
            String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\file\\" + URLDecoder.decode(subPath,"UTF-8");
            File file = new File(filePath);
            if(file.exists() && file.isFile()){
                file.delete();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
