## 一、Eureka

### 1、服务治理

​	在传统的RPC远程调用框架中，管理每个服务与服务之间依赖关系比较复杂，管理比较复杂，所以需要服务治理，管理服务与服务之间以来关系，可以实现服务调用、负载均衡、容错等，实现服务发现与注册。

### 2、服务注册与发现

​	Eureka采用CS设计架构，Eureka Server作为服务注册功能的服务器，它是服务注册中心。而系统中的其他为微服务，使用Eureka的客户端连接到Eureka Server并维持心跳连接。这样系统的维护人员可以通过Eureka Server来监控系统中各个微服务是否正常运行。

​	在服务注册与发现中，有一个注册中心。当服务器启动的时候，回把当前自己服务器的信息，比如：服务地址、通信地址等以别名方式注册到注册中心上。另一方（消费者|服务提供者），以该别名的方式去注册中心上获取到实际的服务通讯地址，然后再实现本地RPC调用RPC远程调用框架可信设计思想：在于注册中心，因为使用注册中心管理每个服务与服务之间的一个依赖关系（服务治理概念）。在任何RPC远程框架中，都会有一个注册中心（存放服务器地址相关信息（接口地址））。

下图为Eureka系统架构：

![Eureka系统架构](.\pics\eureka架构.png)

![Dubbo系统架构](.\pics\dubbo架构.png)

### 3、Eureka包含两个组件：Eureka Server 和 Eureka Client

​	Eureka Server提供服务注册服务

​	各个微服务节点通过配置启动后，会在EurekaServer中进行注册，这样EurekaServer中的服务注册表中将会存储所有可用服务节点的信息，服务节点的信息可以在界面中直观看到。

​	EurekaClient通过注册中心进行访问

​	是一个Java客户端，用于简化Eureka Server的交互，客户端同时也具备一个内置的、使用轮询（rand-robin）负载算法的负载均衡器。在应用启动后，将会向Eureka Server发送心跳（默认周期是30秒）。如果Eureka Server在多个心跳周期内没有接收到某个节点的心跳，Eureka Server将会从服务注册表中把这个服务节点移除（默认90秒）。

### 4、问题：微服务RPC远程服务调用最核心的是什么？

​	高可用，试想你的注册中心只有一个，它出故障了，会导致整个微服务不可用。

### 5、Eureka集群原理

![Eureka集群原理](pics\Eureka集群原理.png)

### 6、Eureka自我保护模式

​	默认情况下，如果Eureka Server在一定时间内没有接收到某个微服务实例的心跳，Eureka Server将会注销该实例（默认90秒）。但当网络分区故障发生（延时、卡顿、拥挤）时，微服务与Eureka Server之间无法正常通信，以上行为变得非常危险了--因为微服务本身时健康的，此时本不应该注销这个微服务。Eureka通过“自我保护机制”来解决这个问题--当Eureka Server 节点在短时间内丢失过多客户端时（可能发生了网络分区故障），那么这个节点就会进入自我保护模式。

​	保护模式主要用于一组客户端和Eureka Server之间存在的网络分区场景下的保护。一旦进入保护模式，Eureka Server将会尝试保护其服务注册表中的信息，不再删除服务注册表中的数据，也就是不会注销任何微服务。

​	简单来说，就是某时刻某一个微服务不可用了，Eureka不会立刻清理，依旧会对该微服务的信息进行保存。这属于CAP里面的AP分支。

**如果在Eureka Server的首页看到一下这段提示，则说明Eureka进入了保护模式：**

``` java
// EMERGENCY! EUREKA MAY BE INCORRECTLY CLAIMING INSTANCES ARE UP WHEN THEY'RE NOT. RENEWALS ARE LESSER THAN THRESHOLD AND HENCE THE INSTANCES ARE NOT BEING EXPIRED JUST TO BE SAFE.
```

​	禁止自我保护：

``` yml
eureka:
  server:
    # 关闭自我保护机制，保证不可用服务被及时剔除
    enable-self-preservation: false
    eviction-interval-timer-in-ms: 2000
```

### 7、Eureka、Zookeeper、Consul异同点

|  组件名   | 语言 | CAP  | 服务健康检查 | 对外暴漏接口 | Spring Cloud集成 |
| :-------: | :--: | :--: | :----------: | :----------: | :--------------: |
|  Eureka   | Java |  AP  |   可配支持   |     HTTP     |      已集成      |
|  Consul   |  Go  |  CP  |     支持     |   HTTP/DNS   |      已集成      |
| Zookeeper | Java |  CP  |     支持     |    客户端    |      已集成      |

​	CAP：

* C：强一致性

* A：可用性

* P：分区容错性（微服务一定要保证该项）

  CAP理论的核心是：一个分布式系统不可能同时很好的满足一致性，可用性和分区容错行这个三个需求。因此，根据CAP原理将NoSQL数据库分成了满足CA原则、满足CP原则和满足AP原则三大类：

  * CA-单点集群，满足一致性，可用性的系统，通常在可扩展上不太强大。
  * CP-满足一致性，分区容错性的系统，通常性能不是特别高。
  * AP-满足可用性，分区容错性的系统，通常可能对一致性要求低一些。

  CAP理论关注粒度是数据，而不是整体系统设计的策略。



## 二、LoadBalance

### 1、LoadBalance负载均衡是什么

​	简单的说就是将用户的请求平摊的分配到多个服务上，从而达到系统的HA（高可用）。常见的负载均衡有：Nginx、LVS、硬件F5等。

### 2、Ribbon本地负载均衡客户端 VS Nginx服务端负载均衡区别

Nginx是服务器负载均衡，客户端所有请求都会交给Nginx，然后由nginx实现转发请求。即负载均衡是由服务端实现的。

Ribbon本地负载均衡，再调用微服务接口时候，会在注册中心上获取注册信息服务列表之后缓存到JVM本地，从而在本地实现RPC远程服务调用技术。

### 3、Ribbon工作步骤

​	Ribbon在工作时分成两步：

* 先选择Eureka Server，它优先选择在同一个区域内负载较少的Server。

* 再根据用户指定的策略，在从Server取到的服务注册列表中选择一个地址。

  其中Ribbon提供了多种策略：比如轮询、随机和根据响应时间加权。

### 4、Ribbon自带的负载规则

​	根据IRule类中的接口看：

* RoundRibinRule--轮询
  * WeightedResponseTimeRule--对RoundRobinRule的扩展，响应速度越快的实例选择权重越大，越容易被选择。

* RandomRule--随机
* RetryRule--先按照RoundRobinRule的策略获取服务，如果获取服务失败则在指定时间内进行重试
* BestAvilableRule--会先过滤掉故障实例，再选择并发较小的实例。
* AvailabilityFilteringRule--会先过滤故障实例，再选择并发较小的实例
* ZoneAvoidanceRule--默认规则，复合判断Server所在区域的性能和Server的可用性选择服务器

### 5、负载均衡算法

​	rest接口第几次请求数 % 服务器集群总数量 = 实际调用服务器位置下标， 每次服务重启后rest接口计数从1开始。

``` java
List<SeryiceInstance> instances = discoveryClient.getInstances("PAYMENT-SERVICE");

如：List[0] instances = 127.0.0.1:8002
    List[1] instances = 127.0.0.1:8001
    8001 + 8002组合为集群，他们共计2台机器，集群总数为2，按照轮询算法原理：
    当总请求数为1时：1 % 2 = 1 对应下标位置为1，则获得服务地址为127.0.0.1：8001
    当总请求数为1时：2 % 2 = 0 对应下标位置为0，则获得服务地址为127.0.0.1：8002
    当总请求数为1时：3 % 2 = 1 对应下标位置为1，则获得服务地址为127.0.0.1：8001
    当总请求数为1时：4 % 2 = 0 对应下标位置为0，则获得服务地址为127.0.0.1：8002
    以此类推。。。
```

## 三、OpenFeign

### 1、概述

​	Feign是一个可声明式的WebService客户端。使用Feign能让编写Web Service客户端更加简单。

​	它使用方法是定义一个服务接口然后再上面添加注解。Feign也支持可拔插式的编码器和解码器。Spring Cloud对Feign进行了封装，使其支持了Spring MVC标准注解和HttpMessageConverters。Feign可以与Eureka和Ribbon组合使用以支持负载均衡。

### 2、Feign能干什么

​	Feign旨在使编写Java Http客户端变得更容易。

​	前面在使用Ribbon+RestTemplate时，哩用RestTemplate对Http请求的封装处理，形成了一套模板化的调用方法。但在实际开发中，由于对服务依赖的调用可能不止一处，往往一个接口会被多处调用，所以通常会针对每个微服务自行封装一些客户端来包装这些依赖微服务的调用。所以，Feign在此基础上做了进一步封装，由他来帮助我们定义和实现依赖服务接口的定义。在Feign的实现下，我们只需要创建一个接口并使用注解的方式来配置它（以前时Dao接口上面标注Mapper注解，现在是一个微服务接口上面标注一个Feign注解即可。），即可完成对服务提供方的接口绑定，简化了使用Spring Cloud Ribbon时，自动封装服务调用客户端的开发量。

​	Feign继承了Ribbon

​	利用Ribbon维护了Payment的服务列表信息，并且通过轮询实现了客户端的负载均衡。而与Ribbon不同的是，通过Feign只需要定义服务绑定接口且以声明式的方法，优雅而简单的实现了服务调用。

### 3、Feign与OpenFeign区别

|                            Feign                             |                          OpenFeign                           |
| :----------------------------------------------------------: | :----------------------------------------------------------: |
| Feign是Spring Cloud组件中的一个轻量级的RESTful的HTTP服务客户端。Feign内置了Ribbon，用来做客户端负载均衡，去调用服务注册中心的服务。Feign的使用方式是：使用Feign的注解定义接口，调用这个接口，就可以调用服务注册中心的服务。 | OpenFeign是Spring Cloud在Feign的基础上支持了SpringMVC的注解，如@RequesMapping等等。OpenFeign的@FeignClient可以解析SpringMVC的@RequestMapping注解下的接口，并通过动态代理的方式产生实现类，实现类中做负载均衡并调用其他服务。 |

### 4、Feign的日志打印

​	Feign提供了日志打印功能，可以通过配置来调整日志级别，从而了解Feign中Http请求的细节。

​	说白了就是对Feign接口的调用情况进行监控和输出

### 5、日志级别

* NONE：默认的，不显示任何日志；
* BASIC：仅记录请求方法、URL、响应状态码及执行时间；
* HEADERS：除了BASIC中定义的信息之外，还有请求和响应的头信息
* FULL：除了HEADERS中定义的信息之外，还有请求和响应的正文及元数据。

## 四、Hystrix

### 1、分布式系统面临的问题

​	复杂分布式体系结构中的应用程序有数十个依赖关系，每个依赖关系在某些时刻将不可避免的失败。

### 2、服务雪崩

​	多个微服务之间调用的时候，假设微服务A调用微服务B和微服务C，微服务B和微服务C又调用其他微服务，这就是所谓的“扇出”。如果扇出的链路上某个微服务的调用响应时间过长或者不可用，对微服务A的调用就会占用越来越多的系统资源，进而引起系统崩溃，这就是“雪崩效应”。

​	对于高流量的应用来说，单一的后端依赖可能回导致所有服务器上的所有资源都在几秒内饱和。比失败更糟糕的是，这些应用程序还可能导致服务之间的延迟增加，备份队列，线程和其他系统资源紧张，导致整个系统发生更多的级联故障。这些都表示需要对故障和延迟进行隔离和管理，以便单个依赖关系的失败，不能取消整个应用程序或系统。

​	所以，通常当发现一个模块下的某个实例失败后，这时候这个模块依然还会接收流量，然后这个有问题的模块还调用了其他的模块，这样就会发生级联故障，或者叫雪崩。

### 3、Hystrix

​	Hystrix是一个用于处理分布式系统的延迟和容错的开源库，在分布式系统里，许多依赖会不可避免的调用失败，比如超时、异常等。

​	Hystrix能够保证在一个依赖出问题的情况下，不会导致整体服务失败，避免级联故障，以提高分布式系统的弹性。

​	“断路器”本身是一种开关装置，当某个服务单元发生故障之后，通过断路器的故障监控（类似熔断保险丝），向调用方返回一个符合预期的、可处理的备选响应（FallBack），而不是长时间的等待或者抛出调用方无法处理的异常，这样就保证了服务调用方的线程不会被长时间、不必要地占用，从而避免了故障在分布式系统中的蔓延，乃至雪崩。

### 4、Hystrix重要概念

* 服务降级（FallBack）
  * 服务器忙，请稍后再试，不让客户端等待并立刻返回一个友好提示，fallback
  * 服务降级放生的情况
    * 程序运行异常
    * 超时
    * 服务熔断触发服务降级
    * 线程池/信号量打满也会导致服务降级
* 服务熔断（Break）
  * 类似保险丝达到最大服务访问后，直接拒绝访问，拉闸限电，然后调用服务降级的方法并返回友好提示。
  * 服务的降级 -> 进而熔断 -> 恢复调用链路
* 服务限流（FlowLimit）
  * 秒杀等高并发操作，严禁一窝蜂的过来拥挤，大家排队，一秒钟N个，有序进行

### 5、服务出现问题的解决方法

* 超时导致服务器变慢（转圈）： 超时不再等待
* 出错（宕机或程序运行出错）：出错要有兜底
* 解决：
  * 对方服务（8001）超时了，调用者（80）不能一直卡死等待，必须要有服务降级
  * 对方服务（8001）宕机了，调用者（80）不能一直卡死等待，必须要有服务降级
  * 对方服务（8001）OK，调用者（80）自己服务出现故障或有自我要求（自己的等到时间小于服务提供者），自己处理降级

### 6、配置全局同意的服务降级方法

``` java
//在类上配置@DefaultProperties（defaultFallback = ""）注解
1：1每个方法配置一个服务降级方法，技术上可用，但是代码太复杂
1：N除了个别重要核心业务有专属，其他普通的可用通过@DefaultProperties（defaultFallback = ""）注解统一跳转到统一处理结果页面。
通用的和独享的各自分开，避免了代码膨胀，合理减少了代码量。
```

### 7、服务熔断

* 熔断机制
  * 熔断机制是应对雪崩效应的一种微服务链路保护机制。当扇出链路的某个微服务出错不可用或者响应时间太长时，会进行服务的降级，进而熔断该节点微服务的调用，快速返回错误的响应信息。
  * 当检测到该节点微服务调用响应正常后，恢复调用链路。
  * 在Spring Cloud框架里，熔断机制通过Hystrix实现。Hystrix会监控微服务间调用的状况，当失败的调用达到一定的阈值，缺省时5秒内20次调用失败，就会启动熔断机制。熔断机制的注解是@HystrixCommand。

### 8、总结

熔断类型：

* 熔断打开：请求不再进行调用当前服务，内部设置时间一般为MTTR（平均故障处理时间），当打开时间达到所设时间则进入半熔断状态
* 熔断关闭：熔断关闭不会对服务进行熔断
* 熔断半开：部分请求i根据规则调用当前服务，如果请求成功且符合规则，则认为当前服务恢复正常，关闭熔断

**涉及到断路器的三个重要参数**：

* 快照时间窗（circuitBreaker.requestVolumeThreshold）：断路器确定是否打开需要统计一些请求和错误数据，而统计的时间范围就是快照时间窗，默认为最近的10秒。
* 请求总数阈值（circuitBreaker.sleepWindowInMilliseconds）：在快照时间窗内，必须满足请求总数阈值才有资格熔断。默认为20，意味着在10秒内，如果该Hystrix命令的调用次数不足20次，即使所有的请求都超时或其他原因失败，断路器都不会打开。
* 错误百分比阈值（circuitBreaker.errorThresholdPercentage）：当请求总数在快照时间窗内超过了阈值，比如发生了30次调用，如果在这30次调用中，有15次发生了超时异常，也就是超过了50%的错误百分比，在默认设定50阈值情况下，这时候就会将断路器打开。

**断路器开启或关闭的条件**：

* 当满足一定的阈值的时候（默认10秒内超过20个请求次数）

* 当失败率达到一定的时候（默认10秒内超过50%的请求失败）

* 到达以上阈值，断路器将会开启

* 当开启的时候，所有请求都不会进行转发

* 一段时间之后（默认是5秒），这个时候断路器是半开状态，会让其中一个请求进行转发。如果成功，断路器会关闭；若失败，继续开启，重复4和5的步骤。

  当断路器开启时，再有请求调用的时候，将不会调用主逻辑，而是直接调用降级fallback。通过断路器，实现了自动的发现错误并将降级逻辑切换为主逻辑，减少响应延迟的效果。

  Hystrix也是西安了自动恢复功能，当断路器打开，对主逻辑进行熔断之后，Hystrix会启动一个休眠时间窗，在这个时间窗内，降级逻辑是临时成为主逻辑，当休眠时间窗到期，断路器将进入半开状态，释放一次请求到原来的主逻辑上，如果此次请求正常返回，那么断路器将继续闭合，主逻辑恢复；如果这次请求依然有问题，断路器继续进入打开状态，休眠时间窗重新计时。