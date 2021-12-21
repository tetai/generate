package cn.zkz.generate.core;

import cn.zkz.generate.config.GlobalConfig;
import cn.zkz.generate.config.PackagePathConfig;
import cn.zkz.generate.model.CodeType;
import cn.zkz.generate.model.TableField;
import cn.zkz.generate.model.TableInfo;
import cn.zkz.generate.utils.CommonUtils;
import cn.zkz.generate.utils.TemplateUtils;
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


    public static Configuration getConfiguration() {
        Configuration cfg = new Configuration();

        cfg.setClassForTemplateLoading(FileGenerate.class, GlobalConfig.newInstance().getTemplatepath());
        cfg.setLocale(Locale.CHINA);
        cfg.setDefaultEncoding("UTF-8");
        return cfg;
    }
    public static void generate(Map<String, Object> data, TemplateUtils.Template t) throws Exception {

        String entityName = data.get("entity").toString();
        String fileNamePath = t.getPath().replaceAll("\\$\\{entityName\\}", entityName);
        System.out.println("fileNamePath:"+fileNamePath);
        String fileDir = StringUtils.substringBeforeLast(fileNamePath, "/");
        Template template = getConfiguration().getTemplate(t.getTemplateName());//获取模版信息
        String s = fileDir.replaceAll("\\.", "/");
        FileUtils.forceMkdir(new File(s + "/"));
        String filePath = s + "/" + StringUtils.substringAfterLast(fileNamePath, "/");
        Writer out = new OutputStreamWriter(
                new FileOutputStream(filePath), "UTF-8");//生成的文件编码
        template.process(data, out);//结合模版生成代码文件
        out.close();
    }

    public static void main(String[] args) {
        String ss = "sssk${dd}dfdsg";
        String res = ss.replaceAll("\\$\\{dd\\}", "zkz");
        System.out.println(res);
    }

}
