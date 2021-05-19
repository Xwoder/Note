# flask

将以下代码所在的 `py` 文件命名为 `helloworld.py`

```python
from flask import Flask

app = Flask(__name__)


@app.route('/')
def hello_world():
    return 'Hello World!'


if __name__ == '__main__':
    app.run()
```

在终端中调用如下命令，即可启动 Web 服务器

```bash
$ python app.py 
```

将得到类似如下输出

```
 * Serving Flask app "app" (lazy loading)
 * Environment: production
   WARNING: This is a development server. Do not use it in a production deployment.
   Use a production WSGI server instead.
 * Debug mode: off
 * Running on http://127.0.0.1:5000/ (Press CTRL+C to quit)
127.0.0.1 - - [14/Feb/2021 16:11:09] "GET / HTTP/1.1" 200 -
```

其中以 `http://` 开头的就是此次启动的 Web 服务器的地址。