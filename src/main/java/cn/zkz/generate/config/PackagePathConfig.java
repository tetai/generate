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

    private String queryPagePath;
    private String queryPageHandlerPath;

    private String gatewayPath;
    private String gatewayImplPath;

    private String do2DtoPath;
    private String entity2DtoPath;
    private String do2EntityPath;

    public static void buildData(Properties properties) {
        PackagePathConfig condif = newInstance();
        String basePath = "";

        condif.basePath = properties.getProperty("path.basepath");
        String aggregation = properties.getProperty("aggregation.name");

        condif.adapterPath = properties.getProperty("path.adapterModulePath");
        condif.appPath = properties.getProperty("path.appModulePath");
        condif.clientPath = properties.getProperty("path.clientModulePath");
        condif.domainPath = properties.getProperty("path.domainModulePath");
        condif.infraPath = properties.getProperty("path.infrastructureModulePath");


        condif.doPath = basePath + properties.getProperty("path.doPath").replaceAll("\\$\\{aggregation.name\\}", aggregation);
        condif.entityPath = basePath + properties.getProperty("path.entityPath").replaceAll("\\$\\{aggregation.name\\}", aggregation);
        condif.dtoPath = basePath + properties.getProperty("path.dtoPath").replaceAll("\\$\\{aggregation.name\\}", aggregation);
        condif.controlPath = basePath + properties.getProperty("path.controlPath").replaceAll("\\$\\{aggregation.name\\}", aggregation);
        condif.servicePath = basePath + properties.getProperty("path.servicePath").replaceAll("\\$\\{aggregation.name\\}", aggregation);
        condif.serviceImplPath = basePath + properties.getProperty("path.serviceImplPath").replaceAll("\\$\\{aggregation.name\\}", aggregation);
        condif.xmlPath = basePath + properties.getProperty("path.xmlPath").replaceAll("\\$\\{aggregation.name\\}", aggregation);
        condif.mapperPath = basePath + properties.getProperty("path.mapperPath").replaceAll("\\$\\{aggregation.name\\}", aggregation);
        condif.domainServicePath = basePath + properties.getProperty("path.domainServicePath").replaceAll("\\$\\{aggregation.name\\}", aggregation);
        condif.queryPagePath = basePath + properties.getProperty("path.queryPagePath").replaceAll("\\$\\{aggregation.name\\}", aggregation);
        condif.queryPageHandlerPath = basePath + properties.getProperty("path.queryPageHandlerPath").replaceAll("\\$\\{aggregation.name\\}", aggregation);
        condif.gatewayPath = basePath + properties.getProperty("path.gatewayPath").replaceAll("\\$\\{aggregation.name\\}", aggregation);
        condif.gatewayImplPath = basePath + properties.getProperty("path.gatewayImplPath").replaceAll("\\$\\{aggregation.name\\}", aggregation);
        condif.do2EntityPath = basePath + properties.getProperty("path.do2EntityPath").replaceAll("\\$\\{aggregation.name\\}", aggregation);
        condif.do2DtoPath = basePath + properties.getProperty("path.do2DtoPath").replaceAll("\\$\\{aggregation.name\\}", aggregation);
        condif.entity2DtoPath = basePath + properties.getProperty("path.entity2DtoPath").replaceAll("\\$\\{aggregation.name\\}", aggregation);
    }

}
