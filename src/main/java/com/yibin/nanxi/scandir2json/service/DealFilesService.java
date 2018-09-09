package com.yibin.nanxi.scandir2json.service;

import java.io.File;

/**
 * Created by LongFF on 2018/9/9
 */
public interface DealFilesService {

    Boolean ZipFiles(String orginDir,String destDir,String zipName);
}
