package com.yibin.nanxi.scandir2json.service.impl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by LongFF on 2018/9/16
 */
@Component
@Order(value = 2)
public class SingleInitData implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("init 3 成功");
    }
}
