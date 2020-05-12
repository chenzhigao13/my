package com.liandi.system.shiro.manager;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import com.liandi.system.redisson.RedissonManager;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义 SessionDao
 *
 * @author czg
 * @date 2020/3/31 10:25
 */
@Slf4j
public class CustomSessionDao extends AbstractSessionDAO {

    @Setter
    private String sessionKey = "sessionMap";

    @Autowired
    private RedissonManager redissonManager;

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.generateSessionId(session);
        super.assignSessionId(session, sessionId);
        this.storeSession(sessionId, session);
        return sessionId;
    }

    @Override
    public Session readSession(Serializable sessionId) throws UnknownSessionException {
        Session s = this.doReadSession(sessionId);
        if (s == null) {
            throw new UnknownSessionException("There is no session with id [" + sessionId + "]");
        }
        return s;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        Serializable sessionId = session.getId();
        this.storeSession(sessionId, session);
    }

    @Override
    public void delete(Session session) {
        if (session == null) {
            throw new NullPointerException("session argument cannot be null.");
        }
        Serializable sessionId = session.getId();
        if (sessionId != null) {
            redissonManager.delMapKey(sessionKey, sessionId);
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        return null;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        SimpleSession s = redissonManager.getMapValue(sessionKey, sessionId);
        if (s == null) {
            throw new UnknownSessionException("There is no session with id [" + sessionId + "]");
        }
        s.setId(sessionId);
        return s;
    }

    protected Session storeSession(Serializable id, Session session) {
        if (id == null) {
            throw new NullPointerException("id argument cannot be null.");
        }
        return redissonManager.putMapValue(sessionKey, id, session);
    }

}
