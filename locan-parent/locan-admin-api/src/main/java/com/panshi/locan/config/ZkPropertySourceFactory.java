package com.panshi.locan.config;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class ZkPropertySourceFactory implements PropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource encodedResource) throws IOException {


        /*ResourcePropertySource resourcePropertySource = new ResourcePropertySource(encodedResource);
        resourcePropertySource.getProperty("zk.hosPath");*/


        //1
        Properties properties = new Properties();

        //2.获取资源文件流
        InputStream inputStream = encodedResource.getInputStream();
        properties.load(inputStream);

        //3. 读取资源文件里的zookeeper信息
        String hostPath = properties.get("zk.hosPath").toString();
        String nodeName = properties.get("zk.nodeName").toString();


        //3.建立zk 连接
        ZooKeeper zooKeeper = new ZooKeeper(hostPath, 2000, null);
        try {

            System.out.println("------"+hostPath+nodeName);

            List<String> children = zooKeeper.getChildren(nodeName, null);

            for (String child : children) {

                //获取值
                String nodeValue = new String(zooKeeper.getData(nodeName + "/" + child, null, null));
                System.out.println(child+"="+nodeValue);

                //将需要放 Spring 中的配置
                properties.setProperty("spring.datasource."+child,nodeValue);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        PropertiesPropertySource propertySource = new PropertiesPropertySource(this.toString(),properties);
        return propertySource;
    }
}
