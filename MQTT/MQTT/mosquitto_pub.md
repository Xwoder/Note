# Eclipse Mosquitto mosquitto_pub

[TOC]

用于订阅主题的客户端，支持 `MQTT 5`、`MQTT 3.1.1` 和 `MQTT 3.1`

## 参数

### 主题 / `topic`

> 主题

|   全写    | 缩写 | 默认值 |
| :-------: | :--: | :----: |
| `--topic` | `-t` |   无   |

### 主机 / `host`

> 主机

|   全写   | 缩写 |   默认值    | 示例           |
| :------: | :--: | :---------: | -------------- |
| `--host` | `-h` | `localhost` | `-h 127.0.0.1` |

### 端口 / `port`

> 端口

|   全写   | 缩写 |      默认值      | 示例      |
| :------: | :--: | :--------------: | --------- |
| `--port` | `-p` | `1883` 或 `8883` | `-p 1883` |

### 消息 / `message`

> 消息

|    全写     | 缩写 | 默认值 |
| :---------: | :--: | :----: |
| `--message` | `-m` |   无   |

### 服务质量 / `QoS`

> 服务质量

|  全写   | 缩写 | 默认值 | 示例   |
| :-----: | :--: | :----: | ------ |
| `--qos` | `-q` |  `0`   | `-q 1` |

### `URL`

> URL

`URL` 格式：

```url
mqtt(s)://[username[:password]@]host[:port]/topic
```

## 示例

```bash
$ mosquitto_pub -d \
		--debug \
    --qos 1 \
    --host "127.0.0.1" \
    --id "Device_A" \
    --topic "sensor/temperature/room1" \
    --message "{temperature: 42}"
```
