#### zuul整合swagger2

1. zuul yml配置：

        zuul:
          routes:
            ucenter:
              # prefix: /ucenter
              path: /ucenter/** # 路由路径
              serviceId: ucenter
              strip-prefix: true # 去掉前缀
2. zuul服务
    
        增加SwaggerConfig和SwaggerDocumentConfig配置类

3. ucenter服务
        
        增加swagger2配置类
        
整合失败问题解读：
    
    1. 访问失败：WARN：Unable to find specification for group
    原因是：
        ucenter服务swagger2配置增加了groupName
                new Docket(DocumentationType.SWAGGER_2)
                        .groupName("ucenter")
        需要在zuul服务需要指定访问组ucenter
                resources.add(swaggerResource("用户权限系统", "/ucenter/v2/api-docs?group=ucenter", "2.0"));
    2. 通过swagger2接口获取请求结果成功，但是页面展示：TypeError: Failed to fetch
       原因是Cors跨域设置问题
         2.1 通过WebMvcConfigurer的addCorsMappings设置的，不是全局有效
            //public class CorsConfig implements WebMvcConfigurer {
            
            //    @Override
            //    public void addCorsMappings(CorsRegistry registry) {
            //        registry.addMapping("/**")
            //                .allowedHeaders("*")
            //                .allowedOrigins("*")
            //                .allowedMethods("GET", "PUT", "POST", "DELETE", "OPTIONS", "HEAD", "PATCH", "TRACE")
            //                .allowCredentials(false)
            //                .maxAge(3600);
            //    }
            //}
         2.2 通过CorsFilter全局拦截才行
            @Configuration
            public class CorsConfig {
             @Bean
                public FilterRegistrationBean corsFilter() {
                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowCredentials(true);
                    // 设置你要允许的网站域名，如果全允许则设为 *
                    config.addAllowedOrigin("*");
                    // 如果要限制 HEADER 或 METHOD 请自行更改
                    config.addAllowedHeader("*");
                    config.addAllowedMethod("*");
                    source.registerCorsConfiguration("/**", config);
                    FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
                    // 这个顺序很重要哦，为避免麻烦请设置在最前
                    bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
                    return bean;
                }
             }
    3. swagger2整合后无法通过zuul路由请求问题
         子系统中，需要设置hosts
         return new Docket(DocumentationType.SWAGGER_2)
                         // swagger2 和 zuul整合后，需要访问/v2/api-docs,这里设置了groupName 那么就需要指定/v2/api-docs?group=ucenter
                         .groupName("ucenter")
                         // swagger2 和 zuul整合后，通过zuul路由请求
                         .host("127.0.0.1:8765/ucenter")
                         .apiInfo(apiInfo())
                         .select()
                         .apis(RequestHandlerSelectors.basePackage(basePackage))
                         .paths(PathSelectors.any())
                         .build()
                         .globalOperationParameters(pars);
    4. Resolved exception caused by Handler execution: org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'POST' not supported
          这个问题是zuul路由配置的问题，导致没有匹配的url请求导致的
    
    5. boot 2.0.1 版本
        boot 2.0.1 及以前版本需要 增加 host配置，指定前缀ucenter进行附加，但是2.0.2版本就不需要，不然swagger2请求就会多一个ucenter请求
        return new Docket(DocumentationType.SWAGGER_2)
                        .groupName("ucenter")
                        // boot 2.0.1 及以前版本需要 增加 host配置，使得swagger2 和 zuul整合后，通过zuul路由请求
                        // .host("127.0.0.1:8765/ucenter")
                        .apiInfo(apiInfo())
                        .select()
                        .apis(RequestHandlerSelectors.basePackage(basePackage))
                        .paths(PathSelectors.any())
                        .build()
                        .globalOperationParameters(pars);
    
    6. zuul 增加日志追踪标示traceId
        总是无法有效传递，查看源码发现：name.toLowerCase() 全部转换为小写了
            public void addZuulRequestHeader(String name, String value) {
                    this.getZuulRequestHeaders().put(name.toLowerCase(), value);
            }
    
    7. feign 调用微服务转发不了request请求头和参数问题解决方案
        
           7.1 参见FeignConfig服务,需进行装配
           7.2 无法通过当前线程获取request对象
                这两种都获取的为空：
                    RequestContext ctx = RequestContext.getCurrentContext();
                    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                解决办法：
                    请求进入时放在线程中，让其当前线程和子线程都能取得，
                    这里必须时InheritableThreadLocal，ThreadLocal去不到数据。原因是Feign走的是独立线程
                    ThreadLocal<HttpServletRequest> REQUEST_LOCAL = new InheritableThreadLocal<>();
                    
                