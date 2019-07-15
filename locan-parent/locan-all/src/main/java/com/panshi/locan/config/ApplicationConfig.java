/*
package com.panshi.locan.config;

import lombok.extern.slf4j.Slf4j;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Deprecated
//@Component
@Slf4j
public class ApplicationConfig  implements  Filter{


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;


        //中文乱码
        response.setCharacterEncoding("UTF-8");
        //response.setHeader("Content-type","text/html;charset=UTF-8");    //中文乱码

        System.out.println("-------------跨域------------");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE");

        */
/*if(!request.getMethod().equals("OPTIONS")){

        }*//*

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }


     */
/* @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedHeaders("*")
                        .allowedMethods("*")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }*//*

}
*/
