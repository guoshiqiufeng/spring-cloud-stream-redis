---
lang: zh-cn
title: 快速开始
description: 
---

<script setup>import {inject} from "vue";
const version = inject('version');
</script>

# 快速开始

我们通过一个简单的Demo来介绍如何使用 [spring-cloud-stream-redis](https://github.com/guoshiqiufeng/spring-cloud-stream-redis)
的功能。

## 初始化

创建一个空的Spring Boot 工程，这里我们使用 3.2.0 版本。

## 添加依赖

<CodeGroup>
  <CodeGroupItem title="Maven" active>

```xml:no-line-numbers:no-v-pre
<dependency>
    <groupId>io.github.guoshiqiufeng.cloud</groupId>
    <artifactId>spring-cloud-starter-stream-redis</artifactId>
    <version>{{version}}</version>
</dependency>
```

  </CodeGroupItem>

  <CodeGroupItem title="Gradle (Short)" active>

```groovy:no-line-numbers:no-v-pre
implementation 'io.github.guoshiqiufeng.cloud:spring-cloud-starter-stream-redis:{{version}}'
```

  </CodeGroupItem>

  <CodeGroupItem title="Gradle">

```groovy:no-line-numbers:no-v-pre
implementation group: 'io.github.guoshiqiufeng.cloud', name: 'spring-cloud-starter-stream-redis', version: '{{version}}'
```

  </CodeGroupItem>
</CodeGroup>

## 配置

在 application.yml 中添加配置：

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

## 编码

编写实体类 MessageVO.java

```java

@Data
public class MessageVO implements Serializable {

    private static final long serialVersionUID = 807173843169199376L;

    private String msg;

    private String key;

    private Set<String> ids;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
```

编写监听类 `MessageHandler.java`

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

## 发送消息

添加测试Controller类，进行功能测试：

```java

@Slf4j
@RestController
public class TestController {

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

}
```
完整的代码示例 查看 [spring-cloud-stream-redis-samples](https://github.com/guoshiqiufeng/spring-cloud-stream-redis-samples)
## 小结

通过以上几个简单的步骤，我们就实现了 MessageVO 的发送和消费。
