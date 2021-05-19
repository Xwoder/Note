#  Requests

[TOC]

## 相关网站

[GitHub](https://github.com/psf/requests)

[Document](https://requests.readthedocs.io/en/master/)

## 导入

```Python
import requests
```

## 实例

```Python
import requests

url = 'http://www.baidu.com'

response = requests.get(url)

response.text
```

将得到

```html
<!DOCTYPE html>
<!--STATUS OK--><html> <head><meta http-equiv=content-type content=text/html;charset=utf-8><meta http-equiv=X-UA-Compatible content=IE=Edge><meta content=always name=referrer><link rel=stylesheet type=text/css href=http://s1.bdstatic.com/r/www/cache/bdorz/baidu.min.css><title>ç¾åº¦ä¸ä¸ï¼ä½ å°±ç¥é</title></head> <body link=#0000cc> <div id=wrapper> <div id=head> <div class=head_wrapper> <div class=s_form> <div class=s_form_wrapper> <div id=lg> <img hidefocus=true src=//www.baidu.com/img/bd_logo1.png width=270 height=129> </div> <form id=form name=f action=//www.baidu.com/s class=fm> <input type=hidden name=bdorz_come value=1> ...
```

## 响应

### 解码

```python
response.content.decode()
```

指定解码编码类型

```
response.content.decode('GBK')

response.content.decode('UTF-8')
```

### 属性

#### url

#### status_code

#### request.headers

#### headers

#### request_cookies

#### cookies

### 方法

#### json()

