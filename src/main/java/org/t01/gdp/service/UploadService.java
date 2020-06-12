package org.t01.gdp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class UploadService {
    public int uploadFile(MultipartFile file, String subPath){
        if (file.isEmpty()) {
            return 1;
        }

        String fileName = file.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\file\\" + subPath + fileName;
        File dest = new File(filePath);

        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        if(!dest.exists()){
            try {
                dest.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return 2;
            }
        }

        try {
            file.transferTo(dest);
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 3;
    }
}
