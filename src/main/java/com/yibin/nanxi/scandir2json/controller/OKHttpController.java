package com.yibin.nanxi.scandir2json.controller;

import com.alibaba.fastjson.JSONObject;
import com.yibin.nanxi.scandir2json.entity.FileInfo;
import com.yibin.nanxi.scandir2json.util.CommonUtils;
import com.yibin.nanxi.scandir2json.util.OkHttpUtils;
import com.yibin.nanxi.scandir2json.vo.ResultVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LongFF on 2018/9/16
 */

@RestController
public class OKHttpController {

    @RequestMapping("/okHttp")
    public ResultVO okTest() throws IOException {
        String api = "/getDirTree";
        String url = "http://localhost:8081" + api + "?path=f:/test";

        OkHttpUtils.httpGetYibu(url);
        return CommonUtils.success("调用");
    }

    @RequestMapping("/okHttpPost")
    public ResultVO okTest2() throws IOException {
        String api = "/getDirTree";
        String url = "http://localhost:8082" + api ;

        Map<String,String> map = new HashMap<>();
        map.put("path", "f:/test");
        String s = OkHttpUtils.postForm(url, map);
        return CommonUtils.success(s);
    }

    @RequestMapping("/okBody")
    public ResultVO okTestBody(@RequestBody FileInfo fileInfo) throws IOException {
        System.out.println(fileInfo);
        return CommonUtils.success(fileInfo);
    }


    @RequestMapping("/okTestBody")
    public ResultVO okTest3() throws IOException {
        String api = "/okBody";
        String url = "http://localhost:8082" + api ;

        FileInfo fileInfo = new FileInfo().setName("goudan").setLocation("/opt").setId("ok");
        Map<String,String> map = new HashMap<>();
        map.put("name", "goudan");
        String s = OkHttpUtils.httpPost(url,fileInfo.toString());
        return CommonUtils.success(s);
    }

    @RequestMapping("/okTestDownLoad")
    public ResultVO okTest4() throws IOException {
        String s = OkHttpUtils.DownloadInterNet("http://www.0551fangchan.com/uploadfile/2018/0916/20180916032015871.png");
        return CommonUtils.success(s);
    }

}
