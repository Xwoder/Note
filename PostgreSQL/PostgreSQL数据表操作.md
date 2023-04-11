# PostgreSQL 数据表操作

## 创建

```postgresql
CREATE TABLE IF NOT EXISTS student
(
    id   SERIAL,
    name VARCHAR(255) NOT NULL
);
```

## 删除

```postgresql
DROP TABLE student;
```

## 重命名

## 查看表结构

在 `psql` 中可使用 `\d table_name`，对应的 SQL 语句暂时略。

## 复制

```postgresql
SELECT *
INTO employees_copy
FROM employees;
```

或者

```postgresql
CREATE TABLE employees_copy AS
SELECT *
FROM employees;
```

## 修改表

假定存在如下定义的表

```postgresql
CREATE TABLE student(
    id SERIAL,
    name CHARACTER VARYING(10)
);
```

### 添加字段

```postgresql
ALTER TABLE student
    ADD COLUMN age INT NOT NULL;
```

```postgresql
ALTER TABLE student
    ADD COLUMN stu_id INT UNIQUE;
```

新增列的同时增加为改列的数据指定一个默认值

```postgresql
ALTER TABLE student
    ADD COLUMN age INT NOT NULL DEFAULT 0;
```

### 删除字段

```postgresql
ALTER TABLE student
    DROP COLUMN age;
```

如果待删除的字段上存在外键约束，则需要使用 `CASCADE` 关键字表示级联删除。例如

```postgresql
ALTER TABLE student
    DROP COLUMN age CASCADE;
```

### 添加约束

添加约束时，系统会检验已有数据是否满足条件，如果不满足将会添加失败。

添加列的同时指定约束

```postgresql
ALTER TABLE student
    ADD COLUMN stu_id INT UNIQUE;
```

对已经存在的列的添加约束，或对表添加约束

```postgresql
ALTER TABLE student
    ADD CONSTRAINT stu_id_nuique UNIQUE (stu_id);
```

```postgresql
ALTER TABLE student
    ADD CONSTRAINT age_greater_than_or_equal_zero CHECK (age >= 0);
```

添加非空约束

```postgresql
ALTER TABLE student
    ALTER COLUMN age SET NOT NULL;
```

### 删除约束

```postgresql
ALTER TABLE student
    DROP CONSTRAINT constraint_name;
```

使用单独的语法删除非空约束

```postgresql
ALTER TABLE student
    ALTER COLUMN age DROP NOT NULL;
```

### 查看约束

在 `psql` 中可以使用 `\d` 命令查看约束，或者使用如下非等价的 `SQL` 语句

```postgresql
SELECT tc.constraint_name,
       tc.table_name,
       kcu.column_name,
       ccu.table_name  AS foreign_table_name,
       ccu.column_name AS foreign_column_name,
       tc.is_deferrable,
       tc.initially_deferred
FROM information_schema.table_constraints AS tc
         JOIN information_schema.key_column_usage AS kcu ON tc.constraint_name = kcu.constraint_name
         JOIN information_schema.constraint_column_usage AS ccu ON ccu.constraint_name = tc.constraint_name
    AND tc.table_name = 'table_name';
```

### 默认值

#### 修改默认值

```postgresql
ALTER TABLE student
    ALTER COLUMN age SET DEFAULT -1;
```

#### 删除默认值

```postgresql
ALTER TABLE student
    ALTER COLUMN age DROP DEFAULT;
```

### 修改类型

```postgresql
ALTER TABLE student
    ALTER name TYPE CHARACTER VARYING(5);
```

修改类型并对已有数据利用表达式进行强制转换

```postgresql
ALTER TABLE student
    ALTER COLUMN age TYPE INTEGER USING age::INTEGER;
```

### 重命名字段

```postgresql
ALTER TABLE student
    RENAME COLUMN age TO age_new;
```

### 重命名表

```postgresql
ALTER TABLE student
    RENAME TO student_new;
```

