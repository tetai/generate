package cn.zkz.generate.utils;

import cn.zkz.generate.config.GlobalConfig;
import cn.zkz.generate.config.PackagePathConfig;
import cn.zkz.generate.model.CodeType;
import cn.zkz.generate.model.TableField;
import cn.zkz.generate.model.TableInfo;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Desc
 * @Author zkz
 * @Date 2021/12/20
 */
public class TemplateUtils {

    public static List<Template> templates = new ArrayList<>();

    public static void handler(Properties properties) {
        PackagePathConfig packagePathConfig = PackagePathConfig.newInstance();
        StringBuilder path = new StringBuilder().append(packagePathConfig.getBasePath());
        String javaPath = "src\\main\\java\\";

        StringBuilder doPath = new StringBuilder(path).append(packagePathConfig.getInfraPath()).append("/").append(javaPath).append(packagePathConfig.getDoPath()).append("/").append("${entityName}").append("DO").append(".java");
        StringBuilder entity = new StringBuilder(path).append(packagePathConfig.getDomainPath()).append("/").append(javaPath).append(packagePathConfig.getEntityPath()).append("/").append("${entityName}").append("Entity").append(".java");
        StringBuilder dto = new StringBuilder(path).append(packagePathConfig.getClientPath()).append("/").append(javaPath).append(packagePathConfig.getDtoPath()).append("/").append("${entityName}").append("DTO").append(".java");
        StringBuilder mapper = new StringBuilder(path).append(packagePathConfig.getInfraPath()).append("/").append(javaPath).append(packagePathConfig.getMapperPath()).append("/").append("${entityName}").append("Mapper").append(".java");
        StringBuilder mapper1 = new StringBuilder(path).append(packagePathConfig.getInfraPath()).append("/").append("src\\main\\resources\\").append(packagePathConfig.getXmlPath()).append("/")
                .append("${entityName}").append("Mapper").append(".xml");
        StringBuilder serviceImpl = new StringBuilder(path).append(packagePathConfig.getAppPath()).append("/").append(javaPath).append(packagePathConfig.getServiceImplPath()).append("/")
                .append("${entityName}").append("ServiceImpl").append(".java");
        StringBuilder controller = new StringBuilder(path).append(packagePathConfig.getAdapterPath()).append("/").append(javaPath).append(packagePathConfig.getControlPath()).append("/")
                .append("${entityName}").append("Controller").append(".java");
        StringBuilder service = new StringBuilder(path).append(packagePathConfig.getClientPath()).append("/").append(javaPath).append(packagePathConfig.getServicePath()).append("/")
                .append("${entityName}").append("Service").append(".java");

        StringBuilder domianService = new StringBuilder(path).append(packagePathConfig.getDomainPath()).append("/").append(javaPath).append(packagePathConfig.getDomainServicePath()).append("/")
                .append("${entityName}").append("DomianService").append(".java");

        StringBuilder pageQuery = new StringBuilder(path).append(packagePathConfig.getClientPath()).append("/").append(javaPath).append(packagePathConfig.getQueryPagePath()).append("/")
                .append("${entityName}").append("PageQuery").append(".java");
        StringBuilder pageQueryHandler = new StringBuilder(path).append(packagePathConfig.getAppPath()).append("/").append(javaPath).append(packagePathConfig.getQueryPageHandlerPath()).append("/")
                .append("${entityName}").append("PageQueryHandler").append(".java");

        StringBuilder gateway = new StringBuilder(path).append(packagePathConfig.getDomainPath()).append("/").append(javaPath).append(packagePathConfig.getGatewayPath()).append("/")
                .append("${entityName}").append("Gateway").append(".java");
        StringBuilder gatewayImpl = new StringBuilder(path).append(packagePathConfig.getInfraPath()).append("/").append(javaPath).append(packagePathConfig.getGatewayImplPath()).append("/")
                .append("${entityName}").append("GatewayImpl").append(".java");


        StringBuilder do2dto = new StringBuilder(path).append(packagePathConfig.getAppPath()).append("/").append(javaPath).append(packagePathConfig.getDo2DtoPath()).append("/")
                .append("${entityName}").append("DtoDoConvert").append(".java");
        StringBuilder do2entity = new StringBuilder(path).append(packagePathConfig.getInfraPath()).append("/").append(javaPath).append(packagePathConfig.getDo2EntityPath()).append("/")
                .append("${entityName}").append("EntityDoConvert").append(".java");
      StringBuilder entity2dto = new StringBuilder(path).append(packagePathConfig.getAppPath()).append("/").append(javaPath).append(packagePathConfig.getEntity2DtoPath()).append("/")
                .append("${entityName}").append("DtoEntityConvert").append(".java");


        templates.add(new Template("controllerTemplate.ftl", CodeType.CONTORL.getVal(), controller.toString()));
        templates.add(new Template("entityTemplate.ftl", CodeType.ENTITY.getVal(), entity.toString()));
        templates.add(new Template("mapperTemplate.ftl", CodeType.MAPPER.getVal(), mapper.toString()));
        templates.add(new Template("mapperXmlTemplate.ftl", CodeType.XML.getVal(), mapper1.toString()));
        templates.add(new Template("serviceImplTemplate.ftl", CodeType.SERVICEIMPL.getVal(),serviceImpl.toString()));
        templates.add(new Template("serviceTemplate.ftl", CodeType.SERVICE.getVal(), service.toString()));
        templates.add(new Template("dtoTemplate.ftl", CodeType.DTO.getVal(), dto.toString()));
        templates.add(new Template("doTemplate.ftl", CodeType.DO.getVal(), doPath.toString()));
        templates.add(new Template("queryPageTemplate.ftl", CodeType.PAGEQUERY.getVal(), pageQuery.toString()));
        templates.add(new Template("queryPageHandlerTemplate.ftl", CodeType.PAGEQUERYHANDLER.getVal(), pageQueryHandler.toString()));
        templates.add(new Template("domainServiceTemplate.ftl", CodeType.DOMAIN.getVal(), domianService.toString()));
        templates.add(new Template("gatewayImplTemplate.ftl", CodeType.GATEWAYIMPL.getVal(), gatewayImpl.toString()));
        templates.add(new Template("gatewayTemplate.ftl", CodeType.GATEWAY.getVal(), gateway.toString()));
        templates.add(new Template("doDtoConvertTemplate.ftl", CodeType.DO2DTO.getVal(), do2dto.toString()));
        templates.add(new Template("doEntityConvertTemplate.ftl", CodeType.DO2ENTITY.getVal(), do2entity.toString()));
        templates.add(new Template("entityDtoConvertTemplate.ftl", CodeType.ENTITY2DTO.getVal(), entity2dto.toString()));

        String property = properties.getProperty("ignore.tempate");
        if (StringUtils.isEmpty(property)) {
            return;
        }
        String[] split = property.split(",");
        templates.forEach(x -> {
            for (String str : split) {
                if (Objects.equals(x.getTemplateName(), str)) {
                    x.setIsGenerate(false);
                }
            }
        });

    }
    public static Map<String, Object> handleData(TableInfo tableInfo) {

        PackagePathConfig packagePathConfig = PackagePathConfig.newInstance();
        GlobalConfig globalConfig = GlobalConfig.newInstance();

        Map data = JSONObject.parseObject(JSONObject.toJSONString(packagePathConfig), Map.class);


        //移除表前缀，表名之间的下划线，得到实体类型
        String entity = CommonUtils.getNoUnderlineStr(CommonUtils.removePrefix(tableInfo.getName().toLowerCase(), new String[]{globalConfig.getDeletePreSuffix()}));
        data.put("entity", StringUtils.capitalize(entity));//实体名称
        data.put("author", globalConfig.getAuthor());//创建作者
        data.put("date",  CommonUtils.getFormatTime("yyyy-MM-dd", new Date() ));//创建时间
        data.put("table", tableInfo);//表信息

        String dateStr = new SimpleDateFormat("MMddHHmm").format(new Date());
        int i = (packagePathConfig.getDtoPath() + StringUtils.capitalize(entity) + "DTO").hashCode();

        long l1 = Long.parseLong( i + dateStr);
        data.put("serialVersionUID", l1);
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
    @Data
    public static class Template {
        private String templateName;
        private String type;
        private Boolean isGenerate;
        private String path;
        private Template(){}
        public Template(String templateName, String type, String path) {
            this.templateName = templateName;
            this.type = type;
            this.path = path;
            this.isGenerate = true;
        }
    }


}
