## 应用目标
使用springboot进行开发搭建一个web版的社区系统

 - springboot 2.1.5.RELEASE
 - lombok
 - Flyway

## 数据库
数据库脚本位置：resource/db/migration/    
通过Flyway是来对数据库版本进行管理    
官方网站：https://flywaydb.org/

加载数据库文件：    
1. 在`application.properties`文件中配置Flyway要加载的SQL脚本位置。
    ```properties
    flyway.locations=classpath:/db/migration
    ```
2. 使用mvn命令
    ```shell
    bar> mvn flyway:migrate
    ```

## 使用
 1. 下载到本地
 1. 解压到磁盘，使用idea导入项目
 1. 等待项目加载依赖
 1. 修改`application.properties`的配置为自己的
     - github.client_secret
 1. 运行 `src/java/pers.sfl.Springboot01Application.class`

 ## 项目完成的功能
- [x] 使用github授权登录
- [x] 发布问题
- [x] 分页功能
- [x] 修改发布的问题
- [ ]  ...