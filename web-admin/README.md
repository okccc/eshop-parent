## 项目准备
```shell
# 项目开发步骤：创建module - 修改pom.xml - 编写application.yml - 启动类 - 开发业务接口
# 接口就是一个Http请求,规定了请求方式、请求路径、请求参数、响应结果,前后端分离项目数据传输格式是JSON
# 快速生成接口的测试类：先导入test启动器 - 选中UserMapper - 右键 - Go To - Test
# 开发可以使用swagger接口文档自测,实现登录功能之后就可以和前端页面联调

# eshop-parent：电商项目父工程,统一管理项目依赖,打包方式为pom
    # model：实体类模块
    # common：通用功能模块
    # log：日志模块
    # web-admin：后台管理系统的后端服务(单体架构,MybatisPlus版本,web-manager是Mybatis版本)
    # service-gateway：网关服务
    # service：前台用户系统的后端服务(微服务架构)
        # service-product：商品服务
        # service-user：用户服务
        # service-cart：购物车服务
        # service-order：订单服务
        # service-pay：支付服务
    # service-client：远程调用服务

# 配置pom.xml,引入相关依赖

# 导入eshop.sql,创建项目中使用的所有表

# model模块
# 项目中涉及到的实体类有三种
# 前端请求参数实体类：定义在dto包(Data Transfer Object)
# 映射数据库表实体类：定义在entity包,通常和数据库表名一致
# 后端响应结果实体类：定义在vo包(View Object)
# 实体类的公共字段id,create_time,update_time,is_deleted可以抽取到基类统一管理,其它实体类继承该基类
# 实体类的状态字段status和类型字段type全部使用枚举类型,方便维护代码

# 跨域请求：通过一个域的javascript脚本和另外一个域的内容进行交互
# 同源(域)策略：是浏览器最核心也最基本的安全功能,会阻止跨域请求
# 域信息：http://localhost:8080/ssm 协议、域名、端口号必须全部相同,有一个不同就会存在跨域问题
# CORS(Cross-origin Resource Sharing)：是跨域的一种解决方案,服务器可以选择是否允许跨域请求访问到它们的资源
# 后端服务器开启跨域支持：a.给每个Controller添加@CrossOrigin  b.在配置类添加跨域请求(推荐)
```

## 后台管理系统 - 前端工程搭建
```shell
# 1.安装nodejs,推荐16及以上版本,node.js是运行前端程序的服务器,node -v查看版本,类似java的tomcat
https://nodejs.org/download/release/v16.16.0/

# 2.npm(Node Package Manager)是node.js的包管理工具,npm -v查看版本,类似java的maven
# 配置阿里镜像
npm config set registry https://registry.npmjs.org/
# 更新npm版本
npm install -g npm@9.6.6

# 3.安装vscode
# 创建工作区：先创建空文件夹web-admin - 使用vscode打开 - 文件 - 将工作区另存为 - 保存
# 下载项目
git clone https://github.com/huzhushan/vue3-element-admin.git
# 进入项目目录
cd vue3-element-admin
# 安装项目依赖
npm install --registry=https://registry.npm.taobao.org
# 启动服务,打开页面
npm start

# 安装echarts,订单统计图表展示
npm install echarts --save
```

## 后台管理系统 - 后台工程搭建
```shell
# 权限管理
# 用户表和角色表是多对多关系,角色表和菜单表也是多对多关系

# MinIO是一款免费的对象存储服务器,可以存储图片、语音、视频等非结构化数据
# MinIO官网地址：https://www.minio.org.cn/docs/minio/macos/index.html
# 安装：brew install minio/stable/minio
# 启动：minio server /Users/okc/modules/minio  服务器存放上传文件的目录
# 登录：http://127.0.0.1:9000  minioadmin/minioadmin
# 已上传图片到minio服务器,但是前端页面没有访问权限：Administrator - Buckets - Summary - Access Policy - Public

# 商品管理
# 分类、品牌、分类品牌、商品规格、商品单元这些接口最终都是为商品接口服务
# EasyExcel是一个快速、简洁、解决大文件内存溢出的Excel处理工具
# EasyExcel官网地址：https://easyexcel.opensource.alibaba.com/docs/current/
# 提高操作效率：手动逐条添加或修改数据费时费力且容易出错,此时就可以将大量数据从Excel导入到系统中
# 实现数据备份：可以将系统中的数据导出为Excel,防止数据丢失
# 方便部门协作：不同部门会使用不同的工具进行数据处理,导入导出Excel方便转换和共享数据

# 订单管理
# 后台管理系统可以对订单数据进行统计,制定图形化报表用于数据分析
# Echarts是百度开发的基于JavaScript的开源可视化图表库,提供了丰富的图表类型、数据统计分析、动态数据更新、多维数据展示等功能
# Echarts支持多种图表类型,如线图、柱状图、饼图、雷达图、散点图等,还支持动态数据显示、图表联动、混搭图表等复杂功能
# ECharts易于上手,提供了丰富的API以及完善的文档和示例,使得开发人员可以更快速、更便捷地使用它做数据可视化
# ECharts官网地址：https://echarts.apache.org/zh/index.html

# Spring Task是Spring框架中的一个定时任务调度模块,提供了一种简单的方式来实现基于时间的调度任务
# 通过配置或注解的方式定义定时任务,设置任务的执行时间、间隔周期、触发条件,可以执行数据备份、缓存清理、邮件发送等操作
# 如果前端页面每次展示图表数据都去查询order_info做分组聚合性能很查,可以在夜间配置定时任务将订单统计数据写入结果表,然后每次查这个结果表就行
```

## 前台用户系统 - 前端工程搭建
```shell
# Nginx服务器三大功能：1.动静分离 2.反向代理 3.负载均衡
# 动静分离：前端发送http请求时,静态资源直接从nginx获取,动态资源调用java接口从tomcat获取
# 正向代理：代理的是客户端,proxy和client同属一个LAN,代表客户端发送请求,隐藏客户端地址 --> 翻墙、爬虫
# 反向代理：代理的是服务器,proxy和server同属一个LAN,代表服务器响应结果,隐藏服务器地址 --> 负载均衡
# 负载均衡：集群模式下,nginx根据策略(轮询/权重/哈希)将请求发送给不同的后端服务器,减轻每台服务器压力,提高吞吐量

# 本地部署
# brew install nginx
# 先清空/usr/local/Cellar/nginx/1.25.5/html,然后将资料中h5目录下的static和index.html拷贝进来
# 启动nginx - 打开页面 http://localhost:8080 - F12调成手机模式
```