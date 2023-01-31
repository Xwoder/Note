# ThingsBoard 调研笔记-入门

[TOC]

> 本文中，默认的用户角色均为租户管理员，即 `Tenant Administrator`

## 服务器管理

### 启动服务器

略

### 关闭服务器

略

## 配置设备

### 添加设备

1. 进入[设备](https://demo.thingsboard.io/devices)菜单
2. 点击**添加设备**按钮
3. 输入设备名称，如：`SHFT-thermometer`
4. 其余设置保持默认，点击**添加**按钮
5. 添加设备的操作完成

### 连接设备

#### 访问令牌

1. 进入[设备](https://demo.thingsboard.io/devices)（`Devices`）面板，右侧将显示已有设备
2. 点击其中的某个设备，将弹出**设备详情**（`Device details`）面板
3. 点击**复制访问令牌**按钮；设备的访问令牌（`Device Token`）将被复制到剪贴板中，例如： `1A454k3ud3X5if86sCQK`

#### 发布数据

通过访问令牌，基于 `HTTP` 或 `MQTT` 等协议，可以模拟设备发送遥测数据。

##### 基于 HTTP 协议

本例中，将向上例中的提及的设备发送一个遥测数据，其 `HTTP` 报文如下：

```http
POST /api/v1/1A454k3ud3X5if86sCQK/telemetry HTTP/1.1
Host: demo.thingsboard.io
Content-Type: application/json
Content-Length: 25

{
    "temperature": 25
}
```

对应的 `curl` 命令如下

```bash
curl \
--location \
--request POST 'https://demo.thingsboard.io/api/v1/1A454k3ud3X5if86sCQK/telemetry' \
--header 'Content-Type: application/json' \
--data-raw '{
    "temperature": 25
}'
```

对应的 `Python` 脚本如下

```python
import requests
import json

# 服务器地址
host = "demo.thingsboard.io"

# 设备访问令牌
device_token = "1A454k3ud3X5if86sCQK"

# 遥测数据
telemetry_data = {
  "temperature": 25
}

# URL
url = f"https://{host}/api/v1/{device_token}/telemetry"

# HTTP 请求头
headers = {
  'Content-Type': 'application/json'
}

payload = json.dumps(telemetry_data)

# HTTP 响应对象
response = requests.request("POST", url, headers=headers, data=payload)

print(response.status_code)
```

当上述请求发布成功后，可以在设备详情面板的最新遥测数据中观察到刚刚发送的遥测数据值。

|   Last update time    |      Key      | Value |
| :-------------------: | :-----------: | :---: |
| `2022-07-06 19:06:49` | `temperature` | `25`  |

##### 基于 MQTT 协议

> 基于 macOS 演示

安装名为`Eclipse Mosquitto`开源的 `MQTT broker`，其官方网站为 [Eclipse Mosquitto](https://mosquitto.org/)

```bash
brew install mosquitto
```

安装成功后，执行如下 `mosquitto_pub` 命令

```bash
<<COMMENT
mosquitto_pub
    -d 开启 debug 消息
    -q QoS 等级
    -h MQTT 主机
    -p 端口号
    -t 发布的主题
    -u 用户名，即设备访问令牌
    -m 消息载荷
COMMENT

mosquitto_pub \
  -d \
  -q 1 \
  -h "demo.thingsboard.io" \
  -p "1883" \
  -t "v1/devices/me/telemetry" \
  -u "1A454k3ud3X5if86sCQK" \
  -m {"temperature":60}
```

执行上述命令可能得到代表发布成功的输出

```text
Client null sending CONNECT
Client null received CONNACK (0)
Client null sending PUBLISH (d0, q1, r0, m1, 'v1/devices/me/telemetry', ... (16 bytes))
Client null received PUBACK (Mid: 1, RC:0)
Client null sending DISCONNECT
```

