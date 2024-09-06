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

import io.github.guoshiqiufeng.cloud.stream.binder.redis.RedisMessageChannelBinder;
import io.github.guoshiqiufeng.cloud.stream.binder.redis.properties.RedisBinderConfigurationProperties;
import io.github.guoshiqiufeng.cloud.stream.binder.redis.properties.RedisExtendedBindingProperties;
import io.github.guoshiqiufeng.cloud.stream.binder.redis.provisioning.RedisTopicProvisioner;
import jakarta.annotation.Nullable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.binder.Binder;
import org.springframework.cloud.stream.config.ConsumerEndpointCustomizer;
import org.springframework.cloud.stream.config.ListenerContainerCustomizer;
import org.springframework.cloud.stream.config.MessageSourceCustomizer;
import org.springframework.cloud.stream.config.ProducerMessageHandlerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.integration.redis.inbound.RedisStoreMessageSource;

/**
 * @author yanghq
 * @version 1.0
 * @since 2024/8/28 10:55
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnMissingBean(Binder.class)
// @Import({RedisBinderHealthIndicatorConfiguration.class})
@EnableConfigurationProperties({RedisProperties.class, RedisExtendedBindingProperties.class})
public class RedisBinderConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.cloud.stream.redis.binder")
    RedisBinderConfigurationProperties configurationProperties(
            RedisProperties redisProperties) {
        return new RedisBinderConfigurationProperties(redisProperties);
    }


    @Bean
    RedisTopicProvisioner provisioningProvider(RedisBinderConfigurationProperties configurationProperties) {
        return new RedisTopicProvisioner(configurationProperties);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    RedisMessageChannelBinder redisMessageChannelBinder(
            RedisBinderConfigurationProperties configurationProperties,
            RedisTopicProvisioner provisioningProvider,
            @Nullable ListenerContainerCustomizer<RedisMessageListenerContainer> listenerContainerCustomizer,
            @Nullable MessageSourceCustomizer<RedisStoreMessageSource> sourceCustomizer,
            @Nullable ProducerMessageHandlerCustomizer<?> messageHandlerCustomizer,
            @Nullable ConsumerEndpointCustomizer<?> consumerCustomizer,
            RedisExtendedBindingProperties redisExtendedBindingProperties
    ) {

        RedisMessageChannelBinder redisMessageChannelBinder = new RedisMessageChannelBinder(
                configurationProperties, provisioningProvider,
                listenerContainerCustomizer, sourceCustomizer);
        redisMessageChannelBinder
                .setExtendedBindingProperties(redisExtendedBindingProperties);
        redisMessageChannelBinder.setProducerMessageHandlerCustomizer(messageHandlerCustomizer);
        redisMessageChannelBinder.setConsumerEndpointCustomizer(consumerCustomizer);
        return redisMessageChannelBinder;
    }


}
