# 学生管理系统

#### 介绍
学生管理系统

#### 软件架构
```
students_springboot
├─base
│   ├─BaseController 返回交互json格式数据
│   ├─BaseEntity 实体类
│   ├─BaseMapper 实现单表sql操作
│   ├─BasePage 分页
│   ├─Result 返回成功或失败结果
│   ├─ResultCode 枚举
│
├─constant
│   ├─ConstantConfig 存放常量数据
│
├─controller
│   ├─BuildngController 宿舍管理接口
│   ├─LoginController 登录，注销，修改密码接口
│   ├─ScoreController 成绩管理接口
│   ├─StudentController 学生管理接口
│   ├─TestController 测试接口
│   ├─UserController 登录用户管理接口
│
├─entity
│   ├─bean
│   │   ├─EditScoreBean 添加、修改学生成绩的实体类
│   │   ├─ScoreBean 学生成绩列表查询的实体类
│
│   ├─vo
│   │   ├─BuildingVo 学生宿舍列表查询的查询条件实体类
│   │   ├─LoginVo 登录信息的实体类
│   │   ├─ScoreVo 成绩列表查询的查询条实例类
│   │   ├─StudentVo 学生列表查询的查询条件实例类
│   │   ├─UserVo 用户列表查询的查询条件实体类
│
│   ├─Building 宿舍表实体类
│   ├─Score 成绩表实体类
│   ├─Student 学生表实体类
│   ├─Subject 科目表实体类
│   ├─User 用户表实体类
│
├─mapper
│   ├─BuildingMapper 宿舍表数据库交互接口
│   ├─ScoreMapper 成绩表数据库交互接口
│   ├─StudentMapper 学生表数据库交互接口
│   ├─SubjectMapper 科目表数据库交互接口
│   ├─UserMapper 用户表数据库交互接口
│
├─redis
│   ├─FastJsonWraper 定义方法
│   ├─GenericFastJson2JsonRedisSerializer 实现RedisSerializer
│   ├─RedisConfig 配置初始化
│   ├─RedisTemplateUtil 工具类
│
├─service
│   ├─impl
│   │   ├─BuildingServiceImpl 宿舍管理逻辑实现类
│   │   ├─ScoreServiceImpl 成绩管理逻辑实现类
│   │   ├─StudentServiceImpl 学生管理逻辑实现类
│   │   ├─SubjectServiceImpl 科目管理逻辑实现类
│   │   ├─UserServiceImpl 用户管理逻辑实现类
│
│   ├─BuildingService 宿舍逻辑接口
│   ├─ScoreService 成绩逻辑接口
│   ├─StudentService 学生逻辑接口
│   ├─SubjectService 科目逻辑接口
│   ├─UserService 用户逻辑接口
│
├─utils
│   ├─MD5Util 加密解密工具类
│
├─ServletInitializer web容器部署
│
├─StudentsSpringbootApplication 项目启动类
│
├─resources
│   ├─mapper 数据库sql
│   ├─static 静态文件
│   ├─templates html页面
│   ├─application.yml 配置文件
│   ├─application-dev.yml 开发环境配置文件
│   ├─application-pro.yml 生产环境配置文件
│   ├─banner.txt 启动图标
│
├─test 存放测试类
```

#### 使用教程

1.  新建springboot+maven项目
2.  引入maven仓库
3.  配置jdk
4.  配置mysql数据库

#### 技术点

1.  前端

技术|名称|官网|备注
---|---|---|---
layui|开源模块化前端UI框架|https://www.layuiweb.com/index.htm|
jQuery|JavaScript库|https://jquery.com/|
JavaScript|web编程||

2.  后台

技术|名称|官网|备注
---|---|---|---
springboot|框架||
thymeleaf|页面模板引擎||
pagehelper|mybatis的物理分页插件||
mybatis-plus|MyBatis-Plus (opens new window)（简称 MP）|https://baomidou.com/|

3.  jar

名称|版本|备注
---|---|---
spring-boot-starter-parent|2.6.7|
spring-boot-starter-data-jpa|2.6.7|
spring-boot-starter-data-redis|2.6.7|
spring-boot-starter-thymeleaf|2.6.7|模板引擎
spring-boot-starter-web|2.6.7|
mysql-connector-java|8.0.28|数据库驱动
lombok|1.18.24|
spring-boot-starter-tomcat|2.6.7|
spring-boot-starter-test|2.6.7|
mybatis-plus-boot-starter|3.4.2|
commons-lang3|3.4|
commons-lang|2.6|
commons-io|2.4|
fastjson|1.2.4|
pagehelper-spring-boot-starter|1.3.0|

#### 开发环境

1.  jdk1.8
2.  Apache-Maven
3.  MySQL8.0
4.  Redis
5.  idea

#### 部署流程
1.  将doc中的students_db.sql文件在数据库中执行
2.  修改application.yml配置文件，将配置信息改为dev
3.  修改application-dev.yml配置文件，将里面的数据库连接改为自己本地数据库的地址
4.  run StudentsSpringbootApplication.java进行项目启动
5.  访问路径为http://ip:端口/login

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
