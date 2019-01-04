package com.yuxiang.li.reptile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by yuxiang.li on 2018/12/3.
 */
@SpringBootApplication
@EnableScheduling
@PropertySource("classpath:application-dev.properties")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.out.println("dev-master");
    }
}


