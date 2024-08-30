package io.github.guoshiqiufeng.cloud.stream.binder.redis.config;

import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.cloud.stream.config.BindingHandlerAdvise;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration for extended binding metadata.
 *
 * @author yanghq
 * @version 1.0
 * @since 2024/8/28 11:39
 */
@Configuration(proxyBeanMethods = false)
public class ExtendedBindingHandlerMappingsProviderConfiguration {

    @Bean
    public BindingHandlerAdvise.MappingsProvider redisExtendedPropertiesDefaultMappingsProvider() {
        return () -> {
            Map<ConfigurationPropertyName, ConfigurationPropertyName> mappings = new HashMap<>();
            mappings.put(
                    ConfigurationPropertyName.of("spring.cloud.stream.redis.bindings"),
                    ConfigurationPropertyName.of("spring.cloud.stream.redis.default"));
            return mappings;
        };
    }
}
