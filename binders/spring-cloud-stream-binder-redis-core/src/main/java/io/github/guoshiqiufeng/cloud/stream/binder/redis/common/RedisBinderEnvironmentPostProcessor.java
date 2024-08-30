package io.github.guoshiqiufeng.cloud.stream.binder.redis.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * An {@link EnvironmentPostProcessor} that sets some common configuration properties (log
 * config etc.,) for Redis binder.
 *
 * @author yanghq
 * @version 1.0
 * @since 2024/8/28 11:34
 */
@Slf4j
public class RedisBinderEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private static final String SPRING_REDIS = "spring.data.redis";

    private static final String SPRING_REDIS_PRODUCER = SPRING_REDIS + ".producer";

    private static final String SPRING_REDIS_CONSUMER = SPRING_REDIS + ".consumer";

    private static final String SPRING_REDIS_PRODUCER_KEY_SERIALIZER = SPRING_REDIS_PRODUCER
            + "." + "keySerializer";

    private static final String SPRING_REDIS_PRODUCER_VALUE_SERIALIZER = SPRING_REDIS_PRODUCER
            + "." + "valueSerializer";

    private static final String SPRING_REDIS_CONSUMER_KEY_DESERIALIZER = SPRING_REDIS_CONSUMER
            + "." + "keyDeserializer";

    private static final String SPRING_REDIS_CONSUMER_VALUE_DESERIALIZER = SPRING_REDIS_CONSUMER
            + "." + "valueDeserializer";

    private static final String REDIS_BINDER_DEFAULT_PROPERTIES = "redisBinderConfigurationProperties";


    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        log.error("postProcessEnvironment:{}", environment.getPropertySources());
        if (!environment.getPropertySources().contains(REDIS_BINDER_DEFAULT_PROPERTIES)) {
            Map<String, Object> redisBinderDefaultProperties = new HashMap<>();
            redisBinderDefaultProperties.put("logging.level.io.github.guoshiqiufeng.stream",
                    "ERROR");
//            redisBinderDefaultProperties.put(SPRING_REDIS_PRODUCER_KEY_SERIALIZER,
//                    ByteArraySerializer.class.getName());
//            redisBinderDefaultProperties.put(SPRING_REDIS_PRODUCER_VALUE_SERIALIZER,
//                    ByteArraySerializer.class.getName());
//            redisBinderDefaultProperties.put(SPRING_REDIS_CONSUMER_KEY_DESERIALIZER,
//                    ByteArrayDeserializer.class.getName());
//            redisBinderDefaultProperties.put(SPRING_REDIS_CONSUMER_VALUE_DESERIALIZER,
//                    ByteArrayDeserializer.class.getName());
            environment.getPropertySources().addLast(new MapPropertySource(
                    REDIS_BINDER_DEFAULT_PROPERTIES, redisBinderDefaultProperties));
        }
    }
}
