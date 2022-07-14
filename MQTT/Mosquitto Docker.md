# Mosquitto Docker

## 拉取镜像

> 镜像地址：[Eclipse-mosquitto - Official Image | Docker Hub](https://hub.docker.com/_/eclipse-mosquitto)

```bash
$ docker pull eclipse-mosquitto
```

## 数据卷

创建目录

```bash
$ mkdir -p ~/mosquitto/config
$ mkdir -p ~/mosquitto/data
$ mkdir -p ~/mosquitto/log
```

权限（待定）

```bash
$ chmod -R 755 ~/mosquitto
$ chmod -R 777 ~/mosquitto/log
```

## 配置

> 配置文件 `mosquitto.conf` 的模板参见：[mosquitto/mosquitto.conf at master · eclipse/mosquitto (github.com)](https://github.com/eclipse/mosquitto/blob/master/mosquitto.conf)

创建配置文件

```bash
$ touch ~/mosquitto/config/mosquitto.conf
```

内容如下

```text
persistence true
persistence_location /home/username/mosquitto/data
log_dest file /home/username/mosquitto/log/mosquitto.log
listener 1883

allow_anonymous true
```

授权

```bash
$ chmod 755 ~/mosquitto/config/mosquitto.conf
```

## 启动镜像

通过查看镜像的描述界面可知，在镜像中以下创建了如下三个目录用户保存配置信息及文件

```
/mosquitto/config
/mosquitto/data
/mosquitto/log
```

对应的，在启动镜像时，可以通过 `-v` 参数分别对应的挂载数据卷

```bash
$ docker run -it \
    --name=mosquitto \
    --privileged \
    -p 1883:1883 \
    -p 9001:9001 \
    -v ~/mosquitto/config:/mosquitto/config \
    -v ~/mosquitto/data:/mosquitto/data \
    -v ~/mosquitto/log:/mosquitto/log \
    -d eclipse-mosquitto
```

## 查看容器

```bash
$ docker ps
```

## 进入容器

```bash
$ docker exec -it mosquitto bash
```

## 权限

如果需要限制登录，则需要在 `mosquitto.conf` 中增加或修改如下配置项

```
allow_anonymous false
password_file /home/username/mosquitto/config/pwfile.conf
```

创建文件

```bash
$ touch ~/mosquitto/config/pwfile.conf
```

授权

```bash
$ chmod -R 755 ~/mosquitto/config/pwfile.conf
```

创建用户并指定密码，用户名为 `admin` 密码为 `123456`

```bash
$ mosquitto_passwd -b ~/mosquitto/config/pwfile.conf admin 123456
```

## 重启服务器

```bash
$ docker restart mosquitto
```

