---
lang: en-US
title: Guide
description: Guide
---
# Guide

[spring-cloud-stream-redis](https://github.com/guoshiqiufeng/spring-cloud-stream-redis) It is based on spring-cloud-stream
Implementation of the Redis messaging framework.

## Characterization

- **Non-intrusive**: Use Redis messages non-intrusively by way of spring-cloud-stream.
- **Standardize**：Adapt multiple MQ messages in one development, and switch to other MQ middleware without loss.

## Supported

- Redis 5.x and above

## Features

* Available - ✅
* In progress - 🚧

| Features                                                              | Status |   
|-----------------------------------------------------------------|--------|
| 【PUBLISH SUBSCRIBE】【Topic】Send                                  | ✅      |    
| 【PUBLISH SUBSCRIBE】【Topic】   Consumer with message listener     | ✅      |    
| 【PUBLISH SUBSCRIBE】【Topic-Pattern】Consumer with message listener | ✅      |    
| 【QUEUE】【Topic】Send                                              | ✅      |    
| 【QUEUE】【Topic】Consumer with message listener                    | ✅      |    

Notes:
- The two function modes cannot be mixed, i.e., if you send a message in PUBLISH SUBSCRIBE mode, you cannot receive a message in QUEUE mode, and vice versa.
- PUBLISH SUBSCRIBE mode message will be lost if not received, QUEUE mode will not.

## Code hosting

> **[GitHub](https://github.com/guoshiqiufeng/spring-cloud-stream-redis)**
