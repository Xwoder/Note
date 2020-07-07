# MySQL 函数

[TOC]

> 基于 `MySQL 8`

## 创建

创建存储过程使用 `CREATE PROCEDURE` 语句

```SQL
# 创建
CREATE FUNCTION func_test(`type` VARCHAR(32))
    RETURNS INT
    READS SQL DATA
BEGIN
    DECLARE `count` INT DEFAULT 0;
    SELECT count(*)
        INTO `count`
        FROM `Product`
        WHERE `product_type` = `type`;
    RETURN `count`;
END;
```

## 调用

```SQL
SELECT func_test('办公用品');
SELECT func_test('厨房用具');
```

## 查看

### 查看定义

```SQL
SHOW CREATE FUNCTION 函数名;
```

## 删除

```SQL
DROP FUNCTION 函数名;
```

