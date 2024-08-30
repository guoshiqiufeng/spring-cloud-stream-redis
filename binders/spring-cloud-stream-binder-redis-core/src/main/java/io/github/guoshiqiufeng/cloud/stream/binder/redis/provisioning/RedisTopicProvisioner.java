package io.github.guoshiqiufeng.cloud.stream.binder.redis.provisioning;

import io.github.guoshiqiufeng.cloud.stream.binder.redis.properties.RedisBinderConfigurationProperties;
import io.github.guoshiqiufeng.cloud.stream.binder.redis.properties.RedisConsumerProperties;
import io.github.guoshiqiufeng.cloud.stream.binder.redis.properties.RedisProducerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cloud.stream.binder.ExtendedConsumerProperties;
import org.springframework.cloud.stream.binder.ExtendedProducerProperties;
import org.springframework.cloud.stream.provisioning.ConsumerDestination;
import org.springframework.cloud.stream.provisioning.ProducerDestination;
import org.springframework.cloud.stream.provisioning.ProvisioningException;
import org.springframework.cloud.stream.provisioning.ProvisioningProvider;
import org.springframework.retry.RetryOperations;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.util.StringUtils;

/**
 * Redis implementation for {@link ProvisioningProvider}.
 *
 * @author yanghq
 * @version 1.0
 * @since 2024/8/28 13:56
 */
@Slf4j
public class RedisTopicProvisioner implements
        ProvisioningProvider<ExtendedConsumerProperties<RedisConsumerProperties>,
                ExtendedProducerProperties<RedisProducerProperties>>, InitializingBean {

    private static final int DEFAULT_OPERATION_TIMEOUT = 30;

    private final RedisBinderConfigurationProperties configurationProperties;

    private final int operationTimeout = DEFAULT_OPERATION_TIMEOUT;

    private RetryOperations metadataRetryOperations;

    /**
     * Create an instance.
     *
     * @param redisBinderConfigurationProperties the binder configuration properties.
     */
    public RedisTopicProvisioner(
            RedisBinderConfigurationProperties redisBinderConfigurationProperties) {

        this.configurationProperties = redisBinderConfigurationProperties;

        // If the application provides AdminConfig customizers
        // and overrides properties, those take precedence.
        // adminClientConfigCustomizers.forEach(customizer -> customizer.configure(this.adminClientProperties));
    }

    @Override
    public void afterPropertiesSet() {
        if (this.metadataRetryOperations == null) {
            RetryTemplate retryTemplate = new RetryTemplate();

            SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
            simpleRetryPolicy.setMaxAttempts(10);
            retryTemplate.setRetryPolicy(simpleRetryPolicy);

            ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
            backOffPolicy.setInitialInterval(100);
            backOffPolicy.setMultiplier(2);
            backOffPolicy.setMaxInterval(1000);
            retryTemplate.setBackOffPolicy(backOffPolicy);
            this.metadataRetryOperations = retryTemplate;
        }
    }

    @Override
    public ProducerDestination provisionProducerDestination(String name, ExtendedProducerProperties<RedisProducerProperties> properties) throws ProvisioningException {
        if (log.isInfoEnabled()) {
            log.info("Using redis topic for outbound: {}", name);
        }
        return new RedisProducerDestination(name, 0);
    }

    @Override
    public ConsumerDestination provisionConsumerDestination(String name, String group, ExtendedConsumerProperties<RedisConsumerProperties> properties) throws ProvisioningException {
        if (!properties.isMultiplex()) {
            return doProvisionConsumerDestination(name, group, properties);
        } else {
            String[] destinations = StringUtils.commaDelimitedListToStringArray(name);
            for (String destination : destinations) {
                doProvisionConsumerDestination(destination.trim(), group, properties);
            }
            return new RedisConsumerDestination(name);
        }
    }

    private ConsumerDestination doProvisionConsumerDestination(final String name,
                                                               final String group,
                                                               ExtendedConsumerProperties<RedisConsumerProperties> properties) {
        final RedisConsumerDestination redisConsumerDestination = new RedisConsumerDestination(name);
        if (properties.getExtension().isDestinationIsPattern()) {
            return redisConsumerDestination;
        }
        return redisConsumerDestination;
    }

    private static final class RedisProducerDestination implements ProducerDestination {

        private final String producerDestinationName;

        private final int partitions;

        RedisProducerDestination(String destinationName, Integer partitions) {
            this.producerDestinationName = destinationName;
            this.partitions = partitions;
        }

        @Override
        public String getName() {
            return this.producerDestinationName;
        }

        @Override
        public String getNameForPartition(int partition) {
            return this.producerDestinationName;
        }

        @Override
        public String toString() {
            return "RedisProducerDestination{" + "producerDestinationName='"
                    + producerDestinationName + '\'' + ", partitions=" + partitions + '}';
        }

    }

    private static final class RedisConsumerDestination implements ConsumerDestination {

        private final String consumerDestinationName;

        private final int partitions;

        private final String dlqName;

        RedisConsumerDestination(String consumerDestinationName) {
            this(consumerDestinationName, 0, null);
        }

        RedisConsumerDestination(String consumerDestinationName, int partitions) {
            this(consumerDestinationName, partitions, null);
        }

        RedisConsumerDestination(String consumerDestinationName, Integer partitions,
                                 String dlqName) {
            this.consumerDestinationName = consumerDestinationName;
            this.partitions = partitions;
            this.dlqName = dlqName;
        }

        @Override
        public String getName() {
            return this.consumerDestinationName;
        }

        @Override
        public String toString() {
            return "RedisConsumerDestination{" + "consumerDestinationName='"
                    + consumerDestinationName + '\'' + ", partitions=" + partitions
                    + ", dlqName='" + dlqName + '\'' + '}';
        }

    }
}
