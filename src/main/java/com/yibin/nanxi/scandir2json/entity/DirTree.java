package com.yibin.nanxi.scandir2json.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LongFF on 2018/8/26
 */
public class DirTree implements Serializable {

    private Map dirTreeMap = new LinkedHashMap();

    private List<FileInfo> fileInfos = new ArrayList<>();

    public Map getDirTreeMap() {
        return dirTreeMap;
    }

    public DirTree setDirTreeMap(Map dirTreeMap) {
        this.dirTreeMap = dirTreeMap;
        return this;
    }

    public List<FileInfo> getFileInfos() {
        return fileInfos;
    }

    public DirTree setFileInfos(List<FileInfo> fileInfos) {
        this.fileInfos = fileInfos;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"dirTreeMap\":")
                .append(dirTreeMap);
        sb.append(",\"fileInfos\":")
                .append(fileInfos);
        sb.append('}');
        return sb.toString();
    }
}
