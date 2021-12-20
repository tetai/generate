package cn.zkz.generate.utils;

import cn.zkz.generate.model.CodeType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author zkz
 * @Date 2021/12/20
 */
public class TemplateUtils {

    public static List<Template> templates = new ArrayList<Template>(){{
        add(new Template("controllerTemplate.ftl", CodeType.CONTORL.getVal()));
        add(new Template("entityTemplate.ftl", CodeType.ENTITY.getVal()));
        add(new Template("mapperTemplate.ftl", CodeType.MAPPER.getVal()));
        add(new Template("mapperXmlTemplate.ftl", CodeType.XML.getVal()));
        add(new Template("serviceImplTemplate.ftl", CodeType.SERVICEIMPL.getVal()));
        add(new Template("serviceTemplate.ftl", CodeType.SERVICE.getVal()));
        add(new Template("dtoTemplate.ftl", CodeType.DTO.getVal()));
        add(new Template("doTemplate.ftl", CodeType.DO.getVal()));


    }};

    @Data
    @AllArgsConstructor
    public static class Template {
        private String templateName;
        private Integer type;


    }


}
