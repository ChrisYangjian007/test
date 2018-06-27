package com.dalian.sea.config.freemarker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author 杨文波
 * @date 2018/1/3
 */
@Slf4j
public class MyFreemarkerView extends FreeMarkerView{
    @Override
    public void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
        InputStream is = this.getClass().getResourceAsStream("/static.properties");
        Properties properties = new Properties();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            properties.load(br);
            model.put("staticCss", properties.getProperty("static.css"));
            model.put("staticImg", properties.getProperty("static.images"));
            model.put("staticJs", properties.getProperty("static.js"));
        }catch (FileNotFoundException e){
            log.error(e.getMessage()+"----------未找到该文件");
        }catch (IOException e){
            log.error(e.getMessage()+"----------读取文件异常");
        }finally {
            is.close();
        }
        super.exposeHelpers(model, request);
    }
}
