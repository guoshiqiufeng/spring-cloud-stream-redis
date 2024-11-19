---
lang: zh-cn
title: 健康检查
description: 
---

# 健康检查

> 请确保您已经安装了 spring-cloud-stream-redis，如果您尚未安装，请查看 [安装](install.md)。

## 开启配置

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

## 访问路径

`/actuator/health`

示例：

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