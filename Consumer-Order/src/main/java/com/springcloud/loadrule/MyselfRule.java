package com.springcloud.loadrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : renjiahui
 * @date : 2020/12/28 18:58
 * @desc : 修改Ribbon负载均衡规则
 *          修改规则的时候，该配置不能放在ComponentScan能扫描的包下。也就是不能放在com.springcloud.consumer下。
 *          加完该配置项之后，在主启动类上加@RibbonClient(name="要调用的微服务名称", configuration="配置类名")注解
 */
@Configuration
public class MyselfRule {

    /**
     * 定义为随机
     */
    @Bean
    public IRule myRule() {
        return new RandomRule();
    }
}
