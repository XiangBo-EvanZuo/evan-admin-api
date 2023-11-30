# spring-cloud-gateway-oauth2
# todos：
## 业务
- 对接evan-admin
  - 用户登录与鉴权
  - 登录菜单鉴权
## 技术
- 架构更改，增加commom-core，将feign放到里面。增加buiness，将业务模块放进去
- System.out.println的logger整改
- 日志统一收集
- 限流
- 熔断
- 降级
- 监控
- ES

## 低优先级
- 文档
- 测试
- 部署

## DDD
- 模块rename【模块名：done】【package名：undo】
- 数据库重新合理设计【done】
- DDD架构拆分

- 租户隔离策略
- 分布式锁、分布式事务、分布式搜索、分布式缓存、分布式限流以及分库分表
- 

## 前言
我们理想的微服务权限解决方案应该是这样的，认证服务负责认证，网关负责校验认证和鉴权，其他API服务负责处理自己的业务逻辑。安全相关的逻辑只存在于认证服务和网关服务中，其他服务只是单纯地提供服务而没有任何安全相关逻辑。
## 架构
通过认证服务(`evan-admin-oauth2-cas`)进行统一认证，然后通过网关（`evan-admin-gateway`）来统一校验认证和鉴权。采用Nacos作为注册中心，Gateway作为网关，使用nimbus-jose-jwtJWT库操作JWT令牌。
- evan-admin-oauth2-cas：Oauth2认证服务，负责对登录用户进行认证，整合Spring Security Oauth2
- ouath2-gateway：网关服务，负责请求转发和鉴权功能，整合Spring Security Oauth2
- evan-admin-order：受保护的API服务，用户鉴权通过后可以访问该服务，不整合Spring Security Oauth2






