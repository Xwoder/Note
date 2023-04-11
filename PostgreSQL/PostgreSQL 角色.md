# PostgreSQL 角色

`PostgreSQL` 使用角色的概念来管理数据库的访问权限。

一个角色可以被认为是或者是一个数据库**用户**（`user`），或者是一个数据库用的**组**（`group`），具体取决于如何配置用户。

**用户**（`user`）是具有登录权限的角色。

## 创建

```postgresql
CREATE ROLE name；
CREATE USER name;
```

## 修改

可以通过 `ALTER ROLE` 语句修改用户包括属性在内的有关信息。

```postgresql
ALTER ROLE admin NOCREATEROLE;
```

## 查看

```postgresql
SELECT rolname
FROM pg_roles;
```

在 `psql` 中还可以使用 `\du` 命令。

## 特殊角色

`postgres` 是系统初始化数据库时创建的默认角色，它是一个超级用户。

## 属性

角色可以拥有属性，属性确定了角色拥有的特权。

### 登录权利属性

只有拥有`LOGIN` 属性的角色才能连接数据库。

创建具有登录权利的角色

```postgresql
CREATE ROLE name LOGIN;
CREATE USER name;
```

通过 `CREATE USER` 语句创建的角色默认包含了 `LOGIN` 属性。

### 超级用户属性

除对登录权利的检查外，拥有超级用户属性的角色将绕过其他一切权限的检查。创建方式：

```postgresql
CREATE ROLE name SUPERUSER;
```

### 创建数据库属性

```postgresql
 CREATE ROLE name CREATEDB；
```

### 创建用户属性

```postgresql
CREATE ROLE name CREATEROLE;
```

### 密码属性

`password` 和 `md5` 认证方式将会使用到密码。

在创建角色的时候，可以指定其使用的密码：

```postgresql
CREATE ROLE jack PASSWORD '123456';
```

---

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

用户：具有登录权限的角色

## 删除用户

```postgresql
DROP USER jack;
```

## 查看

在 psql 中执行 `\du` 命令可以查看角色列表，其等价于如下 `SQL` 语句

```postgresql
SELECT r.rolname,
       r.rolsuper,
       r.rolinherit,
       r.rolcreaterole,
       r.rolcreatedb,
       r.rolcanlogin,
       r.rolconnlimit,
       r.rolvaliduntil,
       ARRAY(SELECT b.rolname
             FROM pg_catalog.pg_auth_members m
                      JOIN pg_catalog.pg_roles b ON (m.roleid = b.oid)
             WHERE m.member = r.oid) AS memberof
        ,
       r.rolreplication
        ,
       r.rolbypassrls
FROM pg_catalog.pg_roles r
WHERE r.rolname !~ '^pg_'
ORDER BY 1;
```

```postgresql
\dg
```

## 授权

授予所有权利

```postgresql
GRANT ALL PRIVILEGES 
    ON DATABASE mydb 
    TO jack;
```

```postgresql
GRANT SELECT, INSERT, UPDATE, DELETE 
    ON employees, departments, jobs 
    To jack;
```

将 `public` 模式中的所有数据表的查询权限授予 jack 用户

```postgresql
GRANT SELECT 
    ON ALL TABLES 
    IN SCHEMA public 
    TO jack;
```

将 `public` 模式中的所有数据表的所有权限授予 jack 用户

```postgresql
GRANT ALL PRIVILEGES 
    ON ALL TABLES 
    IN SCHEMA mydb 
    TO jack;
```

将角色的权利授予用户

```postgresql
GRANT managers TO jack;
```

可以将一个角色授予给另一个角色，使其成为后者的一个成员。

```postgresql
GRANT supermanager to manager;
```



### 再授权

在授权时，使用 `WITH GRANT OPTION` 子句，可以允许用户将自己被授予的权利再授权给其他用户。

```postgresql
GRANT SELECT, INSERT, UPDATE, DELETE
    ON employees, departments, jobs
    TO jack 
    WITH GRANT OPTION;
```

## 撤权

撤回所有权利

```postgresql
REVOKE ALL PRIVILEGES 
    ON ALL TABLES 
    IN SCHEMA public 
    FROM jack;
```

```postgresql
REVOKE SELECT, INSERT, UPDATE, DELETE 
    ON employees, departments, jobs 
    FROM jack;
```

