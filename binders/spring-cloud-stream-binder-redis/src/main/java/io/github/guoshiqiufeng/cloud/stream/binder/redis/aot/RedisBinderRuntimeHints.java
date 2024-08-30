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
package io.github.guoshiqiufeng.cloud.stream.binder.redis.aot;

import io.github.guoshiqiufeng.cloud.stream.binder.redis.properties.RedisBindingProperties;
import io.github.guoshiqiufeng.cloud.stream.binder.redis.properties.RedisConsumerProperties;
import io.github.guoshiqiufeng.cloud.stream.binder.redis.properties.RedisExtendedBindingProperties;
import io.github.guoshiqiufeng.cloud.stream.binder.redis.properties.RedisProducerProperties;
import jakarta.annotation.Nullable;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.ReflectionHints;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

import java.util.stream.Stream;

/**
 * @author yanghq
 * @version 1.0
 * @since 2024/8/28 10:52
 */
public class RedisBinderRuntimeHints implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(RuntimeHints hints, @Nullable ClassLoader classLoader) {
        ReflectionHints reflectionHints = hints.reflection();
        Stream.of(
                        RedisConsumerProperties.class,
                        RedisProducerProperties.class,
                        RedisExtendedBindingProperties.class,
                        RedisBindingProperties.class)
                .forEach(type -> reflectionHints.registerType(type,
                        builder -> builder.withMembers(MemberCategory.INVOKE_DECLARED_METHODS)));
    }
}
