# cloud-proj
#### spring boot cloud版本对应关系
cloud 依赖于boot，每个版本改动都很大，需要明确对应具体版本，否则对应会有一些莫名其妙的错误，比如NoSuchMethodError等
具体通过http://projects.spring.io/spring-cloud/查找关系；比如：2.0 对应 F ,1.5 对应 E

#### 服务 
consul/Eureka：服务发现 （根据情况选择一个） 
Hystrix：断路器 
Zuul：智能路由 
Ribbon/Feign：客户端负载均衡 （Feign用的更多） 
Turbine：集群监控 
Springcloud-config：远程获取配置文件

#### http2 http1.1区别
https://www.cnblogs.com/frankyou/p/6145485.html
