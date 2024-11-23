package com.okccc.eshop.product.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @Author: okccc
 * @Date: 2024/5/21 18:19:53
 * @Desc: redis配置类
 */
@Configuration
public class RedisConfig {

    /**
     * 注册SpringCache缓存框架提供的CacheManager组件
     */
    @Bean
    public CacheManager cacheManager(LettuceConnectionFactory connectionFactory) {
        // redis缓存配置
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                // 过期时间
                .entryTtl(Duration.ofDays(7))
                // key序列化
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                // value序列化
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        // 构造RedisCacheManager对象
        RedisCacheManager cacheManager = RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config)
                .build();

        return cacheManager;
    }

}
