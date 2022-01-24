package io.github.utils;

import cn.hutool.setting.Setting;

/**
 * @author: Jackson.Wen
 */
public class ConfigUtils {
    private static final Setting app = new Setting("app.setting");
    public static String ENV = System.getProperty("env") == null ? "qa" : System.getProperty("env");

    public static String get(String group, String name) {
        return app.get(group, name);
    }

}
