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

import io.github.guoshiqiufeng.cloud.stream.binder.redis.properties.RedisConsumerProperties;
import io.github.guoshiqiufeng.cloud.stream.binder.redis.properties.RedisProducerProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.stream.binder.AbstractPollableConsumerTestBinder;
import org.springframework.cloud.stream.binder.ExtendedConsumerProperties;
import org.springframework.cloud.stream.binder.ExtendedProducerProperties;
import org.springframework.context.ApplicationContext;

/**
 * @author yanghq
 * @version 1.0
 * @since 2024/9/2 15:40
 */
public abstract class AbstractRedisTestBinder extends
        AbstractPollableConsumerTestBinder<RedisMessageChannelBinder,
                ExtendedConsumerProperties<RedisConsumerProperties>, ExtendedProducerProperties<RedisProducerProperties>> {

    @Getter
    @Setter
    private ApplicationContext applicationContext;

    @Override
    public void cleanup() {
        // do nothing - the rule will take care of that
    }
}
