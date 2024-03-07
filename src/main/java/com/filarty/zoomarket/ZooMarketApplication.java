package com.filarty.zoomarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ZooMarketApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZooMarketApplication.class, args);
    }
}
