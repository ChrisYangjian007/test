package com.dalian.sea.controller;

import Utils.GenerateUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalian.sea.ClientIP;
import com.dalian.sea.config.shiro.ShiroUser;
import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.mapper.TagGenerateMapper;
import com.dalian.sea.model.BoxBatchTemp;
import com.dalian.sea.model.SaError;
import com.dalian.sea.model.SysSysLog;
import com.dalian.sea.model.TagGenerate;
import com.dalian.sea.parameter.*;
import com.dalian.sea.service.SysSysLogService;
import com.dalian.sea.service.TagGenerateService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Administrator
 * @date 2018/2/24
 */
@Slf4j
@Controller
@RequestMapping(value = "/tagGenerate")
public class TagGenerateController extends LayoutRazor {
    @Autowired
    private TagGenerateService tagGenerateService;
    @Autowired
    private SysSysLogService sysSysLogService;
    @Autowired
    private TagGenerateMapper generateMapper;

    /**
     * 标签管理—成品包标签
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/generateManage.htm")
    public String generateManage(HttpServletRequest request) {
        return freeMarkerIndexResult("tagGenerate/generateManage.ftl", request);
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/addGenerateModal.htm")
    public String addGenerate(HttpServletRequest request,Long resourceId) {
        request.setAttribute("resourceId",resourceId);
        return "/tagGenerate/addGenerateModal";
    }


    /**
     * 详情
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/detailGenerateModal.htm")
    public String detailGenerate(HttpServletRequest request, Long id) {
        PTagGenerate sweep = tagGenerateService.getTagGenerateById(id);
        request.setAttribute("sweep", sweep);
        return "/tagGenerate/detailGenerateModal";
    }

    @RequestMapping(value = "/qrImportGenerateModal.htm")
    public String qrimportGenerate(HttpServletRequest request, Long id) {
        return "/tagGenerate/qrImportModal";
    }

    /**
     * 添加
     *
     * @param tagCount
     * @return
     */
    @RequestMapping(value = "/addGenerate.json")
    @ResponseBody
    public Json downGenerate(Integer tagCount,Long resourceId,HttpServletRequest httpServletRequest) {
        Json json = new Json();
        try {
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
//            DateFormat format = new SimpleDateFormat("yyMM");
//            //获取标签规则
//            String ruleName = format.format(new Date());
//            PTagGenerate tagGenerate = tagGenerateService.getTagGenerateByRule(ruleName);
//            TagGenerate generate = new TagGenerate();
//            if (tagGenerate != null) {
//                Integer endNo = tagGenerate.getEndNo();
//                generate.setEndNo(endNo + tagCount);
//                generate.setStartNo(endNo + 1);
//                generate.setRuleName(ruleName);
//                generate.setTagCount(tagCount);
//                generate.setRemainCount(tagCount);
//                generate.setCreateDate(new Date());
//                generate.setAllCode(generate.getStartNo() + "-" + generate.getEndNo());
//                generate.setUnAllotCode(generate.getAllCode());
//                generate.setAlreadyCount(0);
//                generate.setStatus((byte) 1);
//                generate.setCreateUserId(shiroUser.getId());
//                generate.setCreateUserName(shiroUser.getName());
//                generate.setUpdateDate(new Date());
//            } else {
//                generate.setEndNo(tagCount);
//                generate.setStartNo(1);
//                generate.setAllCode(generate.getStartNo() + "-" + generate.getEndNo());
//                generate.setUnAllotCode(generate.getAllCode());
//                generate.setRuleName(ruleName);
//                generate.setTagCount(tagCount);
//                generate.setRemainCount(tagCount);
//                generate.setCreateDate(new Date());
//                generate.setAlreadyCount(0);
//                generate.setStatus((byte) 1);
//                generate.setCreateUserId(shiroUser.getId());
//                generate.setCreateUserName(shiroUser.getName());
//            }
            TagGenerate generate = addTagGenerate(tagCount);
            TagGenerate addGenerate = tagGenerateService.addGenerate(generate);
            if (resourceId!=null){
                SysSysLog sysLog = new SysSysLog();
                String ip = ClientIP.getClientIP(httpServletRequest);
                sysLog.setIpAddress(ip);
                sysLog.setCName("二维码库存添加");
                sysLog.setRemark(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())+" — 二维码添加");
                sysLog.setResourceId(resourceId);
                sysSysLogService.addSysSysLog(sysLog);
            }
            json.setSuccess(true);
            json.setObj(addGenerate);
            json.setMsg("添加成功");
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }
  public TagGenerate addTagGenerate(Integer tagCount){
      DateFormat format = new SimpleDateFormat("yyMM");
      //获取标签规则
      String ruleName = format.format(new Date());
      PTagGenerate tagGenerate = tagGenerateService.getTagGenerateByRule(ruleName);
      TagGenerate generate = new TagGenerate();
      if (tagGenerate != null) {
          Integer endNo = tagGenerate.getEndNo();
          generate.setEndNo(endNo + tagCount);
          generate.setStartNo(endNo + 1);
          generate.setRuleName(ruleName);
          generate.setTagCount(tagCount);
          generate.setRemainCount(tagCount);
          generate.setCreateDate(new Date());
          generate.setAllCode(generate.getStartNo() + "-" + generate.getEndNo());
          generate.setUnAllotCode(generate.getAllCode());
          generate.setAlreadyCount(0);
          generate.setStatus((byte) 1);
          generate.setUpdateDate(new Date());
      } else {
          generate.setEndNo(tagCount);
          generate.setStartNo(1);
          generate.setAllCode(generate.getStartNo() + "-" + generate.getEndNo());
          generate.setUnAllotCode(generate.getAllCode());
          generate.setRuleName(ruleName);
          generate.setTagCount(tagCount);
          generate.setRemainCount(tagCount);
          generate.setCreateDate(new Date());
          generate.setAlreadyCount(0);
          generate.setStatus((byte) 1);
      }
      return generate;
  };
    /**
     * 加载表格
     */
    @RequestMapping("/GridJson.json")
    @ResponseBody
    public Object GridJson(HttpServletRequest request, PTagGenerate tagGenerate, Integer page, Integer rows) {
        List<PTagGenerate> tagGenerateList = tagGenerateService.getTagGenerateByGrid(tagGenerate, page, rows);
        PageInfo<PTagGenerate> pageInfo = new PageInfo<>(tagGenerateList);
        return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), tagGenerateList);
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/deleteGenerate.json")
    @ResponseBody
    public Object deleteGenerate(HttpServletRequest request, TagGenerate generate) {
        Json json = new Json();
        try {
            if (null != generate.getGenerateId()) {
                tagGenerateService.deleteGenerateById(generate);
                json.setMsg("删除成功");
                json.setSuccess(true);
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    @RequestMapping(value = "downGenerateZip.htm")
    public void downGenerate(HttpServletRequest request, TagGenerate generate, HttpServletResponse response) throws UnsupportedEncodingException {
        if (generate.getTagCount() != null) {
            generate= addTagGenerate(generate.getTagCount());
            generate=tagGenerateService.addGenerate(generate);
        }
        if (generate.getGenerateId() != null) {
            PTagGenerate tagGenerate = tagGenerateService.getTagGenerateById(generate.getGenerateId());
            if (tagGenerate != null && tagGenerate.getEndNo() != null && tagGenerate.getEndNo() != null) {
                response.setContentType("text/plain");
                String title = "二维码信息";
                String userAgent = request.getHeader("USER-AGENT");
                String importTitle = GenerateUtils.getImportTitle(userAgent, title);
                response.setHeader("Content-Disposition", "attachment; filename=" + importTitle + ".txt");
//                BufferedOutputStream buff = null;
                ServletOutputStream outSTr = null;
                OutputStreamWriter osw=null;
                BufferedWriter bufw=null;
                try {
                    outSTr = response.getOutputStream(); // 建立
                    osw = new OutputStreamWriter(outSTr);
                    bufw = new BufferedWriter(osw);
//                    buff = new BufferedOutputStream(outSTr);
                    String zsCode = null;//追溯码
                     if (tagGenerate.getIsPrint()==1){
                         String unAllotCode = tagGenerate.getUnAllotCode();
                         if (unAllotCode!=null && unAllotCode!=""){
                             List<Integer> integerList = GenerateUtils.codeToList(unAllotCode);
                             for (int i = 0; i < integerList.size(); i++) {
                                 zsCode = GenerateUtils.codetozs(tagGenerate.getRuleName(), integerList.get(i));
//                        buff.write(("http://s.xiaoqin.com.cn/p/" + "1000" + zsCode + System.getProperty("line.separator")).getBytes("utf-8"));
                                 bufw.write("http://s.xiaoqin.com.cn/back/p/" + "1000" + zsCode+"\r\n");
                             }

                         }
                     }else {
                         for (int i = tagGenerate.getStartNo(); i <= tagGenerate.getEndNo(); i++) {
                             zsCode = GenerateUtils.codetozs(tagGenerate.getRuleName(), i);
//                        buff.write(("http://s.xiaoqin.com.cn/p/" + "1000" + zsCode + System.getProperty("line.separator")).getBytes("utf-8"));
                             bufw.write("http://s.xiaoqin.com.cn/back/p/" + "1000" + zsCode+"\r\n");
//                        bufw.newLine();
                         }
                     }

//                    buff.flush();
//                    buff.close();
                    bufw.flush();
                    bufw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
//                        buff.close();
                        bufw.close();
                        outSTr.close();
                        osw.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void zipFile(File[] subs, String baseName, ZipOutputStream zos) throws IOException {
        for (int i = 0; i < subs.length; i++) {
            File f = subs[i];
            zos.putNextEntry(new ZipEntry(baseName + f.getName()));
            FileInputStream fis = new FileInputStream(f);
            byte[] buffer = new byte[1024];
            int r = 0;
            while ((r = fis.read(buffer)) != -1) {
                zos.write(buffer, 0, r);
            }
            f.delete();
            fis.close();
        }
    }

    @RequestMapping(value = "/downGenerate.json")
    @ResponseBody
    public Object downGenerate(HttpServletRequest request, Long generateId) {
        Json json = new Json();
        try {
            if (null != generateId) {
                PTagGenerate tagGenerate = tagGenerateService.getTagGenerateById(generateId);
                if (tagGenerate != null && tagGenerate.getEndNo() != null && tagGenerate.getEndNo() != null) {
                    String dirName = tagGenerate.getStartNo() + "-" + tagGenerate.getEndNo() + ".txt";
                    String realPath = request.getSession().getServletContext().getRealPath("/txt");
                    File file = new File(realPath);
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    File file1 = new File(realPath, dirName);
                    if (!file1.exists()) {
                        file1.createNewFile();
                    } else {
                        file1.delete();
                        file1.createNewFile();
                    }
                    OutputStreamWriter o = new OutputStreamWriter(new FileOutputStream(file1), "gbk");
                    BufferedWriter output = new BufferedWriter(o);
                    String zsCode = null;//追溯码
                    output.write("导出二维码" + System.getProperty("line.separator"));
                    for (int i = tagGenerate.getStartNo(); i <= tagGenerate.getEndNo(); i++) {
                        zsCode = GenerateUtils.codetozs(tagGenerate.getRuleName(), i);
                        output.write("http://s.xiaoqin.com.cn/back/p/" + "1000" + zsCode + System.getProperty("line.separator"));
                    }
                    output.flush(); // 把缓存区内容压入文件
                    output.close(); // 最后记得关闭文件
                    json.setSuccess(true);
                }

            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    @RequestMapping("/importQrCode.json")
    @ResponseBody
    public Object qrCodeImport(HttpServletRequest request) {
        Json json = new Json();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multfile = multipartRequest.getFile("file");
        File tempFile = null;
        if (!multfile.isEmpty()) {
            String filePath = request.getSession().getServletContext().getRealPath("/") + "qrCode/";
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdir();
            }
            String path = filePath + multfile.getOriginalFilename();
            //save to the /upload path
            try {
                tempFile = new File(path);
                FileUtils.copyInputStreamToFile(multfile.getInputStream(), tempFile);
            } catch (Exception e) {
                json.setMsg("系统异常,导入失败");
                json.setSuccess(false);
                return json;
            }
        }
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(tempFile), "GBK");
            BufferedReader br = new BufferedReader(read);//构造一个BufferedReader类来读取文件
            String s = null;
            List<CodeHelp> codeHelpList = new ArrayList<CodeHelp>();
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                        if(s.length()>18){
                            //截取后十四位
                            String sb = s.substring(s.length() - 14, s.length());
                            //获取标签规则
                            String ruleName = sb.substring(0, 4);
                            Integer code = Integer.valueOf(GenerateUtils.zstoCode(sb));
                            CodeHelp help = new CodeHelp();
                            help.setCode(code);
                            help.setRuleName(ruleName);
                            codeHelpList.add(help);
                        }
            }

            br.close();
            tempFile.delete();
            if (codeHelpList.size()>0){
                CodeHelp help = codeHelpList.get(0);
                TagGenerate generate = new TagGenerate();
                generate.setRuleName(help.getRuleName());
                generate.setStartNo(help.getCode());
                generate.setIsPrint((byte) 0);
                generate.setEndNo(help.getCode());
                List<TagGenerate> tagGenerates = generateMapper.selectTagGenerateBy(generate);
                if (tagGenerates.size()<1){
                    json.setMsg("二维码已导入,勿重复!!!!");
                    return json;
                }
                boolean b = tagGenerateService.updateTagGenerateLabelCode(codeHelpList);
                if (b) {
                    json.setSuccess(true);
                    json.setMsg("操作成功");
                }else {
                    json.setMsg("操作失败");
                }
            }else {
                json.setMsg("无二维码信息");
            }
            return json;

        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg("txt数据解析有误");
            return json;
        }

    }
//    @RequestMapping("/importQrCodeIo.json")
//    @ResponseBody
//    public Object qrCodeImportIo(HttpServletRequest request) throws IOException {
//        ServletInputStream sis = request.getInputStream();
//        Json json = new Json();
//        String filePath = request.getSession().getServletContext().getRealPath("/qrCode.txt");
//        File dir = new File(filePath);
//        if (!dir.exists()) {
//            dir.createNewFile();
//        }
//        try {
//            FileOutputStream fos = new FileOutputStream(filePath);
//            byte[] media = new byte[1024];
//            int length = sis.read(media, 0, 1024);
//            while(length  != -1)
//            {
//                fos.write(media, 0, length);
//                length = sis.read(media, 0, 1024);
//            }
//            fos.close();
//            sis.close();
//        } catch (Exception e) {
//            json.setMsg("io流读取失败");
//            json.setSuccess(false);
//            return json;
//        }
//        try {
//            InputStreamReader read = new InputStreamReader(new FileInputStream(dir), "utf-8");
//            BufferedReader br = new BufferedReader(read);//构造一个BufferedReader类来读取文件
//            String s = null;
//
//            List<CodeHelp> codeHelpList = new ArrayList<CodeHelp>();
//            boolean flag = false;
//            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
//                if (flag) {
//                    //截取后十四位
//                    String sb = s.substring(s.length() - 14, s.length());
//                    //获取标签规则
//                    String ruleName = sb.substring(0, 4);
//                    Integer code = Integer.valueOf(GenerateUtils.zstoCode(sb));
//                    CodeHelp help = new CodeHelp();
//                    help.setCode(code);
//                    help.setRuleName(ruleName);
//                    codeHelpList.add(help);
//                }
//                if (s.equals("二维码信息:")) {
//                    flag = true;
//                }
//            }
//
//            br.close();
//            dir.delete();
//            boolean b = tagGenerateService.updateTagGenerateLabelCode(codeHelpList);
//            if (b) {
//                json.setSuccess(true);
//                json.setMsg("操作成功");
//            }
//            return json;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            json.setMsg("txt数据解析有误");
//            return json;
//        }
//
//    }
}
