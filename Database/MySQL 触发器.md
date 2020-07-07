# MySQL 触发器

[TOC]

> 基于 `MySQL 8`

## 分类

### 语句类型

* `INSERT` 型触发器
* `DELETE` 型触发器
* `UPDATE` 型触发器

### 其他分类

`MySQL` 只支持行级触发器

## 概念

### 触发时间

* `AFTER`

* `BEFORE`

### 触发事件

* `INSERT` 
* `DELETE` 
* `UPDATE` 

## 创建

```SQL
CREATE TRIGGER `trig_test`
    # 触发时间 和 触发事件
    AFTER INSERT
    # 触发表格
    ON `Person`
    # 行级触发器
    FOR EACH ROW
BEGIN
    INSERT INTO `Person_Log`(`operation`, `operation_time`, `operation_id`, `operation_params`)
        VALUES ('insert', now(), `NEW`.`id`, NULL);
END;
```

## 触发触发器

```SQL
INSERT INTO `Person`(`name`, `height`)
    VALUES ('Tom', 172);
```

执行上述 `INSERT` 语句将触发触发器

## 查看

### 查看定义

```SQL
SHOW CREATE TRIGGER `trig_test`;
```

### 查看列表

```
SHOW TRIGGERS;
```

