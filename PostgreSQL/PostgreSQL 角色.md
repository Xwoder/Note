# PostgreSQL 角色

## 创建

```postgresql
CREATE ROLE student;
```

角色默认没有登录权限。

```postgresql
CREATE ROLE student PASSWORD '123456' LOGIN;
```

```postgresql
ALTER ROLE student WITH LOGIN CREATEDB;
```

## 查看

```postgresql
SELECT *
FROM pg_roles;
```

```postgresql
SELECT *
FROM pg_user;
```

## 授权

```postgresql
ALTER ROLE student WITH LOGIN;
```

