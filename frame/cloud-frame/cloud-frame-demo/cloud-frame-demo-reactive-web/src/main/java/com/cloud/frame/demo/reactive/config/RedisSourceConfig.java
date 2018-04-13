package com.cloud.frame.demo.reactive.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.ReactiveRedisConnection;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;

/**
 * Created by wd on 2018/4/12.
 */
@Configuration
public class RedisSourceConfig {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;

//    @Bean
//    public LettuceConnectionFactory lettuceConnectionFactory() {
//        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(host,port);
//        return lettuceConnectionFactory;
//    }

    private LettuceConnectionFactory lettuceConnectionFactory() {
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
                .master("master")
                .sentinel(host, port);
        // .sentinel("127.0.0.1", 26380);
        return new LettuceConnectionFactory(sentinelConfig);
    }

//    @Bean
//    public RedisConnectionFactory redisConnectionFactory(LettuceConnectionFactory lettuceConnectionFactory) {
//        return lettuceConnectionFactory;
//    }

    @Bean
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
        return lettuceConnectionFactory();
    }

    @Bean
    public ReactiveRedisConnection reactiveRedisConnection(final ReactiveRedisConnectionFactory redisConnectionFactory) {
        return redisConnectionFactory.getReactiveConnection();
    }

    @Bean
    public ReactiveRedisTemplate<String, String> reactiveRedisTemplate(ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
        return new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory, RedisSerializationContext.string());
//        RedisSerializationContext redisSerializationContext = new RedisSerializationContext() {
//            @Override
//            public SerializationPair getKeySerializationPair() {
//                return SerializationPair.fromSerializer(new StringRedisSerializer());
//            }
//
//            @Override
//            public SerializationPair getValueSerializationPair() {
//                return SerializationPair.fromSerializer(new JsonRedisSerializer());
//            }
//
//            @Override
//            public SerializationPair getHashKeySerializationPair() {
//                return SerializationPair.fromSerializer(new StringRedisSerializer());
//            }
//
//            @Override
//            public SerializationPair getHashValueSerializationPair() {
//                return SerializationPair.fromSerializer(new JsonRedisSerializer());
//            }
//
//            @Override
//            public SerializationPair<String> getStringSerializationPair() {
//                return SerializationPair.fromSerializer(new JsonRedisSerializer());
//            }
//        };
//        return new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory, redisSerializationContext);
    }
}
