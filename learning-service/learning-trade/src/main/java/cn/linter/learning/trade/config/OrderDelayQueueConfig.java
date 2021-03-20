package cn.linter.learning.trade.config;

import cn.linter.learning.trade.entity.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.DelayQueue;

/**
 * 订单延迟队列配置
 *
 * @author wangxiaoyang
 * @since 2021/3/20
 */
@Configuration
public class OrderDelayQueueConfig {

    @Bean
    public DelayQueue<Order> orderDelayQueue() {
        return new DelayQueue<>();
    }

}
