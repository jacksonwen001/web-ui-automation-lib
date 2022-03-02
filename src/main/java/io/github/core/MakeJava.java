package io.github.core;


import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MakeJava {
    public static void main(String[] args) throws IOException {
        // 初始化模板引擎
        VelocityEngine ve = new VelocityEngine();
        // 设置模板加载路径，这里设置的是class下
        ve.setProperty(Velocity.RESOURCE_LOADER, "class");
        ve.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        // 进行初始化操作
        ve.init();
        Template template = ve.getTemplate("java.vm");
        VelocityContext context = new VelocityContext();
        String packageName = "com.chancetop";
        String className = "TestCase";
        String author = "Jackson Wen";
        String datetime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-mm-dd HH:MM:ss"));
        String version = "1.0.0";
        context.put("packageName", packageName);
        context.put("className", className);
        context.put("author", author);
        context.put("version", version);
        context.put("datetime", datetime);

        List<Map<String, String>> cases = new ArrayList<>();
        Map<String, String> testCase = new HashMap<>(16);
        testCase.put("story", "aaaa");
        testCase.put("testDescription", "test desc");
        testCase.put("issue", "AT-11111");
        testCase.put("methodName", "testxxxx");
        testCase.put("description", "xaaaaaaaa");

        cases.add(testCase);
        context.put("cases", cases);
        FileWriter fw  = new FileWriter("Test.java");
        template.merge(context, fw);
        fw.close();
    }
}
