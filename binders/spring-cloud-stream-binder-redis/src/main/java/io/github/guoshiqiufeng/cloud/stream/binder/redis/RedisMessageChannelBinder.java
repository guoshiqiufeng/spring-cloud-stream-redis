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

import io.github.guoshiqiufeng.cloud.stream.binder.redis.properties.RedisBinderConfigurationProperties;
import io.github.guoshiqiufeng.cloud.stream.binder.redis.properties.RedisConsumerProperties;
import io.github.guoshiqiufeng.cloud.stream.binder.redis.properties.RedisExtendedBindingProperties;
import io.github.guoshiqiufeng.cloud.stream.binder.redis.properties.RedisProducerProperties;
import io.github.guoshiqiufeng.cloud.stream.binder.redis.provisioning.RedisTopicProvisioner;
import io.github.guoshiqiufeng.cloud.stream.binder.redis.utils.RedisConnectionFactoryUtil;
import lombok.Setter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.cloud.stream.binder.*;
import org.springframework.cloud.stream.config.ListenerContainerCustomizer;
import org.springframework.cloud.stream.config.MessageSourceCustomizer;
import org.springframework.cloud.stream.provisioning.ConsumerDestination;
import org.springframework.cloud.stream.provisioning.ProducerDestination;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.redis.inbound.RedisInboundChannelAdapter;
import org.springframework.integration.redis.inbound.RedisQueueMessageDrivenEndpoint;
import org.springframework.integration.redis.outbound.RedisPublishingMessageHandler;
import org.springframework.integration.redis.outbound.RedisQueueOutboundChannelAdapter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.UUID;

/**
 * @author yanghq
 * @version 1.0
 * @since 2024/8/28 13:53
 */
public class RedisMessageChannelBinder extends
        AbstractMessageChannelBinder<ExtendedConsumerProperties<RedisConsumerProperties>,
                ExtendedProducerProperties<RedisProducerProperties>, RedisTopicProvisioner>
        implements
        ExtendedPropertiesBinder<MessageChannel, RedisConsumerProperties, RedisProducerProperties> {

    private final RedisBinderConfigurationProperties configurationProperties;

    @Setter
    private RedisExtendedBindingProperties extendedBindingProperties = new RedisExtendedBindingProperties();

    public RedisMessageChannelBinder(RedisBinderConfigurationProperties configurationProperties,
                                     RedisTopicProvisioner provisioningProvider,
                                     ListenerContainerCustomizer<?> containerCustomizer,
                                     MessageSourceCustomizer<?> sourceCustomizer
    ) {
        super(headersToMap(configurationProperties), provisioningProvider,
                containerCustomizer, sourceCustomizer);
        this.configurationProperties = configurationProperties;
    }

    private static String[] headersToMap(
            RedisBinderConfigurationProperties configurationProperties) {
        String[] headersToMap;
        if (ObjectUtils.isEmpty(configurationProperties.getHeaders())) {
            headersToMap = BinderHeaders.STANDARD_HEADERS;
        } else {
            String[] combinedHeadersToMap = Arrays.copyOfRange(
                    BinderHeaders.STANDARD_HEADERS, 0,
                    BinderHeaders.STANDARD_HEADERS.length
                            + configurationProperties.getHeaders().length);
            System.arraycopy(configurationProperties.getHeaders(), 0,
                    combinedHeadersToMap, BinderHeaders.STANDARD_HEADERS.length,
                    configurationProperties.getHeaders().length);
            headersToMap = combinedHeadersToMap;
        }
        return headersToMap;
    }

    @Override
    protected MessageHandler createProducerMessageHandler(ProducerDestination destination,
                                                          ExtendedProducerProperties<RedisProducerProperties> producerProperties,
                                                          MessageChannel errorChannel) throws Exception {
        throw new IllegalStateException(
                "The abstract binder should not call this method");
    }

    @Override
    protected MessageHandler createProducerMessageHandler(ProducerDestination destination, ExtendedProducerProperties<RedisProducerProperties> producerProperties, MessageChannel channel, MessageChannel errorChannel) throws Exception {

        RedisConnectionFactory connectionFactory = RedisConnectionFactoryUtil.getRedisConnectionFactory(
                configurationProperties.getConfiguration());

        AbstractApplicationContext applicationContext = getApplicationContext();
        BeanFactory beanFactory = applicationContext.getBeanFactory();

        RedisBinderConfigurationProperties.SupportType supportType = configurationProperties.getSupportType();
        if (supportType == null || supportType.equals(RedisBinderConfigurationProperties.SupportType.PUBLISH_SUBSCRIBE_CHANNEL)) {
            RedisPublishingMessageHandler handler = new RedisPublishingMessageHandler(connectionFactory);
            handler.setTopic(destination.getName());
            handler.setApplicationContext(applicationContext);
            handler.setBeanFactory(beanFactory);
            return handler;
        } else {
            RedisQueueOutboundChannelAdapter handler = new RedisQueueOutboundChannelAdapter(destination.getName(), connectionFactory);
            // handler.set
            handler.setApplicationContext(applicationContext);
            handler.setBeanFactory(beanFactory);
            return handler;
        }
    }

    @Override
    protected MessageProducer createConsumerEndpoint(ConsumerDestination destination, String group,
                                                     ExtendedConsumerProperties<RedisConsumerProperties> extendedConsumerProperties) {
        boolean anonymous = !StringUtils.hasText(group);

        String consumerGroup = anonymous ? "anonymous." + UUID.randomUUID() : group;

        int partitionCount = extendedConsumerProperties.getInstanceCount()
                * extendedConsumerProperties.getConcurrency();

        boolean usingPatterns = extendedConsumerProperties.getExtension().isDestinationIsPattern();
        Assert.isTrue(!usingPatterns || !extendedConsumerProperties.isMultiplex(),
                "Cannot use a pattern with multiplexed destinations; "
                        + "use the regex pattern to specify multiple topics instead");

        AbstractApplicationContext applicationContext = getApplicationContext();


        RedisConnectionFactory connectionFactory = RedisConnectionFactoryUtil.getRedisConnectionFactory(
                configurationProperties.getConfiguration());

        RedisBinderConfigurationProperties.SupportType supportType = configurationProperties.getSupportType();
        if (supportType == null || supportType.equals(RedisBinderConfigurationProperties.SupportType.PUBLISH_SUBSCRIBE_CHANNEL)) {
            RedisInboundChannelAdapter redisInboundChannelAdapter = new RedisInboundChannelAdapter(connectionFactory);
            if (usingPatterns) {
                redisInboundChannelAdapter.setTopicPatterns(destination.getName());
            } else {
                redisInboundChannelAdapter.setTopics(destination.getName());
            }
            redisInboundChannelAdapter.setBeanName(extendedConsumerProperties.getBindingName());
            redisInboundChannelAdapter.setBeanFactory(getBeanFactory());
            redisInboundChannelAdapter.setApplicationContext(applicationContext);
            return redisInboundChannelAdapter;
        } else {
            Assert.isTrue(!usingPatterns,
                    "Cannot use a pattern with queued channel; "
                            + "Please use the PUBLISH_SUBSCRIBE_CHANNEL support type, or turn off the regex pattern.");

            RedisQueueMessageDrivenEndpoint redisQueueMessageDrivenEndpoint = new RedisQueueMessageDrivenEndpoint(destination.getName(), connectionFactory);
            redisQueueMessageDrivenEndpoint.setBeanName(extendedConsumerProperties.getBindingName());
            redisQueueMessageDrivenEndpoint.setBeanFactory(getBeanFactory());
            redisQueueMessageDrivenEndpoint.setApplicationContext(applicationContext);
            return redisQueueMessageDrivenEndpoint;
        }

    }

    @Override
    public RedisConsumerProperties getExtendedConsumerProperties(String channelName) {
        return this.extendedBindingProperties.getExtendedConsumerProperties(channelName);
    }

    @Override
    public RedisProducerProperties getExtendedProducerProperties(String channelName) {
        return this.extendedBindingProperties.getExtendedProducerProperties(channelName);
    }

    @Override
    public String getDefaultsPrefix() {
        return this.extendedBindingProperties.getDefaultsPrefix();
    }

    @Override
    public Class<? extends BinderSpecificPropertiesProvider> getExtendedPropertiesEntryClass() {
        return this.extendedBindingProperties.getExtendedPropertiesEntryClass();
    }

}
