# Anaconda

[TOC]

## 环境

### 启动

```shell
$ conda activate
```

默认的环境是 `base`

```shell
$ conda activate 环境名称
```

### 停止

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

```shell
$ conda create --name 环境名称 python=版本号
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

