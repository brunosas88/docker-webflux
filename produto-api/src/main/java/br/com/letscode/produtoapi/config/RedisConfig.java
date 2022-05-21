package br.com.letscode.produtoapi.config;


import br.com.letscode.produtoapi.produto.dto.ProdutoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;


@AutoConfigureAfter(RedisAutoConfiguration.class)
@Configuration
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String REDIS_HOST;
    @Value("${spring.redis.port}")
    private Integer REDIS_PORT;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {

        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(REDIS_HOST, REDIS_PORT);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(config);
//        jedisConnectionFactory.getPoolConfig().setMaxIdle(30);
//        jedisConnectionFactory.getPoolConfig().setMinIdle(10);
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, ProdutoDTO> redisTemplate() {
        RedisTemplate<String, ProdutoDTO> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());

        RedisSerializer<String> serializer = new StringRedisSerializer();
        template.setKeySerializer(serializer);

        return template;
    }

//    @PostConstruct
//    public void clearCache() {
//        System.out.println("In Clear Cache");
//        Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT, 1000);
//        jedis.flushAll();
//        jedis.close();
//    }

}
