package org.t01.gdp.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;

@Service
public class DownloadService {
    public boolean downloadFile(HttpServletRequest request, HttpServletResponse response){
        String requestURI = request.getRequestURI();

        String uri = requestURI.substring(requestURI.indexOf("/download")+"/download".length()).replace('/','\\');

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
}
