#Evolto项目概述

##1. 总体说明
###1.1 git commit
    git commit 格式：
        date: yyyy-MM-dd (HH:mm:ss)
        author: 
        description: feat-
    描述必须要有标识：
        feat:     新的功能
        fix:      修复Bug
        docs:     只有文档变更
        style:    空格, 分号等格式修复
        refactor: 代码重构，注意和特性、修复区分开
        perf:     提升性能
        test:     添加测试
        chore:    开发工具变动(构建、脚手架工具等)

##2. doc模块说明
###2.1 swagger2
###2.2 swagger3

##3. util模块说明

##4. dataSource模块说明
###4.1 通用实体标识
        在org.evolto.datasource.model包下有一些通用实体接口，且datasource模块在pojo子包提供了他们的默认实现。
    这些实现是最原始的、未添加任何注解的实体类。
        而persistence模块中jpa、mybatis等子模块都分别以各模块的风格实现了对应的实体类，仅当我们需要拓展一种新的
    持久层框架时，才需实现通用实体接口来适配新的持久层框架。

##5. persistence模块说明
###5.1 mybatis模块说明
####5.1.1 MyBatisPlusMetaObjectHandler 与 BaseEntity
    MyBatisPlusMetaObjectHandler类通过实现MetaObjectHandler接口来配置了MyBatisPlus的自动填充插件，
    以下为配置策略：
* createTime 在insert操作中插入当前时间
* createUid 在insert操作中插入当前SessionUserId
* updateTime 在insert、update操作中插入当前时间
* updateUid 在insert、update操作中插入当前SessionUserId
* delStatus  在insert操作中插入默认值0
  

    BaseEntity类是一个通用业务字段类，抽取了所有核心业务表中除以上5个字段以外还有id（主键）、sort_index（排序号）共七个字段，
    因此所有核心业务表映射的实体类都应该继承BaseEntity类。
    同时，BaseEntity类中也实现了主键id的插入策略和自定义自动填充策略。
**BaseEntity类只适用于使用了MyBatisPlus的项目**
    

##6. web模块说明