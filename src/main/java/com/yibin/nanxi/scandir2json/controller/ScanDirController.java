package com.yibin.nanxi.scandir2json.controller;

import com.yibin.nanxi.scandir2json.entity.DirTree;
import com.yibin.nanxi.scandir2json.service.ScanDirService;
import com.yibin.nanxi.scandir2json.util.CommonUtils;
import com.yibin.nanxi.scandir2json.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by LongFF on 2018/8/26
 */

@RestController
public class ScanDirController {

    @Autowired
    private ScanDirService scanDirService;

    @RequestMapping(value = "/getDirTree",method = RequestMethod.GET)
    public ResultVO getDirTree(@NotNull String path){
        try {
            if(StringUtils.isEmpty(path)){
                return CommonUtils.error("path is empty" );
            }
            DirTree dirTree = scanDirService.getDirTree(path);
            return CommonUtils.success(dirTree);
        }catch (Exception e){
//            return CommonUtils.error(e.getCause().toString());
            e.printStackTrace();
            return CommonUtils.error(e.getMessage());
        }


    }
}
