package com.panshi.locan.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "config/jdbc-config.properties" ,factory = ZkPropertySourceFactory.class)
public class ZkJdbcConfig2 {


}
