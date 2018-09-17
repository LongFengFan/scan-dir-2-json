package com.yibin.nanxi.scandir2json;

import com.alibaba.fastjson.JSONObject;
import com.yibin.nanxi.scandir2json.util.PlaceHolderUtil;
import com.yibin.nanxi.scandir2json.util.ZipUtil;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.junit.Test;


import java.io.File;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ScanDir2JsonApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("user.name"));

    }

    @Test
    public void test() throws Exception {
        String source = "F:/test/test.zip";
        String dest = "F:/test2";
        String password = "password";
        try {
            File zipFile = new File(source);
            ZipFile zFile = new ZipFile(zipFile);  // 首先创建ZipFile指向磁盘上的.zip文件
            zFile.setFileNameCharset("UTF-8");
            File destDir = new File(dest);     // 解压目录
            if (zFile.isEncrypted()) {
                zFile.setPassword(password.toCharArray());  // 设置密码
            }
            zFile.extractAll(dest);      // 将文件抽出到解压目录(解压)
        } catch (ZipException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test2(){
        Boolean aBoolean = ZipUtil.compressFolderWithTargetFolder("F:/test/test5.zip", null, "F:/test2");
        System.out.println(aBoolean);
    }

    @Test
    public void test3() throws ZipException {
//        Instant start1 = Instant.now();
//        Boolean aBoolean = ZipUtil.compressFolderWithTargetFolder("F:/test/test5.zip", null, "F:/test2");
//        System.out.println(aBoolean);
//        Instant start2 = Instant.now();
//        System.out.println("压缩时间 " + Duration.between(start1,start2).toMinutes());

        Instant start3 = Instant.now();
        Boolean bool = ZipUtil.deCompress("F:/test/test.zip", "F:/test2", null);
        System.out.println(bool);
        Instant start4 = Instant.now();
        System.out.println("解压时间 " + Duration.between(start3,start4).toMinutes());

    }

    @Test
    public void test4() throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("stage","model");
        jsonObject.put("stageName","co");
        jsonObject.put("job","cc");
        String s = PlaceHolderUtil.replacePlaceHolders(jsonObject);
        System.out.println(s);
    }


}

