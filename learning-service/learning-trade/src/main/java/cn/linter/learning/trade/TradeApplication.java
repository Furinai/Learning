package cn.linter.learning.trade;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 交易服务启动类
 *
 * @author wangxiaoyang
 * @since 2021/02/16
 */
@SpringCloudApplication
@EnableFeignClients
public class TradeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradeApplication.class, args);
    }

}
