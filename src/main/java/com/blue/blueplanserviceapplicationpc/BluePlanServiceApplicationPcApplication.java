package com.blue.blueplanserviceapplicationpc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(basePackages ="com.blue.blueplanserviceapplicationpc.dao")
public class BluePlanServiceApplicationPcApplication {

    public static void main(String[] args) {
        SpringApplication.run(BluePlanServiceApplicationPcApplication.class, args);
    }

}
