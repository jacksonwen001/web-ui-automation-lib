package io.github.utils;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

import static io.github.utils.ConfigUtils.ENV;

/**
 * @author: Jackson.Wen
 */
public class JsonUtils extends JSONUtil {

    public static String getContent(String path){
        return ResourceUtil.readUtf8Str(String.format(path, ENV));
    }

    public static String getContent(String path, String name){
        return ResourceUtil.readUtf8Str(String.format(path, ENV, name));
    }

    public static String get(Object json, String path) {
        return parse(json).getByPath(path).toString();
    }

}
