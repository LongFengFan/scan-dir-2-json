package com.yibin.nanxi.scandir2json.service;

import com.yibin.nanxi.scandir2json.entity.DirTree;

/**
 * Created by LongFF on 2018/8/26
 */
public interface ScanDirService {

    DirTree getDirTree(String path);
}
