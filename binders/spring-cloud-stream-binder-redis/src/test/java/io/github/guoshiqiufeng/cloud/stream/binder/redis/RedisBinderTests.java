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
import io.github.guoshiqiufeng.cloud.stream.binder.redis.properties.RedisProducerProperties;
import io.github.guoshiqiufeng.cloud.stream.binder.redis.provisioning.RedisTopicProvisioner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cloud.stream.binder.*;
import org.springframework.cloud.stream.config.BindingProperties;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.util.Assert;
import org.springframework.util.MimeTypeUtils;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author yanghq
 * @version 1.0
 * @since 2024/9/2 17:04
 */
public class RedisBinderTests extends
        PartitionCapableBinderTests<AbstractRedisTestBinder, ExtendedConsumerProperties<RedisConsumerProperties>,
                ExtendedProducerProperties<RedisProducerProperties>> implements RedisContainerTest {

    private static RedisProperties redisProperties;

    private final String CLASS_UNDER_TEST_NAME = RedisMessageChannelBinder.class
            .getSimpleName();

    private RedisTestBinder binder;

    @BeforeAll
    static void setupConnection() {
        redisProperties = RedisContainerTest.getRedisProperties();
    }

    /**
     * ----------------------test start------------------------
     **/

    @Test
    @Override
    @SuppressWarnings("unchecked")
    public void testSendAndReceiveNoOriginalContentType(TestInfo testInfo) throws Exception {
        Binder binder = getBinder();
        BindingProperties outputBindingProperties = createProducerBindingProperties(
                createProducerProperties(null));
        DirectChannel moduleOutputChannel = createBindableChannel("output",
                outputBindingProperties);
        ExtendedConsumerProperties<RedisConsumerProperties> consumerProperties = createConsumerProperties();
        DirectChannel moduleInputChannel = createBindableChannel("input",
                createConsumerBindingProperties(consumerProperties));

//        List<ChannelInterceptor> interceptors = moduleOutputChannel.getInterceptors();
//        interceptors.add(new ByteArrayMessageConverter());

        Binding<MessageChannel> producerBinding = binder.bindProducer("bar-0",
                moduleOutputChannel, outputBindingProperties.getProducer());

        Binding<MessageChannel> consumerBinding = binder.bindConsumer("bar-0",
                "testSendAndReceiveNoOriginalContentType", moduleInputChannel, consumerProperties);
        var message = org.springframework.integration.support.MessageBuilder
                .withPayload("foo")
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.TEXT_PLAIN)
                .build();

        // Let the consumer actually bind to the producer before sending a msg
        binderBindUnbindLatency();

        moduleOutputChannel.send(message);
        var latch = new CountDownLatch(1);
        AtomicReference<Message<byte[]>> inboundMessageRef = new AtomicReference<>();

        moduleInputChannel.subscribe(message1 -> {
            try {
                inboundMessageRef.set((Message<byte[]>) message1);
            } finally {
                latch.countDown();
            }
        });

        Assert.isTrue(latch.await(5, TimeUnit.SECONDS), "Failed to receive message");

        assertThat(inboundMessageRef.get()).isNotNull();

        assertThat(
                inboundMessageRef.get().getPayload())
                .isEqualTo("foo".getBytes());
        assertThat(inboundMessageRef.get().getHeaders().get(MessageHeaders.CONTENT_TYPE))
                .isEqualTo(MimeTypeUtils.TEXT_PLAIN);

        producerBinding.unbind();
        consumerBinding.unbind();
    }

    @Test
    @Override
    @SuppressWarnings("unchecked")
    public void testSendAndReceive(TestInfo testInfo) throws Exception {
        Binder binder = getBinder();
        BindingProperties outputBindingProperties = createProducerBindingProperties(
                createProducerProperties(null));
        DirectChannel moduleOutputChannel = createBindableChannel("output",
                outputBindingProperties);
        ExtendedConsumerProperties<RedisConsumerProperties> consumerProperties = createConsumerProperties();
        DirectChannel moduleInputChannel = createBindableChannel("input",
                createConsumerBindingProperties(consumerProperties));


        Binding<MessageChannel> producerBinding = binder.bindProducer("foo.bar",
                moduleOutputChannel, outputBindingProperties.getProducer());
        Binding<MessageChannel> consumerBinding = binder.bindConsumer("foo.bar",
                "testSendAndReceive", moduleInputChannel, consumerProperties);

        var message = org.springframework.integration.support.MessageBuilder
                .withPayload("foo".getBytes(StandardCharsets.UTF_8))
                .setHeader(MessageHeaders.CONTENT_TYPE,
                        MimeTypeUtils.APPLICATION_OCTET_STREAM)
                .build();

        // Let the consumer actually bind to the producer before sending a msg
        binderBindUnbindLatency();
        moduleOutputChannel.send(message);
        var latch = new CountDownLatch(1);
        AtomicReference<Message<byte[]>> inboundMessageRef = new AtomicReference<>();
        moduleInputChannel.subscribe(message1 -> {
            try {
                inboundMessageRef.set((Message<byte[]>) message1);
            } finally {
                latch.countDown();
            }
        });
        Assert.isTrue(latch.await(5, TimeUnit.SECONDS), "Failed to receive message");

        assertThat(inboundMessageRef.get()).isNotNull();
        assertThat(inboundMessageRef.get().getPayload())
                .isEqualTo("foo".getBytes());
        assertThat(inboundMessageRef.get().getHeaders().get(MessageHeaders.CONTENT_TYPE))
                .isEqualTo(MimeTypeUtils.TEXT_PLAIN);

        producerBinding.unbind();
        consumerBinding.unbind();
    }

    /**
     * ----------------------test end------------------------
     **/
    @Override
    protected boolean usesExplicitRouting() {
        return false;
    }

    @Override
    protected String getClassUnderTestName() {
        return CLASS_UNDER_TEST_NAME;
    }

    @Override
    protected void binderBindUnbindLatency() throws InterruptedException {
        Thread.sleep(500);
    }

    @Override
    protected AbstractRedisTestBinder getBinder() {
        if (binder == null) {
            RedisBinderConfigurationProperties binderConfiguration = createConfigurationProperties();
            RedisTopicProvisioner redisTopicProvisioner = new RedisTopicProvisioner(
                    binderConfiguration);
            try {
                redisTopicProvisioner.afterPropertiesSet();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            binder = new RedisTestBinder(binderConfiguration, redisTopicProvisioner);
        }
        return binder;
    }

    @Override
    protected ExtendedConsumerProperties<RedisConsumerProperties> createConsumerProperties() {
        final ExtendedConsumerProperties<RedisConsumerProperties> redisConsumerProperties = new ExtendedConsumerProperties<>(
                new RedisConsumerProperties());
        // set the default values that would normally be propagated by Spring Cloud Stream
        redisConsumerProperties.setInstanceCount(1);
        redisConsumerProperties.setInstanceIndex(0);
        return redisConsumerProperties;
    }

    @Override
    protected ExtendedProducerProperties<RedisProducerProperties> createProducerProperties(TestInfo testInfo) {
        ExtendedProducerProperties<RedisProducerProperties> producerProperties = new ExtendedProducerProperties<>(
                new RedisProducerProperties());

        return producerProperties;
    }

    @Override
    public Spy spyOn(String name) {
        throw new UnsupportedOperationException("'spyOn' is not used by Redis tests");
    }

    private RedisBinderConfigurationProperties createConfigurationProperties() {
        var binderConfiguration = new RedisBinderConfigurationProperties(
                redisProperties);
        binderConfiguration.setSupportType(RedisBinderConfigurationProperties.SupportType.QUEUE_CHANNEL);
        binderConfiguration.getConsumerProperties().setSerializer(RedisSerializer.byteArray());
        binderConfiguration.getProducerProperties().setSerializer(RedisSerializer.byteArray());
        return binderConfiguration;
    }
}
