## 项目准备
```shell
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

# EasyExcel是一个快速、简洁、解决大文件内存溢出的Excel处理工具
# EasyExcel官网地址：https://easyexcel.opensource.alibaba.com/docs/current/
# 提高操作效率：手动逐条添加或修改数据费时费力且容易出错,此时就可以将大量数据从Excel导入到系统中
# 实现数据备份：可以将系统中的数据导出为Excel,防止数据丢失
# 方便部门协作：不同部门会使用不同的工具进行数据处理,导入导出Excel方便转换和共享数据
```