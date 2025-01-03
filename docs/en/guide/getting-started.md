---
lang: en-US
title: Getting-started
description: Getting-started
---

<script setup>import {inject} from "vue";
const version = inject('version');
</script>

# Getting-started

Let's go through a simple demo to introduce how to
use [spring-cloud-stream-redis](https://github.com/guoshiqiufeng/spring-cloud-stream-redis) features。

## Initialization

Create an empty Spring Boot project，Here we are using version 3.2.0 .

## Adding Dependencies

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

## Configuration

Add the configuration to application.yml:

```yaml
spring:
  cloud:
    function:
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

## Coding

Write the entity class `MessageVO.java`

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

Write the Listener class `MessageHandler.java`

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

## Start using

Add a test Controller class for functional testing:

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

## Wrap-up

With these few simple steps, we have implemented the sending and consuming of MessageVO.
