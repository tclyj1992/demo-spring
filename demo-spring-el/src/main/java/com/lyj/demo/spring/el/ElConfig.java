package com.lyj.demo.spring.el;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * Created by Administrator on 2018/7/10.
 */
@Configuration
@ComponentScan("com.lyj.demo.spring.el")
//注入配置文件需要使用@PropertySource指定文件地址
@PropertySource("classpath:test.properties")
public class ElConfig {

    //注入普通字符串
    @Value("hello el")
    private String normal;

    //注入操作系统属性
    @Value("#{systemProperties['os.name']}")
    private String osName;

    //注入表达式结果
    @Value("#{T(java.lang.Math).random()*100.0}")
    private double randomNumber;

    //注入其他Bean属性
    @Value("#{demoService.another}")
    private String fromAnother;

    //注入文件资源
    @Value("classpath:test.txt")
    private Resource testFile;

    //注入网址资源
    @Value("http://www.baidu.com")
    private Resource testUrl;

    //注入配置文件
    @Value("${book.name}")
    private String bookName;

    @Autowired
    private Environment environment;

    //使用@Value注入文件，需要配置该Bean
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    public void output(){
        System.out.println(normal);
        System.out.println(osName);
        System.out.println(randomNumber);
        System.out.println(fromAnother);
        try {
            System.out.println(IOUtils.toString(testFile.getInputStream()));
            System.out.println(IOUtils.toString(testUrl.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(bookName);
        System.out.println(environment.getProperty("book.anthor"));
    }




}
