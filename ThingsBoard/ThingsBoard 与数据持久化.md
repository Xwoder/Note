# ThingsBoard 与数据持久化

`ThingsBoard` 使用数据库存储实体（设备、资产、客户和仪表盘等信息）以及遥测数据（属性、时间序列传感器读数、统计数据和事件等）。

支持以下3种数据存放方式：

- `PostgreSQL`
- `PostgreSQL` + `Cassandra`
- `PostgreSQL` + `TimescaleDB`

## `PostgreSQL`

### 存储方式

| 数据类型 |   存储方式   |
| :------: | :----------: |
|   实体   | `PostgreSQL` |
| 遥测数据 | `PostgreSQL` |

## `PostgreSQL` + `Cassandra`

### 存储方式

| 数据类型 |   存储方式   |
| :------: | :----------: |
|   实体   | `PostgreSQL` |
| 遥测数据 | `Cassandra`  |

## `PostgreSQL` + `TimescaleDB`

### 存储方式

| 数据类型 |   存储方式    |
| -------- | :-----------: |
| 实体     | `PostgreSQL`  |
| 遥测数据 | `TimescaleDB` |

## 配置

相关配置位于 `thingsboard.yml` 文件中，一般位于

```
/etc/thingsboard/conf/thingsboard.yml
```

中的

```yaml
database:
    ts_max_intervals: "${DATABASE_TS_MAX_INTERVALS:700}"
    ts:
        type: "${DATABASE_TS_TYPE:sql}"
    ts_latest:
        type: "${DATABASE_TS_LATEST_TYPE:sql}"
```

参数的具体含义如下

|            参数             |          环境变量           | 默认值 | `描述`                                                       |
| :-------------------------: | :-------------------------: | :----: | ------------------------------------------------------------ |
| `database.ts_max_intervals` | `DATABASE_TS_MAX_INTERVALS` | `700`  | 单个 `API` 调用生成的获取遥测记录的最大数据库查询数          |
|     `database.ts.type`      |     `DATABASE_TS_TYPE`      | `sql`  | `Cassandra`、`sql`或 `timescale`。对于混合模式，`DATABASE_TS_TYPE` 的值应当为 `cassandra` 或 `timescale` |
|  `database.ts_latest.type`  |  `DATABASE_TS_LATEST_TYPE`  | `sql`  | `Cassandra`、`sql`或 `timescale`。对于混合模式，`DATABASE_TS_TYPE` 的值应当为 `cassandra` 或 `timescale` |