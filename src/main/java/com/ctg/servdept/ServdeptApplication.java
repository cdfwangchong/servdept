package com.ctg.servdept;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@EnableTransactionManagement
@MapperScan("com.ctg.servdept.dao")
@SpringBootApplication
public class ServdeptApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServdeptApplication.class, args);
    }

}
