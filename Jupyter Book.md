## Jupyter Book

[TOC]

## 官方信息

[官方网站](https://jupyterbook.org/)

## 安装

```bash
$ pip install -U jupyter-book
```

## 构建

### 产物类型

支持 2 种形式的构建产物

* HTML
* PDF

### 构建类型

#### 构建图书

```bash
$ jupyter-book build 目录
```

例

```bash
$ jupyter-book build mybookname/
```

#### 构建独立页面

```bash
$ jupyter-book build path/to/mypage.ipynb
```

### 构建产物

#### 路径

```bash
_build/html
```

## 帮助

```bash
$ jupyter-book --help
```

输出

```txt
Usage: jupyter-book [OPTIONS] COMMAND [ARGS]...

  Build and manage books with Jupyter.

Options:
  --version   Show the version and exit.
  -h, --help  Show this message and exit.

Commands:
  build   Convert your book's or page's content to HTML or a PDF.
  clean   Empty the _build directory except jupyter_cache.
  config  Inspect your _config.yml file.
  create  Create a Jupyter Book template that you can customize.
  myst    Manipulate MyST markdown files.
  toc     Generate a _toc.yml file for your content folder.
```

## 核心文件

* `_config.yml`
    * 配置文件
* `_toc.yml`
    * 目录文件

### 配置文件

```yaml
#######################################################################################
# Book settings
title : Scientific Python QuickStart
author: Thomas J. Sargent and John Stachurski
logo: 'qe-logo-large.png'

# Information about where the book exists on the web
description: >-
  A brief introduction to Python programming for scientific applications.

#######################################################################################
# Execution settings
execute:
  execute_notebooks           : cache

#######################################################################################
# HTML-specific settings
html:
  home_page_in_navbar         : false

# #######################################################################################
# Interact link settings
notebook_interface            : "notebook"

#######################################################################################
# Launch button settings
repository:
  url                         : https://github.com/executablebooks/quantecon-mini-example
  path_to_book                : "mini_book"

binder:
  binderhub_url               : "https://mybinder.org"
  text                        : "Launch binder"

latex:
  latex_engine                : "xelatex"
  latex_documents:
    targetname: book.tex
```

### 目录文件

```yaml
# - file: README
- file: docs/index
  sections:
    - file: docs/about_py
    - file: docs/getting_started
    - file: docs/python_by_example
    - file: docs/learn_more
```

## 目录结构

```txt
mybookname/
├── _config.yml
├── _toc.yml
├── landing-page.md
└── page1.ipynb
```

## 创建

### 非交互方式

```bash
$ jupyter-book create 目录
```

### 交互方式

```bash
$ jupyter-book create 目录 --cookiecutter
```

