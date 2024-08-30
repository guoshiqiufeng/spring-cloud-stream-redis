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
