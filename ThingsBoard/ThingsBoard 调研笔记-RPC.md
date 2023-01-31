# ThingsBoard 调研笔记-RPC-客户端 RPC

## 概念

`RPC`，`Remote Procedure Call`，即**远程过程调用**

`ThingsBoard` 允许

* 从服务器端应用程序发送 `RPC` 到设备端
* 从设备端发送 `RPC` 到服务器端应用程序

## 分类

### 客户端 RPC

> Client-side RPC

设备端发起的 `RPC` 调用

<img src="thingsboard-client-side-rpc.svg" alt="thingsboard-client-side-rpc" style="width: 80%;"/>

#### 请求与响应

##### 请求 / Request

客户端 RPC请求的由两个必选字段组成：

- `method`

​	`RPC` 调用的方法的名称，值是字符串类型；例如：`getCurrentTime`

- `params`

​	附加参数

例如：

```json
{
   "method": "getCurrentTime",
   "params": {}
}
```

##### 响应 / Response

可以是任何数字、字符串或 `JSON`

#### 示例

为了让服务器能够正确处理 `RPC` 调用，需要在 `Rule chain` 中进行配置。

* 进入[规则链路](https://demo.thingsboard.io/ruleChains)界面

* 点击在规则链路列表中展示的适合的规则链路，弹出**规则链路详情**面板

* 点击位于**详情**（`Details`） 标签下的**打开链路规则**（`Open rule chain`） 按钮，进入**规则链路画布**页面

* 定位途中由 `RPC Request from Device` 箭头引出的规则链路

* 拖拽一个用于转换输入的 **script** 节点至画布中，此时将自动弹出**添加规则节点**对话框

    * 在顶部的**名称**（`Name`）文本框中为该节点起一个名字。例如：`SHFT-script`

    * 在下方的函数体文本框中输入 `Transform` 函数的具体定义，例如：

        ```javascript
        var rpcResponse;
        if (msg.method === "getCurrentTime") {
            rpcResponse = String(new Date());
        } else {
            rpcResponse = "Unknown RPC request method: " + msg
                .method;
        }
        
        return {
            msg: {
                time: rpcResponse
            },
            metadata: metadata,
            msgType: msgType
        };
        ```

        `Transform` 函数的函数原型为：

        ```javascript
        function Transform(msg, metadata, msgType)
        ```

        其有 3 个输入参数

        1. `msg`：消息载荷

        2. `metadata`：消息元数据。例如：

            ```json
            "metadata": {
                "deviceType": "default",
                "deviceName": "Test Device",
                "ts": "1657259178496"
            },
            ```

        3. `msgType`：消息类型。例如：

            ```json
            "msgType": "POST_TELEMETRY_REQUEST"
            ```

    * 点击**添加**按钮，完成操作

* 拖拽一个用于处理 `RPC` 响应的 **rpc call reply** 节点至画布中，此时将自动弹出**添加规则节点**对话框

    * 在顶部的**名称**（`Name`）文本框中为该节点起一个名字。例如：`SHFT-rpc-client`
    * 点击**添加**按钮，完成操作

* 使用带箭头的线条，连接上述新添加的规则节点，连接节点的链路标签应选择 `Success`

* 点击画布右下角**应用修改**按钮（`Apply changes`）

最终效果如下图所示

![thingsboard-rpc-dashboard](thingsboard-rpc-dashboard.png)

为了向服务器发送 `RPC` 命令，需要使用 `POST`方式将请求发送到以下 `URL`

```url
http://host:port/api/v1/$ACCESS_TOKEN/rpc
```

例如

```bash
access_token="1A454k3ud3X5if86sCQK"

curl -X POST \
https://demo.thingsboard.io/api/v1/${access_token}/rpc \
--header "Content-Type:application/json" \
-d @rpc-client-request.json
```

上述代码中的 `rpc-client-request.json` 是一个 `JSON` 格式的文件，其内容为

```json
{
    "method": "getCurrentTime",
    "params": {}
}
```

服务器将会返回

```json
{"time":"Fri Jul 08 2022 05:04:50 GMT+0000 (Coordinated Universal Time)"}
```

### 服务器端 RPC

服务端发起的 `RPC` 调用

服务器端 `RPC` 功能允许您将请求从平台发送到设备，并可选择将响应返回给平台。

服务器端 `RPC` 调用的典型用例是各种远程控制：重启、开关引擎、更改状态、更改配置参数等。

#### 按照方向分类

- 单向 `RPC`

    单向 RPC 请求不期望设备提供任何回复

    ![thingsboard-one-way-rpc](thingsboard-one-way-rpc.svg)

- 双向 `RPC`

    双向 RPC 请求期望在可配置的超时时间内从设备接收响应

    ![thingsboard-two-way-rpc](thingsboard-two-way-rpc.svg)

#### 结构

##### 请求结构

|       字段       | 数据类型 | 单位 | 可选/强制 |           作用            |           示例           |
| :--------------: | :------: | :--: | :-------: | :-----------------------: | :----------------------: |
|     `method`     |  字符串  |      |   强制    | 区分 RPC 调用的方法的名称 |  `"getWeatherForecast"`  |
|     `params`     |  `JSON`  |      |   强制    |           参数            | `{"pin": 4, "value": 1}` |
|    `timeout`     |   数值   | 毫秒 |   可选    |         超时时长          |          `5000`          |
| `expirationTime` |   数值   | 毫秒 |   可选    |         过期时间          |                          |
|   `persistent`   |   布尔   |      |   可选    |     是否是持久 `RPC`      |         `false`          |
|    `retries`     |   数值   |      |   可选    |         重试次数          |                          |
| `additionalInfo` |          |      |   可选    |  元数据，用于持久 `RPC`   |                          |

例如

```json
{
   "method": "setGPIO",
   "params": {
     "pin": 4,
     "value": 1
   },
  "timeout": 30000
}
```

##### 响应结构

任意的 JSON。例如

```json
{
   "pin": 4,
   "value": 1,
   "changed": true
}
```

#### 示例

服务器端 `RPC` 通常使用 `REST API` 或 `Dashboard` 的 `Widget` 发送。事实上，`Widget` 使用相同的 `REST API`。

##### 获取 `JWT` 令牌

为了发起服务器 RPC 调用，首先要获取 `JWT` 令牌，以取代用户名和密码。

下列请求用于发送请求给 ThingsBoard 以获取 `JWT` 令牌：

```bash
curl -X POST \
--header 'Content-Type: application/json' \
--header 'Accept: application/json' \
-d '{"username":"tenant@thingsboard.org", "password":"tenant"}' \
'http://192.168.110.212:8080/api/auth/login'
```

得到响应

```json
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZW5hbnRAdGhpbmdzYm9hcmQub3JnIiwic2NvcGVzIjpbIlRFTkFOVF9BRE1JTiJdLCJ1c2VySWQiOiI3NjNiMWY1MC1mZGNhLTExZWMtOGE2MS00ZmZjNDA3NzY3MWIiLCJlbmFibGVkIjp0cnVlLCJpc1B1YmxpYyI6ZmFsc2UsInRlbmFudElkIjoiNzYwYTI0NDAtZmRjYS0xMWVjLThhNjEtNGZmYzQwNzc2NzFiIiwiY3VzdG9tZXJJZCI6IjEzODE0MDAwLTFkZDItMTFiMi04MDgwLTgwODA4MDgwODA4MCIsImlzcyI6InRoaW5nc2JvYXJkLmlvIiwiaWF0IjoxNjU3MjYxNTE2LCJleHAiOjE2NTcyNzA1MTZ9.hT1W_MYI-e6jzob1IhcLybO4YG9viBtzdEuxaMVkRz5neQXiL3LWYGjy1WW8nunEUyRoFu871VxhZr7q4iV-vA",
    "refreshToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZW5hbnRAdGhpbmdzYm9hcmQub3JnIiwic2NvcGVzIjpbIlJFRlJFU0hfVE9LRU4iXSwidXNlcklkIjoiNzYzYjFmNTAtZmRjYS0xMWVjLThhNjEtNGZmYzQwNzc2NzFiIiwiaXNQdWJsaWMiOmZhbHNlLCJpc3MiOiJ0aGluZ3Nib2FyZC5pbyIsImp0aSI6IjM3ZDE2NmQ4LWQzYTgtNDk3YS1iZTZiLWY5MGM5ZGQzMGM2YSIsImlhdCI6MTY1NzI2MTUxNiwiZXhwIjoxNjU3ODY2MzE2fQ.ZnPd8WOnzOhxc8WwimNPPoS5h2VcIXYHRJ9FFtw3KjjGqjorFNlnoaNm4Vv1jochKv2RUad7eAU6KiGWX4cj0A"
}
```

获取得到 `JWT` 令牌后，后续相关的请求，其应将 `X-Authorization` 请求头的值设置为形如 `Bearer $YOUR_JWT_TOKEN` 的格式。

为了想客户端发送 `RPC` 请求，您需要向以下 `URL` 执行 `HTTP` `POST` 请求

```
http(s)://host:port/api/plugins/rpc/{callType}/{deviceId}
```

其中：

- `callType`：调用类型，可能值为：`oneway` 或 `twoway`

- `deviceId`：目标设备 ID

例如：

```bash
device_id="4827f120-fe87-11ec-afec-c160a54a7bad"

jwt_token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZW5hbnRAdGhpbmdzYm9hcmQub3JnIiwic2NvcGVzIjpbIlRFTkFOVF9BRE1JTiJdLCJ1c2VySWQiOiI3NjNiMWY1MC1mZGNhLTExZWMtOGE2MS00ZmZjNDA3NzY3MWIiLCJlbmFibGVkIjp0cnVlLCJpc1B1YmxpYyI6ZmFsc2UsInRlbmFudElkIjoiNzYwYTI0NDAtZmRjYS0xMWVjLThhNjEtNGZmYzQwNzc2NzFiIiwiY3VzdG9tZXJJZCI6IjEzODE0MDAwLTFkZDItMTFiMi04MDgwLTgwODA4MDgwODA4MCIsImlzcyI6InRoaW5nc2JvYXJkLmlvIiwiaWF0IjoxNjU3MjYxNTE2LCJleHAiOjE2NTcyNzA1MTZ9.hT1W_MYI-e6jzob1IhcLybO4YG9viBtzdEuxaMVkRz5neQXiL3LWYGjy1WW8nunEUyRoFu871VxhZr7q4iV-vA"

curl -v \
-X POST \
-d @set-gpio-request.json \
http://192.168.110.212:8080/api/plugins/rpc/twoway/${device_id} \
--header "Content-Type:application/json" \
--header "X-Authorization: ${jwt_token}"
```

上述代码中的 `set-gpio-request.json` 是一个 `JSON` 格式的文件，其内容为

```json
{
    "method": "setGpio",
    "params": {
        "pin": "23",
        "value": 1
    }
}
```

##### 设备端处理服务器 RPC

在发送服务端 `RPC` 命令以前，需要在设备端订阅 `RPC` 命令

需要通过 `GET` 方式向下述 `URL` 发一个请求

```
http(s)://host:port/api/v1/$ACCESS_TOKEN/rpc
```

例如

```bash
access_token="1A454k3ud3X5if86sCQK"

curl -X GET \
http://192.168.110.212:8080/api/v1/${access_token}/rpc \
--header "Content-Type:application/json"
```

