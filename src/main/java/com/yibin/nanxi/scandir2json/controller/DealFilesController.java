package com.yibin.nanxi.scandir2json.controller;

import com.yibin.nanxi.scandir2json.service.DealFilesService;
import com.yibin.nanxi.scandir2json.util.CommonUtils;
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
public class DealFilesController {

    @Autowired
    private DealFilesService dealFilesService;
    @RequestMapping("/zip")
    public ResultVO zipFiles(@RequestParam String orginDir , @RequestParam String destDir,@RequestParam String zipName){
        Boolean aBoolean = dealFilesService.ZipFiles(orginDir,destDir,zipName);

        return aBoolean ? CommonUtils.success(true) : CommonUtils.error("false");
    }
}
