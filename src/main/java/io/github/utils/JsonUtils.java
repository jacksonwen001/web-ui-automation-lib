package io.github.utils;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;
import io.github.core.BaseConfig;
import org.aeonbits.owner.ConfigFactory;


/**
 * @author: Jackson.Wen
 */
public class JsonUtils extends JSONUtil {
    private static final BaseConfig config = ConfigFactory.create(BaseConfig.class);

    public static String getContent(String path) {
        return ResourceUtil.readUtf8Str(String.format(path, config.env()));
    }

    public static String getContent(String path, String name) {
        return ResourceUtil.readUtf8Str(String.format(path, config.env(), name));
    }

    public static String get(Object json, String path) {
        return parse(json).getByPath(path).toString();
    }

}
