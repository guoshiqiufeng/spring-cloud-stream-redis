---
lang: zh-cn
title: 配置
description: 
---

# 配置

[spring-cloud-stream-redis](https://github.com/guoshiqiufeng/spring-cloud-stream-redis) 的配置异常的简单，我们仅需要一些简单的配置即可！

> 请确保您已经安装了 spring-cloud-stream-redis，如果您尚未安装，请查看 [安装](install.md)。

## Redis

### `application.yml` 配置 连接参数

> 支持redis单机版、集群、哨兵模式
>
> 支持`spring.data.redis`下的所有配置
>

```yaml:no-line-numbers
spring:
  cloud:
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

### 可选客户端

默认与 使用`lettuce`客户端，如需切换 jedis 客户端，可以添加 jedis 依赖 即可，配置参数与 spring-boot-starter-data-redis 一致。

- jedis依赖
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

```groovy:no-line-numbers:no-v-pre
dependencies {
    implementation platform("redis.clients:jedis")
}
```

  </CodeGroupItem>
</CodeGroup>

- 配置
```yaml:no-line-numbers
spring:
  cloud:
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

