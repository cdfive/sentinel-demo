## Demo框架

SpringBoot + Dubbo + Sentinel

*注：选择官方推荐使用的最新RELEASE版本*

| 框架       | 版本号 |
| ---------- | ------ |
| SpringBoot | 2.4.4  |
| Dubbo      | 2.7.6  |
| Sentinel   | 1.8.1  |

| 微服务        | web端口 | telnet端口 | qos端口 | sentinel端口 |
| ------------- | ------- | ---------- | ------- | ------------ |
| rest-app      | 8080    | 20000      | 22220   | 8700         |
| biz-user      | 8081    | 20001      | 22221   | 8701         |
| biz-stock     | 8082    | 20002      | 22222   | 8702         |
| biz-product   | 8083    | 20003      | 22223   | 8703         |
| biz-promotion | 8084    | 20004      | 22224   | 8704         |
| biz-order     | 8085    | 20005      | 22225   | 8705         |

## 启动步骤
- 启动zookeeper
- 启动dubbo-admin(非必须)
- 启动sentinel-dashboard
- 启动各微服务

*注：除了zookeeper需要第1个启动，其它的启动顺序没有影响*

## 演示目标

- 演示QPS/线程两种维度的流控规则在项目中的运用，并发和qps概念上的区分，项目中常见的Dubbo线程池满异常的分析和处理
- 演示一个较长链路的调用，当下游服务有问题时，通过实时流控规则管控避免服务雪崩，并分析流控相关异常如何捕获和处理
- 演示对热点数据的限流，如商品详情页接口，在高流量并发下对热点商品的访问，不影响对普通商品的访问

## Demo1 - 商品列表接口限流 

演示QPS/线程两种维度的流控规则在项目中的运用，并发和qps区别，项目中常见的Dubbo线程池满异常分析和处理

*注：浏览器访问http://localhost:8083/dubbo/threadPoolStatus，可查询biz-product服务Dubbo线程池情况*



**测试接口: **/proudct/list



**演示步骤：**

a. dubbo线程池threads=20, jmeter线程数=10

接口模拟时间10-50ms，因此预计qps为200-1000/s

通过实时监控菜单，观察通过qps情况

通过流控规则菜单，给该接口设置类型为qps，阈值为100的流控规则，观察通过qps为100/s，拒绝qps为几千/s



b. dubbo线程池threads=20, jmeter线程数=25

因增大了jmeter线程数，并且超过dubbo业务线程数，因此很快出现dubbo线程池满异常



c. dubbo线程池threads=20, jmeter线程数=25, queues=5

因配置了大小为5的队列，再次压测没有出现dubbo线程池满

但再调用商品服务其他接口，比如商品详情接口(/product/detail)，会出现dubbo线程池满异常，接口无法访问

通过流控规则菜单，给该接口设置类型为线程，阈值为10的流控规则，再次访问商品详情页接口(/product/detail)，成功访问



**总结: **

- 通过qps限流能限制接口访问速率，作为服务提供方清楚自己的实际情况，可经过压测后配置该值
- 通过线程限流能解决因单个接口导致整个服务其它接口不可用的问题，防止微服务雪崩效应

## Demo2 - 提交订单接口限流
演示一个长链路的调用，当下游服务有问题时，通过实时配置流控规则来管控避免服务雪崩，并分析流控相关异常如何捕获和处理



**演示接口:** /order/submit



**演示步骤：**

a. 启动所有服务后，通过postman工具调用`/order/submit`下单接口，返回true成功，多次调用观察接口处理时间约为200ms

b. jmeter设置线程数为10，压测`/order/submit`下单接口，观察不同服务的实时监控菜单

c. 修改`PromotionServiceImpl#usePromotion`，模拟业务问题慢调用，将耗时10-50ms调整为1000-5000ms，实时监控菜单观察

d. 在biz-promotion服务通过降级规则菜单，对`usePromotion`接口设置降级规则，慢调用2000ms，比例0.5，熔断时长30s

e. 实时监控菜单观察限流情况，在IDEA里观察biz-promotion服务没有异常日志输出，rest-app服务无法捕获流控异常

f.  打开`SentinelBizDubboFallback`的`@Component`注解，自定义Dubbo限流处理抛`SentinelBizException`，再次观察日志



修改`JsonResult`中的`MSG_BLOCK_ERROR`变量值，通过arthas动态替换自定义限流处理的提示信息：

java -jar arthas-boot.jar

redefine /目录/sentinel-demo/base/target/classes/com/cdfive/sentinel/demo/base/vo/JsonResult.class



**总结：**

- **通过Sentinel控制台我们可以对接入的某个链路各微服务进行实时管控，通过流控、降级等规则进行限制；**
- **Dubbo自带ExceptionFilter的处理逻辑以及Sentinel Filter执行顺序问题，可能导致流控异常在调用方不能被捕获**。

## Demo3 - 热点商品接口限流
演示对热点数据的限流，如商品详情页接口，在高流量并发下对热点商品的访问，不影响对普通商品的访问



**业务场景：**

电商业务经常有促销活动，比如秒杀、满减、买赠、预约、抢购等，特别是双11、元旦、春节等时间节点，当业务高峰流量来临时，考虑到服务实际能承载的情况，为保护服务稳定性，会对重要业务接口进行限流，避免超过服务负载器出现接口变慢、甚至服务不可用。

流量中很大一部分比例往往是热点数据，比如秒杀、抢购活动中的热门商品。这时我们希望对热点商品的限流，能不影响到普通商品，避免用户选购普通商品时也出现限流，影响用户体验。



**演示接口:** /proudct/detail

热点商品id=2001，普通商品id=2002



**演示步骤：**

a. 注释掉`ProductDetailUrlParser`类的`@Component`注解；

设置jmeter线程数=10，压测商品详情接口，接口放问的是热点商品id=2001，通过实时监控菜单，观察通过qps情况，约为400/s；

对`/product/detail`接口设置流控规则，限制qps为100/s，假定它是接口能支持的最大qps；

浏览器访问`/product/detail`接口，访问普通商品id=2002，多次访问观察会出现限流提示；

**因为限流是同一个接口，对热点商品的限流影响到了普通商品的访问。**



b. 打开`ProductDetailUrlParser`类的`@Component`注解

设置jmeter线程数=10，压测商品详情接口，接口放问的是热点商品id=2001，通过实时监控菜单，观察通过qps情况，约为400/s；

在簇点链路中观察生产了热点商品接口的资源`/product/detail#id=2001`，对它设置流控规则，限制qps为100/s，假定它是接口能支持的最大qps；

浏览器访问`/product/detail`接口，访问普通商品id=2002，多次访问都不会出现限流；

**因为对热点商品生成了独立的资源，对热点商品的限流不会影响普通商品的访问。**



**总结：**

* **有时业务高峰流量访问大部分集中于热点数据，我们对接口做限流时希望能不影响非热点数据，避免影响用户体验**

* **使用`sentinel-web-servlet`适配我们可通过`UrlCleaner`扩展机制，将热点数据相关接口生成独立的资源，实现热点数据限流**

