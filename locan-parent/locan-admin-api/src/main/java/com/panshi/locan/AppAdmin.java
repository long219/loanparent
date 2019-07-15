package com.panshi.locan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;


@SpringBootApplication
@EnableScheduling
@EnableSwagger2
public class AppAdmin
{
    public static void main( String[] args )
    {
        SpringApplication.run(AppAdmin.class,args);
    }



}
