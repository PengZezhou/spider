package com.yssj.common.autogenerate;

import cn.hutool.core.util.BooleanUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.Entity;
import com.baomidou.mybatisplus.generator.config.builder.Mapper;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Collections;

public class MyAutoGenerator {

    private final CodeGeneratorBo bo;

    public MyAutoGenerator(CodeGeneratorBo bo) {
        this.bo = bo;
    }

    public void execute() {
        FastAutoGenerator.create(dataSourceBuilder())
                .globalConfig(this::globalConfigBuilder)
                .packageConfig(this::packageConfigBuilder)
                .strategyConfig(this::strategyConfigBuilder)
                .execute();
    }

    public DataSourceConfig.Builder dataSourceBuilder() {
        return new DataSourceConfig.Builder(bo.getDbUrl(), bo.getUsername(), bo.getPassword());
    }

    public void globalConfigBuilder(GlobalConfig.Builder builder) {

        builder.fileOverride().author(Config.AUTHOR);

        builder.outputDir(Config.OUTPUT_DIR);

        DateType dateType = DateType.TIME_PACK;
        builder.dateType(dateType);

        if (BooleanUtil.isTrue(Config.SWAGGER_SUPPORT)) {
            builder.enableSwagger();
        }

    }

    public void packageConfigBuilder(PackageConfig.Builder builder) {
        builder
                .parent(Config.PACKAGE_NAME_PARENT)
                .moduleName(bo.getModuleName())
                .controller(Config.PACKAGE_NAME_CONTROLLER)
                .entity(Config.PACKAGE_NAME_MODEL)
                .mapper(Config.PACKAGE_NAME_DAO)
                .xml(Config.DIR_NAME_XML)
                .service(Config.PACKAGE_NAME_SERVICE)
                .serviceImpl(Config.PACKAGE_NAME_SERVICE_IMPL)
                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, Config.OUTPUT_DIR+"/src/main/resources/mapper/" + bo.getModuleName()));
    }

    public void strategyConfigBuilder(StrategyConfig.Builder builder) {
        builder.addInclude(bo.getTableNames())
                .addFieldPrefix(bo.getFieldPrefixes())
                .addTablePrefix(bo.getTablePrefixes())
                .addExclude(bo.getExcludeTableNames())
                .entityBuilder()
                .naming(NamingStrategy.underline_to_camel)
                //.enableChainModel()
                //.enableLombok()
                //.enableActiveRecord()
                .formatFileName(Config.FILE_NAME_MODEL)
                .idType(IdType.AUTO)
                .logicDeleteColumnName(Config.FIELD_LOGIC_DELETE_NAME)
                .versionColumnName(Config.FIELD_VERSION_NAME)
                //.superClass()// 实体父类的全类名
                .addIgnoreColumns(bo.getIgnoreColumns())
                .mapperBuilder()
                .formatMapperFileName(Config.FILE_NAME_MAPPER)
                .formatXmlFileName(Config.FILE_NAME_XML)
                .serviceBuilder()
                .formatServiceFileName(Config.FILE_NAME_SERVICE)
                .formatServiceImplFileName(Config.FILE_NAME_SERVICE_IMPL)
                .controllerBuilder()
                .enableRestStyle()
                .enableHyphenStyle();

        Entity.Builder entityBuilder = builder.entityBuilder();
        if (BooleanUtil.isTrue(Config.CHAIN_MODEL_SUPPORT)) {
            entityBuilder.enableChainModel();
        }
        if (BooleanUtil.isTrue(Config.LOMBOK_SUPPORT)) {
            entityBuilder.enableLombok();
        }
        // 字段注解
        if (BooleanUtil.isTrue(Config.FIELD_ANNOTATION_SUPPORT)) {
            entityBuilder.enableTableFieldAnnotation();
        }

        Mapper.Builder mapperBuilder = builder.mapperBuilder();
        // 开启mapper注解 getMapperAnnotation
        if (BooleanUtil.isTrue(Config.MAPPER_ANNOTATION_SUPPORT)) {
            mapperBuilder.enableMapperAnnotation();
        }
    }

}
