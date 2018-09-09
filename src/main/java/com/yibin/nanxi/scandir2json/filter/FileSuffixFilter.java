package com.yibin.nanxi.scandir2json.filter;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

/**
 * Created by LongFF on 2018/8/26
 */
public class FileSuffixFilter implements FilenameFilter {
    private List<String> suffixs;

    public FileSuffixFilter(List suffixs) {
        this.suffixs = suffixs;
    }

    @Override
    public boolean accept(File dir, String name) {

        return suffixs.stream().anyMatch(name::endsWith);
    }
}
