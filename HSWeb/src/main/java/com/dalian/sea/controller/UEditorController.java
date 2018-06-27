package com.dalian.sea.controller;


import com.dalian.sea.FileUpload.OSSClientUtil;
import com.dalian.sea.ueditor.ActionEnter;
import com.sun.mail.imap.IMAPInputStream;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * UEditorController
 *
 * @author xintao
 * @date 2018/1/17
 */
@Controller
@RequestMapping(value = "/ueditor")
public class UEditorController {

    @Autowired
    private OSSClientUtil ossClientUtil;

    @RequestMapping(value = "/config.htm")
    public void config(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json");
        String rootPath = request.getSession()
                .getServletContext().getRealPath("/");

        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 多文件上传支持
     *
     * @param upfile
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "uploadfile.json")
    public Map uploadFile(@RequestParam(value = "upfile", required = false) MultipartFile[] upfile) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        if (upfile != null && upfile.length > 0) {
            //循环获取file数组中得文件
            for (int i = 0; i < upfile.length; i++) {

                String name = ossClientUtil.uploadImg2Oss(upfile[i]);
                try {
                    map.put("url", ossClientUtil.getImgUrl(name));
                    map.put("state", "SUCCESS");
                } catch (Exception e) {
                    e.printStackTrace();
                    map.put("state", "上传失败!");
                }
            }
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "uploadScrawl.json")
    public Map uploadScrawl(HttpServletRequest request,String upfile) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        InputStream inputStream = null;
        OutputStream  outputStream = null;
        File file = null;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] buffer;
        try {
            //Base64解码
            buffer = upfile.getBytes("UTF-8");
            buffer = Base64.decodeBase64(buffer);
            String classPath = request.getSession().getServletContext().getRealPath("");
            StringBuilder sb = new StringBuilder();
            sb.append(classPath);
            sb.append(".jpg");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(".jpg");
            String path = sb.toString();
            file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            String url = ossClientUtil.uploadFile2OSS(fis,stringBuilder.toString());
            map.put("url", url);
            map.put("name", stringBuilder.toString());
            map.put("state", "SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            map.put("state", "上传失败!");
        }finally {
            outputStream.close();
            inputStream.close();
            file.delete();
        }
        return map;
    }
}
