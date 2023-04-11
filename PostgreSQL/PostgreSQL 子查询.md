# PostgreSQL 子查询

```postgresql
SELECT book_ref, total_amount
FROM bookings
WHERE total_amount >= (SELECT AVG(total_amount) * 10 FROM bookings)
ORDER BY total_amount DESC;
```

