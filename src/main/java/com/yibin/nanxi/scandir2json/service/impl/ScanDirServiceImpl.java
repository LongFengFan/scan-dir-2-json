package com.yibin.nanxi.scandir2json.service.impl;

import com.yibin.nanxi.scandir2json.constant.SuffixConstant;
import com.yibin.nanxi.scandir2json.entity.DirTree;
import com.yibin.nanxi.scandir2json.entity.FileInfo;
import com.yibin.nanxi.scandir2json.filter.FileSuffixFilter;
import com.yibin.nanxi.scandir2json.service.ScanDirService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.*;
import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by LongFF on 2018/8/26
 */
@Service
public class ScanDirServiceImpl implements ScanDirService {
    private static Logger logger = LoggerFactory.getLogger(ScanDirServiceImpl.class);

    @Value("${nginx.url}")
    String nginxUrl;
    @Override
    public DirTree getDirTree(String path) {
        DirTree dirTree = new DirTree();
        File baseFile = new File(path);
        if(baseFile.isFile()){
            dirTree.getFileInfos().add(readFile(baseFile));
        }else if (baseFile.isDirectory()){
            recursionGetDir(baseFile,dirTree);
        }
        System.out.println(dirTree);
        return dirTree;
    }

    private void recursionGetDir(File baseFile , DirTree dirTree) {
        FilenameFilter filenameFilter = new FileSuffixFilter(Arrays.asList(SuffixConstant.Image_Suffixs));
        File[] files = baseFile.listFiles();
        int length = baseFile.list(filenameFilter).length;
        if(length != 0 ){
            dirTree.getDirTreeMap().put(baseFile.getAbsolutePath(), length);
        }
        for (File file : files) {
            if (file.isFile()){
                dirTree.getFileInfos().add(readFile(file));
            }else if (file.isDirectory()){
                recursionGetDir(file,dirTree);
            }
        }
    }

    private FileInfo readFile(File file) {
        FileInfo fileInfo = new FileInfo();
        return fileInfo.setId(UUID.randomUUID().toString()).setName(file.getName()).
                setLocation(file.getAbsolutePath()).setUrl(nginxUrl + "/" + file.getAbsolutePath());
    }
}
