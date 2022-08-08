# ThingsBoard Docker

[TOC]

## 停止

停止 `docker` 前，可能需要如下命令停止 `PostgreSQL` 数据库

```bash
$ docker exec -it 容器标识 stop-db.sh
```

例如

```bash
$ docker exec -it thingsboard_mytb_1 stop-db.sh
```

