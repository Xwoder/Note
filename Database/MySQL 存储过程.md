# MySQL 存储过程

[TOC]

> 基于 `MySQL 8`

## 创建

创建存储过程使用 `CREATE PROCEDURE` 语句

```SQL
# 创建
CREATE PROCEDURE `procName`()
BEGIN
    SELECT * FROM `Product`;
END;
```

```SQL
# 创建
CREATE PROCEDURE `procName`(IN `id` CHAR(5))
BEGIN
    SELECT * FROM `Product` WHERE `product_id` = `id`;
END;
```

## 调用

```SQL
# 调用
CALL `procName`;
```

```SQL

# 调用
CALL procName(`0001`);
```

## 查看

### 查看列表

```SQL
SHOW PROCEDURE STATUS;
SHOW PROCEDURE STATUS WHERE `Db` = '数据库名';
```

### 查看定义

```SQL
SHOW CREATE PROCEDURE `数据库名`.`存储过程名`;
```

### 删除

删除存储过程使用 `DROP PROCEDURE ` 语句

```SQL
DROP PROCEDURE `Test`.`procName`;
```

## 语法

### 变量

#### 定义

```SQL
CREATE PROCEDURE `pro_test`()
BEGIN
    # 定义一个变量；类型为整型，默认值为 10
    DECLARE `num` INT DEFAULT 10;
    SELECT concat('num = ', `num`);
END;
```

调用该存储过程将得到

```text
+-------------------------+
| concat('num = ', `num`) |
+-------------------------+
| num = 10                |
+-------------------------+
```

## 赋值

### 使用 `SET` 语句

```SQL
CREATE PROCEDURE `pro_test`()
BEGIN
    # 定义一个变量；类型为整型，默认值为 0
    DECLARE `num` INT DEFAULT 0;
    # 将变量的值加上 50
    SET `num` = `num` + 50;
    SELECT concat('num = ', `num`);
END;
```

### 使用 `SELECT...INTO...` 语句

```SQL
CREATE PROCEDURE `pro_test`()
BEGIN
    # 定义一个变量；类型为整型，默认值为 0
    DECLARE `num` INT DEFAULT 0;
    # 查询商品数量并将其赋值给 num
    SELECT count(*) INTO `num` FROM `Product`;
    SELECT concat('num = ', `num`);
END;
```

## 参数

### 输入参数

```SQL
# 接收一个名为 `height` 输入参数，其类型为 `INT`
CREATE PROCEDURE `pro_test`(IN `height` INT)
BEGIN
    DECLARE `desc` CHAR(4);
    IF `height` >= 180 THEN
        SET `desc` = 'A';
    ELSEIF `height` >= 170 AND `height` < 180 THEN
        SET `desc` = 'B';
    ELSE
        SET `desc` = 'C';
    END IF;
    SELECT concat('height = ', `height`, ', desc = ', `desc`);
END;
```

调用该存储过程的时候，需要传入一个参数

```SQL
CALL `pro_test`(178);
```

得到如下输出

```text
+----------------------------------------------------+
| concat('height = ', `height`, ', desc = ', `desc`) |
+----------------------------------------------------+
| height = 178, desc = B                             |
+----------------------------------------------------+
```

### 输出参数

以下代码定义了一个存储过程，其第二个参数为输出参数，名为 `desc`，类型为 `char`

```SQL
CREATE PROCEDURE `pro_test`(IN `height` INT, OUT `desc` CHAR(4))
BEGIN
    IF `height` >= 180 THEN
        SET `desc` = 'A';
    ELSEIF `height` >= 170 AND `height` < 180 THEN
        SET `desc` = 'B';
    ELSE
        SET `desc` = 'C';
    END IF;
END;
```

调用该存储过程

```SQL
CALL `pro_test`(180, @`desc`);
```

说明：在变量名前加上`@`符号，表明该变量是一个**会话变量**。

由于其内部没有定义任何打印有关的语句，所以不会得到任何输出。此时，需要手动查看输出变量的值

```SQL
SELECT @`desc`;
```

将得到输出

```SQL
+---------+
| @`desc` |
+---------+
| A       |
+---------+
```

## 分支语句

### `IF...[ELSEIF...][ELSE...]END IF`

对于有如下内容的数据表

```text
+----+------+--------+
| id | name | height |
+----+------+--------+
|  1 | Jack |    180 |
|  2 | Ross |    165 |
|  3 | Emma |    172 |
+----+------+--------+
```

创建一个存储过程

```SQL
CREATE PROCEDURE `pro_test`()
BEGIN
    DECLARE `height` INT DEFAULT 175;
    DECLARE `desc` CHAR(4);
    IF `height` >= 180 THEN
        SET `desc` = 'A';
    ELSEIF `height` >= 170 AND `height` < 180 THEN
        SET `desc` = 'B';
    ELSE
        SET `desc` = 'C';
    END IF;
    SELECT concat('height = ', `height`, ', desc = ', `desc`);
END;
```

并调用

```SQL
CALL `pro_test`()
```

将得到如下结果

```text
+----------------------------------------------------+
| concat('height = ', `height`, ', desc = ', `desc`) |
+----------------------------------------------------+
| height = 175, desc = B                             |
+----------------------------------------------------+
```

### `CASE`

#### 搜索`CASE`

```SQL
CREATE PROCEDURE `proc_month_to_season`(IN `month` INT, OUT `season` INT)
BEGIN
    CASE
        WHEN `month` >= 1 AND `month` <= 3 THEN
            SET `season` = '1';
        WHEN `month` >= 4 AND `month` <= 6 THEN
            SET `season` = '2';
        WHEN `month` >= 7 AND `month` <= 9 THEN
            SET `season` = '3';
        WHEN `month` >= 10 AND `month` <= 12 THEN
            SET `season` = '4';
        END CASE;
END;
```

调用改存储过程

```SQL
CALL proc_month_to_season(10, @`season`);
```

查看输出变量的值，将得到

```text
+-----------+
| @`season` |
+-----------+
|         4 |
+-----------+
```

#### 简单`CASE`

```SQL
CREATE PROCEDURE `proc_month_to_season`(IN `month` INT, OUT `season` INT)
BEGIN
    CASE `month`
        WHEN 1 THEN SET `season` = '1';
        WHEN 2 THEN SET `season` = '1';
        WHEN 3 THEN SET `season` = '1';
        WHEN 4 THEN SET `season` = '2';
        WHEN 5 THEN SET `season` = '2';
        WHEN 6 THEN SET `season` = '2';
        WHEN 7 THEN SET `season` = '3';
        WHEN 8 THEN SET `season` = '3';
        WHEN 9 THEN SET `season` = '3';
        WHEN 10 THEN SET `season` = '4';
        WHEN 11 THEN SET `season` = '4';
        WHEN 12 THEN SET `season` = '4';
        END CASE;
END;
```

调用改存储过程

```SQL
CALL proc_month_to_season(10, @`season`);
```

查看输出变量的值，将得到

```text
+-----------+
| @`season` |
+-----------+
|         4 |
+-----------+
```

## 循环语句

### `WHILE...DO...END WHILE`

```SQL
CREATE PROCEDURE `proc_sum_from_one_to`(IN `n` INT, OUT `sum` INT)
BEGIN
    DECLARE `i` INT DEFAULT 1;
    SET `sum` = 0;
    WHILE `i` <= `n`
        DO
            SET `sum` = `sum` + `i`;
            SET `i` = `i` + 1;
        END WHILE;

END;
```

调用改存储过程

```SQL
CALL proc_sum_from_one_to(100, @`sum`);
```

查看输出变量的值，将得到

```text
+--------+
| @`sum` |
+--------+
|   5050 |
+--------+
```

### `REPEAT...UNTIL...END REPEAT`

```SQL
CREATE PROCEDURE `proc_sum_from_one_to`(IN `n` INT, OUT `sum` INT)
BEGIN
    DECLARE `i` INT DEFAULT 1;
    SET `sum` = 0;
    REPEAT
        SET `sum` = `sum` + `i`;
        SET `i` = `i` + 1;
    UNTIL `i` > `n` END REPEAT;
END;
```

`UNTIL` 语句表示的是退出循环的条件

### `LOOP`

```SQL
CREATE PROCEDURE `proc_sum_from_one_to`(IN `n` INT, OUT `sum` INT)
BEGIN
    DECLARE `i` INT DEFAULT 1;
    SET `sum` = 0;
    `calc_loop`:
    LOOP
        SET `sum` = `sum` + `i`;
        SET `i` = `i` + 1;
        IF `i` > `n` THEN
            LEAVE `calc_loop`;
        END IF;
    END LOOP;
END;
```

`LOOP` 循环的退出或结束需要结合 `LEAVE` 使用