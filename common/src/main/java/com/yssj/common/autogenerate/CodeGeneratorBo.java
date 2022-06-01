package com.yssj.common.autogenerate;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Administrator
 */
@Data
@Accessors(chain = true)
public class CodeGeneratorBo {

    // 模块名
    private String moduleName;

    // 数据库类型
    //private String dbType;
    // 数据库连接地址
    private String dbUrl;
    // 数据库名称
    private String username;
    // 数据库密码
    private String password;
    // 数据库驱动
    //private String driver;

    // 表名
    private String [] tableNames;
    // 表前缀
    private String [] tablePrefixes;
    // 字段前缀
    private String [] fieldPrefixes;
    // 排出表的表名
    private String [] excludeTableNames;
    // 忽略的字段
    private String [] ignoreColumns;

}
