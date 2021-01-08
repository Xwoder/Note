# Requests 库

[TOC]

## 官方信息

[官方网站](https://requests.readthedocs.io/)

[GitHub](https://github.com/psf/requests)

## 示例

```python
import requests

headers = {
    'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9',
    'Host': 'book.douban.com',
    'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36'
};
response = requests.get('https://book.douban.com/subject/35196328/comments/', headers=headers)

# 状态码
print(response.status_code)

# Unicode 码形式的响应内容
print(response.text)
```

## 响应类

### 内容

#### `text`

```python
response.text
```

例

```python
import requests

response = requests.get('https://book.douban.com/subject/35196328/comments/')

# 状态码
print(response.status_code)

# Unicode 码形式的响应内容
print(response.text)
```

#### `json`

```python
response.json
```

#### `content`

```python
response.content
```

例

```python
import requests

response = requests.get('https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png')

with open('google.png', 'wb') as fp:
    fp.write(response.content)
```

