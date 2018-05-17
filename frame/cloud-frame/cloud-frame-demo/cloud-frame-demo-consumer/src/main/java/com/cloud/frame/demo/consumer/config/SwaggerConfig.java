package com.cloud.frame.demo.consumer.config;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
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

    private String basePackage = "com.cloud.frame.demo.consumer.controller";

    @Bean
    public Docket userApi() {
        //header中的ticket参数非必填，传空也可以
        ParameterBuilder ticketPar = new ParameterBuilder();
        ticketPar.name("Authorization").description("user token ticket")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        List<Parameter> pars = new ArrayList<>();
        pars.add(ticketPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("demo-consumer")
                // .host("127.0.0.1:8765/consumer1")
                .apiInfo(apiInfo())
                .produces(Sets.newHashSet(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("demo-consumer")
                .termsOfServiceUrl("http://127.0.0.1:8091")
                //创建人
                .contact(new Contact("demo-consumer", "http://127.0.0.1:8091", "zixcon.fan@outlook.com"))
                //版本号
                .version("0.0.1")
                .build();
    }

}
