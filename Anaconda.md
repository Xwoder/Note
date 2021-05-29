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

指定创建 conda 环境并克隆指定环境包

```python
conda create -n 目标环境名 --clone 源环境名
```

### 版本

#### 查看

```bash
conda --version
```

#### 列示

```bash
conda list --revisions
```

#### 切换

```bash
conda install --revision 版本号
```

### 删除

```bash
$ conda env remove -n 环境名称
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

## 卸载

删除配置

```bash
conda install anaconda-clean
anaconda-clean --yes
```

删除安装文件

```bash
rm -rf ~/anaconda3
```

删除目录

```text
~/.anaconda_backup/<timestamp>
```

清理 `.bash_profile` 或 `.zsh_rc` 等用户配置文件的相关配置项