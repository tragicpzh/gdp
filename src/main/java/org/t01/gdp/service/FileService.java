package org.t01.gdp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;

@Service
public class FileService {

    public void deleteFile(String subPath){
        try {
            String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\file\\" + URLDecoder.decode(subPath==null?"":subPath,"UTF-8");
            File file = new File(filePath);
            if(file.exists() && file.isFile()){
                file.delete();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

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

    public boolean downloadFile(HttpServletRequest request, HttpServletResponse response){
        String requestURI = request.getRequestURI();

        String uri = requestURI.substring(requestURI.indexOf("/download")+"/download".length());

        String filePath = null;
        try {
            filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\file" + URLDecoder.decode(uri,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }

        System.out.println(filePath);

        File file = new File(filePath);

        if(file.exists()){
//            response.setContentType("application/force-download");

            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;

            try{
                response.addHeader("Content-Disposition", "attachment;fileName=" + new String(file.getName().getBytes("utf-8"),"ISO8859-1"));
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();

                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer,0,i);
                    i = bis.read(buffer);
                }

                return true;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(bis != null){
                    try{
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(fis != null){
                    try{
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return false;
    }

    public boolean fileExit(String path) {
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\" + path;
        File file = new File(filePath);
        return file.exists();
    }
}
