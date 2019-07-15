package com.panshi.locan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableScheduling
@EnableSwagger2
public class AppH5
{
    public static void main( String[] args )
    {
        SpringApplication.run(AppH5.class,args);
    }
}
