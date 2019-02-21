package com.naruto;

import redis.clients.jedis.Jedis;

/**
 * 工具类
 *
 * @author naruto
 * @date 2019-02-21 11:26
 **/
public class Util {

    /**
     * 创建jedis连接对象
     * @param host
     * @param port
     * @param password
     * @param dbIndex
     * @return
     */
    public static Jedis createJedis(String host, int port, String password, int dbIndex) {

        Jedis jedis = new Jedis(host, port);

        if (password != null && !password.isEmpty()) {
            String authResult = jedis.auth(password);
            System.out.println("密码认证结果:" + authResult);
        }

        jedis.select(dbIndex);
        return jedis;
    }

}
