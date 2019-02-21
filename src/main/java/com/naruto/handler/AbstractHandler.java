package com.naruto.handler;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import redis.clients.jedis.Jedis;

/**
 * jedis操作抽象类
 *
 * @author naruto
 * @date 2019-02-21 09:45
 **/
public abstract class AbstractHandler {

    private Jedis jedis;

    public AbstractHandler(Jedis jedis, String params) {
        this.jedis = jedis;
        CmdLineParser p = new CmdLineParser(this);
        try {
            String[] args = params.split(" ");
            p.parseArgument(args);
        } catch (CmdLineException e) {
            e.printStackTrace();
        }
    }

    protected Jedis getJedis() {
        return jedis;
    }

    public abstract void execute();
}
