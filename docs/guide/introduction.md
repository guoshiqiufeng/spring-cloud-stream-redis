---
lang: zh-CN
title: 介绍
description: 
---

# 简介

[spring-cloud-stream-redis](https://github.com/guoshiqiufeng/spring-cloud-stream-redis) 是一个 基于 spring-cloud-stream
实现的 Redis 消息 框架。

## 特性

- **无侵入**: 通过 spring-cloud-stream的方式，无侵入式的使用Redis消息。
- **统一规范**：一次开发适配多种MQ消息，可无损切换其他 MQ 中间件。

## 支持的服务

- Redis 5.x 以上

## 功能

* 可用 - ✅
* 进行中 - 🚧

| 功能                                                               | 状态 |   
|------------------------------------------------------------------|----|
| 【PUBLISH SUBSCRIBE】【Topic】Send                                   | ✅  |    
| 【PUBLISH SUBSCRIBE】【Topic】   Consumer with message listener      | ✅  |    
| 【PUBLISH SUBSCRIBE】【Topic-Pattern】Consumer with message listener | ✅  |    
| 【QUEUE】【Topic】Send                                               | ✅  |    
| 【QUEUE】【Topic】Consumer with message listener                     | ✅  |    

注：

- 两个功能模式不能混合使用，即 使用 PUBLISH SUBSCRIBE 模式 发送消息 时，不能使用 QUEUE 模式接收消息，反之亦然

- PUBLISH SUBSCRIBE 模式消息接收不到会丢失，QUEUE 模式不会

## 代码托管

> **[GitHub](https://github.com/guoshiqiufeng/spring-cloud-stream-redis)**
