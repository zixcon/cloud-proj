package com.cloud.frame.demo.auth.config;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * Created by wd on 2018/4/3.
 */
@Component
public class ShiroRedisSessionDAO extends AbstractSessionDAO {

    private final Logger log = LoggerFactory.getLogger(getClass());

    // Session超时时间，单位为毫秒
    private long expireTime = 5000;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    protected Serializable doCreate(Session session) {
        log.info("===============doCreate================");
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);

        redisTemplate.opsForValue().set(session.getId(), session, expireTime, TimeUnit.MILLISECONDS);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        log.info("==============doReadSession=================");
        if (serializable == null) {
            return null;
        }
        return (Session) redisTemplate.opsForValue().get(serializable);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        log.info("===============update================");
        if (session == null || session.getId() == null) {
            return;
        }
        session.setTimeout(expireTime);
        redisTemplate.opsForValue().set(session.getId(), session, expireTime, TimeUnit.MILLISECONDS);
    }

    @Override
    public void delete(Session session) {
        log.info("===============delete================");
        if (null == session) {
            return;
        }
        redisTemplate.opsForValue().getOperations().delete(session.getId());
    }

    /**
     * 获取活跃的session，可以用来统计在线人数，如果要实现这个功能，可以在将session加入redis时指定一个session前缀，
     * 统计的时候则使用keys("session-prefix*")的方式来模糊查找redis中所有的session集合
     *
     * @return
     */
    @Override
    public Collection<Session> getActiveSessions() {
        log.info("==============getActiveSessions=================");
        return redisTemplate.keys("*");
    }
}
