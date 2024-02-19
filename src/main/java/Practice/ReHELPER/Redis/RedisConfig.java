package Practice.ReHELPER.Redis;

import Practice.ReHELPER.Config.YmlLoadFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


@EnableRedisRepositories(basePackages = "Practice.ReHELPER.Redis.Repository")
@Configuration
@PropertySource(value = "classpath:application.yml", factory = YmlLoadFactory.class)
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String redisHost;
    @Value("${spring.data.redis.port}")
    private int redisPort;

    //    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHost, redisPort);
//        return new LettuceConnectionFactory(redisStandaloneConfiguration);
//    }

//    @Bean
//    public RedisTemplate<?,?> redisTemplate() {
//        RedisTemplate<?,?> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory());
////        redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
//        return redisTemplate;
//    }

//    @Bean
//    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
//        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//                .disableCachingNullValues()
//                .entryTtl(Duration.ofMinutes(2))
//                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//        return RedisCacheManager.RedisCacheManagerBuilder
//                .fromConnectionFactory(connectionFactory)
//                .cacheDefaults(cacheConfiguration)
//                .disableCreateOnMissingCache()
//                .withCacheConfiguration("memberSpecDTO", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)))
//                .withCacheConfiguration("memberSpecHistoryDTO",RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)))
//                .withCacheConfiguration("memberDTO",RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)))
//                .withCacheConfiguration("calendarDTO",RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)))
//                .build();
//    }
//    @Bean
//    public RedisCacheManager cacheManager() {
//        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//                .computePrefixWith(CacheKeyPrefix.simple())
//    //                .disableCachingNullValues()
//                .entryTtl(Duration.ofMinutes(2))
//                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//
//        return RedisCacheManager
//                .RedisCacheManagerBuilder
//                .fromConnectionFactory(redisConnectionFactory())
//                .cacheDefaults(redisCacheConfiguration)
//                .build();
//    }
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisHost, redisPort);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(String.class));
        redisTemplate.setConnectionFactory(redisConnectionFactory());

        return redisTemplate;
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        redisCacheConfigurationMap.put("memberSpecDTO", redisCacheConfiguration.entryTtl(Duration.ofMinutes(2)));
        redisCacheConfigurationMap.put("memberSpecHistoryDTO", redisCacheConfiguration.entryTtl(Duration.ofMinutes(2)));

        return RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(connectionFactory)
                .cacheDefaults(redisCacheConfiguration)
                .withInitialCacheConfigurations(redisCacheConfigurationMap)
                .build();
    }


}