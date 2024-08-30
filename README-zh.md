## spring-cloud-stream-redis

[![Maven central](https://img.shields.io/maven-central/v/io.github.guoshiqiufeng.cloud/spring-cloud-starter-stream-redis.svg?style=flat-square)](https://search.maven.org/search?q=g:io.github.guoshiqiufeng%20AND%20a:spring-cloud-starter-stream-redis)
[![License](https://img.shields.io/:license-apache-brightgreen.svg?style=flat-square)](http://www.apache.org/licenses/LICENSE-2.0.html)

阅读其他语言版本: [English](README.md)

### 介绍

基于Spring Cloud Stream 规范实现 Redis 消息 发送、接收

### 文档



### 开发框架

- Spring Cloud 2023.0.3
- Spring Boot 3.2.7

### 功能

- PUBLISH SUBSCRIBE 消息
- QUEUE 消息（BLPOP BRPOP LPUSH RPUSH）

> 注1： 两个功能模式不能混合使用，即 使用 PUBLISH SUBSCRIBE 模式 发送消息 时，不能使用 QUEUE 模式接收消息，反之亦然

> 注2： PUBLISH SUBSCRIBE 模式消息接收不到会丢失，QUEUE 模式不会

### 使用

#### 引入统一版本依赖，不用再使用时指定版本号
```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>io.github.guoshiqiufeng.cloud</groupId>
            <artifactId>spring-cloud-stream-dependencies</artifactId>
            <version>0.1.0</version>
            <type>import</type>
        </dependency>
    </dependencies>
</dependencyManagement>
```

#### 引入starter依赖

```xml
<dependency>
    <groupId>io.github.guoshiqiufeng.cloud</groupId>
    <artifactId>spring-cloud-starter-stream-redis</artifactId>
</dependency>
```

#### yml 配置

```yaml
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

#### 消息发送

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

### 消息接收

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
更多使用参考查看文档
