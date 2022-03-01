package io.github.data;

import cn.hutool.core.io.resource.ResourceUtil;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;

public class YamlParse {
    private final Map<String, Object> yamlContent;
    /**
     * 声明 YAML 关键字
     */
    private final String variables = "variables";
    private final String apis = "apis";
    private final String returns = "returns";
    private final String name = "name";
    private final String desc = "desc";
    private final String url = "url";
    private final String headers = "headers";
    private final String method = "method";
    private final String body = "body";
    private final String path = "path";
    private final String replaces = "replaces";
    private final String response = "response";

    public YamlParse(String fileName) {
        Yaml yaml = new Yaml();
        this.yamlContent = yaml.load(ResourceUtil.getStream(fileName));
    }


}
