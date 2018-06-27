package com.dalian.sea.api;

import com.dalian.sea.json.Json;
import com.dalian.sea.model.PdaApkVersions;
import com.dalian.sea.service.PdaApkVersionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AndroidApkVersionApi
 *
 * @author TONE
 * @date 2018/4/4.
 */
@RestController
@RequestMapping("/apkVersion")
public class AndroidApkVersionApi {
    @Autowired
    private PdaApkVersionsService apkVersionsService;



    @PostMapping("/getApk.json")
    public Object getApk(PdaApkVersions pdaApkVersions){
        Json json = new Json();
        try {
            PdaApkVersions apkVersions = apkVersionsService.getNewPdaApkVersionsBy(pdaApkVersions);
            json.setObj(apkVersions);
            json.setSuccess(true);
        }catch (Exception e){
            json.setMsg("服务器异常!");
        }
        return json;
    }





}
