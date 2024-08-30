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

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.stream.binder.AbstractExtendedBindingProperties;
import org.springframework.cloud.stream.binder.BinderSpecificPropertiesProvider;

import java.util.Map;

/**
 * Redis specific extended binding properties class that extends from
 * {@link AbstractExtendedBindingProperties}.
 *
 * @author yanghq
 * @version 1.0
 * @since 2024/8/28 10:30
 */
@ConfigurationProperties("spring.cloud.stream.redis")
public class RedisExtendedBindingProperties extends
        AbstractExtendedBindingProperties<RedisConsumerProperties, RedisProducerProperties, RedisBindingProperties> {
    private static final String DEFAULTS_PREFIX = "spring.cloud.stream.redis.default";

    @Override
    public String getDefaultsPrefix() {
        return DEFAULTS_PREFIX;
    }

    @Override
    public Map<String, RedisBindingProperties> getBindings() {
        return this.doGetBindings();
    }

    @Override
    public Class<? extends BinderSpecificPropertiesProvider> getExtendedPropertiesEntryClass() {
        return RedisBindingProperties.class;
    }
}
