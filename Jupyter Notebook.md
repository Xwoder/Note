# Jupyter Notebook

## 启动

```Bash
$ jupyter notebook
```

在启动完成后，终端窗口会打印出访问 `Jupyter Notebook` 页面的 `URL` 地址。例如：

```
To access the notebook, open this file in a browser:
    file:///Users/UserName/Library/Jupyter/runtime/nbserver-74158-open.html
Or copy and paste one of these URLs:
    http://localhost:8888/?token=3baa4aab1b84d7cf4df781c72f3e74d8b1e4cc07feb14648
 or http://127.0.0.1:8888/?token=3baa4aab1b84d7cf4df781c72f3e74d8b1e4cc07feb14648
```

通过访问这些 URL 中的任何一个，都可以启动  `Jupyter Notebook` 的 Web 页面。

## 配置

### 路径

```
~/.jupyter/jupyter_notebook_config.py
```

### 生成

```Bash
$ jupyter notebook --generate-config
```

### 配置项

#### `notebook_dir`

指定 `Notebook` 的内核和路径。如：

```
c.NotebookApp.notebook_dir = r'Documents/MyJupyterNotebook'
```

## 帮助

可以通过 `?` 或 `help` 命令来查看帮助信息

```Python
import numpy as np
? np.abs
help(np.abs)
```

## Magic 魔法命令

### 功能

- 混合编程命令
- 执行 `shell` 命令
- 执行特定功能

### 分类

- 行魔法命令 / `Line Magic`

    以 `%`作为开头，只作用于一个单元行

- 单元魔法命令 / `Cell Magic`

    以 `%%`作为开头，多数情况下只能在一个单元的开始使用，个别情况除外。

    一个单元只能有一个同样的单元魔法命令

### 常见魔法命令

- `%lsmagic`

    列示魔法命令

* `%load`

    加载 `Python` 模块。例：

    ```Python
    %load 'tools/string_tools.py'
    ```

* `%pwd`

    查看 `Jupty Notebook` 当前工作路径

* `%run`

    运行指定的 `.py` 格式的文件

* `%system`

    用于在 `Jupty Notebook` 环境中执行 `shell` 命令。

    该命令的简写形式为 `!!`

    例

    ```Python
    %system ping 127.0.0.1 -c 2
    !! ping 127.0.0.1 -c 2
    ```

* `%time`

    测试单元内代码单次的执行时间

* `%timeit`

    测试单元内代码多次的执行时间。默认为7次，可以通过 `-r` 命令指定执行次数

#### 混合编程相关

* `%%bash`

* `%%HTML`

* `%%python2`

* `%%python3`

* `%%ruby`

* `%%perl`

* `%%javascript`

* `%%latex`

    将单元格内容格式指定为 `latex`

* `%%markdown`

    将单元格内容格式指定为 `markdown`

#### 文件操作相关

* `%%writefile`

#### 性能测试相关

* `%%time`
* `%%timeit`

## 快捷键

|     快捷键      | 作用                 |
| :-------------: | -------------------- |
| `Ctrl + Enter`  | 执行当前行格代码     |
| `Shift + Enter` | 创建并移动至下一行格 |
|       `A`       | 在上一行插入格       |
|       `B`       | 在下一行插入格       |
|      `DD`       | 删除当前行格         |

