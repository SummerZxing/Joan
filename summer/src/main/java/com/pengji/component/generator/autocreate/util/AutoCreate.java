package com.pengji.component.generator.autocreate.util;

import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.Map;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;

import com.pengji.component.generator.autocreate.beetl.GroupTemplateFactory;
import com.pengji.component.generator.autocreate.beetl.TemplateUtils;
import com.pengji.component.generator.autocreate.template.CRUD;
import com.pengji.config.Config;
import com.pengji.utils.DateUtils;
import com.pengji.utils.FileUtils;
import com.pengji.utils.StrUtils;


public class AutoCreate {

    private final GroupTemplate groupTemplate = GroupTemplateFactory.getClasspath();

    // 需设置
    private String rootPath = System.getProperty("user.dir");
    private String outputPath = rootPath + "/" +  Config.getStr("template.output.path");
    private String templatePath = Config.getStr("template.path.jfinal_cms");
    private String packagePath = "com.pengji.modules";
    private String module = "summer";
    private Map<String, CRUD> crudMap;

    public void create() throws Exception {
        System.out.println("####user.dir path:" + System.getProperty("user.dir"));
        System.out.println("####rootPath path:" + rootPath);
        System.out.println("####output path:" + rebuild(outputPath));
        System.out.println("####template path:" + rebuild(templatePath));
        createCode();
    }

    private void createCode() throws Exception {
        if (crudMap == null) {
            System.err.println("###crudMap为null，请参考其他配置数据信息。");
            return;
        }

        System.out.println("####生成模板开始...");
        init();
        String path, html;
        System.out.print("####创建文件：");

        if (crudMap != null) {
            for (CRUD crud : crudMap.values()) {
                System.out.print("\t" + crud.getUrlKey() + ".....");
                

                path = rebuild(outputPath); //  "/" + crud.getUrlKey()
                if (!new File(path).exists()) {
                    new File(path).mkdirs();
                }

                if (groupTemplate != null)
                    groupTemplate.close();
                List<String> pageList = FileUtils.findFileNames(rootPath + templatePath,
                        new FileFilter() {

                            public boolean accept(File pathname) {
                                // 有后缀就处理
                                return pathname.getName().indexOf(".") > 0;
                            }
                        });

                for (String name : pageList) {
                    html = TemplateUtils.getStr(templatePath + name, "crud", crud //
                            , "now", DateUtils.getNow(), "packagePath", this.packagePath, "module", this.module);

                    // 文件名处理
                    String fileName = GroupTemplateFactory.getFileName(crud, name, this.module);
                    String filePath = path + "/" + fileName;
                    if (!new File(filePath).getParentFile().exists()) {
                        new File(filePath).getParentFile().mkdirs();
                    }
                    //  写文件
                    FileUtils.write(filePath, html.getBytes("UTF-8"));
                }
            }
            System.out.println();
        }

        System.out.println("####生成模板完成。");
    }

    public void init() {
        // 模板配置和函数加载，区分别的模板~生成没冲突
        groupTemplate.registerFunctionPackage("flyfox", TemplateUtils.class);
        groupTemplate.registerFunctionPackage("strutils", StrUtils.class);
        Configuration cfg = groupTemplate.getConf();

        cfg.setStatementStart(Config.getStr("beetl.statementStart"));
        cfg.setStatementEnd(Config.getStr("beetl.statementEnd"));
        cfg.setPlaceholderStart(Config.getStr("beetl.placeholderStart"));
        cfg.setPlaceholderEnd(Config.getStr("beetl.placeholderEnd"));
    }


    public AutoCreate setRootPath(String rootPath) {
        this.rootPath = rootPath;
        return this;
    }

    public AutoCreate setOutputPath(String outputPath) {
        this.outputPath = outputPath;
        return this;
    }

    public AutoCreate setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
        return this;
    }

    public AutoCreate setPackagePath(String packagePath) {
        this.packagePath = packagePath;
        return this;
    }

    public AutoCreate setModule(String module) {
        this.module = module;
        return this;
    }

    public AutoCreate setCrudMap(Map<String, CRUD> crudMap) {
        this.crudMap = crudMap;
        return this;
    }

    public static String rebuild(String path) {
        String newPath = path;
        newPath = newPath.replaceAll("\\\\", "/");
        newPath = newPath.replaceAll("//", "/");

        return newPath;
    }
}
