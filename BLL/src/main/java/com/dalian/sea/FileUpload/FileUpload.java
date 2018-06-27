package com.dalian.sea.FileUpload;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * FileUpload
 *
 * @author xintao
 * @date 2017/12/5
 */
public class FileUpload {

    private static final String IMAGES_SERVER_URL = "/lduwa/upload/images";
    private static final String VIDEOS_SERVER_URL = "/lduwa/upload/videos";


    public static String uPloadImage(MultipartFile file) throws Exception{
        String fileName=file.getOriginalFilename();
        File newFile = new File(IMAGES_SERVER_URL);
        if (!newFile.isDirectory()) {
            newFile.mkdirs();
        }
        File resultFile = new File(IMAGES_SERVER_URL+"/"+fileName);
        resultFile.createNewFile();
        resultFile.setReadable(true);
        resultFile.setWritable(true);
        file.transferTo(resultFile);
        return IMAGES_SERVER_URL+"/"+fileName;
    }

    public static String uPloadVideo(MultipartFile file) throws Exception{
        String fileName=file.getOriginalFilename();
        File newFile = new File(VIDEOS_SERVER_URL);
        if (!newFile.isDirectory()) {
            newFile.mkdirs();
        }
        File resultFile = new File(VIDEOS_SERVER_URL+"/"+fileName);
        resultFile.createNewFile();
        resultFile.setReadable(true);
        resultFile.setWritable(true);
        file.transferTo(resultFile);
        return VIDEOS_SERVER_URL+"/"+fileName;
    }




}
