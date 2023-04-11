# PostgreSQL SQL SELECT

```postgresql
INSERT INTO student(name, age) VALUES("张三", 20);
INSERT INTO student(name, age) VALUES("李四", 30);
INSERT INTO student(name, age) VALUES("王五", 40);

-- 插入默认值
INSERT INTO student(name, age) VALUES("赵六", DEFAULT);

-- 插入空值
INSERT INTO student(name, age) VALUES(NULL, 50);
```

```postgresql
INSERT INTO student VALUES(1, "张三", 20);
INSERT INTO student VALUES(2, "李四", 30);
INSERT INTO student VALUES(3, "王五", 40);
```

```postgresql
INSERT INTO student(name, age) VALUES("张三", 20), ("李四", 30), ("王五", 40);
```

