package io.github.core;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.Method;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import io.github.core.browsers.Cookies;
import io.github.utils.JsonUtils;
import org.openqa.selenium.json.Json;
import org.yaml.snakeyaml.Yaml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseService {
    private Map<String, Object> yaml;
    private Cookies cookies = new Cookies();
    Map<String, Method> methods = new HashMap<>(16);
    Map<String, String> variables;
    List<Map<String, Object>> apis;

    /**
     * 初始化： 读取文件， 载入变量， 载入方法
     * @param fileName
     */
    public BaseService(String fileName) {
        yaml = new Yaml().load(ResourceUtil.getStream(fileName));
        System.out.println(yaml);
        methods.put("post", Method.POST);
        methods.put("get", Method.GET);
        methods.put("delete", Method.DELETE);
        methods.put("put", Method.PUT);
        variables = MapUtil.createMap(yaml.get("variables").getClass());
        apis = (List<Map<String, Object>>) yaml.get("apis");
    }

    public void setVariable(String key,String value) throws Exception {
        if(this.variables.containsKey(key)){
            throw new Exception("duplicate key!");
        }
        this.variables.put(key, value);
    }

    public String getCookies(String name) throws Exception {
        return cookies.getCookiesAsHeader(name);
    }
    public String getRealValue(String content){
        Pattern pattern = Pattern.compile("\\$\\{(.*?)\\}");
        Matcher matcher = pattern.matcher(content);
        if(matcher.find()) {
            return matcher.replaceAll(variables.get(matcher.group(1)));
        }
        return content;
    }
    public String url(Map<String, Object> api){
       Map<String, String> url = MapUtil.createMap(api.get("url").getClass());
       String baseUrl = getRealValue(url.get("base"));
       String path = getRealValue(url.get("path"));
       StringBuilder sb = new StringBuilder();
       return sb.append(baseUrl).append(path).toString();
    }

    public Map<String, String> headers(Map<String, Object> api) {
        Map<String, String> headers = MapUtil.createMap(api.get("headers").getClass());
        Map<String, String> realHeaders = new HashMap<>();
        if(headers == null) {
            return realHeaders;
        }
        headers.keySet().forEach(key -> {
            String value = getRealValue(headers.get(key));
            realHeaders.put(key, value);
        });

        return realHeaders;
    }

    public String body(Map<String, Object> api){
        Map<String, String> requestBody = (Map<String, String>) api.get("body");
        String json = ResourceUtil.readUtf8Str(getRealValue(requestBody.get("path")));
        JSON requestJson = JSONUtil.parse(json);
        if(api.containsKey("replace")){
            List<Map<String, String>> replaces = (List<Map<String, String>>) api.get("replaces");
            replaces.forEach(item -> {
                requestJson.putByPath(item.get("key"), getRealValue(item.get("val")));
            });
        }
        return JSONUtil.toJsonStr(requestJson);
    }

    public void storeResponse(Map<String, Object> api, HttpResponse response) {
        JSON responseJson = JSONUtil.parse(response.body());
        if(api.containsKey("response")){
            List<Map<String,String>> jsonPaths = (List<Map<String,String>>) api.get("response");
            jsonPaths.forEach( item -> {
                String key = item.get("name");
                String val = responseJson.getByPath(item.get("path")).toString();
                try {
                    setVariable(key,val);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void main(String[] args) throws Exception {
       BaseService baseService = new BaseService("api.yaml");
//       System.out.println(baseService.apis);
//       baseService.setVariable("name", "hello");
//       System.out.println(baseService.getRealValue("/${name}/xxxx"));

    }
}
