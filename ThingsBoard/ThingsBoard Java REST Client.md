# ThingsBoard Java REST 客户端

由 `RestClient` 类表示

## 客户端操作

### 创建与连接

创建该对象时须指定服务器地址

```java
final String url = "http://127.0.0.1:8080";

RestClient client = new RestClient(url);
```

### 关闭

```java
client.close();
```

## 用户操作

### 登录

```java
final String username = "tenant@thingsboard.org";
final String password = "tenant";

client.login(username, password);
```

### 登出

```java
client.logout();
```

## 属性操作