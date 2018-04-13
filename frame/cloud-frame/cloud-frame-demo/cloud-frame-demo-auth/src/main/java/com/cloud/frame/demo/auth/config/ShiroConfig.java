package com.cloud.frame.demo.auth.config;

import com.cloud.frame.demo.auth.manager.ShiroRedisCacheManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wd on 2018/4/3.
 */
@Configuration
public class ShiroConfig {
//
//    // Session超时时间，单位为毫秒
//    private long defaultExpireTime = 5000;
//
//    @Autowired
//    private ShiroRedisSessionDAO sessionDao;
//
//    @Bean
//    public DefaultWebSessionManager configWebSessionManager() {
//        DefaultWebSessionManager manager = new DefaultWebSessionManager();
//        manager.setCacheManager(cacheManager);// 加入缓存管理器
//        manager.setSessionDAO(sessionDao);// 设置SessionDao
//        manager.setDeleteInvalidSessions(true);// 删除过期的session
//        manager.setGlobalSessionTimeout(defaultExpireTime);// 设置全局session超时时间
//        manager.setSessionValidationSchedulerEnabled(true);// 是否定时检查session
//
//        return manager;
//    }
//
//    @Bean
//    public SecurityManager securityManager(DefaultWebSessionManager webSessionManager) {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        // 设置realm.
//        securityManager.setRealm(myShiroRealm());
//
//        // 注入缓存管理器;
//        securityManager.setCacheManager(cacheManager);// 这个如果执行多次，也是同样的一个对象;
//
//        // session管理器
//        securityManager.setSessionManager(webSessionManager);
//
//        //注入记住我管理器;
//        securityManager.setRememberMeManager(rememberMeManager());
//        return securityManager;
//    }


    @Bean(name = "shiroRedisCacheManager")
    public ShiroRedisCacheManager shiroRedisCacheManager(RedisTemplate redisTemplate) {
        ShiroRedisCacheManager shiroRedisCacheManager = new ShiroRedisCacheManager();
        shiroRedisCacheManager.setRedisTemplate(redisTemplate);
        return shiroRedisCacheManager;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        LifecycleBeanPostProcessor lifecycleBeanPostProcessor = new LifecycleBeanPostProcessor();
        return lifecycleBeanPostProcessor;
    }


    @Bean(name = "sessionValidationScheduler")
    public ExecutorServiceSessionValidationScheduler getExecutorServiceSessionValidationScheduler(DefaultWebSessionManager defaultWebSessionManager) {
        ExecutorServiceSessionValidationScheduler scheduler = new ExecutorServiceSessionValidationScheduler();
        scheduler.setInterval(50 * 1000);
        scheduler.setSessionManager(defaultWebSessionManager);
        return scheduler;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1);// 散列的次数，比如散列两次，相当于md5(md5(""));
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(MyRealm myRealm, DefaultWebSessionManager defaultWebSessionManager, ShiroRedisCacheManager shiroRedisCacheManager) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myRealm);
        <span style = "color:#FF0000;" >//defaultWebSecurityManager.setCacheManager(getEhCacheManager());
                defaultWebSecurityManager.setCacheManager(shiroRedisCacheManager);</span >
                defaultWebSecurityManager.setSessionManager(defaultWebSessionManager);
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager());
        return defaultWebSecurityManager;
    }

    @Bean(name = "rememberMeCookie")
    public SimpleCookie rememberMeCookie() {
        // 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        // <!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(60 * 60 * 24 * 30);
        return simpleCookie;
    }

    /**
     * cookie管理对象;
     *
     * @return
     */
    @Bean(name = "rememberMeManager")
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        byte[] cipherKey = Base64.decode("wGiHplamyXlVB11UXWol8g==");
        cookieRememberMeManager.setCipherKey(cipherKey);
        return cookieRememberMeManager;
    }

    public MyRealm myRealm(ShiroRedisCacheManager shiroRedisCacheManager) {
        MyRealm myRealm = new MyRealm();
        //启用缓存
        myRealm.setCachingEnabled(true);
        //启用授权缓存
        myRealm.setAuthorizationCachingEnabled(true);
        myRealm.setAuthorizationCacheName("shiro-AutorizationCache");
        //启用认证信息缓存
        myRealm.setAuthenticationCachingEnabled(true);
        myRealm.setAuthenticationCacheName("shiro-AuthenticationCache");
        myRealm.setCacheManager(shiroRedisCacheManager);
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myRealm;
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator getAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(defaultWebSecurityManager);
        return aasa;
    }


    @Bean(name = "sessionManager")
    public DefaultWebSessionManager defaultWebSessionManager(SessionDao sessionDao) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(1000 * 60 * 60 * 24 * 60);
//      //url中是否显示session Id
        sessionManager.setSessionIdUrlRewritingEnabled(false);
//      // 删除失效的session
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionValidationInterval(18000000);
        sessionManager.setSessionValidationScheduler(getExecutorServiceSessionValidationScheduler(sessionManager));
        //sessionManager.setSessionValidationScheduler(quartzSessionValidationScheduler2(sessionManager));
        //设置SessionIdCookie 导致认证不成功，不从新设置新的cookie,从sessionManager获取sessionIdCookie
        //sessionManager.setSessionIdCookie(simpleIdCookie());
        sessionManager.getSessionIdCookie().setName("session-z-id");
        sessionManager.getSessionIdCookie().setPath("/");
        sessionManager.getSessionIdCookie().setMaxAge(60 * 60 * 24 * 7);

        sessionManager.setSessionDAO(sessionDao);
        Collection<SessionListener> c = new ArrayList<>();
        c.add(new MyShiroSessionListener());
        sessionManager.setSessionListeners(c);

        return sessionManager;
    }

    @Bean
    public RedisUtil redisUtil(RedisTemplate<String,Object> redisTemplate) {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.setRedisTemplate(redisTemplate);
        return redisUtil;
    }

    @Bean
    public SessionDao sessionDao(RedisUtil redisUtil,ShiroRedisCacheManager shiroRedisCacheManager) {
        SessionDao sessionDao = new SessionDao();
        //设置缓存器的名称
        sessionDao.setActiveSessionsCacheName("shiro-activeSessionCache1");
        //注入缓存管理器默认的是ehcache缓存
        sessionDao.setCacheManager(shiroRedisCacheManager);
        //注入缓存管理器2(实现session由redis控制有多种方法，上一步是一种，下面这样写也行)
        sessionDao.setRedisUtil(redisUtil);
        return sessionDao;
    }

    @Bean(name = "filterRegistrationBean1")
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new DelegatingFilterProxy(
                "shiroFilter"));
        filterRegistrationBean
                .addInitParameter("targetFilterLifecycle", "true");
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/");
//      filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST,
//              DispatcherType.FORWARD, DispatcherType.INCLUDE,
//              DispatcherType.ERROR);
        return filterRegistrationBean;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(
            @Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        // SecurityUtils.setSecurityManager(defaultWebSecurityManager);
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/getIndex");
        shiroFilterFactoryBean.setUnauthorizedUrl("/login");
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String, Filter> filterMap1 = shiroFilterFactoryBean.getFilters();
        filterMap1.put("authc", new MyFormAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filterMap1);
        Map<String, String> filterMap = new LinkedHashMap<String, String>();
        filterMap.put("/static/**", "anon");
        filterMap.put("/api/**", "anon");
        filterMap.put("/logout", "anon");
        filterMap.put("/login", "authc");
        filterMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

}
