# PostgreSQL SQL

## 查询

```postgresql
SELECT *
FROM tickets;
```

```postgresql
SELECT ticket_no, passenger_id
FROM tickets;
```

## 无表查询

```postgresql
SELECT 2 + 3;
SELECT 2 + 3 as sum;
```

## 去重

```postgresql
SELECT DISTINCT seat_no
FROM seats;
```

## WHERE 子句

```postgresql
SELECT *
FROM bookings
WHERE total_amount >= 20000;
```

## 别名

### 字段别名

```postgresql
SELECT ticket_no AS ticket, passenger_id AS passenger
FROM tickets;
```

## 比较运算符

| 运算符 |   含义   |
| :----: | :------: |
|  `=`   |   等于   |
|  `!=`  |  不等于  |
|  `<>`  |  不等于  |
|  `>`   |   大于   |
|  `>=`  | 大于等于 |
|  `<`   |   小于   |
|  `<=`  | 小于等于 |
|        |          |
|        |          |

## 范围运算符  

|  运算符   |含义 |
| :-------------------: | :------------------: |
|  `BETWEEN ... AND ...`  |           介于 ... 和 ... 之间           |
|         `IN`          |         属于         |

## 空值查询

```postgresql
SELECT * FROM student;
```

## 排序

### 空值排序

```postgresql
SELECT first_name,
       last_name,
       commission_pct
FROM employees
WHERE first_name = 'Peter'
ORDER BY commission_pct NULLS FIRST;
```

```postgresql
SELECT first_name,
       last_name,
       commission_pct
FROM employees
WHERE first_name = 'Peter'
ORDER BY commission_pct NULLS LAST;
```

## 数量控制

### 限量

```postgresql
SELECT *
FROM seats
    FETCH FIRST 10 ROWS ONLY;
```

等价于

```postgresql
SELECT *
FROM seats
    FETCH NEXT 10 ROWS ONLY;
```

还等价于

```postgresql
SELECT *
FROM seats
LIMIT 10;
```

### 偏移

```postgresql
SELECT *
FROM seats
OFFSET 10 ROWS FETCH FIRST 10 ROWS ONLY;
```

等价于

```postgresql
SELECT *
FROM seats
LIMIT 10 OFFSET 10;
```

## 连接查询

### 内连接

`SQL/92` 标准

```postgresql
INNER JOIN ... ON ...
```

```postgresql
JOIN ... ON ...
```

`SQL/86` 标准

```postgresql
FROM ... WHERE ...
```

### 左外连接

```postgresql
LEFT OUTER JOIN ... ON ...

LEFT JOIN ... ON ...
```

### 右外连接

```postgresql
RIGHT OUTER JOIN ... ON ...

RIGHT JOIN ... ON ...
```

### 全外连接

```postgresql
FULL OUTER JOIN ... ON ...

FULL JOIN ... ON ...
```

### 交叉连接

利用交叉连接的特性打印九九乘法表

```postgresql
SELECT v || ' * ' || h || ' = ' || v * h
FROM GENERATE_SERIES(1, 9) v
         CROSS JOIN GENERATE_SERIES(1, 9) h;

SELECT v || ' * ' || h || ' = ' || v * h
FROM GENERATE_SERIES(1, 9) v,
     GENERATE_SERIES(1, 9) h;

SELECT v || ' *' || h || ' = ' || v * h
FROM GENERATE_SERIES(1, 9) v
         JOIN GENERATE_SERIES(1, 9) h ON TRUE;
```

利用 USING 子句简化连接条件

```postgresql
SELECT d.department_id,
       e.department_id,
       d.department_name,
       e.first_name,
       e.last_name
FROM employees e
         JOIN departments d
              USING (department_id);
```

### 自然连接

```postgresql
SELECT d.department_id,
       d.department_name,
       e.first_name,
       e.last_name
FROM departments d
         NATURAL JOIN employees e;
```

