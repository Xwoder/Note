# PostgreSQL 用户

## 创建用户

```postgresql
CREATE USER jack
    WITH PASSWORD '123456';
```

## 删除用户

```postgresql
DROP USER jack;
```

## 查看

```postgresql
\du
```

```postgresql
\dg
```

## 授权

授予所有权利

```postgresql
GRANT ALL PRIVILEGES ON DATABASE mydb TO jack;
```

```postgresql
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA mydb TO jack;
```

## 撤权

撤回所有权利

```postgresql
REVOKE ALL PRIVILEGES ON ALL TABLES IN SCHEMA mydb FROM jack;
```

