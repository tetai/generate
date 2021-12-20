package cn.zkz.generate.core;

import cn.zkz.generate.config.GlobalConfig;
import cn.zkz.generate.config.PackagePathConfig;
import cn.zkz.generate.model.CodeType;
import cn.zkz.generate.model.TableField;
import cn.zkz.generate.model.TableInfo;
import cn.zkz.generate.utils.CommonUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;



import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @Desc
 * @Author zkz
 * @Date 2021/12/20
 */
public class FileGenerate {

    public static Map<String, Object> handleData(TableInfo tableInfo) {
        Map data = new HashMap();
        PackagePathConfig packagePathConfig = PackagePathConfig.newInstance();
        GlobalConfig globalConfig = GlobalConfig.newInstance();

        data.put("entityPackage", packagePathConfig.getEntityPath());//实体的包名
        data.put("controllerPackage", packagePathConfig.getControlPath());
        data.put("servicePackage", packagePathConfig.getServicePath());
        data.put("serviceImplPackage", packagePathConfig.getServiceImplPath());
        data.put("mapperPackage",packagePathConfig.getMapperPath());
        //移除表前缀，表名之间的下划线，得到实体类型
        String entity = CommonUtils.getNoUnderlineStr(CommonUtils.removePrefix(tableInfo.getName().toLowerCase(), new String[]{globalConfig.getDeletePreSuffix()}));
        data.put("entity", StringUtils.capitalize(entity));//实体名称
        data.put("author", globalConfig.getAuthor());//创建作者
        data.put("date",  CommonUtils.getFormatTime("yyyy-MM-dd", new Date() ));//创建时间
        data.put("table", tableInfo);//表信息
        boolean isKeyflag = false;
        for (TableField field:tableInfo.getFields()) {
            if(field.isKeyIdentityFlag()){//获取主键字段信息
                data.put("tbKey", field.getName());
                data.put("tbKeyType", field.getPropertyType());
                isKeyflag = true;
                break;
            }
        }
        if(!isKeyflag){
            throw new RuntimeException(String.format("[%s]表缺少主键，不能没有主键",tableInfo.getName()));
        }
        return data;
    }
    public static Configuration getConfiguration() {
        Configuration cfg = new Configuration();

        cfg.setClassForTemplateLoading(FileGenerate.class, GlobalConfig.newInstance().getTemplatepath());
        cfg.setLocale(Locale.CHINA);
        cfg.setDefaultEncoding("UTF-8");
        return cfg;
    }
    public static void generate(Map<String, Object> data, String templateFileName, Integer type) throws Exception {
        String entityName = data.get("entity").toString();
        String fileNamePath = getCodePath(type, entityName);//获取生成的文件路径
        System.out.println("fileNamePath:"+fileNamePath);
        String fileDir = StringUtils.substringBeforeLast(fileNamePath, "/");
        Template template = getConfiguration().getTemplate(templateFileName);//获取模版信息
        String s = fileDir.replaceAll("\\.", "/");
        FileUtils.forceMkdir(new File(s + "/"));
        String filePath = s + "/" + StringUtils.substringAfterLast(fileNamePath, "/");
        Writer out = new OutputStreamWriter(
                new FileOutputStream(filePath), "UTF-8");//生成的文件编码
        template.process(data, out);//结合模版生成代码文件
        out.close();
    }

    public static String getCodePath(Integer codeType, String entityName) {
        PackagePathConfig packagePathConfig = PackagePathConfig.newInstance();
        StringBuilder path = new StringBuilder().append(packagePathConfig.getBasePath());
//        String javaPath = "src\\main\\java\\";
        String javaPath = "test\\java\\";
        if(CodeType.DO.getVal().equals(codeType)) {
            //包名 package.path
            path.append(javaPath).append(packagePathConfig.getDoPath()).append("/").append(entityName).append("DO").append(".java");
        }else if(CodeType.ENTITY.getVal().equals(codeType)){
            //包名 package.path
            path.append(javaPath).append(packagePathConfig.getEntityPath()).append("/").append(entityName).append("Entity").append(".java");
        }else if(CodeType.DTO.getVal().equals(codeType)){
            //包名 package.path
            path.append(javaPath).append(packagePathConfig.getDtoPath()).append("/").append(entityName).append("DTO").append(".java");
        }else if(CodeType.MAPPER.getVal().equals(codeType)){
            //包名 package.path
            path.append(javaPath).append(packagePathConfig.getMapperPath()).append("/").append(entityName).append("Mapper").append(".java");
        }else if(CodeType.XML.getVal().equals(codeType)){
            //包名 package.path
            path.append("resources\\").append(packagePathConfig.getXmlPath()).append("/")
                    .append(entityName).append("Mapper").append(".xml");

        }else if(CodeType.SERVICE.getVal().equals(codeType)){
            //包名 package.path
            path.append(javaPath).append(packagePathConfig.getServicePath()).append("/");
            //文件名
            path.append(entityName).append("Service").append(".java");
        }else if(CodeType.SERVICEIMPL.getVal().equals(codeType)){
            //包名 package.path
            path.append(javaPath).append(packagePathConfig.getServiceImplPath()).append("/");
            //文件名
            path.append(entityName).append("ServiceImpl").append(".java");
        }else if(CodeType.CONTORL.getVal().equals(codeType)){
            //包名 package.path
            path.append(javaPath).append(packagePathConfig.getControlPath()).append("/");
            //文件名
            path.append(entityName).append("Controller").append(".java");
        }else{
            throw new IllegalArgumentException("type is not found");
            //其他类型文件生成
        }

        return path.toString();
    }

}
