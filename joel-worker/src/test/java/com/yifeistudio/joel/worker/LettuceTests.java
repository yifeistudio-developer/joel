package com.yifeistudio.joel.worker;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.Duration;

/**
 * @author yi
 * @since 2020/10/30-1:58 下午
 */
@Slf4j
public class LettuceTests {

    @Test
    public void operationTest() {
        RedisURI redisUri = new RedisURI("localhost", 6379, Duration.ofSeconds(5));
        RedisClient redisClient = RedisClient.create(redisUri);
        try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            RedisCommands<String, String> redisCommands = connection.sync();
            redisCommands.set("testKey", "testValue");
            String testValue = redisCommands.get("testKey");
            assert "testValue".equals(testValue);
            log.info("redis set-get test passed.");
            redisCommands.del("testKey");
            assert redisCommands.get("testKey") == null;
            log.info("redis del test passed.");
//            redisCommands.set("key", "value", SetArgs.Builder.ex());
        }
        redisClient.shutdown();
    }


}
