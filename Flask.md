# Flask

[TOC]

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

### IDE

如果使用 `PyCharm Professional` 集成开发环境，则可以在 `Run/Debug Configurations`对话框中的 `Additional options` 文本框中输入如下内容以指定要监听的端口，例如：

```
--host=0.0.0.0
```

### 代码

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

## 配置

### Config 对象

`app.config` 是 `Config` 对象，用于存放配置项，它是一个继承自dict的子类，可以像使用dict一样使用它，例如：

```python
app.config["HOME_PAGE_MESSAGE"] = "Hello World"

@app.route('/')
def homePage():
    # 获取配置项
    message: str = app.config["HOME_PAGE_MESSAGE"]
    return message
```

### 配置文件

配置文件的默认名称：`config.py`，其内容格式如下所示：

```python
HOME_PAGE_MESSAGE = "Hello World"
```

之后需要在代码中使用如下 `from_object` 等方法加载配置：

```python
# 加载配置项
app.config.from_object(config)

@app.route('/')
def homePage():
    # 获取配置项
    message: str = app.config["HOME_PAGE_MESSAGE"]
    return message
```

## @app.route

> 下例中，假定主机地址为192.168.1.1，端口为8080

路由装饰器，使用该装饰器装饰的函数称作**视图函数**。例如：

```python
@app.route("/")
def hello():
    return "Hello World"
```

### rule 参数

用于指定该视图函数对应的 `URL` 规则

```python
@app.route(rule="/")
def hello():
    return "Hello World"
```

上例中，`"/"` 代表根目录，该视图函数可以通过 `http://192.168.1.1:8080/`访问

```python
@app.route(rule="/hello/")
def hello():
    return "Hello World"
```

上例中的视图函数可以通过 `http://192.168.1.1:8080/hello` 或 `http://192.168.1.1:8080/hello/` 访问

#### 无参数

略，见上文

#### 有参数

在 `@app.route` 装饰器的 `rule` 参数中使用，其语法格式为：`<参数名称>`

```python
@app.route(rule='/hello/<name>')
def homePage(name: str):
    return f"Hello, {name}"
```

上例中的视图函数可以通过 `http://127.0.0.1:5000/hello/xxx` 访问，其中 `xxx` 部分的内容会被作为视图函数的 `name` 参数传递给函数， 其默认为字符串类型。

例如：访问

```
http://127.0.0.1:5000/hello/Jack
```

会得到

```
Hello, Jack
```

##### 转换器

使用转换器可以将参数转换为指定类型，其语法格式为：`<转换器:参数名称>`

```python
@app.route('/book/<int:bookID>')
def bookForId(bookID: int):
    return f"Book ID: {bookID}"
```

如果转换失败，`URL` 就不会被匹配，从而引发 `404` 错误。

常见的转换器有：

| 转换器 |   含义   |
| :----: | :------: |
|  int   |   整型   |
| float  | 浮点类型 |
| string | 字符串型 |
|  path  |  路径型  |
|  uuid  |  UUID型  |
|  any   |  any型   |

###### any

`any` 型用于从给定的几个备选项中选择其中的一个，例如：

```python
@app.route("/blog/list/<any(python,flask,django):category>")
def blog_list_with_category(category):
    return f"category: {category}"
```

上例中，`category` 参数可能的值只能是  `python`、`flask`、`django `中的任意一个，否则会引发 `404` 错误。

##### 多参数

```python
@app.route('/searchBook/<string:keyword>/<int:page>')
def searchBook(keyword: str, page: int = 1):
    return f"keyword: {keyword}, page: {page}"
```

##### 参数默认值

```python
@app.route('/searchBook/<string:keyword>')
@app.route('/searchBook/<string:keyword>/<int:page>')
def searchBook(keyword: str, page: int = 1):
    return f"keyword: {keyword}, page: {page}"
```

使用 `searchBook/Python/` 访问视图函数时，由于省略了带有默认值的参数 `page`，会自动使用默认值填充该函数，此时将得到：

```python
keyword: Python, page: 1
```

### HTTP 方法

默认情况下，`@app.route` 装饰器修饰的方式，仅支持使用 `GET` 方法访问。可以通过设置 `methods` 参数的值来修改允许的访问方式。例如：

```python
@app.route("/method/", methods=["GET", "POST"])
def index():
    method: str = request.method
    message = f"method: {method}"
    print(message)
    return message
```

上例中，视图函数可以通过 `GET` 和 `POST` 方法访问。

可以使用快捷装饰路由来指定允许的 `HTTP` 方法：

|    装饰器     | HTTP 方法 |              等价于              |
| :-----------: | :-------: | :------------------------------: |
|  `@app.get`   |   `GET`   |  `@app.route(methods=["GET"])`   |
|  `@app.post`  |  `POST`   |  `@app.route(methods=["POST"])`  |
|  `@app.put`   |   `PUT`   |  `@app.route(methods=["OUT"])`   |
| `@app.delete` | `DELETE`  | `@app.route(methods=["DELETE"])` |
| `@app.patch`  |  `PATCH`  | `@app.route("methods=["PATCH"])` |



## 查询字符串

可以使用查询字符串的形式传递参数，形如：

```
/login?username=root&password=123456
```

对应的，想要在视图函数中获取查询字符串中的各项参数的值，应使用 `request` 对象提供的方法：

```python
@app.post("/login/")
def login():
    username = request.args.get("username")
    password = request.args.get("password")
    if username == "root" and password == "123456":
        return "Login Success"
    else:
        return "Login Failure"
```

`request.args` 是一个继承自 `dict` 的对象，以键值对的形式保存了当前查询字符串的键和值，可以通过索引或`get` 方法获取。

## 模板

默认模板文件夹名称：`templates`

## 静态文件

默认静态文件夹名称：`static`