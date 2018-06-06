package com.cloud.frame.demo.auth.config;

import com.cloud.frame.demo.auth.shiro.RealmManager;
import com.cloud.frame.demo.auth.shiro.ShiroRedisCacheManager;
import com.cloud.frame.demo.auth.shiro.ShiroSubjectFactory;
import com.cloud.frame.demo.auth.shiro.stateless.JwtStatelessFilter;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wd on 2018/5/17.
 */
@Configuration
public class ShiroConfig {

    @Bean
    public FilterRegistrationBean delegatingFilterProxy(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
//        //拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("logout", "logout");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/user/login", "anon");
        // authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        filterChainDefinitionMap.put("/auth/login/**", "anon");
        filterChainDefinitionMap.put("/auth/register/**", "anon");
        filterChainDefinitionMap.put("/auth/shiro/**", "anon");
        filterChainDefinitionMap.put("/auth/user/**", "authc");
        filterChainDefinitionMap.put("/**", "authc");
//        filterChainDefinitionMap.put("/page/**", "authc");        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面          shiroFilterFactoryBean.setLoginUrl("/login.html");
////        shiroFilterFactoryBean.setUnauthorizedUrl("/page/fail.html");//未授权跳转
////        //登录成功跳转的链接 (这个不知道怎么用，我都是自己实现跳转的)
////        shiroFilterFactoryBean.setSuccessUrl("/page/main.html");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        Map<String, Filter> filterChainMap = new LinkedHashMap<>();
        filterChainMap.put("authc", jwtStatelessFilter());
        shiroFilterFactoryBean.setFilters(filterChainMap);
        return shiroFilterFactoryBean;
    }


    /**
     * 凭证匹配器
     * 由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     *
     * @return
     */
//    @Bean
//    public HashedCredentialsMatcher hashedCredentialsMatcher() {
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        //散列算法:这里使用MD5算法;
//        hashedCredentialsMatcher.setHashAlgorithmName("md5");
//        //散列的次数，比如散列两次，相当于 md5(md5(""));
//        hashedCredentialsMatcher.setHashIterations(3);
//        return hashedCredentialsMatcher;
//    }


    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public ShiroRedisCacheManager shiroRedisCacheManager() {
        return new ShiroRedisCacheManager();
    }

    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(1800);
        sessionManager.setCacheManager(shiroRedisCacheManager());
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setDeleteInvalidSessions(true);
        return sessionManager;
    }


//    @Bean
//    public UserAuthorizingRealm userAuthorizingRealm() {
//        UserAuthorizingRealm userAuthorizingRealm = new UserAuthorizingRealm();
//        //使用加密
//        userAuthorizingRealm.setCredentialsMatcher(hashedCredentialsMatcher());
//        return userAuthorizingRealm;
//    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager());
        return new AuthorizationAttributeSourceAdvisor();
    }

    @Bean
    public Authorizer authorizer() {
        ModularRealmAuthorizer authenticator = new ModularRealmAuthorizer();
        List<Realm> realmList = realmManager().getRealms();
        authenticator.setRealms(realmList);
        return authenticator;
    }

    @Bean
    public Authenticator authenticator() {
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        List<Realm> realmList = realmManager().getRealms();
        authenticator.setRealms(realmList);
        return authenticator;
    }

    @Bean
    public SubjectFactory subjectFactory() {
        return new ShiroSubjectFactory();
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setCacheManager(shiroRedisCacheManager());
        List<Realm> realmList = realmManager().getRealms();
        securityManager.setRealms(realmList);
        // 增加session过滤
        securityManager.setSubjectFactory(subjectFactory());
        // 认证 set realms
        securityManager.setAuthenticator(authenticator());
        // 授权 set realms
        securityManager.setAuthorizer(authorizer());
        return securityManager;
    }

    @Bean
    public RealmManager realmManager() {
        RealmManager realmManager = new RealmManager();
        return realmManager;
    }

    @Bean
    public JwtStatelessFilter jwtStatelessFilter(){
        JwtStatelessFilter jwtStatelessFilter = new JwtStatelessFilter();
        return jwtStatelessFilter;
    }

}
