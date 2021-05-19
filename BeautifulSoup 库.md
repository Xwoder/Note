# BeautifulSoup 库

[TOC]

## 官方信息

[官方网站](https://www.crummy.com/software/BeautifulSoup/)

## 示例

### 示例1

```python
from bs4 import BeautifulSoup

markup = '<p class="title"><b>The Little Prince</b></p>'

soup = BeautifulSoup(markup, 'lxml')

# <html><body><p class="title"><b>The Little Prince</b></p></body></html>
print(soup)

# <p class="title"><b>The Little Prince</b></p>
print(soup.p)

# p
print(soup.p.name)

# 获取属性字典
# {'class': ['title']}
print(soup.p.attrs)

# 获取某个属性
# ['title']
print(soup.p['class'])

# 找到所有的标签
# [<b>The Little Prince</b>]
print(soup.p.find_all('b'))

# <b>The Little Prince</b>
print(soup.b)
```

### 解析豆瓣网书籍短评