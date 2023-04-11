# PostgreSQL 事务

## 开启

```postgresql
BEGIN;
```

```postgresql
BEGIN WORK;
```

```postgresql
BEGIN TRANSACTION;
```

## 提交

```postgresql
COMMIT;
```

```postgresql
COMMIT WORK;
```

```postgresql
COMMIT TRANSACTION;
```

## 回滚

```postgresql
ROLLBACK;
```

```postgresql
ROLLBACK WORK;
```

```postgresql
ROLLBACK TRANSACTION;
```