package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    private StringRedisTemplate redisTemplate;

    //设置key-value和超时时间
    public void set(String key, String value, long timeout){
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    //返回key对应的value
    public String get(String key){
        return redisTemplate.opsForValue().get(key);
    }
}
