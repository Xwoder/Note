# SQLite JSON

## JSON

```sqlite
SELECT JSON('{"first":"test", "second":"haha"}');
-- {"first":"test","second":"haha"}
```
## JSON_OBJECT

```sqlite
SELECT JSON_OBJECT('first', 'abc', 'second', 'def');
-- {"first":"abc","second":"def"}
```
## JSON_ARRAY

数组

```sqlite
SELECT JSON_ARRAY(1, 2, 3, 4);
-- [1,2,3,4]
```
## JSON_ARRAY_LENGTH

长度

```sqlite
SELECT JSON_ARRAY_LENGTH('[1,2,3,4]');
-- [1,2,3,4]

SELECT JSON_ARRAY_LENGTH('{"one":[1,2,3]}', '$.one');
-- 3

SELECT JSON_ARRAY_LENGTH('{"one":[1,2,3], "two": "3"}', '$.two');
-- 0
```
## JSON_EXTRACT

提取

```sqlite
SELECT JSON_EXTRACT('{"a":2, "c":[4,5,{"f":7}]}', '$.c');
-- [4,5,{"f":7}]

SELECT JSON_EXTRACT('{"a":2, "c":[4,5,{"f":7}]}', '$.c[0]');
-- 4

SELECT JSON_EXTRACT('{"a":2, "c":[4,5,{"f":7}]}', '$.c[2]');
-- {"f":7}

SELECT JSON_EXTRACT('{"a":2, "c":[4,5,{"f":7}]}', '$.c[2].f');
-- 7

SELECT JSON_EXTRACT('{"a":2, "c":[4,5,{"f":7}]}', '$.a', '$.c');
-- [2,[4,5,{"f":7}]]
```
## JSON_INSERT

插入；只有键不存在，才会插入

```sqlite
SELECT JSON_INSERT('{"a":2, "c":4}', '$.a', 99);
-- {"a":2,"c":4}

SELECT JSON_INSERT('{"a":2, "c":4}', '$.b', 99);
-- {"a":2,"c":4,"b":99}
```
## JSON_REPLACE

替换；无则不变，有则替换

```sqlite
SELECT JSON_REPLACE('{"a":2, "c":4}', '$.a', 99);
-- {"a":99,"c":4}

SELECT JSON_REPLACE('{"a":2, "c":4}', '$.b', 99);
-- {"a":2,"c":4}
```
## JSON_SET

设置；无则插入，有则改之

```sqlite
SELECT JSON_SET('{"a":2, "c":4}', '$.b', 99);
-- {"a":2,"c":4,"b":99}

SELECT JSON_SET('{"a":2, "c":4}', '$.a', 99);
-- {"a":99,"c":4}
```