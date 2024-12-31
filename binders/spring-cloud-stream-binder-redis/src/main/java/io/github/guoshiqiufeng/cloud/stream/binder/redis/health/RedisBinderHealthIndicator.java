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

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.actuate.data.redis.RedisHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;

/**
 * Health indicator for Redis binder.
 *
 * @author yanghq
 * @version 1.0
 * @since 2024/11/17 10:00
 */
public class RedisBinderHealthIndicator extends RedisHealthIndicator {

    private final RedisConnectionFactory connectionFactory;

    public RedisBinderHealthIndicator(RedisConnectionFactory connectionFactory) {
        super(connectionFactory);
        this.connectionFactory = connectionFactory;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        try {
            // 基础连接检查
            super.doHealthCheck(builder);

            // 连接池状态检查
            checkConnectionPoolStatus(builder);

        } catch (Exception e) {
            builder.down().withException(e);
        }
    }

    private void checkConnectionPoolStatus(Health.Builder builder) {
        if (connectionFactory instanceof LettuceConnectionFactory lettuceFactory) {
            LettuceClientConfiguration clientConfiguration = lettuceFactory.getClientConfiguration();
            if (clientConfiguration instanceof LettucePoolingClientConfiguration poolConfig) {
                GenericObjectPoolConfig<?> pool = poolConfig.getPoolConfig();
                builder.withDetail("pool.maxTotal", pool.getMaxTotal())
                        .withDetail("pool.maxIdle", pool.getMaxIdle())
                        .withDetail("pool.minIdle", pool.getMinIdle());
            } else {
                builder.withDetail("pool", "Lettuce connection pool is not configured.");
            }
        } else if (connectionFactory instanceof JedisConnectionFactory jedisFactory) {
            JedisClientConfiguration clientConfiguration = jedisFactory.getClientConfiguration();
            clientConfiguration.getPoolConfig().ifPresent(pool -> {
                builder.withDetail("pool.maxTotal", pool.getMaxTotal())
                        .withDetail("pool.maxIdle", pool.getMaxIdle())
                        .withDetail("pool.minIdle", pool.getMinIdle());
            });
        }
    }
}
