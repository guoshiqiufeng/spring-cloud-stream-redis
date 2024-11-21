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
package io.github.guoshiqiufeng.cloud.stream.binder.redis.health;

import io.github.guoshiqiufeng.cloud.stream.binder.redis.RedisContainerTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author yanghq
 * @version 1.0
 * @since 2024/11/17 17:04
 */
class RedisBinderHealthIndicatorIntegrationTest implements RedisContainerTest {

    @Test
    void shouldReportHealthWithRealRedis() {
        RedisConnectionFactory connectionFactory = RedisContainerTest.connectionFactory();

        RedisBinderHealthIndicator healthIndicator = new RedisBinderHealthIndicator(connectionFactory);
        Health health = healthIndicator.health();

        assertThat(health.getStatus()).isEqualTo(Status.UP);
        assertThat(health.getDetails())
                .containsKey("version")
                .containsKey("mode");
    }
}
