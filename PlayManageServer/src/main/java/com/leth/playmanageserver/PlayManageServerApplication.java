package com.leth.playmanageserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlayManageServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayManageServerApplication.class, args);
    }

}


/*
这段代码是一个Spring Boot应用程序的主类。让我们来看一下：
@SpringBootApplication注解是Spring Boot应用程序的入口点。
它组合了多个注解，包括@Configuration、@EnableAutoConfiguration和@ComponentScan。
这个注解告诉Spring Boot去扫描当前包以及子包中的组件，并自动配置Spring应用程序的一些配置。
main方法是Java应用程序的入口点。
在这里，SpringApplication.run()方法启动了Spring Boot应用程序。它接受两个参数：应用程序的主类和命令行参数。
总之，这个类是整个Spring Boot应用程序的启动点。
*/