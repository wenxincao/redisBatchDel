package com.naruto.handler;

import com.naruto.JedisClient;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

/**
 * jedis操作抽象类
 *
 * @author naruto
 * @date 2019-02-21 09:45
 **/
public abstract class AbstractHandler {

    private JedisClient jedis;


    public AbstractHandler(JedisClient jedis, String params) {
        this.jedis = jedis;
        CmdLineParser p = new CmdLineParser(this);
        try {
            String[] args = params.split(" ");
            p.parseArgument(args);
        } catch (CmdLineException e) {
            e.printStackTrace();
        }
    }


    public JedisClient getJedis() {
        return jedis;
    }

    public abstract void execute();
}
