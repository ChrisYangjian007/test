package com.dalian.sea.controller;

import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by Administrator on 2017-10-23.
 */
public class LayoutRazor extends BaseController {
    String ctrlName = "";
    @Autowired
    org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer _fmc;
    static String _templateLoaderPath = null;

    /**
     * 初始化 freemarker 模板地址
     */
    boolean initTemplateLoaderPath() {
        if (_templateLoaderPath != null) {
            return true;
        }
//        gsPrintln("_fmc = {0}", _fmc == null ? "null" : _fmc.toString());
        //布局模板是否存在
        if (_fmc != null) {
            MultiTemplateLoader utll = new MultiTemplateLoader(new TemplateLoader[] { _fmc.getConfiguration().getTemplateLoader()});
            int n = utll.getTemplateLoaderCount();
//            gsPrintln("TemplateLoaderCount=" + n);
            TemplateLoader tl = null;
            File file = null;
            for (int i = 0; i < n; i++) {
                tl = utll.getTemplateLoader(i);
                if (tl instanceof MultiTemplateLoader ) {
                    MultiTemplateLoader t2 = ((MultiTemplateLoader) tl);
                    if (t2.getTemplateLoaderCount() > 0) tl = t2.getTemplateLoader(0);
                }
//                gsPrintln(tl.toString());
                //获取模板加载目录地址
                if (tl instanceof freemarker.cache.FileTemplateLoader) {
                    _templateLoaderPath = ((freemarker.cache.FileTemplateLoader) tl).getBaseDirectory().getAbsolutePath();
                }
            }
        }
//        gsPrintln("freemark 模板地址：{0}", _templateLoaderPath);
        return _templateLoaderPath != null;
    }

    /**
     * 判断模板是否存在
     * @param filePath
     */
    boolean IsExistsTemplate(String filePath) {
        initTemplateLoaderPath();
        if (_templateLoaderPath==null) {
            return  true;
        }
        else {
            File file = new File(gsFormat("{0}/{1}", _templateLoaderPath, filePath));
            boolean exist = file.exists();
            return exist;
        }
    }

    /**
     * 公共视图解析器：
     * @param prefix
     * @param request
     */
    @RequestMapping("{prefix}")
    public String indexViewer(@PathVariable String prefix,String title, HttpServletRequest request) {
        request.setAttribute("title_name",title);
        return freeMarkerIndexResult(prefix, request);//布局模板
    }

//    /**
//     * 公共视图解析器：
//     * @param prefix
//     * @param request
//     */
//    @RequestMapping("{prefix}")
//    public String formViewer(@PathVariable String prefix,String title, HttpServletRequest request) {
//        request.setAttribute("title_name",title);
//        return freeMarkerFormResult(prefix, request);//布局模板
//    }

    /**
     * freemarker 布局视图解析
     * @param prefix
     * @param request
     */
    public String freeMarkerIndexResult(String prefix, HttpServletRequest request) {
        //布局模板
        String layout = gsFormat("{0}/Shared/LayoutIndex.ftl", ctrlName, prefix);
        //页模板
        String body = gsFormat("{0}/{1}", ctrlName, prefix);
        //页面参数
        request.setAttribute("body_file_path", body);
        //优先使用布局页
        if (IsExistsTemplate(layout)) {
            return layout.replace(".ftl",""); //模板不需要后缀
        }
        return body.replace(".ftl","");//模板不需要后缀
    }

    /**
     * freemarker 布局视图解析
     * @param prefix
     * @param request
     */
    public String freeMarkerFormResult(String prefix, HttpServletRequest request) {
        //布局模板
        String layout = gsFormat("{0}/Shared/LayoutForm.ftl", ctrlName, prefix);
        //页模板
        String body = gsFormat("{0}/{1}.ftl", ctrlName, prefix);
        //页面参数
        request.setAttribute("body_file_path", body);
        //优先使用布局页
        if (IsExistsTemplate(layout)) {
            return layout.replaceAll("\\.ftl$", ""); //模板不需要后缀
        }
        return body.replaceAll("\\.ftl$", "");//模板不需要后缀
    }

    public static void gsPrintln(String msg, Object... args) {
        System.out.println(gsFormat(msg, args));
    }

    /**
     * 字符串内容格式化输出，内部使用{0}\{1}\{2}...为参数占位符
     * @param msg  格式化模板
     * @param args 不固定参数
     */
    public static String gsFormat(String msg, Object... args) {
        return java.text.MessageFormat.format(msg, args);
    }
}
