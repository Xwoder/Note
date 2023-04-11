# PostgreSQL 数据库

在 `psql` 中可以使用 `\d` 命令查看现有数据库，其等价于如下 `SQL` 查询

```postgresql
SELECT d.datname                                   AS "Name",
       pg_catalog.PG_GET_USERBYID(d.datdba)        AS "Owner",
       pg_catalog.pg_encoding_to_char(d.encoding)  AS "Encoding",
       d.datcollate                                AS "Collate",
       d.datctype                                  AS "Ctype",
       pg_catalog.ARRAY_TO_STRING(d.datacl, E'\n') AS "Access privileges"
FROM pg_catalog.pg_database d
ORDER BY 1;
```

或

```postgresql
SELECT datname
FROM pg_catalog.pg_database;
```

## 创建

```postgresql
CREATE DATABASE test;
```

数据库创建完成后，会自动创建一个名为 `test` 的数据库。在 `psql` 中可以使用 `\dn` 命令查看，其等价于

```postgresql
SELECT n.nspname                              AS "Name",
       pg_catalog.PG_GET_USERBYID(n.nspowner) AS "Owner"
FROM pg_catalog.pg_namespace n
WHERE n.nspname !~ '^pg_'
  AND n.nspname <> 'information_schema'
ORDER BY 1;
```

## 修改

### 重命名

```postgresql
ALTER DATABASE test RENAME TO learn;
```

### 更改所有者

```postgresql
ALTER DATABASE test owner TO postgres;
```

### 修改配置变量

```postgresql
ALTER DATABASE test SET ENABLE_INDEXSCAN = OFF;
ALTER DATABASE test SET ENABLE_INDEXSCAN TO OFF;
```

### 重置配置变量

```postgresql
ALTER DATABASE test RESET ENABLE_INDEXSCAN;
```

```postgresql
ALTER DATABASE test SET ENABLE_INDEXSCAN TO DEFAULT;
```

## 删除

只有数据库的拥有者才能删除该数据库，

```postgresql
DROP DATABASE test;

DROP DATABASE IF EXISTS test;
```
