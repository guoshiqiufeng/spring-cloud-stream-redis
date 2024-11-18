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
package io.github.guoshiqiufeng.cloud.stream.binder.redis.config;

import io.github.guoshiqiufeng.cloud.stream.binder.redis.health.RedisBinderHealthIndicator;
import org.springframework.boot.actuate.autoconfigure.health.ConditionalOnEnabledHealthIndicator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * Configuration for Redis binder health indicators.
 *
 * @author yanghq
 * @version 1.0
 * @since 2024/11/17 10:55
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(name = "org.springframework.boot.actuate.health.HealthIndicator")
public class RedisBinderHealthIndicatorConfiguration {

    @Bean
    @ConditionalOnEnabledHealthIndicator("redis")
    public RedisBinderHealthIndicator redisBinderHealthIndicator(RedisConnectionFactory connectionFactory) {
        return new RedisBinderHealthIndicator(connectionFactory);
    }
}
