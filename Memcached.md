# Memcached

[TOC]

> 基于 macOS 和 Homebrew 环境

## 安装

```bash
$ brew install memcached
```

## 启动

```bash
$ brew service start memcached
```

## telnet 连接

```bash
$ telnet 127.0.0.1 11211
```

## python-memcached

### 连接

#### 打开

```python
mcClient = memcache.Client(servers=['127.0.0.1:11211'],
                         debug=True)
```

#### 关闭

```python
mcClient.disconnect_all()
```

### 设置键值对

#### 单个键值对

```python
mcClient.set(key='username',
           val='Jack',
           time=60)
```

#### 多个键值对

```python
mcClient.set_multi({"age": 18, "gender": "M"}, 
                 time=60)
```

### 获取键值对

```python
username = mcClient.get(key='username')
age = mcClient.get(key='age')
gender = mcClient.get(key='gender')
```

### 删除键值对

#### 删除单个键值对

```python
mcClient.delete("username")
mcClient.delete("age")
mcClient.delete("gender")
```

#### 删除多个键值对

```python
mcClient.delete_multi(["username", "age", "gender"])
```

### 自增自减

现有键值对

```python
mcClient.set("counter", 0)
```

#### 自增

每对该键调用一次 `incr` 方法，默认该键对应的值就会增加 `1`，同时返回增加后的值。

```python
mcClient.incr("counter")  # 1
mcClient.incr("counter")  # 2
mcClient.incr("counter")  # 3
```

#### 自减

每对该键调用一次 `decr` 方法，默认该键对应的值就会减少 `1`，同时返回减少后的值。

```python
mcClient.decr("counter")  # 2
mcClient.decr("counter")  # 1
mcClient.decr("counter")  # 0
```

### 替换

```python
mcClient.replace("gender", "F")
```

替换成功返回 `True`，否则返回 `False`