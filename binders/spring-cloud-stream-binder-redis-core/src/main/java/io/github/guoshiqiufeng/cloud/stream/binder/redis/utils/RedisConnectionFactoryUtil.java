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

import lombok.experimental.UtilityClass;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

/**
 * @author yanghq
 * @version 1.0
 * @since 2024/8/28 15:52
 */
@UtilityClass
public class RedisConnectionFactoryUtil {

    /**
     * get the RedisConnectionFactory
     *
     * @param redisProperties redisProperties
     * @return RedisConnectionFactory
     */
    public RedisConnectionFactory getRedisConnectionFactory(@NonNull RedisProperties redisProperties) {
        Assert.notNull(redisProperties,
                "'properties' must not be null");
        RedisProperties.ClientType clientType = redisProperties.getClientType();

        if (clientType == RedisProperties.ClientType.JEDIS) {
            // 使用 Jedis 作为 Redis 客户端
            JedisConnectionFactory jedisConnectionFactory = configureJedisClient(redisProperties);
            jedisConnectionFactory.start();
            return jedisConnectionFactory;
        } else {
            // 使用 Lettuce 作为 Redis 客户端
            LettuceConnectionFactory lettuceConnectionFactory = configureLettuceClient(redisProperties);
            lettuceConnectionFactory.start();
            return lettuceConnectionFactory;
        }
    }


    private JedisConnectionFactory configureJedisClient(RedisProperties redisProperties) {
        JedisClientConfiguration clientConfiguration = JedisClientConfiguration.builder()
                .usePooling()
                .poolConfig(createPoolConfig(redisProperties))
                .build();

        return Optional.ofNullable(getSentinelConfig(redisProperties))
                .map(sentinelConfig -> new JedisConnectionFactory(sentinelConfig, clientConfiguration))
                .orElseGet(() -> createJedisConnectionFactoryWithFallback(redisProperties, clientConfiguration));
    }

    private GenericObjectPoolConfig<RedisProperties.Jedis> createPoolConfig(RedisProperties redisProperties) {
        GenericObjectPoolConfig<RedisProperties.Jedis> poolConfig = new GenericObjectPoolConfig<>();
        BeanUtils.copyProperties(redisProperties.getJedis().getPool(), poolConfig);
        return poolConfig;
    }

    private JedisConnectionFactory createJedisConnectionFactoryWithFallback(RedisProperties redisProperties, JedisClientConfiguration clientConfiguration) {
        RedisClusterConfiguration clusterConfig = getClusterConfiguration(redisProperties);
        if (clusterConfig != null) {
            return new JedisConnectionFactory(clusterConfig, clientConfiguration);
        } else {
            RedisStandaloneConfiguration standaloneConfig = getStandaloneConfig(redisProperties);
            return new JedisConnectionFactory(standaloneConfig, clientConfiguration);
        }
    }


    private LettuceConnectionFactory configureLettuceClient(RedisProperties redisProperties) {
        LettuceClientConfiguration clientConfiguration = LettuceClientConfiguration.builder()
                .build();

        return Optional.ofNullable(getSentinelConfig(redisProperties))
                .map(sentinelConfig -> new LettuceConnectionFactory(sentinelConfig, clientConfiguration))
                .orElseGet(() -> createLettuceConnectionFactoryWithFallback(redisProperties, clientConfiguration));
    }

    private LettuceConnectionFactory createLettuceConnectionFactoryWithFallback(RedisProperties redisProperties, LettuceClientConfiguration clientConfiguration) {
        RedisClusterConfiguration clusterConfig = getClusterConfiguration(redisProperties);
        if (clusterConfig != null) {
            return new LettuceConnectionFactory(clusterConfig, clientConfiguration);
        } else {
            RedisStandaloneConfiguration standaloneConfig = getStandaloneConfig(redisProperties);
            return new LettuceConnectionFactory(standaloneConfig, clientConfiguration);
        }
    }

    private static RedisSentinelConfiguration getSentinelConfig(RedisProperties redisProperties) {
        if (redisProperties.getSentinel() != null) {
            RedisSentinelConfiguration config = new RedisSentinelConfiguration();
            config.master(redisProperties.getSentinel().getMaster());
            config.setSentinels(createSentinels(redisProperties.getSentinel()));
            config.setUsername(redisProperties.getUsername());
            String password = redisProperties.getPassword();
            if (password != null) {
                config.setPassword(RedisPassword.of(password));
            }
            config.setSentinelUsername(redisProperties.getSentinel().getUsername());
            String sentinelPassword = redisProperties.getSentinel().getPassword();
            if (sentinelPassword != null) {
                config.setSentinelPassword(RedisPassword.of(sentinelPassword));
            }
            config.setDatabase(redisProperties.getDatabase());
            return config;
        }
        return null;
    }

    private RedisClusterConfiguration getClusterConfiguration(RedisProperties redisProperties) {
        RedisProperties.Cluster clusterProperties = redisProperties.getCluster();
        if (redisProperties.getCluster() != null) {
            RedisClusterConfiguration config = new RedisClusterConfiguration();
            config.setClusterNodes(getNodes(redisProperties.getCluster()));
            if (clusterProperties != null && clusterProperties.getMaxRedirects() != null) {
                config.setMaxRedirects(clusterProperties.getMaxRedirects());
            }
            config.setUsername(redisProperties.getUsername());
            String password = redisProperties.getPassword();
            if (password != null) {
                config.setPassword(RedisPassword.of(password));
            }
            return config;
        }
        return null;
    }

    private List<RedisNode> getNodes(RedisProperties.Cluster cluster) {
        return cluster.getNodes().stream().map(RedisNode::fromString).toList();
    }


    private RedisStandaloneConfiguration getStandaloneConfig(RedisProperties redisProperties) {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(redisProperties.getHost());
        config.setPort(redisProperties.getPort());
        config.setUsername(redisProperties.getUsername());
        config.setPassword(RedisPassword.of(redisProperties.getPassword()));
        config.setDatabase(redisProperties.getDatabase());
        return config;
    }

    private List<RedisNode> createSentinels(RedisProperties.Sentinel sentinel) {
        return sentinel.getNodes().stream().map(RedisNode::fromString).toList();
    }

}
