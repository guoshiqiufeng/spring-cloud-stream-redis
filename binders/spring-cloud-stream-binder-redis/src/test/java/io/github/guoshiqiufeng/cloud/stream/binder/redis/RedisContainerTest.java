/*
 * Copyright (c) 2023-2024, fubluesky (fubluesky@foxmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.guoshiqiufeng.cloud.stream.binder.redis;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.SocketOptions;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Duration;

/**
 * @author yanghq
 * @version 1.0
 * @since 2024/9/2 15:57
 */
@Testcontainers(disabledWithoutDocker = true)
public interface RedisContainerTest {

    GenericContainer<?> REDIS_CONTAINER = new GenericContainer<>("redis:7.0.2")
            .withExposedPorts(6379);

    @BeforeAll
    static void startContainer() {
        REDIS_CONTAINER.start();
    }

    static RedisProperties getRedisProperties() {
        RedisProperties redisProperties = new RedisProperties();
        redisProperties.setHost(REDIS_CONTAINER.getHost());
        redisProperties.setPort(REDIS_CONTAINER.getMappedPort(6379));
        return redisProperties;
    }

    static LettuceConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setPort(REDIS_CONTAINER.getFirstMappedPort());

        LettuceClientConfiguration clientConfiguration = LettuceClientConfiguration.builder()
                .clientOptions(
                        ClientOptions.builder()
                                .socketOptions(
                                        SocketOptions.builder()
                                                .connectTimeout(Duration.ofMillis(10000))
                                                .keepAlive(true)
                                                .build())
                                .build())
                .commandTimeout(Duration.ofSeconds(10000))
                .build();

        var connectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration, clientConfiguration);
        connectionFactory.afterPropertiesSet();
        return connectionFactory;
    }
}
