package com.yibin.nanxi.scandir2json.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.springframework.util.PropertyPlaceholderHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by LongFF on 2018/9/13
 */
public class PlaceHolderUtil {

    private static final String placeholderPrefix = "${";

    private static final String placeholderSuffix = "}";

    private static PropertyPlaceholderHelper placeHolder = new PropertyPlaceholderHelper(placeholderPrefix,placeholderSuffix);

    public static String replacePlaceHolders(JSONObject jsonForFill) throws IOException {
        InputStream resourceAsStream = PlaceHolderUtil.class.getClassLoader().getResourceAsStream("templates/pipeline_prefix.json");
        String json = IOUtils.toString(resourceAsStream, "utf-8");
        String replacedJson = placeHolder.replacePlaceholders(json, jsonForFill::getString);
        return replacedJson;
    }


}
