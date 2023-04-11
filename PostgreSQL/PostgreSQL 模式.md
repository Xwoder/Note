# PostgreSQL SCHEMA

`SCHEMA` 即**模式**，是视图、索引、数据类型、函数和操作符等的集合。

## 创建

```postgresql
CREATE SCHEMA ...
```

例如

```postgresql
CREATE SCHEMA private;
```

创建模式的同时，指定其拥有者

```postgresql
CREATE SCHEMA private AUTHORIZATION jack;
```

## 删除

```postgresql
DROP SCHEMA ...
```

## 修改

```postgresql
ALTER SCHEMA ...
```

## 搜索路径

搜索路径是一个逗号分隔的模式名称。

- 当我们使用表的时候，PostgreSQL 会依次在这些模 式中进行查找， 返回第一个匹配的表名；
- 当我们创建一个新表时， 如果没有指定模式名称， PostgreSQL 会在第一个模式中进行创建。

### 查看

```postgresql
SHOW SEARCH_PATH;
```

### 设置

```postgresql
SET SEARCH_PATH TO app,public;
```

### 重置

```postgresql
RESET SEARCH_PATH;
```