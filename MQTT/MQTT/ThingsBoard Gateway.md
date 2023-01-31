#  ThingsBoard Gateway

[TOC]

> 基于 `Ubuntu 22 LTS` 环境，使用 `deb` 方式安装

## 安装

> 参见：[Install ThingsBoard IoT Gateway as package. | ThingsBoard IoT Gateway](https://thingsboard.io/docs/iot-gateway/install/deb-installation/)

下载 `deb` 文件

```bash
wget https://github.com/thingsboard/thingsboard-gateway/releases/latest/download/python3-thingsboard-gateway.deb
```

使用 `apt` 安装

```bash
sudo apt install ./python3-thingsboard-gateway.deb -y
```

启动

```bash
systemctl start thingsboard-gateway
```

查看状态

```bash
systemctl status thingsboard-gateway
```

## 目录结构

### 配置文件目录

```bash
/etc/thingsboard-gateway/config
```

其中：

- `tb_gateway.yaml`：主配置文件
- `logs.conf`：日志配置文件

- 连接器配置文件
    - `mqtt.json`：MQTT 连接器配置文件
    - `rest.json`：REST 连接器配置文件
    - ……

### 扩展项目录

```bash
/var/lib/thingsboard_gateway/extensions
```

### 日志文件目录

```bash
/var/log/thingsboard-gateway
```

其中：

- `connector.log`：连接器日志文件
- `service.log`：网关服务主日志文件
- `storage.log`：持久化存储日志文件
- `tb_connection.log`：ThingsBoard 实例连接文件

## 配置

> 配置文件模板参见：[thingsboard-gateway/thingsboard_gateway/config at master · thingsboard/thingsboard-gateway (github.com)](https://github.com/thingsboard/thingsboard-gateway/tree/master/thingsboard_gateway/config)

### 主配置文件

> 即 `tb_gateway.yaml` 配置文件。参见：[IoT Gateway Configuration | ThingsBoard IoT Gateway](https://thingsboard.io/docs/iot-gateway/configuration/)

模板如下：

```yaml
thingsboard:
  host: thingsboard.cloud
  port: 1883
  remoteShell: false
  remoteConfiguration: false
  statistics:
    enable: true
    statsSendPeriodInSeconds: 3600
    configuration: statistics.json
  minPackSendDelayMS: 0
  checkConnectorsConfigurationInSeconds: 60
  handleDeviceRenaming: true
  checkingDeviceActivity:
    checkDeviceInactivity: false
    inactivityTimeoutSeconds: 120
    inactivityCheckPeriodSeconds: 10
  security:
    accessToken: 网关访问令牌
  qos: 1

storage:
  type: memory
  read_records_count: 100
  max_records_count: 100000

grpc:
  enabled: false
  serverPort: 9595
  keepaliveTimeMs: 10000
  keepaliveTimeoutMs: 5000
  keepalivePermitWithoutCalls: true
  maxPingsWithoutData: 0
  minTimeBetweenPingsMs: 10000
  minPingIntervalWithoutDataMs: 5000

connectors:
  -
    name: MQTT Broker Connector
    type: mqtt
    configuration: mqtt.json
#  -
#    name: REQUEST Connector
#    type: request
#    configuration: request.json
```

#### `thingsboard` 配置项

|     参数      |      默认值       |                       描述                        |
| :-----------: | :---------------: | :-----------------------------------------------: |
| `thingsboard` |                   |           欲连接的 `ThingsBoard` 服务器           |
|    `host`     | thingsboard.cloud |      欲连接的 `ThingsBoard` 服务器的主机地址      |
|    `port`     |       1883        | 欲连接的 `ThingsBoard` 服务器的 `MQTT` 服务的端口 |

##### `statistics` 子配置

> 参见：[IoT Gateway Configuration | ThingsBoard IoT Gateway](https://thingsboard.io/docs/iot-gateway/configuration/#subsection-statistics)

略

##### `security` 子配置项

本例中使用基本的认证方式，即访问令牌方式

##### `checkingDeviceActivity` 子配置项

> 参见：[IoT Gateway Configuration | ThingsBoard IoT Gateway](https://thingsboard.io/docs/iot-gateway/configuration/#subsection-checkingdeviceactivity)

略

#### `connectors` 配置项

```yaml
connectors:
  -
    name: MQTT Broker Connector
    type: mqtt
    configuration: mqtt.json
```

含义如下：

|      参数       |        描述        |
| :-------------: | :----------------: |
|     `type`      |     连接器类型     |
|     `name`      |     连接器名称     |
| `configuration` | 连接器配置文件名称 |

可以配置一个或多个连接器，例如：

```yaml
connectors:
  -
    name: MQTT Broker Connector
    type: mqtt
    configuration: mqtt.json
 -
    name: REQUEST Connector
    type: request
    configuration: request.json
```

### `MQTT` 连接器配置

> 参见：[MQTT Connector Configuration | ThingsBoard IoT Gateway](https://thingsboard.io/docs/iot-gateway/config/mqtt/)

示例配置如下：

```json
{
    "broker": {
        "name": "Default Local Broker",
        "host": "192.168.110.220",
        "port": 1885,
        "security": {
            "type": "anonymous",
        }
    },
    "mapping": [
        {
            "topicFilter": "/sensor/data",
            "converter": {
                "type": "json",
                "deviceNameJsonExpression": "${serialNumber}",
                "deviceTypeJsonExpression": "${sensorType}",
                "timeout": 60000,
                "attributes": [
                    {
                        "type": "string",
                        "key": "model",
                        "value": "${sensorModel}"
                    },
                    {
                        "type": "string",
                        "key": "${sensorModel}",
                        "value": "on"
                    }
                ],
                "timeseries": [
                    {
                        "type": "double",
                        "key": "temperature",
                        "value": "${temp}"
                    },
                    {
                        "type": "double",
                        "key": "humidity",
                        "value": "${hum}"
                    },
                    {
                        "type": "string",
                        "key": "combine",
                        "value": "${hum}:${temp}"
                    }
                ]
            }
        },
        {
            "topicFilter": "/sensor/+/data",
            "converter": {
                "type": "json",
                "deviceNameTopicExpression": "(?<=sensor\/)(.*?)(?=\/data)",
                "deviceTypeTopicExpression": "Thermometer",
                "timeout": 60000,
                "attributes": [
                    {
                        "type": "string",
                        "key": "model",
                        "value": "${sensorModel}"
                    }
                ],
                "timeseries": [
                    {
                        "type": "double",
                        "key": "temperature",
                        "value": "${temp}"
                    },
                    {
                        "type": "double",
                        "key": "humidity",
                        "value": "${hum}"
                    }
                ]
            }
        },
        {
            "topicFilter": "/custom/sensors/+",
            "converter": {
                "type": "custom",
                "extension": "CustomMqttUplinkConverter",
                "extension-config": {
                    "temperatureBytes": 2,
                    "humidityBytes": 2,
                    "batteryLevelBytes": 1
                }
            }
        }
    ],
    "connectRequests": [
        {
            "topicFilter": "/sensor/connect",
            "deviceNameJsonExpression": "${serialNumber}"
        },
        {
            "topicFilter": "/sensor/+/connect",
            "deviceNameTopicExpression": "(?<=sensor\/)(.*?)(?=\/connect)"
        }
    ],
    "disconnectRequests": [
        {
            "topicFilter": "/sensor/disconnect",
            "deviceNameJsonExpression": "${serialNumber}"
        },
        {
            "topicFilter": "/sensor/+/disconnect",
            "deviceNameTopicExpression": "(?<=sensor\/)(.*?)(?=\/disconnect)"
        }
    ],
    "attributeRequests": [
        {
            "retain": false,
            "topicFilter": "v1/devices/me/attributes/request",
            "topicExpression": "${SerialNumber}",
            "valueExpression": "${sensorModel}"
        }
    ],
    "attributeUpdates": [
        {
            "retain": false,
            "deviceNameFilter": "SN.*",
            "attributeFilter": "uploadFrequency",
            "topicExpression": "sensor/${deviceName}/${attributeKey}",
            "valueExpression": "{\"${attributeKey}\":\"${attributeValue}\"}"
        }
    ],
    "serverSideRpc": [
        {
            "deviceNameFilter": "SN.*",
            "methodFilter": "echo",
            "requestTopicExpression": "/sensor/${deviceName}/request/${methodName}/${requestId}",
            "responseTopicExpression": "/sensor/${deviceName}/response/${methodName}/${requestId}",
            "responseTimeout": 10000,
            "valueExpression": "${params}"
        },
        {
            "deviceNameFilter": ".*",
            "methodFilter": "no-reply",
            "requestTopicExpression": "/sensor/${deviceName}/request/${methodName}/${requestId}",
            "valueExpression": "${params.hum}::${params.temp}"
        }
    ]
}
```

#### 代理配置

|    参数    |         默认值         | 描述                      |
| :--------: | :--------------------: | :------------------------ |
|   `name`   | `Default Local Broker` | 代理的名称                |
|   `host`   |      `localhost`       | MQTT 代理主机名或 IP 地址 |
|   `port`   |         `1883`         | MQTT 代理的端口           |
| `clientId` | `ThingsBoard_gateway`  | 客户端 ID                 |

##### 安全配置

用于确定 `MQTT` 客户端与代理之间的认证方式

###### 匿名认证

|  参数  |   默认值    | 描述     |
| :----: | :---------: | :------- |
| `type` | `anonymous` | 认证方式 |

安全配置还支持基本认证（`Basic`）和证书认证（`Certificate`）

###### 基本认证

|    参数    |   默认值   | 描述     |
| :--------: | :--------: | :------- |
|   `type`   |  `basic`   | 认证方式 |
| `username` | `username` | 用户名   |
| `password` | `password` | 密码     |

```json
"security": {
    "type": "basic",
    "username": "username",
    "password": "password"
}
```

###### 证书认证

|     参数     |                   默认值                   | 描述         |
| :----------: | :----------------------------------------: | :----------- |
|   `caCert`   |     `/etc/thingsboard-gateway/ca.pem`      | CA 文件路径  |
| `privateKey` | `/etc/thingsboard-gateway/privateKey.pem`  | 私钥文件路径 |
|    `cert`    | `/etc/thingsboard-gateway/certificate.pem` | 证书文件路径 |

```json
"security": {
    "caCert": "/etc/thingsboard-gateway/ca.pem",
    "privateKey": "/etc/thingsboard-gateway/privateKey.pem",
    "cert": "/etc/thingsboard-gateway/certificate.pem"
}
```

#### `mapping` 配置区

>  参见：[MQTT Connector Configuration | ThingsBoard IoT Gateway](https://thingsboard.io/docs/iot-gateway/config/mqtt/#section-mapping)