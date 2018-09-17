package com.yibin.nanxi.scandir2json;

import com.yibin.nanxi.scandir2json.service.InitDataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ServletComponentScan
public class ScanDir2JsonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScanDir2JsonApplication.class, args);
    }

    @Bean
    CommandLineRunner init(InitDataService initDataService) {
        return (args) -> {
            initDataService.printInit();
        };
    }
}


