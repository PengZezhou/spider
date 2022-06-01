package com.yssj.common.autogenerate;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Description: <br/>
 * date: 2022/2/28 15:47<br/>
 *
 * @author Lisland<br />
 */
@Slf4j
public class GenApplication {

    public static void main(String[] args) {
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/crawler?characterEncoding=UTF-8&useSSL=false&autoReconnect=true&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "password";

        //模块名
        //String moduleName = "biz";
        String moduleName = scannerNext("请输模块名");
        //表名 多表用逗号隔开
        //String tableNames = "cppcc_user,cppcc_proposal";
        String tableNames = scannerNext("请输入表名，多表用逗号隔开");
        //表前缀
        String tablePrefixes = "";
        //字段前缀
        String fieldPrefixes = "";
        //排出表的表名 多表用逗号隔开
        String excludeTableNames = "";
        //忽略的字段
        String ignoreColumns = "";

        CodeGeneratorBo codeGeneratorBo = new CodeGeneratorBo();
        codeGeneratorBo.setDbUrl(dbUrl).setUsername(username).setPassword(password);
        codeGeneratorBo.setModuleName(moduleName);
        codeGeneratorBo.setTableNames(split(tableNames))
                .setTablePrefixes(split(tablePrefixes))
                .setFieldPrefixes(split(fieldPrefixes))
                .setExcludeTableNames(split(excludeTableNames))
                .setIgnoreColumns(split(ignoreColumns))
        ;
        //codeGeneratorBo.setOutDir(System.getProperty("user.dir"));
        execute(codeGeneratorBo);
    }

    /**
     * 读取控制台输入内容
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * 控制台输入内容读取并打印提示信息
     *
     * @param message 提示信息
     * @return
     */
    public static String scannerNext(String message) {
        StringBuilder help = new StringBuilder();
        help.append("请输入" + message + "：");
        System.out.println(help.toString());
        String nextLine = scanner.nextLine();
        if (StrUtil.isBlank(nextLine)) {
            // 如果输入空行继续等待
            return scanner.next();
        }
        return nextLine;
    }

    private static void execute(CodeGeneratorBo bo) {
        try {
            new MyAutoGenerator(bo).execute();
        } catch (Exception e) {
            log.error("代码生产异常",e);
        }

    }

    private static  String[] split(String value) {
        if (!StringUtils.hasText(value)) {
            return new String[]{};
        }
        List<String> valueList = new ArrayList<>();
        String[] values;
        if (value.contains(",")) {
            values = value.split(",");
        } else if (value.contains("\n")) {
            values = value.split("\n");
        } else {
            values = value.split(" ");
        }
        for (String str : values) {
            str = str.trim();
            if (StringUtils.hasText(str)) {
                valueList.add(str);
            }
        }
        String[] result = new String[valueList.size()];
        return  valueList.toArray(result);
    }
}
