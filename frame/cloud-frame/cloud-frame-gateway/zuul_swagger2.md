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
    
    访问失败：WARN：Unable to find specification for group
    原因是：
        ucenter服务swagger2配置增加了groupName
                new Docket(DocumentationType.SWAGGER_2)
                        .groupName("ucenter")
        需要在zuul服务需要指定访问组ucenter
                resources.add(swaggerResource("用户权限系统", "/ucenter/v2/api-docs?group=ucenter", "2.0"));

        