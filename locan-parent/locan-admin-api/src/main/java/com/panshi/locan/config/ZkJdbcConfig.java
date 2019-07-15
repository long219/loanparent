package com.panshi.locan.config;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

//@Configuration
public class ZkJdbcConfig {

    @Value("${zk.hosPath}")
    private String hostPath;

    @Value("${zk.nodeName}")
    private String nodeName;


    /*spring:
  datasource:
  url: jdbc:mysql://127.0.0.1:3306/loan
  username: root
  password: 123456
  driver-class-name: com.mysql.jdbc.Driver*/

    //@PostConstruct
    public void getJdbc() throws IOException, KeeperException, InterruptedException {

        //建立zk 连接
        ZooKeeper zooKeeper = new ZooKeeper(hostPath, 2000, null);


        List<String> children = zooKeeper.getChildren(nodeName, null);


        for (String child : children) {
            String nodeValue = new String(zooKeeper.getData(nodeName + "/" + child, null, null));
            System.out.println(child+"="+nodeValue);

            //将需要放 Spring 中的配置
            System.setProperty("spring.datasource."+child,nodeValue);
        }

        System.out.println(hostPath);

    }
}
