package com.cloud.frame.demo.auth.shiro;

import com.cloud.frame.demo.auth.shiro.stateless.JwtStatelessToken;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * 鉴权是无状态的不需要创建SESSION,所以需要对shiro的SubjectFactory做一下改造，并设置到SecurityManager
 * Created by wd on 2018/5/30.
 */
public class ShiroSubjectFactory extends DefaultWebSubjectFactory {

    @Override
    public Subject createSubject(SubjectContext context) {
        AuthenticationToken token = context.getAuthenticationToken();
//        if (null != token && token instanceof JwtStatelessToken) {
//            //不创建session
//            context.setSessionCreationEnabled(false);
//        }
        return super.createSubject(context);
    }
}