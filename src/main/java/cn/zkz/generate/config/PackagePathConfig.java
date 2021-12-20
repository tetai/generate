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
public class PackagePathConfig {

    private static final PackagePathConfig packagePathConfig = new PackagePathConfig();

    private PackagePathConfig(){}

    public static PackagePathConfig newInstance() {
        return packagePathConfig;
    }
    private String basePath;

    private String adapterPath;
    private String appPath;
    private String clientPath;
    private String domainPath;
    private String infraPath;

    private String doPath;

    private String entityPath;

    private String dtoPath;

    private String controlPath;

    private String servicePath;

    private String serviceImplPath;

    private String xmlPath;

    private String mapperPath;

    private String domainServicePath;

    public static void buildData(Properties properties) {
        PackagePathConfig condif = newInstance();
        String basePath = "";

        condif.basePath = properties.getProperty("path.basepath");

        condif.adapterPath = properties.getProperty("path.adapterModulePath");
        condif.appPath = properties.getProperty("path.appModulePath");
        condif.clientPath = properties.getProperty("path.clientModulePath");
        condif.domainPath = properties.getProperty("path.domainModulePath");
        condif.infraPath = properties.getProperty("path.infrastructureModulePath");


        condif.doPath = basePath + properties.getProperty("path.doPath");
        condif.entityPath = basePath + properties.getProperty("path.entityPath");
        condif.dtoPath = basePath + properties.getProperty("path.dtoPath");
        condif.controlPath = basePath + properties.getProperty("path.controlPath");
        condif.servicePath = basePath + properties.getProperty("path.servicePath");
        condif.serviceImplPath = basePath + properties.getProperty("path.serviceImplPath");
        condif.xmlPath = basePath + properties.getProperty("path.xmlPath");
        condif.mapperPath = basePath + properties.getProperty("path.mapperPath");
        condif.domainServicePath = basePath + properties.getProperty("path.domainServicePath");
    }

}
