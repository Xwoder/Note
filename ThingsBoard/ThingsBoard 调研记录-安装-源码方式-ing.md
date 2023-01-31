# ThingsBoard 调研记录-安装-源码方式

[TOC]

> 以 macOS 为例

## 安装 Java

安装 `Java 11`，使用 `HomeBrew` 安装

```bash
brew install java11
```

指定了 Java 版本后，查看 Java 版本信息，以确保为 Java 11

```bash
java --version
```

可能的输出为

```text
openjdk 11.0.15 2022-04-19
OpenJDK Runtime Environment Homebrew (build 11.0.15+0)
OpenJDK 64-Bit Server VM Homebrew (build 11.0.15+0, mixed mode)
```

## 安装 PostgreSQL

使用 `Homebrew` 方式安装

```bash
brew install postgresql
```

启动 `PostgreSQL` 服务

```bash

brew services restart postgresql
```

```postgresql
-- 设置密码
\password
```

输入并确认密码后，退出终端

```postgresql
-- 退出终端
\q
```

### 配置

配置认证方式

```bash
sudo subl /var/lib/pgsql/12/data/pg_hba.conf
```

修改如下内容

```
# IPv4 local connections:
host    all             all             127.0.0.1/32            ident
```

为

```
# IPv4 local connections:
host    all             all             127.0.0.1/32            md5
```

### 重启

重新启动 `PostgreSQL`

```bash
sudo systemctl restart postgresql-12.service
```

### 登录

通过 `PostgreSQL` 交互式终端连接数据库

```bash
psql -U postgres -d postgres -h 127.0.0.1 -W
```

### 创建数据库

创建 `ThingsBoard` 数据库，并退出

```sql
CREATE DATABASE thingsboard;
\q
```

## 安装和运行 ThingsBoard

### 配置

```bash
sudo subl /etc/thingsboard/conf/thingsboard.conf
```

添加如下配置信息

```bash
# DB Configuration 
export DATABASE_TS_TYPE=sql
export SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.PostgreSQLDialect
export SPRING_DRIVER_CLASS_NAME=org.postgresql.Driver
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/thingsboard
export SPRING_DATASOURCE_USERNAME=postgres
export SPRING_DATASOURCE_PASSWORD=PUT_YOUR_POSTGRESQL_PASSWORD_HERE
# Specify partitioning size for timestamp key-value storage. Allowed values: DAYS, MONTHS, YEARS, INDEFINITE.
export SQL_POSTGRES_TS_KV_PARTITIONING=MONTHS
```

同时，替换其中的 `PUT_YOUR_POSTGRESQL_PASSWORD_HERE` 为实际的用户密码

选择 `ThingsBoard` 队列服务，出于学习目的呢，本例中保持默认配置，即内存消息服务（`In Memory`） 

### 安装

执行安装脚本

```bash
# --loadDemo option will load demo data: users, devices, assets, rules, widgets.
sudo /usr/share/thingsboard/bin/install/install.sh --loadDemo
```

### 防火墙配置

ThingsBoard 默认使用 8080 端口

```bash
sudo firewall-cmd --zone=public --add-port=8080/tcp --permanent
sudo firewall-cmd --reload
```

### 启动服务

```bash
sudo service thingsboard start
```

## 访问

```
http://localhost:8080/
```