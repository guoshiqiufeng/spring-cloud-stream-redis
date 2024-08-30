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
