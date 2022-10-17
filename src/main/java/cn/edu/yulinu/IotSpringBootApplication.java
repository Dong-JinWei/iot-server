package cn.edu.yulinu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("cn.edu.yulinu.dao")
@EnableScheduling // 定时任务
public class IotSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(IotSpringBootApplication.class, args);
    }
}
