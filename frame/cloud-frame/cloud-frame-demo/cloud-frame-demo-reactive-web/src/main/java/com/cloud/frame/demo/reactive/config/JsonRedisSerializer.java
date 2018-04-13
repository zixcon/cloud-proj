package com.cloud.frame.demo.reactive.config;

import com.google.gson.Gson;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by wd on 2018/4/12.
 */
//public class JsonRedisSerializer<T> implements RedisSerializer<T> {
//
//    private final Charset charset;
//    private final Gson gson;
//
//    public JsonRedisSerializer() {
//        this(StandardCharsets.UTF_8);
//    }
//
//    public JsonRedisSerializer(Charset charset) {
//        Assert.notNull(charset, "Charset must not be null!");
//        this.charset = charset;
//        gson = new Gson();
//    }
//
//    @Nullable
//    @Override
//    public byte[] serialize(@Nullable T t) throws SerializationException {
//        return t == null ? null : gson.toJson(t).getBytes(this.charset);
//    }
//
//    @Nullable
//    @Override
//    public T deserialize(@Nullable byte[] bytes) throws SerializationException {
//        return bytes == null ? null : gson.fromJson(new String(bytes), T);
//    }
//}
