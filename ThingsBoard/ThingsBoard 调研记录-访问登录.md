# ThingsBoard 调研记录-用户系统

[TOC]

## 访问

在 ThingsBoard 服务启动以后，可以在浏览器中通过`URL`访问，默认端口为8080

```
http://127.0.0.1:8080
```

## 用户与角色

如果在安装时指定了加载示例程序，即添加了 `--loadDemo` 参数，则安装完成会默认生成 3 个权限的账户


角色 | 邮箱账号 | 密码
:-: | :-: | :-: 
**System Administrator** |`sysadmin@thingsboard.org` | `sysadmin`
 **Tenant Administrator** |`tenant@thingsboard.org` | `tenant`
 **Customer User** |`customer@thingsboard.org` | `customer`

### System Administrator

系统管理员角色；主要权限包括租户创建、租户配置，组件包管理和系统设置。

该角色一般分配给系统运维人员。

### Tenant Administrator

租户管理员角色；主要权限包括

* 规则链库（ `Rules chains`）的配置
* 客户（`Customer`）及其客户用户（`Customer User`）管理；增加、删除等
* 资产（Asset）管理
* 设备管理、设备配置
* OTA 升级管理
* 实体（`Entity`）视图
* 边缘实例（`Edge instance`）管理及其管理
* 部件库（`Widget Library`）和仪表盘（`Dashboard`）的管理
* API 使用统计

该角色权限与职能较多

### Customer User

客户用户角色；主要权限包括查看资产、查看设备信息、属性、遥测数据、警告、事件等信息。

该角色主要分配给主要职能是查看设备和仪表板信息的末端普通用户。