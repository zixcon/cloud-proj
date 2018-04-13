package com.cloud.frame.demo.reactive.router;

import com.cloud.frame.demo.reactive.handler.DemoReactiveHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.ipc.netty.http.server.HttpServer;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;

/**
 * Created by wd on 2018/4/10.
 */
@Configuration
public class DemoReactiveRouter { //implements CommandLineRunner {

    @Autowired
    private DemoReactiveHandler demoReactiveHandler;

    /**
     * 注册自定义RouterFunction
     */
    @Bean
    public RouterFunction<ServerResponse> demoRouter() {
        RouterFunction<ServerResponse> router = route(GET("/reactive/restaurants").and(accept(APPLICATION_JSON_UTF8)), demoReactiveHandler::findAll)
                .andRoute(GET("/reactive/delay/restaurants").and(accept(APPLICATION_JSON_UTF8)), demoReactiveHandler::findAllDelay)
                .andRoute(GET("/reactive/restaurants/{id}").and(accept(APPLICATION_JSON_UTF8)), demoReactiveHandler::get)
                .andRoute(POST("/reactive/restaurants").and(accept(APPLICATION_JSON_UTF8)).and(contentType(APPLICATION_JSON_UTF8)), demoReactiveHandler::create)
                .andRoute(DELETE("/reactive/restaurants/{id}").and(accept(APPLICATION_JSON_UTF8)), demoReactiveHandler::delete)
                // 注册自定义HandlerFilterFunction
                .filter((request, next) -> {
                    if (HttpMethod.PUT.equals(request.method())) {
                        return ServerResponse.status(HttpStatus.BAD_REQUEST).build();
                    }
                    return next.handle(request);
                });
        return router;
    }

//    @Override
//    public void run(String... strings) throws Exception {
//        RouterFunction<ServerResponse> router = demoRouter();
//        // 转化为通用的Reactive HttpHandler
//        HttpHandler handler = toHttpHandler(router);
//        // 适配成Netty Server所需的Handler
//        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler);
//        // 创建Netty Server
//        HttpServer server = HttpServer.create("127.0.0.1", 9090);
//        // 注册Handler并启动Netty Server
//        server.newHandler(adapter).block();
//    }

}
