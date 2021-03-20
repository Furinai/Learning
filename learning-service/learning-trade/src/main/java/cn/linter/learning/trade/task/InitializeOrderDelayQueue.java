package cn.linter.learning.trade.task;

import cn.linter.learning.trade.entity.Order;
import cn.linter.learning.trade.service.OrderService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.DelayQueue;

/**
 * 初始化订单延时队列任务
 *
 * @author wangxiaoyang
 * @since 2021/3/20
 */
@Component
public class InitializeOrderDelayQueue implements ApplicationRunner {

    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;
    private final DelayQueue<Order> orderDelayQueue;
    private final OrderService orderService;

    public InitializeOrderDelayQueue(ThreadPoolTaskExecutor threadPoolTaskExecutor,
                                     DelayQueue<Order> orderDelayQueue,
                                     OrderService orderService) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
        this.orderDelayQueue = orderDelayQueue;
        this.orderService = orderService;
    }

    @Override
    public void run(ApplicationArguments args) {
        orderDelayQueue.addAll(orderService.listUnpaid());
        threadPoolTaskExecutor.execute(() -> {
            while (true) {
                try {
                    Order order = orderDelayQueue.take();
                    order.setStatus(2);
                    orderService.update(order);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
