package com.cloud.frame.demo.reactive.router;

import com.cloud.frame.demo.reactive.entity.AccountInfo;
import com.cloud.frame.demo.reactive.handler.AccountInfoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by wd on 2018/4/11.
 */
@RestController
@RequestMapping(value = "/account")
public class AccountInfoRouter {

    @Autowired
    private AccountInfoHandler accountInfoHandler;


    @RequestMapping(value = "get/all", method = RequestMethod.GET)
    public Flux<AccountInfo> findAll() {
        return accountInfoHandler.findAll();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Mono<AccountInfo> findOne(@PathVariable("id") Long id) {
        return accountInfoHandler.get(id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Mono<Long> delete(@PathVariable("id") Long id) {
        return accountInfoHandler.delete(id);
    }

    @RequestMapping(value = "/set/redis/{id}", method = RequestMethod.GET)
    public Mono<Boolean> setRedis(@PathVariable("id") Long id) {
        return accountInfoHandler.setRedis(id);
    }


    @RequestMapping(value = "/get/redis/{id}", method = RequestMethod.GET)
    public Mono<AccountInfo> getRedis(@PathVariable("id") Long id) {
        return accountInfoHandler.getRedis(id);
    }
}
