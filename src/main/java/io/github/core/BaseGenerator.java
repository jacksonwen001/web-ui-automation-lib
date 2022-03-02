package io.github.core;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.FileWriter;
import java.io.IOException;

public class BaseGenerator {
    Template template;
    VelocityContext context = new VelocityContext();

    public BaseGenerator(String fileName) {
        // 初始化模板引擎
        VelocityEngine ve = new VelocityEngine();
        // 设置模板加载路径，这里设置的是class下
        ve.setProperty(Velocity.RESOURCE_LOADER, "class");
        ve.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        // 进行初始化操作
        ve.init();
        template = ve.getTemplate(fileName);
    }

    public void run() throws IOException {
        FileWriter fw  = new FileWriter("Test.java");
        template.merge(context, fw);
        fw.close();
    }
}
