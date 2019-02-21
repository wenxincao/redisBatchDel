package com.naruto.handler;

import org.kohsuke.args4j.Option;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * 批量删除key实例
 *
 * @author naruto
 * @date 2019-02-21 09:42
 **/
public class DeleteByKeysHandler extends AbstractHandler {


    @Option(name = "-keys", usage = "keys pattern", required = true)
    private String keysPattern;

    public DeleteByKeysHandler(Jedis jedis, String params) {
        super(jedis, params);
    }

    public void execute() {
        System.out.println("keys匹配表达式:" + keysPattern);
        Jedis jedis = getJedis();
        Set<byte[]> keySet = jedis.keys(keysPattern.getBytes());
        if (keySet == null || keySet.isEmpty()) {
            System.out.println("没有匹配的key列表");
            jedis.close();
            return;
        }
        System.out.println("根据表达式找到" + keySet.size() + "个key!");

        keySet.forEach(key -> {
            System.out.print("删除key:" + new String(key) + ",");
            Long del = jedis.del(key);
            System.out.print(del > 0 ? "成功" : "失败");
            System.out.println("!");
        });
    }
}
