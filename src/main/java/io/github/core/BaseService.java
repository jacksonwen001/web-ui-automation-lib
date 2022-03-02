package io.github.core;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Getter
public class BaseService {
    final static Logger logger = LoggerFactory.getLogger(BaseService.class);
    private final Map<String, Object> yaml;
    private final BaseCookies baseCookies = new BaseCookies();
    Map<String, Method> methods = new HashMap<>(16);
    Map<String, String> variables;
    List<Map<String, Object>> apis;

    /**
     * 初始化： 读取文件， 载入变量， 载入方法
     *
     * @param fileName
     */
    public BaseService(String fileName) {
        yaml = new Yaml().load(ResourceUtil.getStream(fileName));
        methods.put("post", Method.POST);
        methods.put("get", Method.GET);
        methods.put("delete", Method.DELETE);
        methods.put("put", Method.PUT);
        this.variables = (Map<String, String>) yaml.get("variables");
        apis = (List<Map<String, Object>>) yaml.get("apis");
    }

    public void setVariable(String key, String value) throws Exception {
        this.variables.put(key, value);
    }

    public String getName(Map<String, Object> api) {
        String name = Optional.ofNullable(api.get("name").toString()).orElse("");
        logger.info("执行:【 " + name + " 】API");
        return name;
    }


    public String getDescription(Map<String, Object> api) {
        String desc = Optional.ofNullable(api.get("desc").toString()).orElse("");
        logger.info("描述:【 " + desc + " 】");
        return desc;
    }

    public Object getRealValue(Object content) {
        if (!(content instanceof String)) {
            return content;
        }
        Pattern pattern = Pattern.compile("\\$\\{(.*?)\\}");
        Matcher matcher = pattern.matcher(content.toString());
        if (matcher.find()) {
            // System.out.println(matcher.group(1));
            return matcher.replaceAll(variables.get(matcher.group(1)));
        }
        return content;
    }

    public Boolean condition(Map<String, Object> api) {
        if (!api.containsKey("conditions")) {
            return true;
        }

        Map<String, Object> conditions = (Map<String, Object>) api.get("conditions");
        Boolean flag = false;
        for (String key : conditions.keySet()) {
            if (key.equalsIgnoreCase("eq")) {
                String[] values = getRealValue(conditions.get(key)).toString().split(",");
                flag = values[0].equals(values[1]);
            }
        }
        return flag;
    }

    public String url(Map<String, Object> api) {
        Map<String, String> url = (Map<String, String>) api.get("url");
        String baseUrl = getRealValue(url.get("base")).toString();
        String path = getRealValue(url.get("path")).toString();
        StringBuilder sb = new StringBuilder();
        return sb.append(baseUrl).append(path).toString();
    }

    public Method getMethod(Map<String, Object> api) {
        return this.methods.get(api.get("method"));
    }

    public Map<String, String> headers(Map<String, Object> api) {
        if (!api.containsKey("headers")) {
            return null;
        }
        Map<String, String> headers = (Map<String, String>) api.get("headers");
        Map<String, String> realHeaders = new HashMap<>();
        headers.keySet().forEach(key -> {
            String value = getRealValue(headers.get(key)).toString();
            realHeaders.put(key, value);
        });
        logger.info("headers: " + realHeaders);
        return realHeaders;
    }

    public String body(Map<String, Object> api) {
        if (!api.containsKey("body")) {
            return null;
        }

        Map<String, Object> requestBody = (Map<String, Object>) api.get("body");
        String json = ResourceUtil.readUtf8Str(getRealValue(requestBody.get("path").toString()).toString());
        JSON requestJson = JSONUtil.parse(json);
        if (requestBody.containsKey("replaces")) {
            List<Map<String, String>> replaces = (List<Map<String, String>>) requestBody.get("replaces");
            replaces.forEach(replace -> {
                String key = replace.get("path");
                Object val = getRealValue(replace.get("val"));
                requestJson.putByPath(key, val);
            });
        }
        String bodyStr = JSONUtil.toJsonStr(requestJson);
        logger.info("body: " + bodyStr);
        return bodyStr;
    }

    public void storeResponse(Map<String, Object> api, HttpResponse response) {
        logger.info("返回: " + response.body());
        JSON responseJson = JSONUtil.parse(response.body());
        if (api.containsKey("response")) {
            Map<String, Object> responseMap = (Map<String, Object>) api.get("response");

            if (responseMap.containsKey("status")) {
                assertThat(response.getStatus()).isEqualTo(responseMap.get("status"));
            }

            if (responseMap.containsKey("store")) {
                List<Map<String, String>> jsonPaths = (List<Map<String, String>>) responseMap.get("store");
                jsonPaths.forEach(item -> {
                    String key = item.get("name");
                    String val = responseJson.getByPath(item.get("path")).toString();
                    try {
                        setVariable(key, val);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }

    public void run() {
        for (Map<String, Object> api : this.apis) {
            Boolean condition = this.condition(api);
            if (!condition) {
                continue;
            }
            String name = this.getName(api);
            String desc = this.getDescription(api);

            String url = this.url(api);
            Method method = this.getMethod(api);
            Map<String, String> headers = this.headers(api);
            String body = this.body(api);
            HttpRequest request = HttpUtil.createRequest(method, url);
            if (headers != null) {
                request.addHeaders(headers);
            }
            if (body != null) {
                request.body(body);
            }
            HttpResponse response = request.execute();
            this.storeResponse(api, response);
        }
    }
}
