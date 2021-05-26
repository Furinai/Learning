package cn.linter.learning.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 课程服务启动类
 *
 * @author wangxiaoyang
 * @since 2021/02/16
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }

}