# HTTP 设备 API

> 参考：https://thingsboard.io/docs/reference/http-api/
>
> 本文使用 `curl` 模拟客户端设备

## 数据格式

默认情况下，`ThingsBoard` 支持 `key-value` 格式的 `JSON` 数据。例如：

```json
{
   "stringKey": "value1",
   "booleanKey": true,
   "doubleKey": 42.0,
   "longKey": 73,
   "jsonKey": {
      "someNumber": 42,
      "someArray": [1, 2, 3],
      "someNestedObject": {
         "key": "value"
      }
   }
}
```

## 遥测上传 API

> 参见：[HTTP Device API Reference | ThingsBoard Community Edition](https://thingsboard.io/docs/reference/http-api/#telemetry-upload-api)

### API

```
http(s)://host:port/api/v1/$ACCESS_TOKEN/telemetry
```

### 数据格式

支持以下 3 种格式：

#### 数据格式1

```json
{
   "key1": "value1", "key2": "value2"
}
```

#### 数据格式2

```json
[
   {"key1": "value1"},
   {"key2": "value2"}
]
```

#### 数据格式3

使用上述两种格式上传数据，由于不包含时间戳，则由服务器将自己的时间戳进行填充。如果需要在设备端上传的数据各种填充给定的时间戳，则需要使用如下格式：

```json
{
    "ts": 1657242320000,
    "values": {
        "key1": "value1",
        "key2": "value2"
    }
}
```

其中的时间戳  `1657242320000`，对应时间 `2022-07-08 09:05:20`

### 例

#### 例1

```bash
access_token="1A454k3ud3X5if86sCQK"

curl -v \
-X POST \
--data "{"temperature":42, "humidity":73}" \
https://demo.thingsboard.io/api/v1/${access_token}/telemetry \
--header "Content-Type:application/json"
```

#### 例2

```bash
access_token="1A454k3ud3X5if86sCQK"

curl -v \
-X POST \
-d @telemetry-data-as-object.json \
https://demo.thingsboard.io/api/v1/${access_token}/telemetry \
--header "Content-Type:application/json"
```

上述代码中的 `telemetry-data-as-object.json` 是一个 `json` 格式的文件，其内容为

```json
{ 
   "temperature": 42, 
   "humidity": 73
}
```

#### 例3

```bash
access_token="1A454k3ud3X5if86sCQK"

curl -v \
-X POST \
-d @telemetry-data-as-array.json \
https://demo.thingsboard.io/api/v1/${access_token}/telemetry \
--header "Content-Type:application/json"
```

上述代码中的 `telemetry-data-as-array.json` 是一个 `json` 格式的文件，其内容为

```json
[
    {
        "temperature": 42
    },
    {
        "humidity": 73
    }
]
```

#### 例4

```bash
access_token="1A454k3ud3X5if86sCQK"

curl -v \
-X POST \
-d @telemetry-data-with-ts.json \
https://demo.thingsboard.io/api/v1/${access_token}/telemetry \
--header "Content-Type:application/json"
```

上述代码中的 `telemetry-data-with-ts.json` 是一个 `json` 格式的文件，其内容为

```json
{
    "ts": 1657242320000,
    "values": {
        "temperature": 42,
        "humidity": 73
    }
}
```

## 属性 API

> 参见：[HTTP Device API Reference | ThingsBoard Community Edition](https://thingsboard.io/docs/reference/http-api/#attributes-api)

属性 API 允许：

- 上传**客户端属性**至服务端
- 从服务端请求**客户端属性**、**共享属性**
- 订阅来自服务端的**共享属性**

### 发布属性更新至服务端

> 参见：[Swagger UI](http://192.168.110.212:8080/swagger-ui/#/device-api-controller/postDeviceAttributesUsingPOST)

```bash
curl -X 'POST' \
    'http://192.168.110.212:8080/api/v1/YHEZgGM4zNBiLThkNfyL/attributes' \
    -H 'accept: application/json' \
    -H 'Content-Type: application/json' \
    -d '{
      "client_attribute_key_1": "client_attribute_value_1",
      "client_attribute_key_2": "client_attribute_value_2"
}'
```

### 从服务器请求属性值

> 参见：[Swagger UI](http://192.168.110.212:8080/swagger-ui/#/device-api-controller/getDeviceAttributesUsingGET)

从服务端请求：客户端属性、共享属性

```bash
curl -X 'GET' \
  'http://192.168.110.212:8080/api/v1/YHEZgGM4zNBiLThkNfyL/attributes?clientKeys=client_attribute_key_1%2C%20client_attribute_key_2&sharedKeys=shared_attribute_key_1%2Cshared_attribute_key_2' \
  -H 'accept: application/json'
```

响应

```json
{
    "client": {
        "client_attribute_key_1": "client_attribute_value_1"
    },
    "shared": {
        "shared_attribute_key_1": "shared_attribute_value_1",
        "shared_attribute_key_2": "shared_attribute_value_2"
    }
}
```

### 订阅来自服务器的属性更新

> 参见：[HTTP Device API Reference | ThingsBoard Community Edition](https://thingsboard.io/docs/reference/http-api/#subscribe-to-attribute-updates-from-the-server)

```bash
access_token=YHEZgGM4zNBiLThkNfyL

curl -v -X GET "http://192.168.110.212:8080/api/v1/${access_token}/attributes/updates?timeout=20000"
```

假定客户端已经存在一个共享属性 `shared_attribute_key_1`，当其值发生变化时，客户端将会收到响应：

```json
{
    "shared_attribute_key_1": "shared_attribute_value_1_1"
}
```

