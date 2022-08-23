# Docker

[TOC]

## 数据卷容器

创建数据卷容器

```bash
docker run --it --name=c3 -v /volume centos:7 /bin/bash
```

创建普通容器

```bash
docker run -it --name=c1 --volumes-from c3 centos:7 /bin/bash

docker run -it --name=c2 --volumes-from c3 centos:7 /bin/bash
```

## 部署示例

### 部署 `MySQL` 镜像

```bash
docker run -id \
-p 3307:3306 \
--name=c_mysql \
-v $PWD/conf:/etc/mysql/conf.d \
-v $PWD/logs:/logs
-v $PWD/data:/var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=123456 \
mysql:5.6
```

### 部署 `Tomcat` 镜像

```bash
docker run -id \
--name=tomcat \
-p 8080:8080 \
-v $PWD/webapps:/usr/local/tomcat/webapps \
tomcat
```

### 部署 `Nginx`

```bash
docker run -id \
--name=nginx \
-p 80:80 \
-v $PWD/conf/nginx.conf:/etc/nginx/nginx.conf \
-v $PWD/logs:/var/log/nginx \
-v $PWD/html:/usr/share/nginx/html \
nginx
```

### 部署 `Redis`

```bash
docker run -id \
--name=redis \
-p 6379:6379 \
redis:5.0
```

## 镜像制作

### 容器转换为镜像

```bash
docker commit 容器ID 镜像名称:版本号
```

### 压缩镜像

```bash
docker save -o 压缩文件名称 镜像名称:版本号
```

### 加载镜像

从压缩文件中加载镜像

```bash
docker load -i 压缩文件名称
```

## Dockerfile

> https://docs.docker.com/engine/reference/builder/

### 指令

#### `FROM`

定义父镜像

#### `WORKDIR`

定义工作目录

#### `CMD`

容器的缺省命令

### 命令

#### 构建

```bash
docker build -f ./xxx_dockerfile -t 镜像名称
```



# 启动 Docker
```bash
systemctl start docker
```



# 关闭 Docker
```bash
systemctl stop docker
```



# 重启 Docker
```bash
systemctl restart docker
```



# 启动 Docker 服务
# Docker 将每次跟随开机自行启动
```bash
systemctl enable docker
```



# 查看 Docker 运行状态
```bash
systemctl status docker
```

# 查看本地镜像
```bash
docker images
```

# 查看所有的镜像ID
```bash
docker images -q
```

# 搜索镜像
docker search ...
docker search redis

# 拉取镜像，即下载镜像
docker pull ...
docker pull redis:5.0

# 删除镜像
```bash
docker rmi 镜像ID
docker rmi 镜像名称
```

# 运行容器
```bash
docker run 镜像名称 参数
docker run --interactive --tty --name=myCentOS centos:7 /bin/bash
docker run --interactive --detach --name=myCentOS centos:7
```

# 启动容器
```bash
docker start 容器ID/容器名称
```

# 停止容器
```
docker stop 容器ID/容器名称
```

# 删除容器
docker rm 容器ID/容器名称

# 查看容器状态
## 查看正在运行的容器
```bash
docker ps
```


## 查看所有容器
```bash
docker ps -a
```


## 查看所有容器的ID
```bash
docker ps -q/--quiet
```



# 在运行中的容器内执行命令
```bash
docker exec -it myCentOS /bin/bash
```



# 查看容器信息
```bash
docker inspect 容器ID/容器名称
```



# 配置数据卷
```bash
docker run ... -v 宿主机目录或文件:容器目录或文件
```



容器目录或文件必须是绝对路径

如果目录不存在，会自动创建

# 创建数据卷容器
```
docker run -it --name=c3 -v /volume centos:7 /bin/bash
```

