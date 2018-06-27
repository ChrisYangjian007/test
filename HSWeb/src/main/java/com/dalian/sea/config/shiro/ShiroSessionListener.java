package com.dalian.sea.config.shiro;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author 杨文波
 * @date 2017/12/25
 */
@Slf4j
public class ShiroSessionListener implements SessionListener{


    @Override
    public void onStart(Session session) {
        // 会话创建时触发
        log.info("ShiroSessionListener session {} 被创建", session.getId());
    }

    @Override
    public void onStop(Session session) {
        // 会话被停止时触发
        log.info("ShiroSessionListener session {} 被销毁", session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        //会话过期时触发
        log.info("ShiroSessionListener session {} 过期", session.getId());
    }
}
