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
package io.github.guoshiqiufeng.cloud.stream.binder.redis.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * @author yanghq
 * @version 1.0
 * @since 2024/9/2 14:37
 */
class RedisConnectionFactoryUtilTest {

    @Test
    void getRedisConnectionFactoryForNull() {
        try {
            RedisConnectionFactoryUtil.getRedisConnectionFactory(null);
        } catch (Exception e) {
            assert e instanceof IllegalArgumentException;
        }
    }

    @Test
    void getRedisConnectionFactoryForNullClientTypeOrLettuce() {
        RedisProperties redisProperties = new RedisProperties();
        redisProperties.setClientType(null);
        RedisConnectionFactory redisConnectionFactory = RedisConnectionFactoryUtil.getRedisConnectionFactory(redisProperties);
        assert redisConnectionFactory instanceof LettuceConnectionFactory;
        redisProperties.setClientType(RedisProperties.ClientType.LETTUCE);
        redisConnectionFactory = RedisConnectionFactoryUtil.getRedisConnectionFactory(redisProperties);
        assert redisConnectionFactory instanceof LettuceConnectionFactory;
    }

    @Test
    void getRedisConnectionFactoryForJedis() {
        RedisProperties redisProperties = new RedisProperties();
        redisProperties.setClientType(RedisProperties.ClientType.JEDIS);
        RedisConnectionFactory redisConnectionFactory = RedisConnectionFactoryUtil.getRedisConnectionFactory(redisProperties);
        assert redisConnectionFactory instanceof JedisConnectionFactory;
    }

}
