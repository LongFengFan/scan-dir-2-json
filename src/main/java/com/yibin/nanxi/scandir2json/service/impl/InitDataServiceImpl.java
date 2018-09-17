package com.yibin.nanxi.scandir2json.service.impl;

import com.yibin.nanxi.scandir2json.service.InitDataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * Created by LongFF on 2018/9/16
 */
@Service
@Order(value = 1)
public class InitDataServiceImpl implements InitDataService , CommandLineRunner{
    @Override
    public void printInit() {
        System.out.println("init 1 成功");
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("init 2 成功");
    }
}
