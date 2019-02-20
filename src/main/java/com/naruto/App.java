package com.naruto;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args)  {

        Options options = new Options();
        CmdLineParser p = new CmdLineParser(options);
        try {
            p.parseArgument(args);
            System.out.println("参数:" +options);
        } catch (CmdLineException e) {
            e.printStackTrace();
        }


        Jedis jedis = new Jedis(options.getHost(), options.getPort());

        String password = options.getPassword();
        if (password != null && !password.isEmpty()) {
            String authResult = jedis.auth(password);
            System.out.println("密码认证结果:" + authResult);
        }

        jedis.select(options.getDbIndex());

        Set<byte[]> keySet = jedis.keys(options.getKeysPattern().getBytes());
        if (keySet == null || keySet.isEmpty()) {
            System.out.println("没有匹配的key列表");
            jedis.close();
            return;
        }
        System.out.println("根据表达式找到"+keySet.size()+"个key!");

        keySet.forEach(key -> {
            System.out.print("删除key:" + new String(key) + ",");
            Long del = jedis.del(key);
            System.out.print(del > 0 ? "成功" : "失败");
            System.out.println("!");
        });
        jedis.close();
        System.out.println("Done!");
    }
}
