## spring-cloud-stream-redis

[![Maven central](https://img.shields.io/maven-central/v/io.github.guoshiqiufeng.cloud/spring-cloud-starter-stream-redis.svg?style=flat-square)](https://search.maven.org/search?q=g:io.github.guoshiqiufeng.cloud%20AND%20a:spring-cloud-starter-stream-redis)
[![License](https://img.shields.io/:license-apache-brightgreen.svg?style=flat-square)](http://www.apache.org/licenses/LICENSE-2.0.html)
[![CodeQL](https://github.com/guoshiqiufeng/spring-cloud-stream-redis/actions/workflows/github-code-scanning/codeql/badge.svg)](https://github.com/guoshiqiufeng/spring-cloud-stream-redis/actions/workflows/github-code-scanning/codeql)

Read in other languages: [简体中文](README-zh.md)

### Introduction

Sending and Receiving Redis Messages Based on Spring Cloud Stream Specification，Official Version Aligned with Spring
Cloud Stream.

### Documentation

https://guoshiqiufeng.github.io/spring-cloud-stream-redis/en/

### Development Framework

- Spring Cloud Stream 4
- Spring Boot 3

### Features

- PUBLISH SUBSCRIBE message
- QUEUE message（BLPOP BRPOP LPUSH RPUSH）

> Tips 1: The two function modes cannot be mixed, i.e., if you send a message in PUBLISH SUBSCRIBE mode, you cannot
> receive a message in QUEUE mode, and vice versa.

> Tips 2： PUBLISH SUBSCRIBE mode messages will be lost if not received, QUEUE mode will not.

### Use

#### Introduces a uniform version dependency, so you don't have to specify a version number when you use it.

```xml

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>io.github.guoshiqiufeng.cloud</groupId>
            <artifactId>spring-cloud-stream-dependencies</artifactId>
            <version>0.5.2</version>
            <type>import</type>
        </dependency>
    </dependencies>
</dependencyManagement>
```

#### Introducing starter dependencies

```xml

<dependency>
    <groupId>io.github.guoshiqiufeng.cloud</groupId>
    <artifactId>spring-cloud-starter-stream-redis</artifactId>
</dependency>
```

#### yml configuration

```yaml
spring:
  cloud:
    function:
      definition: send;recall
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

#### Messaging

```java

@Autowired
private StreamBridge streamBridge;

@GetMapping("/send")
public String send() {
    MessageVO messageVO = new MessageVO();
    messageVO.setKey(UUID.randomUUID().toString());
    messageVO.setMsg("hello ");
    messageVO.setIds(Set.of("1", "2"));
    messageVO.setCreateTime(LocalDateTime.now());
    streamBridge.send("out-0", JSON.toJSONString(messageVO, JSONWriter.Feature.WriteClassName));
    return "success";
}
```

### Message reception

```java

@Slf4j
@Component("send")
public class MessageHandler implements Consumer<Message<String>> {

    /**
     * Performs this operation on the given argument.
     *
     * @param messageVOMessage the input argument
     */
    @Override
    public void accept(Message<String> messageVOMessage) {
        log.info("send Receive New Messages: {}", messageVOMessage.getPayload());
    }
}
```

For more usage references check the [documentation](https://guoshiqiufeng.github.io/spring-cloud-stream-redis/en/)

More Examples View [spring-cloud-stream-redis-samples](https://github.com/guoshiqiufeng/spring-cloud-stream-redis-samples)
