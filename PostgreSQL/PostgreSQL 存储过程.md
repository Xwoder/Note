# PostgreSQL 存储过程

> 存储过程，英文名：`Stored Procedure`

`PostgreSQL` 默认支持的存储过程是 `PL/pgSQL` 存储过程。

## 示例

### 简单代码块

```postgresql
DO
$$
    DECLARE
        name TEXT;
    BEGIN
        name := 'PostgreSQL';
        RAISE NOTICE 'Hello %', name;
    END
$$;
```

运行上述代码块，将得到输出

```
Hello PostgreSQL
```

### 嵌套代码块

`PostgreSQL` 支持代码块的嵌套。被嵌套的代码块称为子块（`subblock`），包含子块的代码块称为外部块。

```postgresql
DO -- 执行代码块
$$
    <<outer_block>> -- 外部代码块标签，可选，名为 outer_block
        DECLARE
        name TEXT; -- 外部代码块中的变量
    BEGIN
        name := 'outer_block';
        RAISE NOTICE 'This is %', name;
        <<inner_block>> -- 内部代码块标签，可选，名为 inner_block
            DECLARE
            name TEXT := 'sub_block'; -- 内部代码块中的变量，与外部代码块中的变量名相同
        BEGIN
            RAISE NOTICE 'This is %', name;
            RAISE NOTICE 'The name FROM the outer block is %', outer_block.name;
        END inner_block; -- 内部代码块结束
        RAISE NOTICE 'This is %', name;
    END outer_block
$$;
```

运行上述代码块将得到

```
This is outer_block
This is sub_block
The name FROM the outer block is outer_block
This is outer_block
```

## 变量

```postgresql
name TEXT;
user_id INTEGER;

-- 声明变量，同时指定默认值
quantity NUMERIC(5) DEFAULT 0;

-- 声明变量，同时指定初始值
url VARCHAR := 'http://mysite.com';
```

## 常量

```postgresql
PI CONSTANT NUMERIC := 3.14159265;
```

## `IF` 语句

### `IF … THEN … END IF`

```postgresql
DO
$$
    DECLARE
        i INTEGER := 3;
        j INTEGER := 3;
    BEGIN
        IF i > j THEN
            RAISE NOTICE 'i > j';
        END IF;

        IF i < j THEN
            RAISE NOTICE 'i < j';
        END IF;

        IF i = j THEN
            RAISE NOTICE 'i = j';
        END IF;
    END
$$;
```

输出

```postgresql
i = j
```

### `IF … THEN … ELSE … END IF`

```postgresql
DO
$$
    DECLARE
        i INTEGER := 3;
        j INTEGER := 3;
    BEGIN
        IF i > j THEN
            RAISE NOTICE 'i > j';
        ELSE
            IF i < j THEN
                RAISE NOTICE 'i < j';
            ELSE
                RAISE NOTICE 'i = j';
            END IF;
        END IF;
    END
$$;
```

输出

```postgresql
i = j
```

### `IF … THEN … ELSIF … THEN … ELSE … END IF`

```postgresql
DO
$$
    DECLARE
        i INTEGER := 3;
        j INTEGER := 3;
    BEGIN
        IF i > j THEN
            RAISE NOTICE 'i > j';
        ELSIF i < j THEN
            RAISE NOTICE 'i < j';
        ELSE
            RAISE NOTICE 'i = j';
        END IF;
    END
$$;
```

输出

```postgresql
i = j
```

## `CASE` 语句

### 简单 `CASE` 语句

```postgresql
DO
$$
    DECLARE
        i INTEGER := 3;
    BEGIN
        CASE i
            WHEN 1, 2 THEN RAISE NOTICE '1 or 2';
            WHEN 3, 4 THEN RAISE NOTICE '3 or 4';
            ELSE RAISE NOTICE 'other';
            END CASE;
    END
$$;
```

输出

```postgresql
3 or 4
```

### 搜索 CASE 语句

```postgresql
DO
$$
    DECLARE
        i INTEGER := 3;
    BEGIN
        CASE
            WHEN i BETWEEN 0 AND 10 THEN RAISE NOTICE '[0, 10]';
            WHEN i BETWEEN 11 AND 20 THEN RAISE NOTICE '[11, 20]';
            ELSE RAISE NOTICE 'other';
            END CASE;
    END
$$;
```

输出

```postgresql
[0, 10]
```

## `RAISE` 语句

### 格式

```postgresql
RAISE level format
```

## 等级

不同等级（`level`） 代表了不同的严重等级。

定义了如下等级

- `DEBUG`
- `LOG`
- `NOTICE`
- `INFO`
- `WARNING`
- `EXCEPTION`

若不指定 `level`，则默认为 `EXCEPTION`，将会抛出异常并终止代码运行。

并非所有的消息都会打印到客户端和服务器日志中。

### 示例

```postgresql
DO
$$
    BEGIN
        RAISE DEBUG 'This is a debug text.';
        RAISE INFO 'This is an information.';
        RAISE LOG 'This is a log.';
        RAISE WARNING 'This is a warning at %', NOW();
        RAISE NOTICE 'This is a notice %%';
    END
$$;
```

