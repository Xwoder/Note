# Flask

## 安装

```bash
pip install flask
```

## app.run

### debug模式

#### IDE

如果使用 `PyCharm Professional` 集成开发环境，则可以在 `Run/Debug Configurations`对话框中的勾选 `FLASK_DEBUG` 选项以使用 `debug` 模式。

#### 代码

如果使用其他编辑器或者集成开发环境，则可以通过在 `app.run` 方法中将 `debug` 参数指定为 `True` 以实现使用调试模式，如：

```python
if __name__ == '__main__':
    app.run(debug=True)
```

## 主机地址

#### IDE

如果使用 `PyCharm Professional` 集成开发环境，则可以在 `Run/Debug Configurations`对话框中的 `Additional options` 文本框中输入如下内容以指定要监听的端口，例如：

```
--host=0.0.0.0
```

#### 代码

```python
if __name__ == '__main__':
    app.run(host="0.0.0.0")
```

## 端口

#### IDE

如果使用 `PyCharm Professional` 集成开发环境，则可以在 `Run/Debug Configurations`对话框中的 `Additional options` 文本框中输入如下内容以指定要监听的端口，例如：

```
--post=8080
```

#### 代码

```python
if __name__ == '__main__':
    app.run(port=8080)
```

## 示例

### 示例1

```python
from flask import Flask

app = Flask(__name__)


@app.route("/")
def hello():
    return "Hello World"


if __name__ == '__main__':
    app.run()
```

## Config 对象

## 模板

默认模板文件夹名称：`templates`

## 静态文件

默认静态文件夹名称：`static`