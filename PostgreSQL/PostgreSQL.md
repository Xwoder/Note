# PostgreSQL

[TOC]

## 架构

`PostgreSQL` 采用 `C/S` 即 `Client / Server` 结构。

`PostgreSQL` 采用多进程架构。主进程称为 `Postmaster`，其对应的程序名为 `postgres`。

当接受新的客户端连接请求之后，`Postmaster` 会创建一个新的 `postgres` 服务进程，并让该服务进程专门服务新的客户端连接，直到该连接关闭为止。

## 管理

> 以下命令均以 Ubuntu 环境为例

### 启动

```bash
$ service postgresql start
```

### 重启

```bash
$ service postgresql restart
```

### 停止

```bash
$ service postgresql stop
```

### 状态

```bash
$ service postgresql status
```

可能的输出

```text
● postgresql.service - PostgreSQL RDBMS
     Loaded: loaded (/lib/systemd/system/postgresql.service; enabled; vendor preset: enabled)
     Active: active (exited) since Tue 2022-07-12 12:22:02 CST; 1h 30min ago
    Process: 9098 ExecStart=/bin/true (code=exited, status=0/SUCCESS)
   Main PID: 9098 (code=exited, status=0/SUCCESS)
        CPU: 736us

Jul 12 12:22:02 shft-Vostro-5890 systemd[1]: Starting PostgreSQL RDBMS...
Jul 12 12:22:02 shft-Vostro-5890 systemd[1]: Finished PostgreSQL RDBMS.
```

## 配置

### 基本配置

#### 配置文件路径

```bash
/etc/postgresql/12/main/postgresql.conf
```

参考：[PostgreSQL: Documentation: 14: 20.3. Connections and Authentication](https://www.postgresql.org/docs/current/runtime-config-connection.html)

|       配置项       |    说明    |   默认值    |
| :----------------: | :--------: | :---------: |
| `listen_addresses` | 监听服务器 | `localhost` |
|       `post`       |  监听端口  |   `5432`    |
|                    |            |             |

#### 数据文件路径

数据文件的存放路径在 `postgresql.conf` 文件中配置，对应的配置项为是 `data_directory`。例如：

```yaml
#------------------------------------------------------------------------------
# FILE LOCATIONS
#------------------------------------------------------------------------------

# The default values of these variables are driven from the -D command-line
# option or PGDATA environment variable, represented here as ConfigDir.

data_directory = '/var/lib/postgresql/12/main'		# use data in another directory
					# (change requires restart)
```

### 日志

|       配置项        |     说明     |              默认值              |
| :-----------------: | :----------: | :------------------------------: |
| `logging_collector` | 日志采集开关 |              `off`               |
|   `log_directory`   | 日志文件路径 |             `pg_log`             |
|   `log_filename`    |  日志文件名  | `postgresql-%Y-%m-%d_%H%M%S.log` |

## `psql` 命令行

`psql` 即 `PostgreSQL interactive terminal` 是 `PostgreSQL` 一个命令行交互式客户端工具

```bash
$ psql
```

### 参数

|     参数     | 缩写 |   作用   |         示例          |
| :----------: | :--: | :------: | :-------------------: |
|   `--host`   | `-h` |   主机   |  `--host=127.0.0.1`   |
| `--username` | `-U` | `pg_log` | `--username=postgres` |

### 例

```bash
$ psql -h 127.0.0.1 -p 5432 -U postgres
```

登录的同时，使用 `-d` 参数指定要使用的数据库

```bash
$ psql -h 127.0.0.1 -p 5432 -U postgres -d database_test
```

### 命令

|     命令      |             作用             |        示例        |
| :-----------: | :--------------------------: | :----------------: |
|     `\c`      |          切换数据库          |     `\c mydb`      |
|     `\d`      | 查看数据库的关系或关系的结构 | `\d`、`\d student` |
| `\l`、`\list` |          查看数据库          |                    |

#### `\c`

切换数据库

```postgresql
\c 数据库名
```

例如

```
\c thingsboard
```

得到输出

```
您现在已经连接到数据库 "thingsboard", 用户 "postgres".
```

#### `\l` 或 `\list`

查看所有的数据库

#### `\q`

退出数据库

### 操作

#### 创建数据库

```postgresql
CREATE DATABASE mydb;
```

```postgresql
CREATE DATABASE test OWNER postgres TABLESPACE pg_default;
```

`PostgresSQL` 数据库还提供了一个 `Shell` 命令 `createdb` 来创建数据库

```bash
$ creatdb mydb;
```

#### 删除数据库

```postgresql
DROP DATABASE test;
```

```postgresql
DROP DATABASE IF EXISTS test;
```

