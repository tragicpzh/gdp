package org.t01.gdp.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Service
public class DownloadService {
    public String downloadFile(HttpServletRequest request, HttpServletResponse response){
        String requestURI = request.getRequestURI();

        String uri = requestURI.substring(requestURI.indexOf("/download")+"/download".length()).replace('/','\\');
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\file" + uri;

        System.out.println(filePath);

        File file = new File(filePath);

        if(file.exists()){
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + file.getName());

            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;

            try{
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();

                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer,0,i);
                    i = bis.read(buffer);
                }

                System.out.println("success");

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

        return null;
    }
}
