---
lang: en-US
title: Configure
description: Configure
---

# Configure

[spring-cloud-stream-redis](https://github.com/guoshiqiufeng/spring-cloud-stream-redis) is very easy to configure, we
just need some simple configuration！

> ake sure you have spring-cloud-stream-redis installed，if you haven't, check out the [Install](install.md)。

## Redis

### `application.yml` Configure connection parameters

> Support Redis Standalone, Cluster, and Sentinel modes
>
> Support all configurations under `spring.data.redis`

```yaml
spring:
  cloud:
    function:
#      definition: send;test
      definition: send
    stream:
      default-binder: redis
      binders:
        redis:
          type: redis
      redis:
        binder:
          configuration:
            host: 127.0.0.1
            port: 6379
            password: 123456
            database: 7
          support-type: queue_channel
#        bindings:
#          send-in-0:
#            consumer:
#              destination-is-pattern: true
      bindings:
        out-0:
          destination: test-topic
          content-type: text/plain
          group: push-producer-group
        send-in-0:
          destination: test-topic
          content-type: text/plain
          group: test-send-group
```

### Optional Clients

The default is to use the `lettuce` client, if you need to switch to the jedis client, you can add a jedis dependency to
do so, with the same configuration parameters as spring-boot-starter-data-redis.

- jedis dependencies
  
<CodeGroup>
  <CodeGroupItem title="Maven" active>

```xml:no-line-numbers:no-v-pre
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
</dependency>
```

  </CodeGroupItem>

  <CodeGroupItem title="Gradle">

```groovy
dependencies {
    implementation platform("redis.clients:jedis")
}
```

  </CodeGroupItem>
</CodeGroup>

- configuration
```yaml
spring:
  cloud:
    function:
#      definition: send;test
      definition: send
    stream:
      default-binder: redis
      binders:
        redis:
          type: redis
      redis:
        binder:
          configuration:
            host: 127.0.0.1
            port: 6379
            password: 123456
            database: 7
            client-type: jedis
            jedis:
            pool:
              enabled: true
              max-idle: 8
              max-active: 8

```

 A simple project with the above configuration will work fine, see the following project: [spring-cloud-stream-redis-samples](https://github.com/guoshiqiufeng/spring-cloud-stream-redis-samples)
