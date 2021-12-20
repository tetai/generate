package cn.zkz.generate.config;

import lombok.Data;
import lombok.ToString;

import java.util.Properties;

/**
 * @Desc
 * @Author zkz
 * @Date 2021/12/16
 */
@Data
@ToString
public class GlobalConfig {
    private final static GlobalConfig globalConfig = new GlobalConfig();

    public static GlobalConfig newInstance() {

        return globalConfig;
    }

    private GlobalConfig(){}

    private String author;

    private String tables;

    private String deletePreSuffix;

    private String templatepath;

    public static void bulidData(Properties properties) {
        GlobalConfig globalConfig = newInstance();

        globalConfig.author = properties.getProperty("author");
        globalConfig.tables = properties.getProperty("tables");
        globalConfig.deletePreSuffix = properties.getProperty("deletePreSuffix");
        globalConfig.templatepath = properties.getProperty("templatepath");

    }

}
