package org.t01.gdp.service;

import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;

@Service
public class FileService {
    private static final Logger LOG = LoggerFactory.getLogger(FileService.class);
    private final String fileDir = System.getProperty("user.dir") + "\\src\\main\\resources\\file\\";
    private final String imageDir = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\";

    public void deleteFile(String subPath){
        try {
            String filePath = fileDir + URLDecoder.decode(subPath==null?"":subPath,"UTF-8");
            File file = new File(filePath);
            if(file.exists() && file.isFile()){
                if(!file.delete()){
                    LOG.warn("文件\"{}\"未成功删除",filePath);
                }
            }
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage());
        }
    }

    public int uploadFile(MultipartFile file, String subPath){
        if (file.isEmpty()) {
            return 1;
        }

        String fileName = file.getOriginalFilename();
        String filePath = fileDir + subPath + fileName;
        File dest = new File(filePath);

        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        if(!dest.exists()){
            try {
                if(!dest.createNewFile()){
                    LOG.warn("文件\"{}\"创建失败",filePath);
                }
            } catch (IOException e) {
                LOG.error(e.getMessage());
                return 2;
            }
        }

        try {
            file.transferTo(dest);
            return 0;
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return 3;
    }

    public boolean downloadFile(HttpServletRequest request, HttpServletResponse response){
        String requestURI = request.getRequestURI();

        String uri = requestURI.substring(requestURI.indexOf("/download") +"/download".length());

        String filePath = null;
        try {
            filePath = fileDir + URLDecoder.decode(uri,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage());
            return false;
        }

        System.out.println(filePath);

        File file = new File(filePath);

        if(file.exists()){

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

            } catch (IOException e) {
                LOG.error(e.getMessage());
            }finally {
                if(bis != null){
                    try{
                        bis.close();
                    } catch (IOException e) {
                        LOG.error(e.getMessage());
                    }
                }
                if(fis != null){
                    try{
                        fis.close();
                    } catch (IOException e) {
                        LOG.error(e.getMessage());
                    }
                }
            }
        }

        return false;
    }

    public boolean imageExit(String path) {
        String filePath = imageDir + path;
        File file = new File(filePath);
        return file.exists();
    }

    public boolean uploadUserImage(String subPath, MultipartFile multipartFile){
        File file = new File( imageDir+ subPath + multipartFile.getOriginalFilename());
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        if(!file.exists()){
            try {
                if(!file.createNewFile()){
                    LOG.warn("文件\"{}\"未创建成功",subPath + multipartFile.getOriginalFilename());
                    return false;
                }
            } catch (IOException e) {
                LOG.error(e.getMessage());
                return false;
            }
        }
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            LOG.error(e.getMessage());
            return false;
        }
        try {
            Thumbnails.of(file).size(160, 160).keepAspectRatio(false).toFile(file);
        } catch (IOException e) {
            LOG.error(e.getMessage());
            return false;
        }
        File fileRename = new File(imageDir + subPath + "userimage.jpg");
        if(fileRename.exists()){
            if(!fileRename.delete()){
                LOG.warn("文件\"{}userimage.jpg\"未删除成功",subPath);
                return false;
            }
        }
        if(!file.renameTo(fileRename)){
            LOG.warn("文件\"{}userimage.jpg\"未重命名成功",subPath);
            return false;
        }
        return true;
    }
}
