package com.template.mybatis;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.toolkit.PackageHelper;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成工具
 * 原则上只生成一次, 新添加字段时只需要在对应的entity中添加属性, 同时更新对应的xml中的BaseResultMap和BaseColumnList
 * entity:属性与表字段一一对应, 不能添加自定义属性
 * dao/xml:可添加自定义方法
 * <p>
 * 配置时只需要更新静态字段值
 */
public class MyBatisPlusGenerator {

    private static final String URL = "jdbc:mysql://db201.uat1.rs.com:3306/db_liquidation?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&autoReconnect=true&failOverReadOnly=false";
    private static final String USERNAME = "liquidation";
    private static final String PASSWORD = "liquidation_user";
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    /**
     * 作者
     */
    private static final String AUTHOR = "xiong.canwei";
    /**
     * 服务模块名称
     */
    private static final String SERVICE_MODULE_NAME = "template-service";

    private static final String REST_E_MODULE_NAME = "template-e-rest";

    private static final String API_MODULE_NAME = "template-api";
    /**
     * 基础包路径
     */
    private static final String BASE_PACKAGE = "com.template";

    private static final String BASE_E_PACKAGE = "com.template.e.controller";
    private static final String BASE_CORE_PACKAGE = "com.template.core";
    private static final String POJO_DTO_PACKAGE = BASE_PACKAGE+".api.pojo.dto";
    private static final String POJO_VO_PACKAGE = BASE_PACKAGE+".api.pojo.vo";
    /**
     * 表名
     */
    private static final String[] TABLES = new String[]
            {"liquidation_detail"};
    /**
     * 表前端
     */
    private static final String TABLE_PREFIX = "";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        final String projectPath = System.getProperty("user.dir") + "/" + SERVICE_MODULE_NAME;
        final String projectRestPath = System.getProperty("user.dir") + "/" + REST_E_MODULE_NAME;
        final String projectApiPath = System.getProperty("user.dir") + "/" + API_MODULE_NAME;
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(AUTHOR);
        gc.setOpen(false);
        gc.setSwagger2(true);
        gc.setFileOverride(true);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setServiceName("%sService");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(URL);
        // dsc.setSchemaName("public");
        dsc.setDriverName(DRIVER_CLASS_NAME);
        dsc.setUsername(USERNAME);
        dsc.setPassword(PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        final PackageConfig pc = new PackageConfig();
//        pc.setModuleName("");
        pc.setMapper("dao");
        pc.setParent(BASE_CORE_PACKAGE);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                //自定义生成模板参数
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                //模板中获取值：${cfg.requestPathPrefix}
                map.put("DTOPackage", POJO_DTO_PACKAGE);
                map.put("VOPackage", POJO_VO_PACKAGE);
                map.put("BasePackage", BASE_PACKAGE);
                map.put("ControllerPackage", BASE_E_PACKAGE);
                this.setMap(map);
            }
        };

        // 如果模板引擎是 freemarker

        String queryDtoTemplatePath = "/templates/entityQueryDTO.java.ftl";
        String dtoTemplatePath = "/templates/entityDTO.java.ftl";
        String voTemplatePath = "/templates/entityVO.java.ftl";
        String mapperXmlTemplatePath = "/templates/mapper.xml.ftl";
        String serviceTemplatePath = "/templates/serviceImpl.java.ftl";
        String controllerTemplatePath = "/templates/controller.java.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
//         自定义配置会被优先输出

        focList.add(new FileOutConfig(queryDtoTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectApiPath + "/src/main/java/"
                        + (StringUtils.isBlank(pc.getModuleName()) ? "" : pc.getModuleName() + "/")
                        + BASE_PACKAGE.replace(".", "/")
                        + "/api/pojo/dto"
                        + "/" + tableInfo.getEntityName() + "QueryDTO" + StringPool.DOT_JAVA;
            }
        });

        focList.add(new FileOutConfig(dtoTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectApiPath + "/src/main/java/"
                        + (StringUtils.isBlank(pc.getModuleName()) ? "" : pc.getModuleName() + "/")
                        + BASE_PACKAGE.replace(".", "/")
                        + "/api/pojo/dto"
                        + "/" + tableInfo.getEntityName() + "DTO" + StringPool.DOT_JAVA;
            }
        });

        focList.add(new FileOutConfig(voTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectApiPath + "/src/main/java/"
                        + (StringUtils.isBlank(pc.getModuleName()) ? "" : pc.getModuleName() + "/")
                        + BASE_PACKAGE.replace(".", "/")
                        + "/api/pojo/vo"
                        + "/" + tableInfo.getEntityName() + "VO" + StringPool.DOT_JAVA;
            }
        });
        //生成mapper
        focList.add(new FileOutConfig(mapperXmlTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/"
                        + (StringUtils.isBlank(pc.getModuleName()) ? "" : pc.getModuleName() + "/")
                        + pc.getParent().replace(".", "/")
                        + "/dao"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        //生成service
        focList.add(new FileOutConfig(serviceTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/java/"
                        + (StringUtils.isBlank(pc.getModuleName()) ? "" : pc.getModuleName() + "/")
                        + pc.getParent().replace(".", "/")
                        + "/service"
                        + "/" + tableInfo.getEntityName() + "Service" + StringPool.DOT_JAVA;
            }
        });
        //生成controller
        focList.add(new FileOutConfig(controllerTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectRestPath + "/src/main/java/"
                        + (StringUtils.isBlank(pc.getModuleName()) ? "" : pc.getModuleName() + "/")
                        + BASE_PACKAGE.replace(".", "/")
                        + "/e/controller"
                        + "/" + tableInfo.getEntityName() + "Controller" + StringPool.DOT_JAVA;
            }
        });


        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                if (fileType == FileType.ENTITY) {
                    return true;
                }
                // 判断自定义文件夹是否需要创建
//                if (fileType == FileType.MAPPER || fileType == FileType.XML
//                        || fileType == FileType.SERVICE_IMPL || fileType == FileType.SERVICE) {
//                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
//                    return !new File(filePath).exists();
//                }
                //否则先判断文件是否存在
                File file = new File(filePath);
                boolean exist = file.exists();
                if (!exist) {
                    PackageHelper.mkDir(file.getParentFile());
                }
                // 允许生成模板文件
                return !exist || configBuilder.getGlobalConfig().isFileOverride();
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setController(null);
        templateConfig.setXml(null);
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
//        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setInclude(TABLES);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(TABLE_PREFIX);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
