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

import lombok.Data;
import org.springframework.cloud.stream.binder.BinderSpecificPropertiesProvider;

/**
 * Container object for Redis specific extended producer and consumer binding properties.
 *
 * @author yanghq
 * @version 1.0
 * @since 2024/8/28 10:00
 */
@Data
public class RedisBindingProperties implements BinderSpecificPropertiesProvider {

    /**
     * Consumer specific binding properties. @see {@link RedisConsumerProperties}.
     */
    private RedisConsumerProperties consumer = new RedisConsumerProperties();

    /**
     * Producer specific binding properties. @see {@link RedisProducerProperties}.
     */
    private RedisProducerProperties producer = new RedisProducerProperties();

}
