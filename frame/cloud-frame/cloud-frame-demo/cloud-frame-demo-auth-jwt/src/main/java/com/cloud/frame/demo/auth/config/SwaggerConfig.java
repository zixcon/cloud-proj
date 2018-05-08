package com.cloud.frame.demo.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wd on 2018/5/3.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private String basePackage = "com.cloud.frame.demo.auth.api";
    @Value("${spring.application.name}")
    private String serviceName;

    @Autowired
    private JwtConfig jwtConfig;

    @Bean
    public Docket userApi() {
        //header中的ticket参数非必填，传空也可以
        ParameterBuilder ticketPar = new ParameterBuilder();
        ticketPar.name(jwtConfig.getHeader()).description("user token ticket")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        //根据每个方法名也知道当前方法在设置什么参数
        List<Parameter> pars = new ArrayList<>();
        pars.add(ticketPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
// swagger2 和 zuul整合后，需要访问/v2/api-docs,这里设置了groupName 那么就需要指定/v2/api-docs?group=ucenter
                .groupName("ucenter")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Spring 中使用Swagger2构建文档")
                .termsOfServiceUrl("http://127.0.0.1:9098")
                //创建人
                .contact(new Contact("demo-auth-jwt ", "http://127.0.0.1:9098", "zixcon.fan@outlook.com"))
                //版本号
                .version("0.0.1")
                .build();
    }

}
