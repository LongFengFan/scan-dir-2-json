package com.yibin.nanxi.scandir2json.entity;


import java.io.Serializable;

/**
 * Created by LongFF on 2018/7/15
 */
public class FileInfo implements Serializable {
    private String id;
    private String name;
    private String location;
    private String url;

    public String getId() {
        return id;
    }

    public FileInfo setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public FileInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public FileInfo setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public FileInfo setUrl(String url) {
        this.url = url;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"location\":\"")
                .append(location).append('\"');
        sb.append(",\"url\":\"")
                .append(url).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
