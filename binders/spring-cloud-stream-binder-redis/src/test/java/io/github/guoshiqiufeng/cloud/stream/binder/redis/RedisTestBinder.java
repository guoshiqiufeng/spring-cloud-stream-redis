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
import io.github.guoshiqiufeng.cloud.stream.binder.redis.provisioning.RedisTopicProvisioner;
import org.springframework.cloud.stream.binder.ExtendedConsumerProperties;
import org.springframework.cloud.stream.provisioning.ConsumerDestination;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;

/**
 * Test support class for {@link RedisMessageChannelBinder}
 *
 * @author yanghq
 * @version 1.0
 * @since 2024/9/2 15:44
 */
class RedisTestBinder extends AbstractRedisTestBinder {

    RedisTestBinder(RedisBinderConfigurationProperties binderConfiguration,
                    RedisTopicProvisioner redisTopicProvisioner) {

        try {
            RedisMessageChannelBinder binder = new RedisMessageChannelBinder(
                    binderConfiguration, redisTopicProvisioner, null, null) {
                @Override
                protected String errorsBaseName(ConsumerDestination destination,
                                                String group,
                                                ExtendedConsumerProperties<RedisConsumerProperties> consumerProperties) {
                    return super.errorsBaseName(destination, group, consumerProperties)
                            + "-" + consumerProperties.getInstanceIndex();
                }
            };
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                    Config.class);

            setApplicationContext(context);
            binder.setApplicationContext(context);
            binder.afterPropertiesSet();
            this.setPollableConsumerBinder(binder);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Configuration
    @EnableIntegration
    static class Config {

//        @Bean
//        public MessageConverter messageConverter() {
//            return new ByteArrayMessageConverter();
//        }
    }
}
