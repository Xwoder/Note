# PostgreSQL 集合运算

```postgresql
SELECT *
FROM (VALUES (1), (2), (3)) AS t(x)
UNION
SELECT *
FROM (VALUES (1), (2), (4)) AS t(x);
```

```postgresql
SELECT *
FROM (VALUES (1), (2), (3)) AS t(x)
UNION ALL
SELECT *
FROM (VALUES (1), (2), (4)) AS t(x);
```

```postgresql
SELECT *
FROM (VALUES (1), (2), (3)) AS t(val)
UNION ALL
SELECT *
FROM (VALUES (1), (2), (4)) AS t(val)
ORDER BY val;
```

