package com.dalian.sea.api;

import com.dalian.sea.json.Json;
import com.dalian.sea.model.BaUser;
import com.dalian.sea.parameter.PBaUser;
import com.dalian.sea.service.BaResourceService;
import com.dalian.sea.service.BaUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author 杨文波
 * @date 2017/12/27
 */
@RestController
@Slf4j
public class AndroidBaseApi {

    @Autowired
    private BaUserService baUserService;
    @Autowired
    private BaResourceService baResourceService;

    @PostMapping("/login.json")
    public Object login(HttpServletRequest request, BaUser baUser){
        Json json = new Json();
        try{
            PBaUser user = baUserService.getBaUserBy(baUser);
            if (null!=user){
                if (2!=user.getStatus()){
                    if (1!=user.getUserType()){
                        String password = new SimpleHash("md5", baUser.getPassword(),  ByteSource.Util.bytes(baUser.getAccount()), 2).toString();
                        if (password.equals(user.getPassword())){
                            user = baUserService.loginFromPDA(user);
                            if (null!=user) {
                                json.setMsg("登陆成功");
                                json.setObj(user);
                            }else {
                                json.setMsg("登陆失败，请联系管理员！");
                            }
                        }else {
                            json.setMsg("密码不正确");
                        }
                    }else {
                        json.setMsg("用户仅有PC端权限，暂不可登陆手持PDA");
                    }
                }else {
                    json.setMsg("账号被冻结");
                }
            }else {
                json.setMsg("账号不存在");
            }
            json.setSuccess(true);
        }catch (Exception e){
            json.setMsg("服务器异常!");
        }
        return json;
    }
    @RequestMapping("/base")
    public String base(){
        return "hello";
    }
}
