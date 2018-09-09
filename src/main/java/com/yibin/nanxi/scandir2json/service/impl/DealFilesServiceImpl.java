package com.yibin.nanxi.scandir2json.service.impl;

import com.yibin.nanxi.scandir2json.service.DealFilesService;
import com.yibin.nanxi.scandir2json.util.ShellUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by LongFF on 2018/9/9
 */
@Service
public class DealFilesServiceImpl implements DealFilesService {

    private static Logger logger = LoggerFactory.getLogger(ScanDirServiceImpl.class);

    @Override
    public Boolean ZipFiles(String orginDir, String destDir, String zipName) {
        File WrapOriginDir = new File(orginDir);
        File WrapDestDir = new File(destDir);
        if (WrapDestDir.exists() && WrapDestDir.isDirectory() && WrapDestDir.exists() && WrapDestDir.isDirectory()){
            String cmd = String.format("cmd /C start winrar a -r %s/%s *",destDir,zipName);
            Boolean aBoolean = ShellUtils.executeShell(cmd, WrapOriginDir);
            if(aBoolean){
                String destZip = destDir + File.separator + zipName;
                logger.info(destZip);
                File destFile = new File(destZip);
                logger.info(destFile.getAbsolutePath());
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(10000);
                        logger.info("睡上10秒查询是否打包成功");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return false;
                    }
                    if (destFile.exists()){
                        logger.info(String.valueOf(destFile.exists()));
                        return true;
                    }
                }
                return false;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
}
