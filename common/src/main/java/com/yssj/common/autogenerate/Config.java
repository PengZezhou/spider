package com.yssj.common.autogenerate;

/**
 * @author Administrator
 */
public class Config {

    /**父包名**/
    public static final String PACKAGE_NAME_PARENT = "com.yssj.core.module";
    /** 包名：controller */
    public static final String PACKAGE_NAME_CONTROLLER = "controller";

    /** 包名：service */
    public static final String PACKAGE_NAME_SERVICE = "service";

    /** 包名：service.impl */
    public static final String PACKAGE_NAME_SERVICE_IMPL = "service.impl";

    /** 包名：model */
    public static final String PACKAGE_NAME_MODEL = "entity";

    /** 包名：mapper */
    public static final String PACKAGE_NAME_DAO = "mapper";

    /** 目录名：xml */
    public static final String DIR_NAME_XML = "mapper";

    /** 文件名后缀：Model */
    public static final String FILE_NAME_MODEL = "%s";

    /** 文件名后缀：Dao */
    public static final String FILE_NAME_MAPPER = "%sMapper";

    /** 文件名后缀：Mapper */
    public static final String FILE_NAME_XML = "%sMapper";

    /** I开头，Service结尾 */
    public static final String FILE_NAME_SERVICE = "I%sService";

    /** 文件名后缀：ServiceImpl */
    public static final String FILE_NAME_SERVICE_IMPL = "%sServiceImpl";

    /** 文件名后缀：Controller */
    public static final String FILE_NAME_CONTROLLER = "%sController";

    /** 逻辑删除字段 */
    public static final String FIELD_LOGIC_DELETE_NAME = "del_flag";


    /** 乐观锁字段名 */
    public static final String FIELD_VERSION_NAME = "version";

    /** 作者 */
    public static final String AUTHOR = "Administrator";

    /** 生成文件的输出目录 */
    public static String PROJECT_PATH = System.getProperty("user.dir");

    /** 输出目录 */
    public static final String OUTPUT_DIR = PROJECT_PATH + "/temp/code-generator";

    /** 模板引擎。velocity / freemarker / beetl */
    public static final String TEMPLATE_ENGINE = "velocity";

    /** 是否支持Swagger，默认不支持 */
    public static final Boolean SWAGGER_SUPPORT = false;

    /** 开启Mapper注解**/
    public static final Boolean MAPPER_ANNOTATION_SUPPORT = false;
    /**  开启字段注解**/
    public static final Boolean FIELD_ANNOTATION_SUPPORT = false;
    /** lombok支持**/
    public static final Boolean LOMBOK_SUPPORT = true;
    /** CHAIN_MODEL支持**/
    public static final Boolean CHAIN_MODEL_SUPPORT = false;


}
