package com.cloud.frame.demo.auth.config;

import com.cloud.frame.demo.auth.service.FrameUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by wd on 2018/3/30.
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //    @Autowired
//    private SecurityProperties securityProperties;
//    @Autowired
//    private FrameAuthenticationSuccessHandler frameAuthenticationSuccessHandler;  //设置自定义成功处理器
//    @Autowired
//    private FrameAuthenticationFailureHandler frameAuthenticationFailureHandler;  //设置自定义失败处理器
    @Autowired
    private FrameUserDetailsService frameUserDetailsService;

//    @Override
//    protected UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user_1").password("123456").authorities("USER").build());
//        manager.createUser(User.withUsername("user_2").password("123456").authorities("USER").build());
//        return manager;
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();//图形验证码过滤器
//        validateCodeFilter.setAuthenticationFailureHander(imoocAuthenticationFailureHandler);
//        validateCodeFilter.setSecurityProperties(securityProperties);
//        validateCodeFilter.afterProspertiesSet();
//
//        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();//图形验证码过滤器
//        smsCodeFilter.setAuthenticationFailureHander(imoocAuthenticationFailureHandler);
//        smsCodeFilter.setSecurityProperties(securityProperties);
//        smsCodeFilter.afterPropertiesSet();
//
////        http.httpBasic().
//        http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
//                .formLogin()
//                .loginPage("/authentication/require")
//                .loginProcessingUrl("/authentication/form")   //自定义 登录url
//                .successHandler(frameAuthenticationSuccessHandler)  //设置自定义成功处理器
//                .failureHandler(frameAuthenticationFailureHandler) //设置自定义失败处理器
//                .and()
//                .rememberMe()
//                .tokenRepository(persistentTokenRepository())
//                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
//                .userDetailsService(userDetailsService)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/authentication/require",
//                        securityProperties.getBrowser().getLoginPage()
//                        , "/code/*").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and().csrf().disable()   // 禁用默认开启的跨站请求伪造
//                .apply(smsCodeAuthenticationSecurityConfig);
//    }


    /**
     * 密码加密类配置
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        /**
         * PasswordEncoder密码验证clientId的时候会报错，
         * 因为5.0新特性中需要在密码前方需要加上{Xxx}来判别。
         * 所以需要自定义一个类，重新BCryptPasswordEncoder的match方法。
         */
        return new BCryptPasswordEncoder() {
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
                String presentedPassword = passwordEncoder.encode(encodedPassword);
                return passwordEncoder.matches(rawPassword, presentedPassword);
            }
        };
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(frameUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        // 配置表单登录
//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage( "/login") //指定了登录页面的位置
//                .permitAll(); //允许所有用户访问这个页面。
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .csrf().disable()
                .httpBasic();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/favor.ioc");
    }

}
