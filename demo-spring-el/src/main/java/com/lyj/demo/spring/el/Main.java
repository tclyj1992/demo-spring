package com.lyj.demo.spring.el;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Administrator on 2018/7/10.
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ElConfig.class);

        ElConfig elConfig = context.getBean(ElConfig.class);
        elConfig.output();

        context.close();
    }
}
