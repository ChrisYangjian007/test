package com.dalian.sea.config.shiro;

import com.dalian.sea.config.redis.MyRedisProperties;
import com.dalian.sea.config.redis.RedisClient;
import com.dalian.sea.config.redis.SecondaryRedisConfig;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.filter.DelegatingFilterProxy;
import redis.clients.jedis.JedisPool;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author 杨文波
 * @date 2017/12/11
 */
@Configuration
@Import(SecondaryRedisConfig.class)
@Slf4j
public class MyShiroConfig {

    /**
     * FilterRegistrationBean
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
        return filterRegistration;
    }

    /**
     * @see org.apache.shiro.spring.web.ShiroFilterFactoryBean
     * @return
     */
    @Bean(name = "shiroFilter")
    @DependsOn(value = "securityManager")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager")DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        bean.setLoginUrl("/login.htm");
        //未授权界面;
        bean.setUnauthorizedUrl("/error/403.htm");
        // 登录成功后要跳转的链接
        bean.setSuccessUrl("/index.htm");
        //拦截器.
        Map<String, Filter> filters = Maps.newHashMap();
        filters.put("anon", new AnonymousFilter());
        filters.put("perms", shiroRequestUrl());
        bean.setFilters(filters);
        /*
        * anon 不需要认证,authc 需要认证,user 验证通过或RememberMe登录的都可以
        * */
        Map<String, String> chains = Maps.newHashMap();
        chains.put("/login.htm", "anon");
        chains.put("/login.json", "anon");
        //chains.put("/logout.htm", "logout");

        chains.put("/saOrder/**", "anon");
        chains.put("/tagGenerate/**", "anon");
        chains.put("/productBatch/**", "anon");
        chains.put("/p/**", "anon");
        chains.put("/box/**", "anon");
        chains.put("/PreviewPage/**", "anon");

        //chains.put("/druid/**", "anon");
        chains.put("/js/**", "anon");
        chains.put("/css/**", "anon");
        chains.put("/images/**", "anon");
        chains.put("/**", "user,perms");
        bean.setFilterChainDefinitionMap(chains);
        return bean;
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     *  所以我们需要修改下doGetAuthenticationInfo中的代码;
     * ）
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));

        return hashedCredentialsMatcher;
    }

    /**
     * 权限管理器
     * @param redisCacheManager
     * @param userRealm
     * @param sessionManager
     * @return
     */
    @Bean(name = "securityManager")
    @DependsOn(value = {"redisCacheManager","userRealm","sessionManager"})
    public DefaultWebSecurityManager securityManager(@Qualifier("redisCacheManager") RedisCacheManager redisCacheManager
            ,@Qualifier("userRealm")MyShiroRealm userRealm
            ,@Qualifier("sessionManager")DefaultWebSessionManager sessionManager) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        // 数据库认证的实现
        manager.setRealm(userRealm);
        // session 管理器
        manager.setSessionManager(sessionManager);
        // 缓存管理器
        manager.setCacheManager(redisCacheManager);
        return manager;
    }

    @Bean(name = "sessionListener")
    public ShiroSessionListener sessionListener(){
        ShiroSessionListener shiroSessionListener = new ShiroSessionListener();
        return shiroSessionListener;
    }


    /**
     * @see MyShiroRealm--->AuthorizingRealm
     * @return
     */
    @Bean
    @DependsOn(value = { "lifecycleBeanPostProcessor", "redisCacheManager" })
    public MyShiroRealm userRealm(@Qualifier("redisCacheManager") RedisCacheManager redisCacheManager) {
        MyShiroRealm userRealm = new MyShiroRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        userRealm.setCacheManager(redisCacheManager);
        userRealm.setCachingEnabled(true);
        userRealm.setAuthenticationCachingEnabled(false);
        userRealm.setAuthorizationCachingEnabled(true);
        return userRealm;
    }

    @Bean
    public URLPermissionsFilter urlPermissionsFilter() {
        return new URLPermissionsFilter();
    }

    @Bean
    public ShiroRequestUrl shiroRequestUrl() {
        return new ShiroRequestUrl();
    }


    @Bean(name = "redisSessionDAO")
    public EnterpriseCacheSessionDAO redisSessionDAO(){
        EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
        return sessionDAO;
    }

    /**
     * DefaultWebSessionManager
     *
     * @return
     */
    @Bean(name = "sessionManager")
    @DependsOn(value = {"sessionListener","redisSessionDAO"})
    public DefaultWebSessionManager sessionManager(@Qualifier("redisSessionDAO")EnterpriseCacheSessionDAO redisSessionDAO
    ,@Qualifier("simpleCookie")SimpleCookie simpleCookie) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        Collection<SessionListener> collection = Lists.newArrayList();
        collection.add(sessionListener());
        sessionManager.setSessionListeners(collection);
        sessionManager.setSessionDAO(redisSessionDAO);
        sessionManager.setSessionIdCookie(simpleCookie);
        return sessionManager;
    }

    /**
     * simpleCookie,不定义在集群环境下会出现There is no session with id ....
     * */
    @Bean(name = "simpleCookie")
    public SimpleCookie simpleCookie(){
        SimpleCookie simpleCookie = new SimpleCookie("custom.session");
        simpleCookie.setPath("/");
        return simpleCookie;
    }

    @Bean(name = "redisClient")
    public RedisClient redisClient(JedisPool jedisPool,MyRedisProperties redisProperties){
        RedisClient redisClient = new RedisClient(jedisPool);
        redisClient.setExpire(redisProperties.getSessionExpire());
        return redisClient;
    }

    @Bean(name = "redisCacheManager")
    @DependsOn(value = "redisClient")
    public RedisCacheManager redisCacheManager(@Qualifier("redisClient") RedisClient redisClient){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setKeyPrefix("shiro_session:");
        redisCacheManager.setRedisManager(redisClient);
        return redisCacheManager;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}
