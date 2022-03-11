# SQL

[TOC]

> 基于 `MySQL 8`

## 数据库

### 创建数据库

```SQL
CREATE DATABASE `数据库名`;
```

```SQL
CREATE DATABASE `数据库名`
    DEFAULT CHARACTER SET = 'utf8mb4'
    DEFAULT COLLATE = 'utf8mb4_0900_ai_ci';
```

### 修改数据库

```SQL
ALTER DATABASE `数据库名`
    DEFAULT CHARACTER SET 'gb2312'
    DEFAULT COLLATE 'gb2312_chinese_ci';
```

### 删除数据库

```SQL
DROP DATABASE IF EXISTS `数据库名`;
```

### 查看数据库

```SQL
SHOW DATABASES;

SHOW DATABASES like '%pattern%';
```

## 数据表

### 创建表

```SQL
CREATE TABLE `customers`
(
    `id`      INT          NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(10)  NOT NULL,
    `address` VARCHAR(100) NULL,
    PRIMARY KEY (`id`)
);
```

### 删除表

```SQL
DROP TABLE `数据表名`;
```

### 修改表

#### 增加列

```SQL
ALTER TABLE `customers`
    ADD COLUMN `city` VARCHAR(10) NULL AFTER `name`;
```

#### 修改列

```sql
ALTER TABLE `customers`
    CHANGE COLUMN `name` `name` VARCHAR(5) NULL;
    
ALTER TABLE `customers`
    ALTER COLUMN `name` SET DEFAULT 'None';
    
ALTER TABLE `customers`
    MODIFY COLUMN `name` VARCHAR(10) NULL AFTER `city`;
```

#### 删除列

```SQL
ALTER TABLE `数据表名`
    DROP COLUMN `列名`;
```

#### 重命名表

```SQL
ALTER TABLE `customers` RENAME TO `customer`;

# 或

RENAME TABLE `customer` TO `customers`;
```

### 查询

```sql
SELECT * FROM `表名`
```

```sql
SELECT `列名1`, `列名2`, ... FROM `表名`
```

### 更新

```sql
UPDATE `表名`
SET `列名1` = 值1, `列名2` = 值2, 
WHERE `列名n` = 值n;
```

### 插入

```sql
INSERT INTO `表名`
VALUE (值1, 值2, ...);
```

```sql
INSERT INTO `表名`(`列名1`, `列名2`, ...)
VALUE (值1, 值2, ...);
```

```sql
INSERT INTO `表名`
VALUES (值1.1, 值1.2, ...), (值2.1, 值2.2, ...);
```

## 主键

### 删除主键

```SQL
ALTER TABLE `Product`
    DROP PRIMARY KEY;
```

### 添加主键

```SQL
ALTER TABLE `Product`
    ADD PRIMARY KEY (`product_id`);
```

## 连接

### 交叉连接

```SQL
SELECT *
    FROM `数据表1`
             CROSS JOIN `数据表2`;

# 或

# 省略 CROSS JOIN 改用逗号
SELECT *
    FROM `数据表1`, `数据表2`;
```

### 内连接

```SQL
SELECT *
    FROM `数据表1`
             INNER JOIN `数据表2` ON 连接条件;
             
# 省略 INNER 关键字
SELECT *
    FROM `数据表1`
             JOIN `数据表2` ON 连接条件;
```

### 外连接

#### 左外连接

```SQL
SELECT *
    FROM `数据表1`
             LEFT OUTER JOIN `数据表2` ON 连接条件;

SELECT *
    FROM `数据表1`
             LEFT JOIN `数据表2` ON 连接条件;
```

#### 右外连接

```SQL
SELECT *
    FROM `数据表1`
             RIGHT OUTER JOIN `数据表2` ON 连接条件;

SELECT *
    FROM `数据表1`
             RIGHT JOIN `数据表2` ON 连接条件;
```

## WHERE 子句

### 比较运算符

| 运算符 |   含义   |     例      |
| :----: | :------: | :---------: |
|  `>`   |   大于   | `age > 18`  |
|  `>=`  | 大于等于 | `age >= 18` |
|  `<`   |   小于   | `age < 18`  |
|  `<=`  | 小于等于 | `age <= 18` |
|  `!=`  |  不等于  | `age != 18` |
|  `<>`  |  不等于  | `age <> 18` |

例

```SQL
SELECT * FROM `Product` WHERE `sale_price` > 100;
```
```SQL
SELECT * FROM `Product` WHERE `sale_price` >= 100;
```
```SQL
SELECT * FROM `Product` WHERE `sale_price` < 100;
```
```SQL
SELECT * FROM `Product` WHERE `sale_price` <= 100;
```
```SQL
SELECT * FROM `Product` WHERE `sale_price` != 100;
```
```SQL
SELECT * FROM `Product` WHERE `sale_price` <> 100;
```

### BETWEEN ... AND ...

```SQL
SELECT *
    FROM `Product`
    WHERE `sale_price` BETWEEN 1000 AND 5000;
```

```SQL
SELECT *
    FROM `Product`
    WHERE `sale_price` NOT BETWEEN 1000 AND 5000;
```

### IN

```SQL
SELECT *
    FROM `Product`
    WHERE `product_type` IN ('办公用品', '交通工具');
```

### IS NULL

```SQL
SELECT *
    FROM `Product`
    WHERE `purchase_price` IS NULL;
```

```SQL
SELECT *
    FROM `Product`
    WHERE `purchase_price` IS NOT NULL;
```

### 逻辑运算符

| 符号  | 含义 | 例                               | 优先级 |
| :---: | :--: | :------------------------------- | ------ |
| `NOT` |  非  | `NOT age >= 18`                  | 高     |
| `AND` |  且  | `age >= 18 AND gender = "女"`    |        |
| `OR`  |  或  | `price <= 0.01 OR price >= 1000` | 低     |

#### 结合使用

* `NOT` 与 `BETWEEN ... AND ...` 连用，则为 `NOT BETWEEN ... AND ...`
* `NOT` 与 `IN` 连用，则为 `NOT IN`
* `NOT` 与 `IS NULL` 结合使用，则为 `IS NOT NULL`

## LIKE 子句

### 掩码

| 符号 |      含义      | 例           |
| :--: | :------------: | ------------ |
| `%`  | 任意个任意字符 | `%@qq.com`   |
| `_`  |  单个任意字符  | `02037012__` |

## LIMIT 子句

```SQL
SELECT *
    FROM `Product`
    ORDER BY `purchase_price` DESC
    LIMIT 0,3;
    
# 或

SELECT *
    FROM `Product`
    ORDER BY `purchase_price` DESC
    LIMIT 3 OFFSET 0;

# 或

SELECT *
    FROM `Product`
    ORDER BY `purchase_price` DESC
    LIMIT 3;
```

## CASE

### 例1

```SQL
SELECT `product_name`,
       CASE
           WHEN `product_type` = '衣服' THEN concat('A:', `product_type`)
           WHEN `product_type` = '办公用品' THEN concat('B:', `product_type`)
           WHEN `product_type` = '厨房用具' THEN concat('C:', `product_type`)
           ELSE NULL
           END AS `abc_product_type`
    FROM `Product`;

# 使用简单 CASE 表达式

SELECT `product_name`,
       CASE `product_type`
           WHEN '衣服' THEN concat('A:', `product_type`)
           WHEN '办公用品' THEN concat('B:', `product_type`)
           WHEN '厨房用具' THEN concat('C:', `product_type`)
           ELSE NULL
           END AS `abc_product_type`
    FROM `Product`;
```

输出

product_name | abc_product_type
--- | ---
T恤 | A:衣服
打孔器 | B:办公用品
运动T恤 | A:衣服
菜刀 | C:厨房用具
高压锅 | C:厨房用具
叉子 | C:厨房用具
擦菜板 | C:厨房用具
圆珠笔 | B:办公用品 

### 例2

```SQL
SELECT sum(CASE WHEN `product_type` = '衣服' THEN `sale_price` ELSE 0 END)   AS `sum_price_cloth`,
       sum(CASE WHEN `product_type` = '办公用品' THEN `sale_price` ELSE 0 END) AS `sum_price_kitchen`,
       sum(CASE WHEN `product_type` = '厨房用具' THEN `sale_price` ELSE 0 END) AS `sum_price_office`
    FROM `Product`;

# 使用 IF

SELECT sum(IF(`product_type` = '衣服', `sale_price`, 0))   AS `sum_price_cloth`,
       sum(IF(`product_type` = '办公用品', `sale_price`, 0)) AS `sum_price_kitchen`,
       sum(IF(`product_type` = '厨房用具', `sale_price`, 0)) AS `sum_price_office`
    FROM `Product`;
```

输出

sum_price_cloth | sum_price_kitchen | sum_price_office
--- | --- | ---
5002 | 602 | 11184

## 存储过程

[存储过程](./MySQL 存储过程.md)

## 函数

### 例1

```SQL
# 定义
CREATE FUNCTION funcName()
    RETURNS VARCHAR(100)
BEGIN
    RETURN (SELECT `product_name`
                FROM `Product`
                WHERE `product_id` = '0001');
END;

# 调用
SELECT funcName();
```

## 变量

```SQL
CREATE PROCEDURE `procName`()
BEGIN
    DECLARE `cus_product_id` VARCHAR(4) DEFAULT '0001';
    SET `cus_product_id` = '0002';

    SELECT `product_id`, `product_name` FROM `Product` WHERE `product_id` = `cus_product_id`;
END;

CALL `procName`();
```

```SQL
CREATE PROCEDURE `procName`()
BEGIN
    DECLARE `cus_product_id` VARCHAR(4);
    DECLARE `cus_prodcut_name` VARCHAR(100);

    SELECT `product_id`, `product_name`
        INTO `cus_product_id`, `cus_prodcut_name`
        FROM `Product`
        WHERE `product_id` = '0005';

    SELECT `cus_product_id`, `cus_prodcut_name`;
END;

CALL `procName`();
```

## 触发器

### 创建

#### 单语句触发器

```SQL
CREATE TABLE `account`
(
    `account_num` INT,
    `amount`   INT
);

CREATE TRIGGER `ins_sum`
    BEFORE INSERT
    ON `account`
    FOR EACH ROW SET @`sum` = @`sum` + `NEW`.`amount`;

SET @`sum` = 0;

# 0
SELECT @`sum`;

INSERT INTO `account`
    VALUES (1, 100);

# 100
SELECT @`sum`;

INSERT INTO `account`
    VALUES (2, 500);

# 600
SELECT @`sum`;
```

#### 多语句触发器

### 查看触发器

```SQL
SHOW TRIGGERS;
```

```SQL
SELECT *
    FROM `information_schema`.`TRIGGERS`;
```

```SQL
SELECT *
    FROM `information_schema`.`TRIGGERS`
    WHERE `TRIGGER_NAME` = 'calcSum';
```

### 删除触发器

```SQL
DROP TRIGGER `calcSum`;
```

## 用户

### 创建用户

```SQL
CREATE USER `用户名` IDENTIFIED BY '密码';
```

#### 删除用户

```SQL
DROP USER `jack`;
```

## 权限

### 授予权限

```
GRANT SELECT, UPDATE, ... ON `数据库名`.`数据表名` TO `用户名`;
```

## 视图

### 创建视图

```SQL
CREATE VIEW 视图名称 AS SELECT语句;
```

例

```SQL
CREATE VIEW `view_product` AS
    SELECT `product_id`, `product_name`
        FROM `Product`;
```

### 查询

对视图的查询与对表的查询语法一致，可以通过 `SELECT` 语句进行查询。例

```SQL
SELECT *
    FROM `view_product`;

SELECT *
    FROM `view_product`
    WHERE `product_id` = '0001';
```

### 更新

```SQL
UPDATE `view_product`
SET `product_name` = '新T恤'
    WHERE `product_id` = '0001';
```

对视图的更新会影响到该与视图有关的基表

### 修改

```SQL
ALTER VIEW `view_product` AS
    SELECT `product_id`, `product_name`, `product_type`
        FROM `Product`;
```

### 查看

```SQL
SHOW CREATE VIEW `view_product`;
```

### 删除

```SQL
DROP VIEW 视图名;
```

## 分隔符

`MySQL`中，默认的语句分隔符为 `;`，可以通过 `DELIMITER` 命令修改，如

```SQL
DELIMITER $
```

执行完上述语句后，分隔符将改为`$`符，之后输入的语句都应以`$`结尾。

## 插入

不指明要插入数据的字段

```SQL
INSERT INTO 表名
VALUES (值1, 值2, ...);
```

指明要插入数据的字段

```SQL
INSERT INTO 表名(字段1, 字段2, ..., 字段n)
VALUES (值1, 值2, ..., 值n);
```

## 临时表

```sql
CREATE TEMPORARY TABLE 表名 AS (
  SELECT 语句
);
```

例

```sql
CREATE TEMPORARY TABLE `temp_userinfo_famle` AS (
    SELECT `userid`, `username`
    FROM `userinfo`
    WHERE `sex` = 1
);
```

## 注释

### 单行注释

使用符号 `#`

```sql
SELECT `userid`,
       # `username`,
       `sex`,
       `usermoney`
FROM `userinfo`
WHERE `sex` = 1;
```

使用符号 `--`

```sql
SELECT `userid`,
       -- `username`,
       `sex`,
       `usermoney`
FROM `userinfo`
WHERE `sex` = 1;
```

例

```sql
# 查询指定性别的用户的信息
SELECT `userid`,
       `username`,
FROM `userinfo`
WHERE `sex` = 1;
```

### 多行注释

使用 `/*` 和 `*/`

```sql
SELECT `userid`,
       /* `username`,
       `sex`,*/
       `usermoney`
FROM `userinfo`
WHERE `sex` = 1;
```

## 算数运算

| 符号 | 含义 |
| :--: | :--: |
| `+`  |  加  |
| `-`  |  减  |
| `*`  |  乘  |
| `/`  |  除  |

## 聚合函数

|  函数名   |  含义  |
| :-------: | :----: |
| `count()` |  计数  |
|  `min()`  | 最小值 |
|  `max()`  | 最大值 |
|  `avg()`  |  均值  |
|  `sum()`  |   和   |

## GROUP BY 子句

## HAVING 子句

## 字符串

### 函数

#### SUBSTR 函数

```SQL
SUBSTR(字符串值/列, 起始位置, 长度)
```

例

```sql
SELECT SUBSTR("Hello World", 7, 2);

-- Wo
```

```sql
SELECT SUBSTR(name, 2, 5) 
FROM user;
```

### UPPER 函数

```sql
SELECT UPPER("heLLo world");

-- HELLO WORLD
```

### LOWER 函数

```sql
SELECT LOWER("heLLo world");

-- hello world
```

### UCASE 函数

```sql
SELECT UCASE("heLLo world");

-- HELLO WORLD
```

## 日期

### 函数

#### STRFTIME 函数

```sqlite
SELECT DATE("2020-12-31"),
       STRFTIME("%Y", DATE("2020-12-31")) AS Year,
       STRFTIME("%m", DATE("2020-12-31")) AS Month,
       STRFTIME("%d", DATE("2020-12-31")) AS Day;
```
