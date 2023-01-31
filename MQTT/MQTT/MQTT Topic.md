# MQTT Topic

[TOC]

`topic` 即**主题**

## 分层结构

`MQTT` 的主题采用分层结构。例如：

```
/sensor/temperature/room1
```

### 通配符

在订阅主题时，如果想要一次性订阅多个主题，在满足一定条件的情况下，可以使用 `#` 和 `+` 两种通配符。

### `#` 通配符

```
/sensor/temperature/#
```

表示：所有以 `/sensor/temperature/` 开头的主题

### `+` 通配符

```
/sensor/+/room1
```

表示：所有以 `/sensor/` 开头并且以 `/room1` 结尾的主题

### 示例

现有如下主题，其分别为

```
/sensor/temperature/room1
/sensor/temperature/room2
/sensor/temperature/room3
/sensor/temperature/room4
/sensor/humid/room1
/sensor/humid/room2
/sensor/humid/room3
/sensor/humid/room4
```

则

- 对于主题 `/sensor/temperature/room1`，其表示：1号房间的温度传感器的值
- 对于主题 `/sensor/temperature/#`，其表示：所有房间的温度传感器的值

- 对于主题 `/sensor/+/room1`，其表示：1号房间的所有传感器的值
