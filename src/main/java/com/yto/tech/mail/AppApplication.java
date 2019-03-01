package com.yto.tech.mail;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.yto.tech.mail"})
public class AppApplication {

    public static void main( String[] args )
    {
        new SpringApplicationBuilder(AppApplication.class).web(true).run(args);
    }
}

