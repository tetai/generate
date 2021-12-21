package cn.zkz.generate.core;

import cn.zkz.generate.Start;
import cn.zkz.generate.config.DatabaseConfig;
import cn.zkz.generate.config.GlobalConfig;
import cn.zkz.generate.config.PackagePathConfig;
import cn.zkz.generate.database.InitMetaData;
import cn.zkz.generate.model.TableField;
import cn.zkz.generate.model.TableInfo;
import cn.zkz.generate.utils.TemplateUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Desc
 * @Author zkz
 * @Date 2021/12/16
 */
public class CodeGenerate {

    public static void loadProperties() throws IOException {
        // 封装properties数据
        Properties properties = new Properties();

        InputStream resourceAsStream = Start.class.getClass().getResourceAsStream("/application.properties");
        properties.load(resourceAsStream);

        DatabaseConfig.buildData(properties);
        PackagePathConfig.buildData(properties);
        GlobalConfig.bulidData(properties);
        TemplateUtils.handler(properties);

    }

    /**
     * 1、初始化配置文件
     * 2、加载表结构
     * 3、替换文本里占位符
     * 4、输出文件
     */
    public static void codeGenerate() throws Exception {

        loadProperties();
        GlobalConfig globalConfig = GlobalConfig.newInstance();
        String tables = globalConfig.getTables();
        if (StringUtils.isEmpty(tables)) {
            throw new RuntimeException("没有填写表名");
        }

        String[] split = tables.split(",");
        for (String str : split) {
            List<TableField> tableField = InitMetaData.getTableField(str);
            TableInfo tableInfo = new TableInfo();
            tableInfo.setName(str);
            tableInfo.setFields(tableField);

            Map<String, Object> data = TemplateUtils.handleData(tableInfo);

            for (TemplateUtils.Template template : TemplateUtils.templates) {
                if (template.getIsGenerate()) {
                    FileGenerate.generate(data, template);
                }
            }
        }

    }
}







