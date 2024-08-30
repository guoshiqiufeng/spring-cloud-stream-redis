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

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Extended consumer properties for Redis binder.
 *
 * @author yanghq
 * @version 1.0
 * @since 2024/8/28 10:09
 */
public class RedisConsumerProperties {

    /**
     * When true, the destination is treated as a regular expression Pattern used to match topic names by the broker.
     */
    @Getter
    @Setter
    private boolean destinationIsPattern;

    /**
     * Map with a key/value pair containing generic redis consumer properties.
     * In addition to having redis consumer properties, other configuration properties can be passed here.
     */
    private Map<String, String> configuration = new HashMap<>();

    /**
     * Various topic level properties. @see {@link RedisTopicProperties} for more details.
     */
    @Getter
    @Setter
    private RedisTopicProperties topic = new RedisTopicProperties();

}
