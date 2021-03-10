package cn.linter.learning.user;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 用户服务启动类
 *
 * @author wangxiaoyang
 * @since 2020/11/12
 */
@SpringCloudApplication
@EnableFeignClients
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
