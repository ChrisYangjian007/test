package com.dalian.sea.controller;

import Models.Enum.CurrentDatabase;
import Models.Enum.ErrorInfo;
import Models.Enum.OperateResult;
import Models.JqGridParam;
import Utils.CommonHelper;
import com.dalian.sea.Base.BaseLogic;
import com.dalian.sea.Base.ManageLogic;
import com.dalian.sea.WebClass.JqGridResult;
import com.dalian.sea.WebClass.JsonMessage;
import com.dalian.sea.WebClass.ManageUser;
import com.dalian.sea.config.shiro.ShiroUser;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.BaUser;
import com.dalian.sea.service.BaUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by 杨文波 on 2017/11/28.
 */
@Controller
@Slf4j
@RequestMapping("/")
public class BaseController {


    @Autowired
    private BaUserService baUserService;

    @RequestMapping(value={"", "/","/test"})
    public String home(HttpServletRequest request) {

        return "redirect:login.htm";
    }

    @RequestMapping("/index.htm")
    public String index(HttpServletRequest request) {
        Subject user = SecurityUtils.getSubject();
        if (user.isAuthenticated()) {
            return "Home/AccordionIndex";
        }
        return "redirect:login.htm";
    }

    @PostMapping("/login.json")
    @ResponseBody
    public Object loginJson(HttpServletRequest request, @Param("username") String username, @Param("password")String password){
        Json json = new Json();
        Subject user = SecurityUtils.getSubject();
        String message = "";
        Boolean loginSuccess = true;
        if (user.isAuthenticated()) {
            json.setSuccess(true);
            return json;
        }
        if (null == username  || null == password ) {
            json.setMsg("账号或密码为空");
            return json;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            user.login(token);
//            可以在登录认证那里抛出以下异常信息
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            user.getSession().setAttribute("user",shiroUser);
            baUserService.updateUserLoginDate(shiroUser.getId());
            request.getSession().setAttribute("shiroUser",shiroUser);
        } catch (UnknownAccountException e) {
            message = "账号不存在!";
            loginSuccess = false;
            token.clear();
        } catch (DisabledAccountException e) {
            message = "账号未启用或权限不足!";
            loginSuccess = false;
            token.clear();
        } catch (IncorrectCredentialsException e) {
            message = "密码错误!";
            loginSuccess = false;
            token.clear();
        } catch (Throwable e) {
            loginSuccess = false;
            message = e.getMessage();
            token.clear();
        }
        json.setSuccess(loginSuccess);
        if (loginSuccess == false) {
            json.setMsg(message);
            return json;
        }
        return json;
    }

    @RequestMapping(value = "/login.htm")
    public String login(HttpServletRequest request) {
        Subject user = SecurityUtils.getSubject();
        if (user.isAuthenticated()) {
            return "redirect:index.htm";
        }
        return "Home/Login";
    }


    @PostMapping("/addBaUser.json")
    @ResponseBody
    public String addBaUser(BaUser baUser){
        baUser.setCreateDate(new Date());
        baUser.setUpdateDate(new Date());
        String algorithmName = "md5";
        int hashIterations = 2;
        String newPassword = new SimpleHash(algorithmName, baUser.getPassword(),  ByteSource.Util.bytes(baUser.getAccount()), hashIterations).toHex();
        baUser.setPassword(newPassword);
        Long id = baUserService.addBaUser(baUser);
        return "success";
    }

    /**
     * 退出
     * 如果在ShiroFilterFactoryBean有配置登出url，此处可以忽略不写
     * @return {Result}
     * @deprecated
     */
    @RequestMapping(value = "/logout.htm")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:login.htm";
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String errorMsg = "请求服务器失败!";

    protected ManageUser _nowLoginUser;
    protected int DBIndex= CurrentDatabase.MainDB.getCode();

    public <T, R> Models.Json FunExcute(Function<T, R> fun, T para, String successMsg, String failMsg) {
        int regcode = 0;
        String regmsg = "";
        R mObject = null;
        Models.Json returnJson = new Models.Json();
        try {
            //CheckHead(out regcode, out regmsg);//检查头信息是否一致
            regcode = 1;
            if (regcode == OperateResult.success.toCode()) {
                mObject = fun.apply(para);
                Object m_Type = mObject.getClass();
                if ((m_Type instanceof Integer && ((Integer) m_Type).intValue() > 0) || (m_Type instanceof Boolean && ((Boolean) m_Type).booleanValue()) ||
                        (mObject != null && m_Type.toString().contains("DAO")) ||
                        (mObject != null && m_Type.toString().contains("Generic"))) {
                    regcode = OperateResult.success.toCode();
                    regmsg = successMsg;
                } else {
                    regcode = OperateResult.fail.toCode();
                }
            }
        } catch (Exception ex) {
            regcode = OperateResult.error.toCode();
            regmsg = errorMsg;
            logger.error(ex.getMessage());
        }
        if (regcode == 2 && failMsg != "") {
            regmsg = failMsg;
        }
        if (regcode == 2 && failMsg == "") {
            regmsg = "";
        }
        returnJson.setMsg(regmsg);
        returnJson.setObj(mObject);
        returnJson.setSuccess(regcode == OperateResult.success.toCode());
        return returnJson;
    }

    public <T, U, R> Models.Json FunExcute(BiFunction<T, U, R> fun, T para1, U para2, String successMsg, String failMsg) {
        int regcode = 0;
        String regmsg = "";
        R mObject = null;
        Models.Json returnJson = new Models.Json();
        try {
            //CheckHead(out regcode, out regmsg);//检查头信息是否一致
            regcode = 1;
            if (regcode == OperateResult.success.toCode()) {
                mObject = fun.apply(para1, para2);
                Object m_Type = mObject.getClass();
                if ((m_Type instanceof Integer && ((Integer) m_Type).intValue() > 0) ||
                        (m_Type instanceof Boolean && ((Boolean) m_Type).booleanValue()) ||
                        (mObject != null && m_Type.toString().contains("DAO")) ||
                        (mObject != null && m_Type.toString().contains("Generic"))) {
                    regcode = OperateResult.success.toCode();
                    regmsg = successMsg;
                } else {
                    regcode = OperateResult.fail.toCode();
                }
            }
        } catch (Exception ex) {
            regcode = OperateResult.error.toCode();
            regmsg = errorMsg;
            logger.error(ex.getMessage());
        }
        if (regcode == 2 && failMsg != "") {
            regmsg = failMsg;
        }
        if (regcode == 2 && failMsg == "") {
            regmsg = "";
        }
        returnJson.setMsg(regmsg);
        returnJson.setObj(mObject);
        returnJson.setSuccess(regcode == OperateResult.success.toCode());
        return returnJson;
    }

    public ManageUser NowLoginUser(HttpServletRequest request) {
        if (_nowLoginUser == null) {
            /*_nowLoginUser = ManageLogic.Instance().Current(request);*/
        }
        return _nowLoginUser;
    }

    /**
     * 判断请求是否手机端
     *
     * @param req
     */
    public static boolean isMobile(HttpServletRequest req) {
        String[] deviceArray = new String[]{"android", "mac os", "windows phone"};
        String requestHeader = req.getHeader("user-agent");
        if (requestHeader == null) {
            return false;
        }
        requestHeader = requestHeader.toLowerCase();
        for (int i = 0; i < deviceArray.length; i++) {
            if (requestHeader.indexOf(deviceArray[i]) > 0) {
                return true;
            }
        }
        return false;
    }

    public JqGridResult BaseGridPageJson(JqGridParam jqgridparam, Object listData) {
        return new JqGridResult() {{
            setTotal(jqgridparam.getTotal());
            setPage(jqgridparam.getPage());
            setRecords(jqgridparam.getRecords());
            setRows(listData);
        }};
    }

    public JqGridResult BaseGridPageJson(StopWatch watch, JqGridParam jqgridparam, Object listData) {
        watch.stop();
        return new JqGridResult() {{
            setTotal(jqgridparam.getTotal());
            setPage(jqgridparam.getPage());
            setCosttime(String.valueOf(watch.getTotalTimeMillis()));
            setRecords(jqgridparam.getRecords());
            setRows(listData);
        }};
    }

    public JsonMessage SubmitResult(int isOk) {
        return isOk > 0 ? SucceedResult() : DefeatedResult("操作失败，" + ErrorInfo.getValueByID(isOk));
    }

    public JsonMessage SubmitResult(Boolean isOk) {
        return isOk ? SucceedResult() : DefeatedResult("操作失败!");
    }

    public JsonMessage DefeatedResult(String message) {
        return DefeatedResult(message, "-1");
    }

    public JsonMessage DefeatedResult(String message, String code) {
        return new JsonMessage() {{
            setSuccess(false);
            setMessage(message);
            setCode(code);
        }};
    }

    public JsonMessage SucceedResult() {
        return SucceedResult("操作成功！");
    }

    public JsonMessage SucceedResult(String message) {
        return SucceedResult(message, "1");
    }

    public JsonMessage SucceedResult(String message, Object data) {
        return new JsonMessage() {{
            setSuccess(true);
            setMessage(message);
            setData(data);
        }};
    }

    public JsonMessage SucceedResult(String message, String code) {
        return new JsonMessage() {{
            setSuccess(true);
            setMessage(message);
            setCode(code);
        }};
    }

    public JqGridResult BaseGridPageListJson(StopWatch watch, JqGridParam jqgridparam, Object listData) {
        JqGridResult jsonData = new JqGridResult() {{
            setCosttime(CommonHelper.TimerEnd(watch));
            setRows(listData);
            setRecords(jqgridparam.getRecords());
            setPage(jqgridparam.getPage());
            setTotal(jqgridparam.getTotal());
        }};
        return jsonData;
    }

    public JqGridResult BaseGridPageListJson(JqGridParam jqgridparam, Object listData) {
        JqGridResult jsonData = new JqGridResult() {{
            setRows(listData);
            setRecords(jqgridparam.getRecords());
            setPage(jqgridparam.getPage());
            setTotal(jqgridparam.getTotal());
        }};
        return jsonData;
    }

    /// <summary>
    /// 绑定表格
    /// </summary>
    /// <param name="ParameterJson">查询条件</param>
    /// <param name="Gridpage">分页条件</param>
    public <T> JqGridResult GridPageJson(Class<T> t,String ParameterJson, JqGridParam jqgridparam) {
        StopWatch watch = CommonHelper.TimerStart();
        List<T> ListData = new ArrayList<T>();
        ListData = BaseLogic.Instance().getListByPara(t,ParameterJson,jqgridparam,DBIndex,"");
        return BaseGridPageJson(watch, jqgridparam, ListData);
    }
}
