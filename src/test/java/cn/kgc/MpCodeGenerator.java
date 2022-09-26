package cn.kgc;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MpCodeGenerator {
    // main运行
    public static void main(String[] args) {
        String url = "jdbc:mysql:///db2202?serverTimezone=GMT%2B8";
        String username = "root";
        String password = "123456";
        String outputDir = "D:\\workspace\\2202\\emp-sys-2022\\src\\main\\java";
        String moduleName = "emp";
        String outputXml = "D:\\workspace\\2202\\emp-sys-2022\\src\\main\\resources" + "\\mapper\\" + moduleName;
        List<String> tables = Arrays.asList("t_emp");
        String tablePrefix = "t_";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("kgc") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            //.fileOverride() // 覆盖已生成文件
                            .outputDir(outputDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("cn.kgc") // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, outputXml)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables) // 设置需要生成的表名
                            .addTablePrefix(tablePrefix); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    // test运行
}
