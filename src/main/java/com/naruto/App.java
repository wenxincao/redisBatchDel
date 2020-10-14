package com.naruto;

import com.naruto.handler.*;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import redis.clients.jedis.Jedis;


/**
 * Main方法入口
 * @author naruto
 */
public class App {
  /*  public static void main1(String[] args) {

        Options options = new Options();
        CmdLineParser p = new CmdLineParser(options);
        try {
            p.parseArgument(args);
            System.out.println("参数:" + options);
        } catch (CmdLineException e) {
            e.printStackTrace();
        }


        Jedis jedis = Util.createJedis(options.getHost(), options.getPort(), options.getPassword(), options.getDbIndex());

        AbstractHandler handler = null;
        switch (options.getFunction()) {
            case DEL_BY_KEYS:
                handler = new DeleteByKeysHandler(jedis, options.getParams());
                break;
            case MOVE_BY_KEYS:
                handler = new MoveByKeysHandler(jedis, options.getParams());
                break;
            case COPY_BY_KEYS:
                handler = new CopyByKeysHandler(jedis, options.getParams());
                break;
        }
        if (handler == null) {
            System.out.println("不支持的功能:" + options.getFunction());
            return;
        }
        handler.execute();

        jedis.close();
        System.out.println("Done!");
    }*/

    public static void main(String[] args) {

        Options options = new Options();
        CmdLineParser p = new CmdLineParser(options);
        try {
            p.parseArgument(args);
            System.out.println("参数:" + options);
        } catch (CmdLineException e) {
            e.printStackTrace();
        }

        JedisClient jedis = Util.createJedis(options.getHost(), options.getPort(), options.getPassword(), options.getDbIndex(),options.getCluster());

        AbstractHandler handler = null;
        switch (options.getFunction()) {
            case DEL_BY_KEYS:
                handler = new DeleteByKeysHandler(jedis, options.getParams());
                break;
            case MOVE_BY_KEYS:
                handler = new MoveByKeysHandler(jedis, options.getParams());
                break;
            case COPY_BY_KEYS:
                handler = new CopyByKeysHandler(jedis, options.getParams());
                break;
            case GET_BY_KEYS:
                handler=new GetByKeysHandler(jedis,options.getParams());
                break;
        }
        if (handler == null) {
            System.out.println("不支持的功能:" + options.getFunction());
            return;
        }
        handler.execute();

        System.out.println("Done!");
    }
}
