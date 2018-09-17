package com.yibin.nanxi.scandir2json;

import com.yibin.nanxi.scandir2json.util.OkHttpUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Created by LongFF on 2018/9/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestOkHttp {

    @Test
    public void test1() throws IOException {
        String api = "/getDirTree";
        String url = "http://localhost:8082" + api;

        String s = OkHttpUtils.httpGet(url);
        System.out.println(s);
    }
}
