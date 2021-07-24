# <center>  基于Vue.js+微信小程序的前后端分离的社区管理系统的设计与实现项目设计文档</center>

*By* ***2019002342_汤福锦*** *2021.07*   

*[系统访问地址] (https://hutao.ink/c/community/swagger-ui/index.html#/)*

**目录**

[TOC]

<div style="page-break-after: always;"></div>

## 功能设计

> 用户端
>
> - 微信原生登录；
> - 手机短信验证码，绑定用户信息；
> - 报修；
> - 邻里圈。
> - 公告，轮播图。
> - 车牌号识别
> - 员工查看处理报修信息
>
> 管理员端
>
> - 邮箱或手机号加密码登录，邮箱或手机验证码验证
> - 管理员权限认证
> - 用户管理
>
> - 圈子管理
> - 公告管理
> - 轮播图管理
> - 车辆管理

> 模块设计（Controller）
>
> 1. 业主模块(OwnerController)；
> 2. 报修模块(RepairController)；
> 3. 轮播图模块(BannerController)；
> 4. 邻里圈模块(CircleController)。
> 5. 员工模块(EmployeeController)
> 6. 公告模块(NoticeController)
> 7. 管理员模块(AdminController)
> 8. 车辆模块(CarController)

<div style="page-break-after: always;"></div>

## 模块设计

### 1. 数据表设计

- **业主表（sys_owner）**

>| 字段        | 数据类型     | 说明                     | 默认值 |
>| ----------- | ------------ | ------------------------ | ------ |
>| id          | bigint       | 雪花id主键               | 非空   |
>| openId      | varchar(50)  | 微信用户唯一凭证         | null   |
>| nickName    | char(20)     | 微信昵称                 | null   |
>| imageUrl    | varchar(255) | 微信头像地址             | null   |
>| gender      | tinyint(1)   | 性别0="女"，1=“男”       | null   |
>| city        | char(20)     | 城市                     | null   |
>| province    | char(20)     | 省份                     | null   |
>| country     | char(20)     | 国家                     | null   |
>| deleted     | tinyint(1)   | 逻辑删除，1删除，0没删除 | null   |
>| createTime  | datetime     | 注册日期                 | null   |
>| modifyTime  | datetime     | 修改日期                 | null   |
>| communityId | bigint       | 小区id                   | null   |
>| roleId      | bigint       | 角色id                   | null   |

- **真实信息表（sys_real）**

>| 字段        | 数据类型     | 说明             | 默认值 |
>| ----------- | ------------ | ---------------- | ------ |
>| id          | bigint       | 雪花id主键       | 非空   |
>| openId      | varchar(50)  | 微信用户唯一凭证 | 非空   |
>| realName    | varchar(50)  | 业主真实姓名     | null   |
>| address     | varchar(255) | 业主住址         | null   |
>| phoneNumber | varchar(50)  | 业主手机号码     | null   |
>| createTime  | datetime     | 创建日期         | null   |
>| modifyTime  | datetime     | 修改日期         | null   |

<div style="page-break-after: always;"></div>

- **小区表（sys_communityId）**

> | 字段          | 数据类型    | 说明     | 默认值(是否为空) |
> | ------------- | ----------- | -------- | ---------------- |
> | id            | bigint      | 唯一id   | NO               |
> | communityName | varchar(20) | 小区名称 | YES              |
> | createTime    | datetime    | 创建时间 | YES              |
> | modifyTime    | datetime    | 修改时间 | YES              |

- **公告表(sys_notice)**

> | 字段       | 数据类型      | 说明                     | 默认值 |
> | ---------- | ------------- | ------------------------ | ------ |
> | id         | bigint        | 雪花id主键               | 非空   |
> | headline   | varchar(50)   | 标题                     | null   |
> | notice     | varchar(5000) | 公告（MarkDown）         | null   |
> | briefInfo  | varchar(255)  | 简略公告信息             | null   |
> | imageUrl   | varchar(255)  | 图片地址                 | null   |
> | deleted    | tinyint(1)    | 逻辑删除，1删除，0没删除 | null   |
> | createTime | datetime      | 注册日期                 | null   |
> | modifyTime | datetime      | 修改日期                 | null   |
>
> 

- **角色表(sys_role)**

> | 字段       | 数据类型 | 说明       | 默认值 |
> | ---------- | -------- | ---------- | ------ |
> | id         | bigint   | 雪花id主键 | 非空   |
> | roleName   | char(10) | 角色权限名 | null   |
> | createTime | datetime | 注册日期   | null   |
> | modifyTime | datetime | 修改日期   | null   |

- **邻里圈表(sys_circle)**

> | 字段       | 数据类型     | 说明                     | 默认值 |
> | ---------- | ------------ | ------------------------ | ------ |
> | id         | bigint       | 雪花id主键               | 非空   |
> | ownerId    | bigint       | 业主id                   | null   |
> | message    | varchar(255) | 圈子内容                 | null   |
> | imagesUrl  | varchar(255) | 图片地址                 | null   |
> | parentId   | bigint       | 父圈子id                 | null   |
> | deleted    | tinyint(1)   | 逻辑删除，1删除，0没删除 | null   |
> | createTime | datetime     | 注册日期                 | null   |
> | modifyTime | datetime     | 修改日期                 | null   |

- **报修表（sys_repair）**

> | 字段          | 数据类型      | 说明         | 默认值 |
> | ------------- | ------------- | ------------ | ------ |
> | id            | bigint        | 唯一id       | NO     |
> | ownerId       | bigint        | 业主id       | YES    |
> | imageUrl      | varchar(255)  | 图片地址     | YES    |
> | repairContext | varchar(5000) | 报修内容说明 | YES    |
> | repairState   | char(10)      | 报修状态     | YES    |
> | employeeId    | bigint        | 员工id       | YES    |
> | deleted       | tinyint(1)    | 逻辑删除     | YES    |
> | createTime    | datetime      | 创建时间     | YES    |

- **车辆信息表（sys_car）**

> | 字段        | 数据类型     | 说明     | 默认值 |
> | ----------- | ------------ | -------- | ------ |
> | id          | bigint       | 唯一id   | NO     |
> | carNumber   | char(10)     | 车牌号   | YES    |
> | carInfo     | varchar(25)  | 车辆信息 | YES    |
> | ownerId     | bigint       | 业主id   | YES    |
> | deleted     | tinyint(1)   | 逻辑删除 | YES    |
> | carImageUrl | varchar(255) | 汽车图片 | YES    |
> | createTime  | datetime     | 创建时间 | YES    |
> | modifyTime  | datetime     | 修改时间 | YES    |

- **轮播图表（sys_banner）**

> | 字段          | 数据类型      | 说明                     | 默认值 |
> | ------------- | ------------- | ------------------------ | ------ |
> | id            | bigint        | 唯一id                   | NO     |
> | bannerUrl     | varchar(255)  | 轮播图地址               | YES    |
> | bannerContext | varchar(5000) | 轮播图内容               | YES    |
> | deleted       | tinyint(1)    | 逻辑删除，1删除，0没删除 | YES    |
> | createTime    | datetime      | 创建时间                 | YES    |
> | modifyTime    | datetime      | 修改时间                 | YES    |

- **密码表（sys_cipher）**

> | 字段       | 数据类型     | 说明        | 默认值 |
> | ---------- | ------------ | ----------- | ------ |
> | id         | bigint       | 唯一id      | NO     |
> | cipher     | varchar(255) | 密码（MD5） | YES    |
> | adminId    | bigint       | 管理员id    | YES    |
> | createTime | datetime     | 创建时间    | YES    |
> | modifyTime | datetime     | 修改时间    | YES    |

- **管理员表（sys_admin）**

> | 字段       | 数据类型     | 说明        | 默认值 |
> | ---------- | ------------ | ----------- | ------ |
> | id         | bigint       | 唯一id      | NO     |
> | cipher     | varchar(255) | 密码（MD5） | YES    |
> | adminId    | bigint       | 管理员id    | YES    |
> | createTime | datetime     | 创建时间    | YES    |
> | modifyTime | datetime     | 修改时间    | YES    |

### 2. 相关SQL

#### 2.1 建库

```sql
 CREATE SCHEMA `community` DEFAULT CHARACTER SET utf8 ;
```

#### 2.2 建表

- 业主表（sys_owner）  

```sql
DROP TABLE IF EXISTS `sys_owner`;
CREATE TABLE `sys_owner` (
  `id` bigint NOT NULL COMMENT '唯一id',
  `openId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录凭证',
  `nickName` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '昵称',
  `imageUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户头像地址',
  `roleId` bigint DEFAULT NULL COMMENT '角色id',
  `communityId` bigint DEFAULT NULL COMMENT '小区id',
  `gender` tinyint(1) DEFAULT NULL COMMENT '性别0="女"，1=“男”',
  `city` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '城市',
  `province` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '省份',
  `country` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '国家',
  `deleted` tinyint(1) DEFAULT NULL COMMENT '逻辑删除，1删除，0没删除',
  `createTime` datetime DEFAULT NULL COMMENT '注册日期',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `openId` (`openId`),
  KEY `commFK` (`communityId`),
  KEY `roleFK` (`roleId`),
  CONSTRAINT `commFK` FOREIGN KEY (`communityId`) REFERENCES `sys_community` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `roleFK` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='业主表';
```

- 真实信息表（sys_real）  

```sql
CREATE TABLE `sys_real` (
  `id` bigint NOT NULL COMMENT '唯一id',
  `ownerId` bigint DEFAULT NULL COMMENT '业主id',
  `realName` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '业主真实姓名',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '业主住址',
  `phoneNumber` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '手机号码',
  `createTime` datetime DEFAULT NULL COMMENT '创建日期',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`),
  KEY `realFK` (`ownerId`),
  CONSTRAINT `realFK` FOREIGN KEY (`ownerId`) REFERENCES `sys_owner` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='真实信息表';
```

- 小区表（sys_community）

```sql
CREATE TABLE `sys_community` (
  `id` bigint NOT NULL COMMENT '唯一id',
  `communityName` varchar(20) DEFAULT NULL COMMENT '小区名称',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='小区表';
```

- 公告表（sys_notice）

```sql
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `id` bigint unsigned NOT NULL COMMENT '唯一id',
  `headline` varchar(50) DEFAULT NULL COMMENT '标题',
  `notice` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '公告（MarkDown）',
  `briefInfo` varchar(255) DEFAULT NULL COMMENT '简略公告信息',
  `imageUrl` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `deleted` tinyint(1) DEFAULT NULL COMMENT '逻辑删除，1删除，0没删除',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='公告表';
```

- 角色表(sys_role)

```sql
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(0) NOT NULL COMMENT '唯一id',
  `roleName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色权限名',
  `adminId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '管理员id',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modifyTIme` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;
```

- 邻里圈表(sys_circle)

```sql
DROP TABLE IF EXISTS `sys_circle`;
CREATE TABLE `sys_circle` (
  `id` bigint NOT NULL COMMENT '唯一id',
  `ownerId` bigint DEFAULT NULL COMMENT '业主id',
  `message` varchar(255) DEFAULT NULL COMMENT '发言内容',
  `circleImagesUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '图片地址',
  `parentId` bigint DEFAULT NULL COMMENT '父评论的id',
  `deleted` tinyint(1) DEFAULT NULL COMMENT '逻辑删除',
  `createTime` datetime DEFAULT NULL COMMENT '发表时间',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `circleFK` (`ownerId`),
  CONSTRAINT `circleFK` FOREIGN KEY (`ownerId`) REFERENCES `sys_owner` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='圈子表';
```

- 报修表（sys_repair）

```sql
DROP TABLE IF EXISTS `sys_repair`;
CREATE TABLE `sys_repair` (
  `id` bigint NOT NULL COMMENT '唯一id',
  `ownerId` bigint DEFAULT NULL COMMENT '业主id',
  `imageUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片地址',
  `repairContext` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '报修内容说明',
  `repairState` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '报修状态',
  `employeeId` bigint DEFAULT NULL COMMENT '员工id',
  `deleted` tinyint(1) DEFAULT NULL COMMENT '逻辑删除',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `reFK` (`ownerId`),
  CONSTRAINT `reFK` FOREIGN KEY (`ownerId`) REFERENCES `sys_owner` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='报修表';
```

- 轮播图表（sys_banner）

```sql
DROP TABLE IF EXISTS `sys_banner`;CREATE TABLE `sys_banner` (  `id` bigint NOT NULL COMMENT '唯一id',  `bannerUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '轮播图地址',  `bannerContext` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '轮播图内容',  `deleted` tinyint(1) DEFAULT NULL COMMENT '逻辑删除，1删除，0没删除',  `createTime` datetime DEFAULT NULL COMMENT '创建时间',  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',  PRIMARY KEY (`id`) USING BTREE) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='轮播图表';
```

- 车辆信息表（sys_car）

```sql
DROP TABLE IF EXISTS `sys_car`;CREATE TABLE `sys_car` (  `id` bigint NOT NULL COMMENT '唯一id',  `carNumber` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '车牌号',  `carInfo` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '车辆信息',  `ownerId` bigint DEFAULT NULL COMMENT '业主id',  `deleted` tinyint(1) DEFAULT NULL COMMENT '逻辑删除',  `carImageUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '汽车图片',  `createTime` datetime DEFAULT NULL COMMENT '创建时间',  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',  PRIMARY KEY (`id`),  KEY `carFK` (`ownerId`),  CONSTRAINT `carFK` FOREIGN KEY (`ownerId`) REFERENCES `sys_owner` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='业主车辆表';
```

- 管理员表（sys_admin）

```sql
DROP TABLE IF EXISTS `sys_admin`;CREATE TABLE `sys_admin` (  `id` bigint NOT NULL COMMENT '唯一id',  `nickName` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '管理员名称',  `account` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '账户',  `roleId` bigint DEFAULT NULL COMMENT '角色id',  `createTime` datetime DEFAULT NULL COMMENT '创建时间',  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',  PRIMARY KEY (`id`) USING BTREE,  KEY `rolFK` (`roleId`),  CONSTRAINT `rolFK` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='管理员表';
```

- 密码表

```sql
DROP TABLE IF EXISTS `sys_cipher`;CREATE TABLE `sys_cipher` (  `id` bigint NOT NULL COMMENT '唯一id',  `cipher` varchar(255) DEFAULT NULL COMMENT '密码（MD5）',  `adminId` bigint DEFAULT NULL COMMENT '管理员id',  `createTime` datetime DEFAULT NULL COMMENT '创建时间',  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',  PRIMARY KEY (`id`),  KEY `cipherFK` (`adminId`),  CONSTRAINT `cipherFK` FOREIGN KEY (`adminId`) REFERENCES `sys_admin` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='密码表';
```

#### 2.3 测试数据

```sql
/*sys_owner测试数据*/INSERT INTO `sys_owner` VALUES (608994684236611584, 'o38rx4uzjqPDFNqZ-k3P-D9KPNE4', '无名', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTL3ASdruNTz4NN43ct4Fne9HPws5xL7TBLichmibic3zV90M2ezFrccAncFNmLOTvahlEfXYZE0xmGqw/132', NULL, 1, 'Wenzhou', 'Zhejiang', 'China', 0, '2021-07-04 09:28:33', NULL);-- Records of sys_adminINSERT INTO `sys_admin` VALUES (612285894673973248, '汤福锦', '489516067@qq.com', '2021-07-13 11:27:26', NULL);INSERT INTO `sys_admin` VALUES (612389480695218176, '郑濠杰', '1415495826@qq.com', '2021-07-13 18:18:50', NULL);-- Records of sys_bannerINSERT INTO `sys_banner` VALUES (612657139068321792, 'https://hutao.ink/c/community/owner/upLoadImages', '1', 1, '2021-07-14 12:01:50', NULL);INSERT INTO `sys_banner` VALUES (612657306169393152, 'http://hutao-ink.oss-cn-hangzhou.aliyuncs.com/ChMlWVzuIO-IIzCeAAG32cYGRCYAAKRegC3sFcAAbfx312.jpg', 'fasfasd', 1, '2021-07-14 12:02:30', '2021-07-14 13:33:57');INSERT INTO `sys_banner` VALUES (612657384523186176, 'http://hutao-ink.oss-cn-hangzhou.aliyuncs.com/3.jpg', '3', 0, '2021-07-14 12:02:49', NULL);INSERT INTO `sys_banner` VALUES (612657495315726336, 'http://hutao-ink.oss-cn-hangzhou.aliyuncs.com/4.jpg', '3', 0, '2021-07-14 12:03:15', NULL);INSERT INTO `sys_banner` VALUES (612675520110673920, 'http://hutao-ink.oss-cn-hangzhou.aliyuncs.com/1.jpg', 'fafd', 0, '2021-07-14 13:14:53', NULL);INSERT INTO `sys_banner` VALUES (613049581047267328, 'http://hutao-car.oss-cn-shanghai.aliyuncs.com/u=1988229486,613102416&fm=26&fmt=auto&gp=0.jpg', '1', 1, '2021-07-15 14:01:16', NULL);INSERT INTO `sys_banner` VALUES (613159298033664000, 'http://hutao-car.oss-cn-shanghai.aliyuncs.com/u=1988229486,613102416&fm=26&fmt=auto&gp=0.jpg', '1', 0, '2021-07-15 21:17:14', NULL);-- Records of sys_carINSERT INTO `sys_car` VALUES (612798180517888000, '浙F033YB', '小型汽车', 608997036406489088, 0, 'http://hutao-car.oss-cn-shanghai.aliyuncs.com/P10714-123841 (1).jpg', '2021-07-14 21:22:17', NULL);INSERT INTO `sys_car` VALUES (613133981516709888, '沪KR9888', '小型汽车', 608994684236611584, 1, 'http://hutao-car.oss-cn-shanghai.aliyuncs.com/clKJk1d8bvRcabbb338c3b1e78de4be810b6a018ef73.jpg', '2021-07-15 19:36:38', NULL);-- Records of sys_cipherINSERT INTO `sys_cipher` VALUES (612286238539792384, '698d51a19d8a121ce581499d7b701668', 612285894673973248, '2021-07-13 11:28:18', NULL);INSERT INTO `sys_cipher` VALUES (612389686425829376, 'c6f057b86584942e415435ffb1fa93d4', 612389480695218176, '2021-07-13 18:19:21', NULL);-- Records of sys_circleINSERT INTO `sys_circle` VALUES (609358681309855744, 608997036406489088, '楼主', 'string', 0, 1, '2021-07-05 09:34:57', NULL);INSERT INTO `sys_circle` VALUES (609358852638785536, 608994684236611584, '第一楼', 'string', 609358681309855744, 0, '2021-07-05 09:35:37', NULL);INSERT INTO `sys_circle` VALUES (609359013716836352, 608997036406489088, '楼主回复第一楼', 'string', 609358852638785536, 0, '2021-07-05 09:36:16', NULL);-- Records of sys_communityINSERT INTO `sys_community` VALUES (611557263207186432, '蓝钻天成', '2021-07-11 11:11:40', NULL);INSERT INTO `sys_community` VALUES (612651343131590656, '白马湖一号', '2021-07-14 11:41:18', NULL);-- Records of sys_ownerINSERT INTO `sys_owner` VALUES (608994684236611584, 'o38rx4uzjqPDFNqZ-k3P-D9KPNE4', '无名', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTL3ASdruNTz4NN43ct4Fne9HPws5xL7TBLichmibic3zV90M2ezFrccAncFNmLOTvahlEfXYZE0xmGqw/132', 611557263207186432, 1, 'Wenzhou', 'Zhejiang', 'China', 0, '2021-07-04 09:28:33', NULL);INSERT INTO `sys_owner` VALUES (608997036406489088, 'o38rx4n1fD6lfBmqeEUgECNQN9Uc', '渡己', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Tf9fFskssrrFEdOco3bbOzefdr9lzplWoowBGkKVic6Ka9Kp9Hu8A5Hib7hgvVEWeibicdMgViaAMxeZcsBv7kMaXfA/132', 612651343131590656, 1, 'Jiujiang', 'Jiangxi', 'China', 0, '2021-07-04 09:37:54', NULL);INSERT INTO `sys_owner` VALUES (612453973785722880, 'o38rx4moWnwXVQMjhLpYXNpNsXVo', '红莲之瞳', 'https://thirdwx.qlogo.cn/mmopen/vi_32/XwyPibpWesoPDBGm4Rmxht8a19sFBEteebMfkI0yadm5zZu3aXoIuc0UgAZawtjUrUKmO955dDEtKFzoQIrHSlQ/132', 612651343131590656, 1, '', 'Tokyo', 'Japan', 0, '2021-07-13 22:34:32', NULL);INSERT INTO `sys_owner` VALUES (612632224973864960, 'o38rx4m6b65FapMqCKoZt-cc4UCo', 'Cle⃰arLove⃰', 'https://thirdwx.qlogo.cn/mmopen/vi_32/lxNrV33vFAGwk2M1EhcBQ4QP8Ebic1Ne5Z2IdrAtcZRBO1206t2SgyWfD05mAl2WQsic030ZH7aLceU5Sa6CTwQQ/132', 612651343131590656, 1, '', 'Paris', 'France', 0, '2021-07-14 10:22:50', NULL);-- Records of sys_repairINSERT INTO `sys_repair` VALUES (609091637876244480, 'o38rx4uzjqPDFNqZ-k3P-D9KPNE4', 'http://hutao-ink.oss-cn-hangzhou.aliyuncs.com/lytJW9T6MK5d6bfdd51f73dc3366c1b1358a588ff590.jpg', '等价无穷小！！！！', '已处理', NULL, 1, '2021-07-04 15:53:48', NULL);INSERT INTO `sys_repair` VALUES (609146112259276800, 'o38rx4uzjqPDFNqZ-k3P-D9KPNE4', 'string', 'wowowo', '已处理', NULL, 0, '2021-07-04 19:30:16', NULL);
```

### 3. 接口设计

#### 业主模块接口设计

- **getOpenId**

>接口说明 ：向微信API发送请求获取该用户openId(微信用户唯一凭证) 
>
>接口地址 ：https://hutao.ink/c/community/owner/getOpenId  
>
>输入参数 ：
>
>```json
>{"code":"wx.login随机生成"}
>```
>
>返回值 ：
>
>```json
>{"openId":"微信用户唯一凭证"}
>```
>
>返回值说明 ：微信用户唯一凭证

- **postUserInfo** 

>接口说明 ：获取微信用户信息入库  
>
>接口地址 ：https://hutao.ink/c/community/owner/postUserInfo 
>
>输入参数 ：
>
>```json
>{	"city": "string",	"country": "string",	"gender": 0,	"imageUrl": "string",	"nickName": "string",	"openId": "string",	"province": "string"}
>```
>
>返回值 ：
>
>```json
>{"timestamp": "2021-07-16T06:56:55.367+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": "获取成功","path": "/owner/postUserInfo "}
>```
>
>返回值说明 ：无

- **sendSmsCode**

> 接口说明 ：绑定用户信息发送手机验证码  
>
> 接口地址 ：https://hutao.ink/c/community/owner/sendSmsCode
>
> 输入参数 ：
>
> ```json
> {"ownerId":"业主id","phoneNumber":"手机号码"}
> ```
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T06:56:55.367+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": "发送成功","path": "/owner/updateBindInfo"}
> ```
>
> 返回值说明 ：发送手机验证码成功

<div style="page-break-after: always;"></div>

- **judgeCode**

> 接口说明 ：输入手机验证码验证 
>
> 接口地址 ：https://hutao.ink/c/community/owner/judgeCode
>
> 输入参数 ：
>
> ```json
> {"ownerId":"业主id","code":"手机收到的验证码"}
> ```
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T06:56:55.367+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": "验证成功","path": "/owner/judgeCode"}
> ```
>
> 返回值说明 ：验证成功

- **judgeOwnerState**

> 接口说明 ：判断用户绑定状态
>
> 接口地址 ：https://hutao.ink/c/community/owner/judgeOwnerState
>
> 输入参数 ：
>
> ```json
> {"ownerId:":"业主id"}
> ```
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T06:56:55.367+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": "验证成功","path": "/owner/judgeOwnerState"}
> ```
>
> 返回值说明 ：获取成功

<div style="page-break-after: always;"></div>

- **bindOwnerInfo**

> 接口说明 ：绑定微信用户信息 
>
> 接口地址 ：https://hutao.ink/c/community/owner/bindOwnerInfo
>
> 输入参数 ：
>
> ```json
> {"ownerId":"业主id","address":"住址","realName":"真实姓名"}
> ```
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T06:56:55.367+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": "绑定成功","path": "/owner/bindOwnerInfo"}
> ```
>
> 返回值说明 ：绑定成功

- **getOwnerRealInfo**

> 接口说明 ：获取业主真实信息和住址
>
> 接口地址 ：https://hutao.ink/c/community/owner/getOwnerRealInfo
>
> 输入参数 ：
>
> ```json
> {"ownerId":"业主id"}
> ```
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-05T11:11:55.126+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": {"nickName": "渡己","imageUrl": "https://thirdwx.qlogo.cn/mmopen/vi_32/Tf9fFskssrrFEdOco3bbOzefdr9lzplWoowBGkKVic6Ka9Kp9Hu8A5Hib7hgvVEWeibicdMgViaAMxeZcsBv7kMaXfA/132","realName": "汤福锦","address": "杭科院竹B425","phoneNumber": "19941395047"},"path": "/owner/getOwnerRealInfo"}
> ```
>
> 返回值说明：获取成功

<div style="page-break-after: always;"></div>

- **updateBindInfo**

> 接口说明 ：业主修改绑定信息操作，发送验证码
>
> 接口地址 ：https://hutao.ink/c/community/owner/updateBindInfo
>
> 输入参数 ：
>
> ```json
> { "ownerId" : '业主id',"phoneNumber":"手机号码"}
> ```
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T06:56:55.367+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": "发送成功","path": "/owner/updateBindInfo"}
> ```
>
> 返回值说明 ：绑定成功

- **judgeCodeUpdate**

> 接口说明 ：业主修改绑定信息，验证验证码
>
> 接口地址 ：https://hutao.ink/c/community/owner/judgeCodeUpdate
>
> 输入参数 ：
>
> ```json
> {'ownerId' : "业主id",'phoneNumber' : '手机号码',"address": "住址","realName": "真实姓名","code":"手机接收验证码"}
> ```
>
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T06:58:42.658+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": "修改绑定信息成功","path": "/owner/judgeCodeUpdate"}
> ```
>
> 返回值说明 ：发送验证码成功

<div style="page-break-after: always;"></div>

- **upLoadImages**

> 接口说明 ：文件上传
>
> 接口地址 ：https://hutao.ink/c/community/owner/upLoadImages
>
> 输入参数 ：
>
> ```json
> {multipart/form-data:"文件"}
> ```
>
> 返回值 ：http://hutao-ink.oss-cn-hangzhou.aliyuncs.com/gh_ce1ad16e6559_258 (4).jpg
>
> 返回值说明 ：上传成功

#### 公告模块接口设计

- **getAllNotice**

>接口说明 ：获取所有公告信息  
>
>接口地址 ：https://hutao.ink/c/community/notice/getAllNotice  
>
>输入参数 ：null  
>
>返回值 ：
>
>```json
>{"timestamp": "2021-07-16T07:28:18.878+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": [{"id": "610835663788851200","headline": "关于防止破坏小区绿化的告示","notice": "尊敬的各位业主、广大住户：您们好！为了保持小区内的绿化和美观，为大家提供舒适健康的生活环境，请大家爱护小区内绿化环境，请勿践踏草坪和损坏树木；如果发现有以上行为者，将处以2000元人民币罚款的处罚并追究相关责任。望各位住户相互转告，特此告示！同时呼吁广大居民爱护环境，共同营造美好家园，也希望各位居民支持我们的工作，互相监督。 谢谢大家！","briefInfo": "关于防止破坏小区绿化的告示","imageUrl": "https://img1.baidu.com/it/u=1211793831,27508219&fm=15&fmt=auto&gp=0.jpg","deleted": 0,"createTime": "2021-07-09T03:23:57.000+00:00","modifyTime": null},"path": "/notice/getAllNotice"}
>```
>
>返回值说明 ：获取成功

<div style="page-break-after: always;"></div>

- **getNewestBriefInfo**

> 接口说明 ：获取最新公告简略信息  
>
> 接口地址 ：https://hutao.ink/c/community/notice/getNewestBriefInfo  
>
> 输入参数 ： null  
>
> 返回值 ：200
>
> ```json
> {"timestamp": "2021-07-16T07:48:54.101+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": "2021百名最美生态环保志愿者（16）丨郭玉荣：长期致力于生态环境保护等公益事业 ","path": "/notice/getNewestBriefInfo"}
> ```
>
> 返回值说明 ：获取成功

- **releaseNotice**

> 接口说明 ：发布公告  
>
> 接口地址 ：https://hutao.ink/c/community/notice/releaseNotice 
>
> 输入参数 ：
>
> ```json
> {	"briefInfo": "简略信息",	 "headline": "标题",	 "imageUrl": "图片地址",	 "notice": "公告内容"}
> ```
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T07:54:59.795+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": "发布公告成功","path": "/notice/releaseNotice"}
> ```
>
> 返回值说明 ：发布成功

- **updateNotice**

> 接口说明 ：修改公告  
>
> 接口地址 ：https://hutao.ink/c/community/notice/updateNotice 
>
> 输入参数 ：
>
> ```json
> {"briefInfo": "简略信息",	 "headline": "标题",	 "imageUrl": "图片地址",	 "notice": "公告内容"}
> ```
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T08:02:40.697+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": "修改公告成功","path": "/notice/updateNotice"}
> ```
>
> 返回值说明 ：修改成功

- **deleteNotice**

> 接口说明 ：逻辑删除公告  
>
> 接口地址 ：https://hutao.ink/c/community/notice/releaseNotice 
>
> 输入参数 ：
>
> ```json
> {"id":"公告id"}
> ```
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T07:59:54.946+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": "逻辑删除成功","path": "/notice/deleteNotice"}
> ```
>
> 返回值说明 ：删除成功

#### 员工模块接口设计

- **getOwnerRepairInfo**

>接口说明 ：修理员查看业主报修信息  
>
>接口地址 ：https://hutao.ink/c/community/employee/getOwnerRepairInfo 
>
>输入参数 ：null  
>
>返回值 ：
>
>```json
>{"timestamp": "2021-07-16T10:32:44.739+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": [{"id": "610819655271333888","imageUrl": "http://hutao-ink.oss-cn-hangzhou.aliyuncs.com/tzpxHBuZntjof954124446e1adce0618eb097980825b.jpg","repairContext": "水管破裂破裂","repairState": "已处理","createTime": "2021-07-09T02:20:20.000+00:00","phoneNumber": "15988766162"}],"path": "/employee/getOwnerRepairInfo"}
>```
>
>返回值说明 ：获取成功

- **resolveRepair**

>接口说明 ：修理员处理报修信息  
>
>接口地址 ：https://hutao.ink/c/community/employee/resolveRepair 
>
>输入参数 ：
>
>```json
>{"repairId":"报修信息id","employeeId":"员工id"}
>```
>
>返回值 ：
>
>```json
>{"timestamp": "2021-07-16T10:38:27.196+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": "处理成功","path": "/employee/resolveRepair"}
>```
>
>返回值说明 ：处理成功

<div style="page-break-after: always;"></div>

- **judgeEmployee**

> 接口说明 ：判断是否为员工或者管理员 
>
> 接口地址 ：https://hutao.ink/c/community/employee/judgeEmployee
>
> 输入参数 ：
>
> ```json
> {"ownerId","用户id"}
> ```
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T10:41:54.088+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": true,"path": "/employee/judgeEmployee"}
> ```
>
> 返回值说明 ：true为真，false为假

#### 邻里圈模块接口设计

- publishedCircle

> 接口说明 ：发表圈子  
>
> 接口地址 ：https://hutao.ink/c/community/circle/publishedCircle 
>
> 输入参数 ：
>
> ```json
> {"circleImagesUrl": "string","message": "string","parentId": 0 ,#父id，第一层默认0"ownerId":"业主id"}
> ```
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T10:41:54.088+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": 发布成功,"path": "/circle/publishedCircle"}
> ```
>
> 返回值说明 ：发表成功

<div style="page-break-after: always;"></div>

- **deleteCircle**

> 接口说明 ：逻辑删除圈子  
>
> 接口地址 ：https://hutao.ink/c/community/circle/deleteCircle 
>
> 输入参数 ：
>
> ```json
> {"id":"圈子id"}
> ```
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T10:41:54.088+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": 删除成功,"path": "/circle/deleteCircle"}
> ```
>
> 返回值说明 ：删除成功

<div style="page-break-after: always;"></div>

- **getChildCircleByParentId**

> 接口说明 ：通过父id查询子圈子  
>
> 接口地址 ：https://hutao.ink/c/community/circle/getChildCircleByParentId
>
> 输入参数 ：
>
> ```json
> {"id":"父圈子id"}
> ```
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T11:11:02.347+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": [{"id": "609358852638785536","ownerId": null,"parentId": "609358681309855744","childPoster": {  "id": "608994684236611584",  "nickName": "无名",  "imageUrl": "https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTL3ASdruNTz4NN43ct4Fne9HPws5xL7TBLichmibic3zV90M2ezFrccAncFNmLOTvahlEfXYZE0xmGqw/132"},"originalPoster": null,"message": "第一楼","circleImagesUrl": "string","createTime": "2021-07-05T01:35:37.000+00:00","childrenList": [  {"id": "609359013716836352",    "ownerId": null,    "parentId": "609358852638785536",    "childPoster": {      "id": "608997036406489088",      "nickName": "渡己",      "imageUrl": "https://thirdwx.qlogo.cn/mmopen/vi_32/Tf9fFskssrrFEdOco3bbOzefdr9lzplWoowBGkKVic6Ka9Kp9Hu8A5Hib7hgvVEWeibicdMgViaAMxeZcsBv7kMaXfA/132"},    "originalPoster": null,    "message": "楼主回复第一楼",    "circleImagesUrl": "string",    "createTime": "2021-07-05T01:36:16.000+00:00",    "childrenList": null,    "thirdList": null}],"thirdList": null}],"path": "/circle/getChildCircleByParentId"}
> ```
>
> 返回值说明 ：二级圈子查询成功

<div style="page-break-after: always;"></div>

- **getThirdCircleByChildId**

> 接口说明 ：通过子圈子id查找三级圈子
>
> 接口地址 ：https://hutao.ink/c/community/circle/getThirdCircleByChildId
>
> 输入参数 ：
>
> ```json
> {"id":"子圈子id"}
> ```
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T11:14:46.016+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": [{"id": "609359013716836352","ownerId": null,"parentId": "609358852638785536","childPoster": {  "id": "608997036406489088",  "nickName": "渡己",  "imageUrl": "https://thirdwx.qlogo.cn/mmopen/vi_32/Tf9fFskssrrFEdOco3bbOzefdr9lzplWoowBGkKVic6Ka9Kp9Hu8A5Hib7hgvVEWeibicdMgViaAMxeZcsBv7kMaXfA/132"},"originalPoster": null,"message": "楼主回复第一楼","circleImagesUrl": "string","createTime": "2021-07-05T01:36:16.000+00:00","childrenList": null,"thirdList": null}],"path": "/circle/getThirdCircleByChildId"}
> ```
>
> 返回值说明 ：获取三级圈子成功

- **getOwnerCircle**

> 接口说明 ：业主端获取所有圈子展示
>
> 接口地址 ：https://hutao.ink/c/community/owner/getOwnerCircle
>
> 输入参数 ：null
>
> 返回值 ：
>
> ```json
> .....
> ```
>
> 返回值说明 ：略

#### 报修模块接口设计

- **postRepairInfo**

> 接口说明 ：业主提交报修信息
>
> 接口地址 ：https://hutao.ink/c/community/repair/postRepairInfo
>
> 输入参数 ：
>
> ```json
> {"ownerId":"业主id""imageUrl": "string","repairContext": "string"}
> ```
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T11:27:55.986+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": "报修提交成功","path": "/repair/postRepairInfo"}
> ```
>
> 返回值说明 ：提交成功

- **deleteRepair**

> 接口说明 ：业主撤回报修
>
> 接口地址 ：https://hutao.ink/c/community/repair/deleteRepair
>
> 输入参数 ：
>
> ```json
> {"id":"报修id"}
> ```
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T11:27:55.986+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": "撤回成功","path": "/repair/postRepairInfo"}
> ```
>
> 返回值说明 ：撤回成功

<div style="page-break-after: always;"></div>

- **getHistoryRepair**

> 接口说明 ：业主获取报修记录
>
> 接口地址 ：https://hutao.ink/c/community/repair/getHistoryRepair
>
> 输入参数 ：
>
> ```json
> {"ownerId":"业主id"}
> ```
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T11:32:25.513+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": [{"imageUrl": "string","repairContext": "string","repairState": "待处理","modifyTime": null}],"path": "/repair/getHistoryRepair"}
> ```
>
> 返回值说明 ：获取成功

#### 车辆模块接口设计

- **addOwnerCar**

> 接口说明 ：业主添加所属车辆
>
> 接口地址 ：https://hutao.ink/c/community/car/addOwnerCar
>
> 输入参数 ：
>
> ```json
> {"multipart/form-data":"带有车牌号的图片","ownerId":"业主id"}
> ```
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T11:42:17.341+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": "业主添加所属车辆成功","path": "/car/addOwnerCar"}
> ```
>
> 返回值说明 ：获取成功

<div style="page-break-after: always;"></div>

- **getOwnerCarInfo**

> 接口说明 ：获取业主车辆信息
>
> 接口地址 ：https://hutao.ink/c/community/car/getOwnerCarInfo
>
> 输入参数 ：
>
> ```json
> {"ownerId":"业主id"}
> ```
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T11:45:02.508+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": [{"carNumber": "浙F033YB","carInfo": "小型汽车","carImageUrl": "http://hutao-car.oss-cn-shanghai.aliyuncs.com/P10714-123841 (1).jpg","createTime": "2021-07-16T11:42:17.000+00:00"}],"path": "/car/getOwnerCarInfo"}
> ```
>
> 返回值说明 ：获取成功

- **judgeCarNumber**

> 接口说明 ：判断是否是本小区的车
>
> 接口地址 ：https://hutao.ink/c/community/car/judgeCarNumber
>
> 输入参数 ：
>
> ```json
> {"multipart/form-data":"带有车牌号的图片"}
> ```
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T11:48:02.210+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": false,"path": "/car/judgeCarNumber"}
> ```
>
> 返回值说明 ：true为是，false否

<div style="page-break-after: always;"></div>

- **licensePlateIdentification**

> 接口说明 ：车牌号识别
>
> 接口地址 ：https://hutao.ink/c/community/car/licensePlateIdentification
>
> 输入参数 ：
>
> ```json
> {"multipart/form-data":"带有车牌号的图片"}
> ```
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T11:49:45.420+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": {"clsName": "小型汽车","txt": "京A88888"},"path": "/car/licensePlateIdentification"}
> ```
>
> 返回值说明 ：识别成功

#### 轮播图模块接口设计

- **addBanner**

> 接口说明 ：添加轮播图
>
> 接口地址 ：https://hutao.ink/c/community/banner/addBanner
>
> 输入参数 ：
>
> ```json
> {"bannerContext": "string","bannerUrl": "string"}
> ```
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T11:55:07.803+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": "添加轮播图成功","path": "/banner/addBanner"}
> ```
>
> 返回值说明 ：添加轮播图成功

<div style="page-break-after: always;"></div>

- **deleteBanner**

> 接口说明 ：删除轮播图
>
> 接口地址 ：https://hutao.ink/c/community/banner/deleteBanner
>
> 输入参数 ：
>
> ```json
> {"id":"轮播图id"}
> ```
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T11:58:06.142+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": "逻辑删除轮播图成功","path": "/banner/deleteBanner"}
> ```
>
> 返回值说明 ：删除成功

- **getAllBanner**

> 接口说明 ：获取所有轮播图
>
> 接口地址 ：https://hutao.ink/c/community/banner/getAllBanner
>
> 输入参数 ：null
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T11:59:11.066+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": [{"id": "612657495315726336","bannerUrl": "http://hutao-ink.oss-cn-hangzhou.aliyuncs.com/4.jpg","bannerContext": "3","deleted": 0,"createTime": "2021-07-14T04:03:15.000+00:00","modifyTime": null},],"path": "/banner/getAllBanner"}
> ```
>
> 返回值说明 ：获取成功

<div style="page-break-after: always;"></div>

- **updateBanner**

> 接口说明 ：修改轮播图
>
> 接口地址 ：https://hutao.ink/c/community/banner/updateBanner
>
> 输入参数 ：
>
> ```json
> {"id":"轮播图id"}
> ```
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T11:58:06.142+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": "修改轮播图成功","path": "/banner/updateBanner"}
> ```
>
> 返回值说明 ：修改成功

#### 管理员模块接口设计

- **adminLogin**

> 接口说明 ：管理员登录
>
> 接口地址 ：https://hutao.ink/c/community/admin/adminLogin
>
> 输入参数 ：
>
> ```json
> {"account":"账号","cipher":"密码"}
> ```
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T12:11:38.668+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": {"saToken": "e8bdb333-338f-47b9-9285-8a504c7952ce","info": "账号密码正确"},"path": "/admin/adminLogin"}
> ```
>
> 返回值说明 ：验证账号密码，并给该账号发送验证码

- **judgeAdminLogin**

> 接口说明 ：管理员登录验证码验证
>
> 接口地址 ：https://hutao.ink/c/community/admin/judgeAdminLogin
>
> 输入参数 ：
>
> ```json
> {"account":"账号","code":"该账号接收到的验证码"}
> ```
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T12:15:41.299+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": "验证成功","path": "/admin/judgeAdminLogin"}
> ```
>
> 返回值说明 ：验证成功

<div style="page-break-after: always;"></div>

- **putAdminCipher**

> 接口说明 ：管理员修改密码
>
> 接口地址 ：https://hutao.ink/c/community/admin/putAdminCipher
>
> 输入参数 ：
>
> ```json
> {"account":"账号","newCipher":"新密码","oldCipher":"原密码"}
> ```
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T12:15:41.299+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": "修改成功","path": "/admin/putAdminCipher"}
> ```
>
> 返回值说明 ：修改成功

- **getAllOwnerRealInfo**

> 接口说明 ：获取所有用户真实信息
>
> 接口地址 ：https://hutao.ink/c/community/admin/getAllOwnerRealInfo
>
> 输入参数 ：null
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T12:21:35.622+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": [{"id": "613471979441635328","nickName": "无名","imageUrl": "https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTL3ASdruNTz4NN43ct4Fne9HPws5xL7TBLichmibic3zV90M2ezFrccAncFNmLOTvahlEfXYZE0xmGqw/132","realName": "郑濠杰","address": "第3幢302室","phoneNumber": "15988766162","communityName": "白马湖一号","createTime": "2021-07-04T01:28:33.000+00:00"},....],"path": "/admin/getAllOwnerRealInfo"}
> ```
>
> 返回值说明 ：获取成功

- **getAllOwnerCar**

> 接口说明 ：获取所有用户姓名和车辆信息
>
> 接口地址 ：https://hutao.ink/c/community/admin/getAllOwnerCar
>
> 输入参数 ：null
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T12:23:12.687+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": [{"id": "613133981516709888","realName": "郑濠杰","imageUrl": "https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTL3ASdruNTz4NN43ct4Fne9HPws5xL7TBLichmibic3zV90M2ezFrccAncFNmLOTvahlEfXYZE0xmGqw/132","carNumber": "沪KR9888","carInfo": "小型汽车","carImageUrl": "http://hutao-car.oss-cn-shanghai.aliyuncs.com/clKJk1d8bvRcabbb338c3b1e78de4be810b6a018ef73.jpg"}，......],"path": "/admin/getAllOwnerCar"}
> ```
>
> 返回值说明 ：获取成功

<div style="page-break-after: always;"></div>

- **deleteOwnerCarInfo**

> 接口说明 ：删除用户姓名和车辆信息
>
> 接口地址 ：https://hutao.ink/c/community/admin/deleteOwnerCarInfo
>
> 输入参数 ：
>
> ```json
> {"carId":"汽车id"}
> ```
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T12:23:12.687+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": [{"id": "613133981516709888","realName": "郑濠杰","imageUrl": "https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTL3ASdruNTz4NN43ct4Fne9HPws5xL7TBLichmibic3zV90M2ezFrccAncFNmLOTvahlEfXYZE0xmGqw/132","carNumber": "沪KR9888","carInfo": "小型汽车","carImageUrl": "http://hutao-car.oss-cn-shanghai.aliyuncs.com/clKJk1d8bvRcabbb338c3b1e78de4be810b6a018ef73.jpg"}，......],"path": "/admin/getAllOwnerCar"}
> ```
>
> 返回值说明 ：获取成功

- **exitLogin**

> 接口说明 ：退出登录
>
> 接口地址 ：https://hutao.ink/c/community/admin/exitLogin
>
> 输入参数 ：null
>
> 返回值 ：
>
> ```json
> {"timestamp": "2021-07-16T12:27:09.582+00:00","status": 200,"info": "请求被成功地完成，所请求的资源发送到客户端。","details": "The request is successfully completed and the requested resource is sent to the client.","message": "退出登录成功","path": "/admin/exitLogin"}
> ```
>
> 返回值说明 ：退出成功
