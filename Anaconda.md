# Anaconda

[TOC]

## 源

### 清华大学

```
https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/main/
https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/free/
https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/conda-forge/
https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/pytorch/
```

### 中国科学技术大学

```
https://mirrors.ustc.edu.cn/anaconda/pkgs/main/
https://mirrors.ustc.edu.cn/anaconda/pkgs/free/
https://mirrors.ustc.edu.cn/anaconda/cloud/conda-forge/
```

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

```shell
rm -rf ~/anaconda3
```

删除目录

```text
~/.anaconda_backup/<timestamp>
```

清理 `.bash_profile` 或 `.zsh_rc` 等用户配置文件的相关配置项

```shell
conda info
```

```shell
conda config --show
```

```shell
conda update -n base conda
```

```shell
conda update anaconda
```

```shell
conda update python 
```

```shell
conda list
conda list 包名
```

```shell
conda list -n 环境名
```

```shell
conda search 包名
conda search pandas
```

```shell
conda install 包名
conda install pandas

conda install 包名=版本号
conda install pandas=1.4.2
```

```shell
conda install "包名[version='版本号1 | 版本号2']"
conda install "pandas[version='1.4.2 | 1.4.0']"
```

```shell
conda update 包名
conda update pandas
```

```shell
conda remove 包名
conda remove pandas
```

```shell
conda activate 环境名
conda activate quant
```

```shell
conda deactivate
```

