# MongoDB

[TOC]

## 安装

通过 `Homebrew` 安装

```bash
brew install mongodb-community
```

## 启动

通过 `brew services` 命令启动

```bash
brew services start mongodb/brew/mongodb-community
```

通过 `mongod` 命令启动

```bash
mongod --config /usr/local/etc/mongod.conf
```

## 停止

通过 `brew services` 命令启动的，依然需要通过 `brew services` 命令停止

```bash
brew services stop mongodb/brew/mongodb-community
```

通过 `mongod` 命令启动的，直接终止进程即可。

## 数据类型

|    类型     |     说明     |
| :---------: | :----------: |
| `Object ID` |              |
|  `String`   |  字符串类型  |
|  `Boolean`  |   布尔类型   |
|  `Integer`  |     整型     |
|  `Double`   | 双精度浮点型 |
|  `Arrays`   |   数组类型   |
|   `Null`    |    空类型    |
|  `Object`   |              |
| `Timestamp` |  时间戳类型  |
|   `Date`    |   日期类型   |

## 操作

### 连接

连接 `MongoDB` 服务端

```bash
mongosh
```

### 数据库操作

查看当前数据库

```mongodb
db
```

查看所有数据库

```mongodb
show dbs
```

```mongodb
show databases
```

切换数据库

```mongodb
use db_name
```

如果数据库不存在，将自动创建

删除当前数据库

```mongodb
db.dropDatabase()
```

### 集合操作

#### 查看

```mongodb
show collections
```

### 创建

```mongodb
db.createCollection("集合名称")
```

```mongodb
db.createCollection("集合名称", {capped: true, size: 10})
```

### 删除

```mongodb
db."集合名称".drop()
```

### 是否容量限制

```mongodb
db.isCapped()
```

## 插入

### `insertOne` 方法

如果不指定ID主键，会自动生成

```javascript
db.collection_name.insertOne({
    "name": "AAA",
    "age": 15,
    "gender": "M"
})

db.collection_name.insertOne({
    "name": "BBB",
    "age": 16,
    "gender": "F"
})
```

### `insertMany()` 方法

```mongodb
db.collection_name.insertMany(
    [
        { "name": "AAA", "age": 15, "gender": "M"},
        { "name": "BBB", "age": 16, "gender": "F"},
        { "name": "CCC", "age": 17, "gender": "M"},
        { "name": "DDD", "age": 18, "gender": "F"},
        { "name": "EEE", "age": 19, "gender": "M"},
    ]
)
```

## 查看

### `find` 方法

```javascript
db.collection_name.find()
```

将得到

```text
+------------------------+---+------+----+
|_id                     |age|gender|name|
+------------------------+---+------+----+
|621de6e5f25a65693b7dac72|15 |M     |AAA |
|621de6e5f25a65693b7dac73|16 |F     |BBB |
|621de6e5f25a65693b7dac74|17 |M     |CCC |
|621de6e5f25a65693b7dac75|18 |F     |DDD |
|621de6e5f25a65693b7dac76|19 |M     |EEE |
+------------------------+---+------+----+
```

### `findOne` 方法

只返回第一条数据

```text
+------------------------+---+------+----+
|_id                     |age|gender|name|
+------------------------+---+------+----+
|621de6e5f25a65693b7dac72|15 |M     |AAA |
+------------------------+---+------+----+
```

### 美化

`pretty` 方法与 `find` 方法簇联合使用，用于美化输出

```mongodb
db.collection_name.find().pretty()
```

将得到

```text
[
  { _id: ObjectId("621de22bf25a65693b7dac6b"), name: 'AAA', age: 15 },
  { _id: ObjectId("621de22bf25a65693b7dac6c"), name: 'BBB', age: 16 },
  { _id: ObjectId("621de22bf25a65693b7dac6d"), name: 'CCC', age: 17 },
  { _id: ObjectId("621de22bf25a65693b7dac6e"), name: 'DDD', age: 18 },
  { _id: ObjectId("621de22bf25a65693b7dac6f"), name: 'EEE', age: 19 }
]
```

## 比较运算符

|  符号  |    功能    |
| :----: | :--------: |
| `$lt`  |    小于    |
| `$lte` | 小于或等于 |
| `$gt`  |    大于    |
| `$gte` | 大于或等于 |
| `$ne`  |   不等于   |

查看 `age` 等于 `18` 的人员信息

```javascript
db.collection_name.find({
    "age": 18
})
```

查看 `age` 大于或等于 `18` 的人员信息

```javascript
db.collection_name.find({
    "age": {
        $gte: 18
    }
})
```

## 逻辑运算符

|  含义  |                             示例                             |              说明              |
| :----: | :----------------------------------------------------------: | :----------------------------: |
| 逻辑与 |  `db.collection_name.find({"age":{$gte:18}, "gender":"M"})`  |  年龄大于等于18岁并且性别为男  |
|        | `db.collection_name.find({$and:[{"age":{$gte:18}}, {gender:"M"}]})` |  年龄大于等于18岁并且性别为男  |
| 逻辑或 | `db.collection_name.find({$or:[{"age":18}, {"gender":"M"}]})` | 或者年龄等于18岁，或者性别为男 |

## 范围运算符

```javascript
db.collection_name.find({
    "age": {
        $in: [15, 18]
    }
})
```

将得到

```text
+------------------------+---+------+----+
|_id                     |age|gender|name|
+------------------------+---+------+----+
|621de6e5f25a65693b7dac72|15 |M     |AAA |
|621de6e5f25a65693b7dac75|18 |F     |DDD |
+------------------------+---+------+----+
```

## 正则表达式运算符

```javascript
db.collection_name.find({
    "name": {
        $regex: "^B"
    }
})
```

将得到

```text
+------------------------+---+------+----+
|_id                     |age|gender|name|
+------------------------+---+------+----+
|621de6e5f25a65693b7dac72|15 |M     |AAA |
|621de6e5f25a65693b7dac75|18 |F     |DDD |
+------------------------+---+------+----+
```

## `where` 运算

```javascript
db.collection_name.find({
	$where: "function () { return this.age >= 18 }"
})
```

如果在 `mongodb shell` 中执行，还可以省略引号，写作

```javascript
db.collection_name.find({
	$where: function() {
		return this.age >= 18
	}
})
```

省略 `function` 等关键字，可写作

```javascript
db.collection_name.find({
	$where: "return this.age >= 18"
})
```

`return` 关键字可以省略，得到

```javascript
db.collection_name.find({
	$where: "this.age >= 18"
})
```

## `skip` 方法

跳过指定数量的记录

```javascript
db.collection_name.find().skip(2)
```

将得到

```text
+------------------------+---+------+----+
|_id                     |age|gender|name|
+------------------------+---+------+----+
|621de6e5f25a65693b7dac74|17 |M     |CCC |
|621de6e5f25a65693b7dac75|18 |F     |DDD |
|621de6e5f25a65693b7dac76|19 |M     |EEE |
+------------------------+---+------+----+
```

## `limit` 方法

返回指定最大数量的记录

```javascript
db.collection_name.find().limit(3)
```

## 投影

`_id` 列默认显示

不显示 `age` 列

```javascript
db.collection_name.find({},
{
	age: 0
})
```

得到

```text
+------------------------+------+----+
|_id                     |gender|name|
+------------------------+------+----+
|621de6e5f25a65693b7dac72|M     |AAA |
|621de6e5f25a65693b7dac73|F     |BBB |
|621de6e5f25a65693b7dac74|M     |CCC |
|621de6e5f25a65693b7dac75|F     |DDD |
|621de6e5f25a65693b7dac76|M     |EEE |
+------------------------+------+----+
```

不显示 `id` 列和 `age` 列

```javascript
db.collection_name.find({}, {_id:0, age:0})
```

```text
+------+----+
|gender|name|
+------+----+
|M     |AAA |
|F     |BBB |
|M     |CCC |
|F     |DDD |
|M     |EEE |
+------+----+
```

仅显示 `name` 和 `gender` 列

```javascript
db.collection_name.find({},
{
	_id: 0,
	name: 1,
	gender: 1
})
```

得到

```
+------+----+
|gender|name|
+------+----+
|M     |AAA |
|F     |BBB |
|M     |CCC |
|F     |DDD |
|M     |EEE |
+------+----+
```

## 排序

1表示升序，-1表示降序

```javascript
db.collection_name.find().sort({
	age: 1,
	gender: -1
})
```

将得到

```
+------------------------+---+------+----+
|_id                     |age|gender|name|
+------------------------+---+------+----+
|621de6e5f25a65693b7dac72|15 |M     |AAA |
|621de6e5f25a65693b7dac73|16 |F     |BBB |
|621de6e5f25a65693b7dac74|17 |M     |CCC |
|621e04a4f25a65693b7dac79|17 |F     |GGG |
|621de6e5f25a65693b7dac75|18 |F     |DDD |
|621de6e5f25a65693b7dac76|19 |M     |EEE |
+------------------------+---+------+----+
```

```javascript
db.collection_name.find().sort({age:1, gender:1})
```

将得到

```
+------------------------+---+------+----+
|_id                     |age|gender|name|
+------------------------+---+------+----+
|621de6e5f25a65693b7dac72|15 |M     |AAA |
|621de6e5f25a65693b7dac73|16 |F     |BBB |
|621e04a4f25a65693b7dac79|17 |F     |GGG |
|621de6e5f25a65693b7dac74|17 |M     |CCC |
|621de6e5f25a65693b7dac75|18 |F     |DDD |
|621de6e5f25a65693b7dac76|19 |M     |EEE |
+------------------------+---+------+----+
```

## 计数

```javascript
db.collection_name.find().count()
```

```
+------+
|result|
+------+
|6     |
+------+
```

将得到

```javascript
db.collection_name.count({age:17})
```

```
+------+
|result|
+------+
|2     |
+------+
```

```
db.collection_name.count({age:{$gte:17}})
```

将得到

```
+------+
|result|
+------+
|4     |
+------+
```

## 更新

### `updateOne` 方法

更新符合条件的第一条记录

```javascript
db.collection_name.updateOne({
	age: 17
},
{
	$set: {
		gender: 1,
		name: "FFF"
	}
})
```

若记录不存在，在更新失败

```javascript
db.collection_name.updateOne({
	age: 20
},
{
	$set: {
		gender: 1,
		name: "FFF"
	}
})
```

指定 `upsert` 参数为 `true`，则表明若待更新的记录不存在，则插入一条新纪录

```javascript
db.collection_name.updateOne({
	age: 20
},
{
	$set: {
		gender: "F",
		name: "FFF"
	}
},
{
	upsert: true
})
```

### `updateMany` 方法

如果需要同时更新多条记录，则需要使用 `updateMany` 方法

## 删除

### `deleteOne` 方法

删除符合条件的一条记录

### `deleteMany` 方法

删除符合条件的全部记录
