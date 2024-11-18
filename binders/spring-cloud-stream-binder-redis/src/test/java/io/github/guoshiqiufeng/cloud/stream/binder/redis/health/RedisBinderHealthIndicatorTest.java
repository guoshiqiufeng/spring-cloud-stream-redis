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
package io.github.guoshiqiufeng.cloud.stream.binder.redis.health;

import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisServerCommands;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author yanghq
 * @version 1.0
 * @since 2024/11/17 17:04
 */
class RedisBinderHealthIndicatorTest {

    private static final String REDIS_PONG_RESPONSE = "PONG";
    private static final String REDIS_VERSION_KEY = "redis_version";
    private static final String REDIS_MODE_KEY = "redis_mode";
    private static final String SERVER_INFO_SECTION = "server";

    @Test
    void healthUp() {
        RedisConnectionFactory connectionFactory = mock(RedisConnectionFactory.class);
        RedisConnection redisConnection = mock(RedisConnection.class);
        RedisServerCommands serverCommands = mock(RedisServerCommands.class);

        when(connectionFactory.getConnection()).thenReturn(redisConnection);
        when(redisConnection.ping()).thenReturn(REDIS_PONG_RESPONSE);
        when(redisConnection.serverCommands()).thenReturn(serverCommands);

        // Mock Redis info command response
        Properties serverInfo = new Properties();
        serverInfo.setProperty(REDIS_VERSION_KEY, "7.0.2");
        serverInfo.setProperty(REDIS_MODE_KEY, "standalone");
        when(serverCommands.info(SERVER_INFO_SECTION)).thenReturn(serverInfo);

        RedisBinderHealthIndicator healthIndicator = new RedisBinderHealthIndicator(connectionFactory);
        Health health = healthIndicator.health();

        assertThat(health.getStatus()).isEqualTo(Status.UP);
        assertThat(health.getDetails())
                .containsEntry("version", "7.0.2")
                .containsEntry("mode", "standalone");

        verify(redisConnection).close();
    }

    @Test
    void healthDown() {
        RedisConnectionFactory connectionFactory = mock(RedisConnectionFactory.class);
        RedisConnection redisConnection = mock(RedisConnection.class);

        when(connectionFactory.getConnection()).thenReturn(redisConnection);
        when(redisConnection.ping()).thenReturn("ERROR");

        RedisBinderHealthIndicator healthIndicator = new RedisBinderHealthIndicator(connectionFactory);
        Health health = healthIndicator.health();

        assertThat(health.getStatus()).isEqualTo(Status.DOWN);
        assertThat(health.getDetails())
                .containsEntry("ping", "ERROR");

        verify(redisConnection).close();
    }

    @Test
    void healthDownOnException() {
        RedisConnectionFactory connectionFactory = mock(RedisConnectionFactory.class);
        when(connectionFactory.getConnection())
                .thenThrow(new RuntimeException("Connection failed"));

        RedisBinderHealthIndicator healthIndicator = new RedisBinderHealthIndicator(connectionFactory);
        Health health = healthIndicator.health();

        assertThat(health.getStatus()).isEqualTo(Status.DOWN);
        assertThat(health.getDetails())
                .containsKey("error")
                .containsValue("java.lang.RuntimeException: Connection failed");
    }
}
