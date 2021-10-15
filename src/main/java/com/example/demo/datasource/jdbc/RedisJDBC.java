package com.example.demo.datasource.jdbc;

import redis.clients.jedis.Jedis;

/**
 * @author czy
 * @date 2021/3/25
 */
public class RedisJDBC {
    public static void main(String[] args) {
        String ip = "192.168.2.162";
        int port = 6379;
        //数据库连接
        Jedis jedis = new Jedis(ip,port);
        //密码验证
        jedis.auth("123456");
        //选择端口
        jedis.select(0);
        String s = jedis.get("SIS:SCENECACHE:::getSceneById:317370806156267520");
        jedis.close();
        System.out.println(s);
    }
}
