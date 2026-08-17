# Docker

[TOC]

## 服务管理

> 以下命令适用于 Linux 系统

| 命令 | 作用 |
| :---: | :--- |
| `systemctl disable docker` | 禁用开机自启 |
| `systemctl enable docker` | 启用开机自启 |
| `systemctl stop docker` | 停止服务 | 
| `systemctl start docker` | 启动服务 |
| `systemctl restart docker` | 重启服务 |
| `systemctl reload docker` | 重载配置 |
| `systemctl status docker` | 查看状态 |

## 镜像管理

### 搜索镜像

```bash
docker search 关键字

docker search mysql
```

### 拉取镜像

```bash
docker pull 镜像名

# 拉取指定版本的 Redis 镜像
docker pull redis:5.0

# 拉取指定版本的 MySQL 镜像
docker pull mysql:8.0
```

### 查看镜像

```bash
docker images

docker images -q
```

### 删除镜像

```bash
# 根据镜像名删除
docker rmi nginx:latest

# 根据镜像ID删除
docker rmi abcdef123456

# 使用 -f 参数强制删除镜像
docker rmi -f nginx:latest
```

## 容器管理

### 创建容器

```bash
# 创建并启动一个容器，默认以前台方式运行
docker run 镜像名

# 示例：启动一个 Redis 容器
docker run redis:5.0

# 端口映射。格式：-p 宿主机端口:容器端口
docker run -d --name=my-web -p 8080:80 nginx
# 访问：http://localhost:8080 就能看到 Nginx 欢迎页
```

#### 后台运行

`-d` 参数指定容器以后台方式运行

```bash
# ==================== Docker 命令完整解释 ====================
# 命令功能：基于 Redis 5.0 版本镜像，创建并**后台启动**一个 Redis 容器
docker run -d redis:5.0

# 1. docker：Docker 客户端主命令，用于与 Docker 引擎通信，执行容器/镜像操作
# 2. run：Docker 核心子命令，作用 = 创建新容器 + 启动该容器
# 3. -d：--detach 的缩写，关键参数 → 容器以**后台守护进程模式**运行
#    效果：不占用当前终端，命令执行后直接返回容器ID
# 4. redis:5.0：指定容器使用的镜像（格式：镜像名:版本标签）
#    redis：官方 Redis 镜像名称
#    5.0：镜像的版本号，指定使用 Redis 5.0 稳定版
# ============================================================
```

#### 端口映射

`-d` 参数指定容器需要映射的内外部端口，格式为：`-d 宿主机端口:内部端口`

```bash
# ==================== Docker 命令完整解释 ====================
# 命令功能：创建并**前台启动**一个 Nginx 容器，将宿主机 8080 端口映射到容器 80 端口
docker run -p 8080:80 nginx

# 1. docker：Docker 客户端主命令，用于调用 Docker 引擎执行操作
# 2. run：Docker 核心子命令，作用 = 新建容器 + 启动容器（一步完成）
# 3. -p：端口映射参数（全称 --publish），核心作用：打通宿主机与容器的网络端口
#    格式：宿主机端口:容器内部端口
# 4. 8080:80：具体端口映射规则
#    宿主机 8080 端口 → 容器内部 80 端口
#    （Nginx 服务默认监听容器内 80 端口，外部访问宿主机 8080 即可访问容器内 Nginx）
# 5. nginx：指定使用的镜像（格式：镜像名:标签）
#    未指定版本 → 默认使用最新版 nginx:latest
# ============================================================
```

#### 命名容器

`--name` 参数指定容器需要映射的内外部端口，格式为：`--name 容器名称` 或者 `--name=容器名称`

```bash
# ==================== Docker 命令完整解释 ====================
# 命令功能：创建并**前台启动**一个 Redis 5.0 容器，为容器指定自定义名称 my-redis
docker run --name my-redis redis:5.0

# 1. docker：Docker 客户端主命令，用于与 Docker 引擎交互
# 2. run：Docker 核心子命令，功能 = 创建新容器 + 启动容器
# 3. --name：指定容器**自定义名称**的参数（全称 --name）
#    作用：替代 Docker 自动生成的随机名称，方便识别和操作容器
# 4. my-redis：自定义的容器名称（可自定义修改）
# 5. redis:5.0：指定使用的镜像（格式：镜像名:版本标签）
#    redis：官方 Redis 镜像
#    5.0：固定使用 Redis 5.0 版本
# ============================================================

# ==================== Docker 命令完整解释 ====================
# 命令功能：创建并**前台启动**一个 MySQL 8.0 容器，自定义容器名称为 my-mysql
docker run --name my-mysql mysql:8.0

# 1. docker：Docker 客户端主命令，用于与 Docker 引擎交互
# 2. run：Docker 核心子命令，功能 = 创建新容器 + 启动容器
# 3. --name：为容器指定**自定义名称**的参数，替代随机生成的名称
# 4. my-mysql：自定义的容器名称，后续可直接用该名称操作容器
# 5. mysql:8.0：指定使用的 Docker 镜像（格式：镜像名:版本标签）
#    mysql：官方 MySQL 数据库镜像
#    8.0：固定使用 MySQL 8.0 正式版本
# ============================================================
```

### 数据卷挂载

`-v` 参数指定容器需要映射的内外部端口，格式为：`-v 宿主机路径:内部路径`

```bash
docker run -id \
    -v ./webapps:/usr/local/tomcat/webapps \
    tomcat
```

### 后台运行

`-d` 参数让容器在后台运行

```bash
# ==================== 第一条命令：启动 Nginx 容器 ====================
# 功能：后台创建并启动一个名为 my-nginx 的 Nginx 容器（使用最新版镜像）
docker run -d --name my-nginx nginx

# 1. docker run    : 创建并启动容器
# 2. -d            : 后台运行容器（守护进程模式），不占用当前终端
# 3. --name my-nginx : 给容器起一个固定名字 my-nginx，方便后续管理
# 4. nginx         : 使用官方 Nginx 镜像，未指定版本 = 最新版 latest


# ==================== 第二条命令：启动 Redis 容器 ====================
# 功能：后台创建并启动一个名为 my-redis 的 Redis 5.0 容器
docker run -d --name my-redis redis:5.0 

# 1. docker run    : 创建并启动容器
# 2. -d            : 后台运行容器
# 3. --name my-redis : 容器名字叫 my-redis
# 4. redis:5.0     : 使用 Redis 5.0 版本的镜像
```

### 部署示例

```bash
# 部署 MySQL
docker run -id \
    -p 3307:3306 \
    --name=c_mysql \
    -v $PWD/conf:/etc/mysql/conf.d \
    -v $PWD/logs:/logs
    -v $PWD/data:/var/lib/mysql \
    -e MYSQL_ROOT_PASSWORD=123456 \
    mysql:8.0

# 部署 Tomcat
docker run -id \
    --name=tomcat \
    -p 8080:8080 \
    -v $PWD/webapps:/usr/local/tomcat/webapps \
    tomcat

# 部署 Nginx
docker run -id \
    --name=nginx \
    -p 80:80 \
    -v $PWD/conf/nginx.conf:/etc/nginx/nginx.conf \
    -v $PWD/logs:/var/log/nginx \
    -v $PWD/html:/usr/share/nginx/html \
    nginx

# 部署 Redis
docker run -id \
    --name=redis \
    -p 6379:6379 \
    redis:5.0
```



### 启动容器

```bash
# 根据容器名称启动
docker start nginx:latest

# 根据容器ID启动
docker start abcdef123456

# 启动全部容器
docker start $(docker ps -aq) 
```

### 停止容器

```bash
# 根据容器名称停止
docker stop nginx:latest

# 根据容器ID停止
docker stop abcdef123456

# 停止全部容器
docker stop $(docker ps -aq)
```

### 删除容器

```bash
# 根据容器名称删除
docker rm nginx:latest

# 根据容器ID删除
docker rm abcdef123456

docker rm $(docker ps -aq) # 删除全部容器
```

## 卷管理

存放卷的默认路径

```bash
/var/lib/docker/volumes
```

### 查看

```bash
docker volume ls
```

输出

```bash
DRIVER    VOLUME NAME
local     a11ff1f0b49ed78a1c4c2961cb037f04030434d52041024e1bed55a6aee187d0
local     jenkins_data
```

### 创建卷

```bash
docker volume create 卷名

# 创建一个名为 jenkins_data 的数据卷
docker volume create jenkins_data

# 创建一个名为 mysql_data 的数据卷
docker volume create mysql_data
```

### 删除卷

删除卷是不可逆的操作

```bash
docker volume rm 卷名

# 删除名为 jenkins_data 的数据卷
docker volume rm jenkins_data

# 删除名为 mysql_data 的数据卷
docker volume rm mysql_data
```

批量删除

```bash
# 删除所有未被任何容器挂载的卷
docker volume prune

# 强制删除所有未被任何容器挂载的卷
docker volume prune -f
```

## 运行状态管理

### 查看容器

```bash
# 仅列出当前正在运行的容器
# 显示容器ID、镜像、命令、创建时间、状态、端口映射和容器名称
docker ps

# 列出所有容器
# 无论容器是否正在运行，都会显示出来
docker ps -a

# 仅列出当前正在运行的容器的ID
# 输出结果仅包含容器ID，不包含表头和其他信息
docker ps -q
docker ps --quiet

# 列出所有容器ID，包含已停止或退出的容器
# 输出结果仅包含容器ID，不包含表头和其他信息
docker ps -a -q
```
