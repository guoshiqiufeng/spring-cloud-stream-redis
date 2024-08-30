package io.github.guoshiqiufeng.cloud.stream.binder.redis.properties;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Properties for configuring topics.
 *
 * @author yanghq
 * @version 1.0
 * @since 2024/8/28 10:35
 */
@Data
public class RedisTopicProperties {

    private Short replicationFactor;

    private Map<Integer, List<Integer>> replicasAssignments = new HashMap<>();

    private Map<String, String> properties = new HashMap<>();

}
