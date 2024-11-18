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

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.util.Assert;

import java.util.Properties;

/**
 * Health indicator for Redis binder.
 *
 * @author yanghq
 * @version 1.0
 * @since 2024/11/17 10:00
 */
public class RedisBinderHealthIndicator extends AbstractHealthIndicator {

    private static final String REDIS_PONG_RESPONSE = "PONG";
    private static final String REDIS_VERSION_KEY = "redis_version";
    private static final String REDIS_MODE_KEY = "redis_mode";
    private static final String SERVER_INFO_SECTION = "server";

    private final RedisConnectionFactory connectionFactory;

    public RedisBinderHealthIndicator(RedisConnectionFactory connectionFactory) {
        super("Redis health check failed");
        Assert.notNull(connectionFactory, "RedisConnectionFactory must not be null");
        this.connectionFactory = connectionFactory;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        try (RedisConnection connection = connectionFactory.getConnection()) {
            String result = connection.ping();
            if (REDIS_PONG_RESPONSE.equals(result)) {
                Properties serverInfo = connection.serverCommands().info(SERVER_INFO_SECTION);
                if (serverInfo != null) {
                    String version = serverInfo.getProperty(REDIS_VERSION_KEY);
                    String mode = serverInfo.getProperty(REDIS_MODE_KEY);
                    builder.up()
                            .withDetail("version", version != null ? version : "unknown")
                            .withDetail("mode", mode != null ? mode : "unknown");
                } else {
                    builder.up()
                            .withDetail("version", "unknown")
                            .withDetail("mode", "unknown");
                }
            } else {
                builder.down()
                        .withDetail("ping", result);
            }
        } catch (Exception e) {
            builder.down()
                    .withException(e);
        }
    }
}
