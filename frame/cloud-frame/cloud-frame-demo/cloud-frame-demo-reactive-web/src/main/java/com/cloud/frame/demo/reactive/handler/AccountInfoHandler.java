package com.cloud.frame.demo.reactive.handler;

import com.cloud.frame.demo.reactive.entity.AccountInfo;
import com.cloud.frame.demo.reactive.mapper.AccountInfoMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ReactiveRedisConnection;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;
import java.util.Date;

/**
 * mysql 不支持 reactive 编程，需要转换一下，但是这样事物
 * Created by wd on 2018/4/11.
 */
@Component
@Slf4j
public class AccountInfoHandler {

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    @Autowired
    private ReactiveRedisConnection reactiveRedisConnection;

    @Autowired
    private Gson gson;


    public Flux<AccountInfo> findAll() {
        return Flux.create(sink -> {
            accountInfoMapper.findAll().forEach(accountInfo -> {
                sink.next(accountInfo);
            });
            sink.complete();
        });
    }

    public Mono<AccountInfo> get(Long id) {
        return Mono.create(sink -> sink.success(accountInfoMapper.findById(id)));
    }

    public Mono<Long> delete(Long id) {
        return Mono.create(sink -> sink.success(accountInfoMapper.delete(id)));
    }

    public Mono<Boolean> setRedis(Long id) {
        AccountInfo info = new AccountInfo();
        info.setId(1l);
        info.setCreateTime(new Date());
        Mono<Boolean> booleanMono = reactiveRedisConnection.stringCommands().set(ByteBuffer.wrap(id.toString().getBytes()),
                ByteBuffer.wrap(gson.toJson(info).getBytes()));
        return booleanMono;
    }

    public Mono<AccountInfo> getRedis(Long id) {
        Mono<AccountInfo> infoMono = reactiveRedisConnection.stringCommands()
                .get(ByteBuffer.wrap(id.toString().getBytes()))
                .flatMap(value -> {
                    AccountInfo accountInfo = gson.fromJson(new String(value.array()), AccountInfo.class);
                    return Mono.create(sink -> sink.success(accountInfo));
                });
        return infoMono;
//        return Mono.create(sink -> sink.success("sdfasdfasd"));
    }
}
