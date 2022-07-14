# MySQL

[TOC]

## 数据类型

* 数值类型

    * 整型类型

        |  类型名称   | 字节数 |
        | :---------: | :----: |
        |  `TINYINT`  |   1    |
        | `SMALLINT`  |   2    |
        | `MEDIUMINT` |   3    |
        |    `INT`    |   4    |
        |  `BIGINT`   |   8    |

    * 浮点类型

        | 类型名称  | 类型说明 |    类型示例     |     示例说明     |
        | :-------: | :------: | :-------------: | :--------------: |
        |  `float`  |  单精度  |                 |                  |
        | `double`  |  双精度  |                 |                  |
        | `decimal` |  定点数  | `decimal(5, 2)` | 5位数字，2位小数 |

* 日期时间类型

    |  类型名称   | 类型说明          |
    | :---------: | ----------------- |
    |   `DATE`    | 年-月-日          |
    | `DATETIME`  | 年-月-日 时:分:秒 |
    | `TIMESTAMP` | 年-月-日 时:分:秒 |
    |   `TIME`    | 时:分:秒          |
    |   `YEAR`    | 年                |

    `DATETIME` 类型表示的日期时间范围从 `'1000-01-01 00:00:00'` 到 `'9999-12-31 23:59:59' `

    `TIMESTAMP` 类型表示的日期时间范围从 `'1970-01-01 00:00:01'` 到 `'2038-01-19 03:14:07'` 

* 字符串类型

    |   类型名称   |  类型说明  |   类型示例    |
    | :----------: | :--------: | :-----------: |
    |    `CHAR`    | 定长字符串 |   `CHAR(8)`   |
    |  `VARCHAR`   | 变长字符串 | `VARCHAR(10)` |
    |  `TINYTEXT`  |            |               |
    |    `TEXT`    |            |               |
    | `MEDIUMTEXT` |            |               |
    |  `LONGTEXT`  |            |               |

* 枚举类型

定义语法

```SQL
gender ENUM('男', '女')
```

## 约束

|   约束类型    |    说明    |
| :-----------: | :--------: |
|  `NOT NULL`   |  非空约束  |
| `PRIMARY KEY` |  主键约束  |
| `UNIQUE KEY`  | 唯一性约束 |
|   `DEFAULT`   | 默认值约束 |
| `FOREIGN KEY` |  外键约束  |

## 登录

同时指定用户名和密码

```bash
$ mysql -uroot -ppassword
```

仅指定用户

```bash
$ mysql -uroot -p
```

## 登出

```mysql
mysql> quit
```

```mysql
mysql> exit
```

还可以通过快捷键 `CTRL + D` 退出 `MySQL` 终端

## 数据库操作

### 查看数据库

```mysql
SHOW DATABASES;
```

### 创建数据库

```mysql
CREATE DATABASE `数据库名` CHARSET = `utf8`;
CREATE DATABASE IF NOT EXISTS `数据库名` CHARSET = `utf8`;
```

查看数据库的创建语句

```mysql
SHOW CREATE DATABASE `数据库名`;
```

### 使用数据库

```mysql
USE `数据库名`;
```

### 查看当前数据库

```mysql
SELECT DATABASE();
```

### 删除数据库

```mysql
DROP DATABASE `数据库名`
```

## 数据表操作

### 查看数据表

```mysql
SHOW TABLES;
SHOW TABLES LIKE 'per%';
SHOW TABLES LIKE 'perso_';
```

### 创建数据表

```mysql
CREATE TABLE `student`
(
    `id`     SERIAL,
    `name`   VARCHAR(20),
    `age`    INT,
    `high`   DECIMAL(5, 2),
    `gender` ENUM ('男', '女'),
    `cls_id` INT
);
```

表结构修改-添加字段

```mysql
ALTER TABLE `student`
    ADD COLUMN `age` INT;
```

```mysql
ALTER TABLE `student`
    ADD COLUMN `age` INT NOT NULL DEFAULT 0 AFTER `name`;
```

表结构修改-修改字段名

```mysql
ALTER TABLE `student`
    CHANGE `agee` `age` INT NOT NULL DEFAULT 0;
```

修改表结构-删除字段

```mysql
ALTER TABLE `student`
    DROP `age`;
```

### 删除数据表

```mysql
DROP TABLE `student`;
```

### 查看表结构

```mysql
DESC `表名`;
```

### 查看建表语句

```mysql
SHOW CREATE TABLE `表名`;
```

### 插入数据

插入单个记录，指定全部列的值

```mysql
INSERT INTO `表名` VALUES (值1, 值2, 值3, ...);
```

插入单个记录，指定部分列的值

```mysql
INSERT INTO `表名(列1, 列2, 列3)` VALUES (值1, 值2, 值3, ...);
```

插入多个记录，指定全部列的值

```mysql
INSERT INTO `表名` VALUES (值1, 值2, 值3, ...), (值1, 值2, 值3, ...), ...;
```

插入多个记录，指定部分列的值

```mysql
INSERT INTO `表名(列1, 列2, 列3)` VALUES (值1, 值2, 值3, ...), (值1, 值2, 值3, ...), ...;
```

将从一个数据表中查询到的结果插入到另一个数据表中

```mysql
INSERT INTO `student`(`name`, `gender`)
VALUES ('张三', 'F'), ('李四', 'F'), ('张三', 'M');

CREATE TABLE `student_female`
(
    `id`     SERIAL,
    `name`   VARCHAR(20)    NOT NULL,
    `gender` ENUM ('M','F') NOT NULL
);

INSERT INTO `student_female`(`name`, `gender`)
    SELECT `name`, `gender`
    FROM `student`
    WHERE `gender` = 'F';
```

### 修改数据

```mysql
UPDATE `表名`
SET 字段1 = 值1
WHERE `字段` = 值;
```

```mysql
UPDATE `表名`
SET 字段1 = 值1,
    字段2 = 值2
WHERE `字段` = 值;
```

对于枚举类型的字段，既可以通过指定枚举值，也可以通过指定其对应的数值来插入或更新数据

```mysql
UPDATE `student`
SET `gender` = '男'
WHERE `id` = 1;
```

```mysql
UPDATE `student`
SET `gender` = 1
WHERE `id` = 1;
```

其中，gender 列的定义为

```mysql
`gender` enum('男','女') DEFAULT NULL,
```

### 删除数据

删除全部数据

```mysql
DELETE FROM 表名;
```

删除指定数据

```mysql
DELETE FROM 表名 WHERE <条件语句>;
```

## 查询

### 查询数据

```mysql
SELECT * FROM 表名;

SELECT 列名1, 列名2, ... FROM 表名;

SELECT 列名1, 列名2, ... FROM 表名 where 列名3 = 值3;
```

### 指定数据库

```mysql
SELECT *
FROM `数据库名`.`数据表名`;
```

### 指定数据表

```mysql
SELECT `数据表名`.`字段名`
FROM `数据库名`.`数据表名`;
```

### 字段别名

```mysql
SELECT `字段名` AS `别名`
FROM `数据表名`;
```

### 表别名

```mysql
SELECT `字段名`
FROM `数据表名` AS `别名`;
```

### 去重

```mysql
SELECT DISTINCT `字段名`
FROM `数据表名`;
```

### WHERE 子句

##### 比较运算符

```mysql
SELECT *
FROM `students`
WHERE `age` > 18;
```

```mysql
SELECT *
FROM `students`
WHERE `age` >= 18;
```

```mysql
SELECT *
FROM `students`
WHERE `age` < 18;
```

```mysql
SELECT *mysql
FROM `students`
WHERE `age` <= 18;
```

```mysql
SELECT *
FROM `students`
WHERE `age` != 18;
```

```mysql
SELECT *
FROM `students`
WHERE `age` <> 18;
```

##### 逻辑运算符

###### AND

```mysql
SELECT *
FROM `students`
WHERE `age` >= 18
  AND `age` <= 28;
```

```mysql
SELECT *
FROM `students`
WHERE `age` >= 18
  AND `gender` = '女';
```

###### OR

```mysql
SELECT *
FROM `students`
WHERE `age` >= 18
   OR `height` >= 175;
```

###### NOT

```mysql
SELECT *
FROM `students`
WHERE NOT (`age` >= 18
    AND `gender` = '女');
```

模糊查询

```mysql
SELECT *
FROM `students`
WHERE `name` LIKE '小%';

SELECT *
FROM `students`
WHERE `name` LIKE '%小%';

SELECT *
FROM `students`
WHERE `name` LIKE '小_';

SELECT *
FROM `students`
WHERE `name` LIKE '__';

SELECT *
FROM `students`
WHERE `name` LIKE '__%';
```

| 符号 | 含义           |
| :--: | -------------- |
| `%`  | 任意个任意字符 |
| `_`  | 单个任意字符   |

##### 范围查询

###### BETWEEN ... AND ...

```mysql
SELECT *
FROM `students`
WHERE `age` BETWEEN 18 AND 28;
```

###### IN

```mysql
SELECT *
FROM `students`
WHERE `age` IN (10, 20, 30);
```

判空

```mysql
SELECT *
FROM `students`
WHERE `height` IS NULL;
```

```mysql
SELECT *
FROM `students`
WHERE `height` IS NOT NULL;
```

#### ORDER BY 排序

```mysql
SELECT *
FROM `students`
ORDER BY `age`;
```

```mysql
SELECT *
FROM `students`
ORDER BY `age` ASC;
```

```mysql
SELECT *
FROM `students`
ORDER BY `age` DESC;
```

```mysql
SELECT *
FROM `students`
ORDER BY `age` ASC, `height` ASC;
```

#### 聚合函数

```mysql
SELECT COUNT(*)
FROM `students`
WHERE `gender` = '男';
```

```mysql
SELECT MIN(`age`)
FROM `students`;
```

```mysql
SELECT MAX(`height`)
FROM `students`;
```

```mysql
SELECT SUM(`age`)
FROM `students`;
```

```mysql
SELECT SUM(`height`)
FROM `students`;
```

```mysql
SELECT AVG(`height`)
FROM `students`;
```

计算过程中自动忽略值为 NULL 的记录

不允许出现嵌套，例如 `SUM(MAX(xxx))`

#### GROUP BY 分组

```mysql
SELECT `gender`
FROM `students`
GROUP BY `gender`;
```

```mysql
SELECT `gender`, count(*)
FROM `students`
GROUP BY `gender`;
```

```mysql
SELECT GROUP_CONCAT(`name`), `gender`
FROM `students`
GROUP BY `gender`;
```

输出

| name                                            | gender |
| :---------------------------------------------- | :----- |
| 彭于晏,刘德华,周杰伦,程坤,郭靖,司马二狗         | 男     |
| 小明,小月月,黄蓉,王祖贤,刘亦菲,静香,周杰,凌小小 | 女     |
| 金星                                            | 中性   |
| 凤姐                                            | 保密   |

```
SELECT `gender`, AVG(`age`)
FROM `students`
GROUP BY `gender`;
```

输出

| gender | AVG(age) |
| :----- | :-------- |
| 女     | 23.8750   |
| 男     | 31.8333   |
| 保密   | 28.0000   |
| 中性   | 33.0000   |

#### HAVING

```mysql
SELECT `gender`, AVG(`age`)
FROM `students`
GROUP BY `gender`
HAVING AVG(`age`) >= 30;
```

#### WITH ROLLUP

```mysql
SELECT `gender`, COUNT(*)
FROM `students`
GROUP BY `gender`
WITH ROLLUP;
```

#### LIMIT

从第 1 条记录开始，查询 2 条数据，即：第 1、2 条数据

```mysql
SELECT *
FROM `students` ORDER BY `id` ASC
LIMIT 0 ,2
```

输出

| id   | name   | age  | height | gender | cls\_id | is\_delete |
| :--- | :----- | :--- | :----- | :----- | :------ | :--------- |
| 1    | 小明   | 18   | 180.00 | 女     | 1       | false      |
| 2    | 小月月 | 18   | 180.00 | 女     | 2       | true       |

从第 3 条数据开始，查询 2 条数据，即：第 3、4 条数据

```mysql
SELECT *
FROM `students` ORDER BY `id` ASC
LIMIT 2 ,2;
```

输出

| id   | name   | age  | height | gender | cls\_id | is\_delete |
| :--- | :----- | :--- | :----- | :----- | :------ | :--------- |
| 3    | 彭于晏 | 29   | 185.00 | 男     | 1       | false      |
| 4    | 刘德华 | 59   | 175.00 | 男     | 2       | true       |

从第 1 条数据开始，查询前 4 条数据，即：第 1、2、3、4 条数据

```mysql
SELECT *
FROM `students`
ORDER BY `id` ASC
LIMIT 4;
```

输出

| id   | name   | age  | height | gender | cls\_id | is\_delete |
| :--- | :----- | :--- | :----- | :----- | :------ | :--------- |
| 1    | 小明   | 18   | 180.00 | 女     | 1       | false      |
| 2    | 小月月 | 18   | 180.00 | 女     | 2       | true       |
| 3    | 彭于晏 | 29   | 185.00 | 男     | 1       | false      |
| 4    | 刘德华 | 59   | 175.00 | 男     | 2       | true       |

### 函数

#### ROUND

```mysql
SELECT ROUND(3.1415926, 2);
```

### 连接

#### 内连接

```mysql
SELECT `s`.`id`, `s`.`name`, `c`.`id`, `c`.`name`
FROM `students` `s`
         INNER JOIN `classes` `c` ON `s`.`id` = `c`.`id`;
```

输出

| id   | name   | id   | name         |
| :--- | :----- | :--- | :----------- |
| 1    | 小明   | 1    | python\_01期 |
| 2    | 小月月 | 2    | python\_02期 |
| 8    | 周杰伦 | 8    | Python\_03期 |

#### 外连接

##### 左连接

```mysql
SELECT `s`.`id`, `s`.`name`, `c`.`id`, `c`.`name`
FROM `students` s
         LEFT OUTER JOIN `classes` `c` ON `s`.`cls_id` = `c`.`id`;
```

输出

| id   | name     | id   | name         |
| :--- | :------- | :--- | :----------- |
| 1    | 小明     | 1    | python\_01期 |
| 2    | 小月月   | 2    | python\_02期 |
| 3    | 彭于晏   | 1    | python\_01期 |
| 4    | 刘德华   | 2    | python\_02期 |
| 5    | 黄蓉     | 1    | python\_01期 |
| 6    | 凤姐     | 2    | python\_02期 |
| 7    | 王祖贤   | 1    | python\_01期 |
| 8    | 周杰伦   | 1    | python\_01期 |
| 9    | 程坤     | 2    | python\_02期 |
| 10   | 刘亦菲   | 2    | python\_02期 |
| 11   | 金星     | NULL | NULL         |
| 12   | 静香     | NULL | NULL         |
| 13   | 郭靖     | NULL | NULL         |
| 14   | 周杰     | NULL | NULL         |
| 15   | 凌小小   | 1    | python\_01期 |
| 16   | 司马二狗 | 1    | python\_01期 |

```mysql
SELECT `s`.`id`, `s`.`name`, `c`.`id`, `c`.`name`
FROM `students` `s`
         LEFT OUTER JOIN `classes` `c` ON `s`.`cls_id` = `c`.`id`
WHERE `c`.`id` IS NULL;
```

输出

| id   | name | id   | name |
| :--- | :--- | :--- | :--- |
| 11   | 金星 | NULL | NULL |
| 12   | 静香 | NULL | NULL |
| 13   | 郭靖 | NULL | NULL |
| 14   | 周杰 | NULL | NULL |

##### 右连接

```mysql
SELECT `s`.`id`, `s`.`name`, `c`.`id`, `c`.`name`
FROM `students` `s`
         RIGHT OUTER JOIN `classes` `c` ON `s`.`cls_id` = `c`.`id`;
```

输出

| id   | name     | id   | name         |
| :--- | :------- | :--- | :----------- |
| 1    | 小明     | 1    | python\_01期 |
| 3    | 彭于晏   | 1    | python\_01期 |
| 5    | 黄蓉     | 1    | python\_01期 |
| 7    | 王祖贤   | 1    | python\_01期 |
| 8    | 周杰伦   | 1    | python\_01期 |
| 15   | 凌小小   | 1    | python\_01期 |
| 16   | 司马二狗 | 1    | python\_01期 |
| 2    | 小月月   | 2    | python\_02期 |
| 4    | 刘德华   | 2    | python\_02期 |
| 6    | 凤姐     | 2    | python\_02期 |
| 9    | 程坤     | 2    | python\_02期 |
| 10   | 刘亦菲   | 2    | python\_02期 |
| NULL | NULL     | 8    | Python\_03期 |

#### 自连接

```mysql
SELECT `province`.`title` AS `province`, `cities`.`title` AS `city`
FROM `areas` AS `cities`
         INNER JOIN `areas` AS `province` ON `cities`.`pid` = `province`.`id`
WHERE `province`.`title` = '广东省';
```

输出

| province | city   |
| :------- | :----- |
| 广东省   | 广州市 |
| 广东省   | 韶关市 |
| 广东省   | 深圳市 |
| 广东省   | 珠海市 |
| 广东省   | 汕头市 |
| 广东省   | 佛山市 |
| 广东省   | 江门市 |
| 广东省   | 湛江市 |
| 广东省   | 茂名市 |
| 广东省   | 肇庆市 |
| 广东省   | 惠州市 |
| 广东省   | 梅州市 |
| 广东省   | 汕尾市 |
| 广东省   | 河源市 |
| 广东省   | 阳江市 |
| 广东省   | 清远市 |
| 广东省   | 东莞市 |
| 广东省   | 中山市 |
| 广东省   | 潮州市 |
| 广东省   | 揭阳市 |
| 广东省   | 云浮市 |

### 子查询

#### 标量子查询

子查询结果为一行一列

```mysql
SELECT *
FROM `students`
WHERE `age` > (SELECT AVG(`age`) FROM `students`);
```

输出

| id   | name     | age  |
| :--- | :------- | :--- |
| 3    | 彭于晏   | 29   |
| 4    | 刘德华   | 59   |
| 5    | 黄蓉     | 38   |
| 6    | 凤姐     | 28   |
| 8    | 周杰伦   | 36   |
| 11   | 金星     | 33   |
| 14   | 周杰     | 34   |
| 15   | 凌小小   | 28   |
| 16   | 司马二狗 | 28   |

#### 列子查询

子查询结果为一列多行

```mysql
SELECT *
FROM `students`
WHERE `cls_id` IN (SELECT `id` FROM `classes`);
```

输出

| id   | name     | age  | height | gender | cls\_id | is\_delete |
| :--- | :------- | :--- | :----- | :----- | :------ | :--------- |
| 1    | 小明     | 18   | 180.00 | 女     | 1       | false      |
| 2    | 小月月   | 18   | 180.00 | 女     | 2       | true       |
| 3    | 彭于晏   | 29   | 185.00 | 男     | 1       | false      |
| 4    | 刘德华   | 59   | 175.00 | 男     | 2       | true       |
| 5    | 黄蓉     | 38   | 160.00 | 女     | 1       | false      |
| 6    | 凤姐     | 28   | 150.00 | 保密   | 2       | true       |
| 7    | 王祖贤   | 18   | 172.00 | 女     | 1       | true       |
| 8    | 周杰伦   | 36   | NULL   | 男     | 1       | false      |
| 9    | 程坤     | 27   | 181.00 | 男     | 2       | false      |
| 10   | 刘亦菲   | 25   | 166.00 | 女     | 2       | false      |
| 15   | 凌小小   | 28   | 180.00 | 女     | 1       | false      |
| 16   | 司马二狗 | 28   | 120.00 | 男     | 1       | false      |

将子查询的结果插入到另一张表

```mysql
INSERT INTO `goods_cates`(`name`)
SELECT DISTINCT `cate_name`
FROM `goods`;
```

利用子查询更新数据

```mysql
UPDATE (`goods` `g`
    INNER JOIN `goods_cates` `gc` ON `g`.`cate_name` = `gc`.`name`)
SET `g`.`cate_name` = `gc`.`id`;
```

#### 行子查询

子查询结果为一行多列

### 外键

添加外键

```mysql
ALTER TABLE `goods`
    ADD FOREIGN KEY (`cate_id`) REFERENCES `goods_cates` (`id`);
    
ALTER TABLE `goods`
    ADD CONSTRAINT FOREIGN KEY (`cate_id`) REFERENCES `goods_cates` (`id`);
```

删除外键约束

```mysql
ALTER TABLE `表名`
    DROP FOREIGN KEY `约束名`;

ALTER TABLE `表名`
    DROP CONSTRAINT `约束名`;
```

在创建数据表的同时指定外键约束

```mysql
CREATE TABLE `student`
(
    `id`   SERIAL,
    `name` VARCHAR(20) NOT NULL
);


CREATE TABLE `class`
(
    `id`   SERIAL,
    `name` VARCHAR(20) NOT NULL
);

CREATE TABLE `student_class`
(
    `student_id` BIGINT UNSIGNED NOT NULL,
    `class_id`   BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (`student_id`, `class_id`),
    CONSTRAINT `外键约束名1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `外键约束名2` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);
```

### 视图

#### 创建

```mysql
CREATE VIEW `视图名` AS
SELECT `g`.`name` AS `goods_name`, `gc`.`name` AS `category_name`, `gb`.`name` AS `brand_name`
FROM `goods` `g`
         INNER JOIN `goods_cates` `gc` ON `g`.`cate_id` = `gc`.`id`
         INNER JOIN `goods_brands` `gb` ON `g`.`brand_id` = `gb`.`id`;
```

#### 删除

```mysql
DROP VIEW `视图名`;
```

## 事务

`MySQL` 数据库的 `MyISAM` 引擎不支持实务，`InnoDB` 引擎支持事务。

### 四大特性 / `ACID`

* 原子性 / `Atomicity`
* 一致性 / `Consistency`
* 隔离性 / `Isolation`
* 持久性 / `Durability`

### 使用步骤

* 开启事务

    ```mysql
    BEGIN;
    ```

    ```mysql
    START TRANSACTION ;
    ```

* 提交事务

    ```mysql
    COMMIT;
    ```

* 回滚事务

    ```mysql
    ROLLBACK;
    ```

- 禁用事务自动提交

```mysql
SET AUTOCOMMIT = 0;
```

## 索引

### 显示

```mysql
SHOW INDEX FROM `表名`;
```

### 创建索引

```mysql
ALTER TABLE `表名`
    ADD INDEX `索引名` (`字段名1`, `字段名2`, ...);
```

其中，索引名可以省略

### 删除索引

```mysql
DROP INDEX `索引名` ON `表名；`
```

**创建数据表的同时插入数据**

```mysql
CREATE TABLE `goods`
(
    `id`    SERIAL,
    `name`  VARCHAR(20) NOT NULL,
    `brand` VARCHAR(20) NOT NULL
);

INSERT INTO `goods`(`name`, `brand`)
VALUES ('笔记本电脑1', '联想'), ('笔记本电脑2', '华为'), ('笔记本电脑3', '华硕');

CREATE TABLE `brand`
(
    `id`   SERIAL,
    `name` VARCHAR(20) NOT NULL
)
SELECT DISTINCT `brand` AS `name`
FROM `goods`;
```

## 索引

### 创建/添加索引

```mysql
ALTER TABLE `表名`
    ADD INDEX `索引名` (`字段名`);
```

联合索引

```mysql
ALTER TABLE `表名`
    ADD INDEX `索引名` (`列名1`, `列名2`);
```

### 查看索引

```mysql
SHOW INDEX FROM `表名`;
```

### 删除索引

```mysql
ALTER TABLE `表名`
    DROP INDEX `索引名`;
```

对于复合索引，在查询时是否会使用到改类索引，遵循一个最左原则。


```mysql
ALTER TABLE `student`
    ADD INDEX (`name`, `gender`);
```

这两个查询会使用的上述索引

```mysql
SELECT *
FROM `student`
WHERE `name` = '李四';

SELECT *
FROM `student`
WHERE `name` = '李四'
  AND `gender` = 'F';
```

这个查询不会使用到上述索引

```mysql
SELECT *
FROM `student`
WHERE `gender` = 'F';
```

## 引擎

```mysql
SHOW ENGINES;
```

```mysql
ALTER TABLE `表名`
    ENGINE = 'MyISAM'
```
