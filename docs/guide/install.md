---
lang: zh-CN
title: 安装
description: 
---
<script setup>
import {inject} from "vue";
const version = inject('version');
</script>
# 安装

[spring-cloud-stream-redis](https://github.com/guoshiqiufeng/spring-cloud-stream-redis) 基于JDK21, 支持SpringBoot 3.x.


## SpringBoot 3.x

### Redis依赖安装

> 支持Redis 5.x以上

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

## Bom

<CodeGroup>
  <CodeGroupItem title="Maven" active>

```xml:no-line-numbers:no-v-pre
<dependencyManagement>
   <dependencies>
       <dependency>
            <groupId>io.github.guoshiqiufeng.cloud</groupId>
            <artifactId>spring-cloud-stream-dependencies</artifactId>
            <version>{{version}}</version>
            <type>pom</type>
            <scope>import</scope>
       </dependency>
   </dependencies>
</dependencyManagement>
```

  </CodeGroupItem>

  <CodeGroupItem title="Gradle">

```groovy:no-line-numbers:no-v-pre
dependencies {
    implementation platform("io.github.guoshiqiufeng.cloud:spring-cloud-stream-dependencies:{{version}}")
}
```

  </CodeGroupItem>
</CodeGroup>
