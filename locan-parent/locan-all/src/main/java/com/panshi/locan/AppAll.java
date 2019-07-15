package com.panshi.locan;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.*;

/**
 * Hello world!
 *
 */
public class AppAll
{


    private static  String hostPath="127.0.0.1:2181/config";

    public static void main1( String[] args ) throws IOException, KeeperException, InterruptedException {


        Properties properties  = new Properties();
        properties.load(new FileInputStream("D:/学习资料/高级课学习资料/work/config/config.properties"));


        //创建zk 连接
        ZooKeeper zk = new ZooKeeper(hostPath, 2000, null);


        Stat exists = zk.exists("/loan", null);
        if (exists==null){
            zk.create("/loan",null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        for (Object s : properties.keySet()) {

            String value = properties.get(s).toString();

            //判断是否存在， 如不存在就创建
            Stat stat = zk.exists("/loan/" + s, null);
            if (stat==null){
                zk.create("/loan/"+s,value.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        }

    }


    public static void main(String[] args) throws IOException {


        Content content = Request.Get("http://puodaaift.bkt.clouddn.com/config.properties").execute().returnContent();

        String[] split = content.toString().split("\r\n");
        for (String s : split) {
           // System.out.println("---"+s);

            String s1 = s.split("=")[0];
            String s2 = s.split("=")[1];

            System.out.println(s1+"------"+s2);

        }

    }
}
