package com.dalian.sea.config.shiro;

import com.dalian.sea.model.BaResource;
import com.dalian.sea.model.BaUser;
import com.dalian.sea.parameter.PBaUser;
import com.dalian.sea.service.BaResourceService;
import com.dalian.sea.service.BaUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author 杨文波
 * @date 2017/12/1
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private BaUserService baUserService;
    @Autowired
    private BaResourceService baResourceService;
    /**
     * Shiro权限认证,授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        ShiroUser user=(ShiroUser) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        List<String> permissionList = new ArrayList<String>();
        simpleAuthorizationInfo.addStringPermissions(user.getUrlSet());
        return simpleAuthorizationInfo;
    }

    /**
     * Shiro登录认证(原理：用户提交 用户名和密码  --- shiro 封装token ---- realm 通过用户名将密码查询返回 ---- shiro 自动去比较查询出密码和用户输入密码是否一致---- 进行登陆控制 )
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken utoken=(UsernamePasswordToken) authenticationToken;//获取用户输入的token
        String username = utoken.getUsername();
        PBaUser user = new PBaUser();
        user.setAccount(username);
        user = baUserService.getBaUserBy(user);
        if (null==user){
            throw new UnknownAccountException();
        }
        if (2==user.getStatus()||2==user.getUserType()){
            throw new DisabledAccountException();
        }
        List<BaResource> baResourceList = baResourceService.getAllBaResourceByUserId(user.getUserId(),0);
        Set<String> urlSet=new HashSet<>();
        if (null!=baResourceList&&0!=baResourceList.size()){
            for (BaResource resource:baResourceList){
                if (null!=resource.getLocation() && !"".equals(resource.getLocation())) {
                    urlSet.add(resource.getLocation());
                }
            }
        }
        ShiroUser shiroUser = new ShiroUser(user.getUserId(),user.getAccount(),user.getCName());
        shiroUser.setCompanyId(user.getCompanyId());
        shiroUser.setUrlSet(urlSet);
        SimpleAuthenticationInfo info =  new SimpleAuthenticationInfo(shiroUser,user.getPassword(),getName());
        info.setCredentialsSalt(ByteSource.Util.bytes(user.getAccount()));
        return info;
    }

}
