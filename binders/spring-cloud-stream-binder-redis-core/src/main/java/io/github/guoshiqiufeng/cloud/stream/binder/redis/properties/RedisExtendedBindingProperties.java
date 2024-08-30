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
