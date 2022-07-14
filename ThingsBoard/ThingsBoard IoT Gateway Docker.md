# ThingsBoard IoT Gateway Docker

[TOC]

## 镜像

`ThingsBoard IoT Gateway` 的镜像参见：[thingsboard/tb-gateway - Docker Image | Docker Hub](https://hub.docker.com/r/thingsboard/tb-gateway)

## 拉取

```bash
$ docker pull thingsboard/tb-gateway
```

## 启动

```bash
$ docker run \
		--interactive \
    --tty \
    --volume ~/tb-gateway/logs:/var/log/thingsboard-gateway \
    --volume ~/tb-gateway/extensions:/var/lib/thingsboard_gateway/extensions \
    --volume ~/tb-gateway/config:/etc/thingsboard-gateway/config \
    --name tb-gateway \
    --restart always \
    thingsboard/tb-gateway
```

