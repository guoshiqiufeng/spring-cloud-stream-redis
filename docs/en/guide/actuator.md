---
lang: en-US
title: Actuator
description: Actuator
---

# Actuator

> ake sure you have spring-cloud-stream-redis installed，if you haven't, check out the [Install](install.md)。

## Enable configuration

```yaml:no-line-numbers
management:
  endpoints:
    web:
      exposure:
        include: '*'
  endpoint:
    health:
      show-details: always
  health:
    binders:
      enabled: true
    redis:
      enabled: false
```

## Access path

`/actuator/health`

Example:

```json:no-line-numbers
{
    "status": "UP",
    "components": {
        "binders": {
            "status": "UP",
            "components": {
                "redis": {
                    "status": "UP",
                    "details": {
                        "version": "7.1.2"
                    }
                }
            }
        },
        "ping": {
            "status": "UP"
        }
    }
}
```