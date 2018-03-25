package com.cloud.frame.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@EnableZipkinServer
@SpringBootApplication
public class CloudFrameZipkinApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudFrameZipkinApplication.class, args);
    }

// sping boot 2.0 暂不支持 embeddedundertow启动，默认写死了UndertowEmbeddedServletContainerFactory
//    @Bean
//    public UndertowEmbeddedServletContainerFactory embeddedServletContainerFactory() {
//        UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory();
//        factory.addBuilderCustomizers(
//                builder -> builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true));
//        return factory;
//    }
}
