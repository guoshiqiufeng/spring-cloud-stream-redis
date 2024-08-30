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
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.util.Assert;

/**
 * @author yanghq
 * @version 1.0
 * @since 2024/8/28 09:58
 */
@Slf4j
public class RedisBinderConfigurationProperties {

    private static final String DEFAULT_REDIS_CONNECTION_STRING = "localhost:6379";

    @Getter
    @Setter
    private SupportType supportType = SupportType.PUBLISH_SUBSCRIBE_CHANNEL;

    @Getter
    private final RedisProperties configuration;

    @Getter
    @Setter
    private String[] headers = new String[]{};

    public RedisBinderConfigurationProperties(RedisProperties redisProperties) {
        Assert.notNull(redisProperties, "'redisProperties' cannot be null");
        this.configuration = redisProperties;
    }


    public enum SupportType {
        /**
         * publish subscribe
         */
        PUBLISH_SUBSCRIBE_CHANNEL,
        /**
         * list right pop
         */
        QUEUE_CHANNEL
    }
}
