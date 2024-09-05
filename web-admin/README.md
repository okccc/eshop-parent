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
```