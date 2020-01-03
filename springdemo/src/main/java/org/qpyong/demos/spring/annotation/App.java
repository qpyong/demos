package org.qpyong.demos.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Config.class);
        context.refresh();
        Parser parser = context.getBean(Parser.class);
        System.out.println(parser.getId(context));
    }
}
