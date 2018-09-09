package com.yibin.nanxi.scandir2json.controller;

import com.yibin.nanxi.scandir2json.service.DealFilesService;
import com.yibin.nanxi.scandir2json.util.CommonUtils;
import com.yibin.nanxi.scandir2json.util.ShellUtils;
import com.yibin.nanxi.scandir2json.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * Created by LongFF on 2018/9/9
 */

@RestController
public class ShellController {

    @RequestMapping("/doShell")
    public ResultVO zipFiles(@RequestParam String cmd , @RequestParam String dir){
        File wrapDir = new File(dir);
        Boolean aBoolean = ShellUtils.executeShell(cmd, wrapDir);
        return aBoolean ? CommonUtils.success(true) : CommonUtils.error("false");
    }
}
