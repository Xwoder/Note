# Anaconda

[TOC]

## 环境

### 启动

```shell
$ conda activate
```

默认激活的环境是 `base`。如果想指定需要激活的环境，则可以使用如下命令

```shell
$ conda activate 环境名称
```

### 退出

```shell
$ conda deactivate
```

### 列示

```shell
$ conda env list
```

```bash
$ conda info --envs
```

### 创建

```bash
$ conda create --name 环境名称
```

```bash
$ conda create --name 环境名称 python=版本号
```

### 删除

```bash
$ conda remove -n 环境名称
```

## 升级

```shell
$ conda update anaconda
```

```shell
$ conda update --all
```

## 库

### 列示

列出所有已经链接至 `conda` 环境的库

```bash
$ conda list
```

### 安装

```shell
$ conda intall 库名
```

