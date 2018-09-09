package com.yibin.nanxi.scandir2json;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ServletComponentScan
public class ScanDir2JsonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScanDir2JsonApplication.class, args);
    }
}
