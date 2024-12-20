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
package io.github.guoshiqiufeng.cloud.stream.binder.redis.properties;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author yanghq
 * @version 1.0
 * @since 2024/8/28 10:41
 */
public class RedisBinderConfigurationPropertiesTest {

    @Test
    void testDefaultProperties() {
        RedisProperties redisProperties = new RedisProperties();
        RedisBinderConfigurationProperties properties = new RedisBinderConfigurationProperties(redisProperties);

        assertEquals("localhost", properties.getConfiguration().getHost());
        assertEquals(6379, properties.getConfiguration().getPort());
    }

    @Test
    void testCustomProperties() {
        RedisProperties redisProperties = new RedisProperties();
        redisProperties.setHost("redis.example.com");
        redisProperties.setPort(6380);

        RedisBinderConfigurationProperties properties = new RedisBinderConfigurationProperties(redisProperties);

        assertEquals("redis.example.com", properties.getConfiguration().getHost());
        assertEquals(6380, properties.getConfiguration().getPort());
    }
}
