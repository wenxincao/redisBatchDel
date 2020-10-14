package com.naruto;

import redis.clients.jedis.*;

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
    public static JedisClient createJedis(String host, int port, String password, int dbIndex,int cluster) {

       if(cluster != 0){
           JedisClientCluster jedisClientCluster = new JedisClientCluster();
           HostAndPort hostAndPort = new HostAndPort(host, port);
           JedisCluster jedisCluster = new JedisCluster(hostAndPort);
           jedisClientCluster.setJedisCluster(jedisCluster);

           return jedisClientCluster;
       }else{
           JedisClientPool jedisClient = new JedisClientPool();
           JedisPoolConfig poolConfig = new JedisPoolConfig();
           JedisPool jedisPool;

           if (password != null && !password.isEmpty()) {
               jedisPool  = new JedisPool(poolConfig,host,port,30000,password,dbIndex);
           }else{
               jedisPool = new JedisPool(poolConfig,host,port,30000,null,dbIndex);
           }
           jedisClient.setJedisPool(jedisPool);
           return jedisClient;
       }
    }

}
