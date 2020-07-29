# Python 模块

每一个以 `py` 结尾的 `Python 源文件` 都是一个**模块**

模块中可以包含*函数*和*变量*

## 导入

模块需要通过 `import` 关键字导入

```Python
import 模块名

import 模块名1, 模块名2
```

## 命名

模块名也是一个标识符。

不能以数字开头


以数字开头的模块源文件是无法使用的。

## 别名

```Python
import 模块名 as 模块别名
```

## 部分导入

```
from 模块名 import 函数名/类名/变量名
from 模块名 import 函数名/类名/变量名 as 别名
from 模块名 import *
```

