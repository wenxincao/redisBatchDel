package com.naruto.handler;

import com.naruto.JedisClient;
import org.kohsuke.args4j.Option;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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

    public DeleteByKeysHandler(JedisClient jedis, String params) {
        super(jedis, params);
    }

    public void execute1() {
        System.out.println("keys匹配表达式:" + keysPattern);
        JedisClient jedis = getJedis();
        Set<byte[]> keySet = jedis.keys(keysPattern.getBytes());
        if (keySet == null || keySet.isEmpty()) {
            System.out.println("没有匹配的key列表");
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

    public void execute() {
        System.out.println("keys匹配表达式:" + keysPattern);
        JedisClient jedis = getJedis();

        String cursor = ScanParams.SCAN_POINTER_START;
        String key = keysPattern;
        ScanParams scanParams = new ScanParams();
        scanParams.match(key);// 匹配以 PLFX-ZZSFP-* 为前缀的 key
        scanParams.count(1000);

        Set<String> list = jedis.findKeys(cursor,scanParams);
        if(list!=null) {
            System.out.println("根据表达式找到" + list.size() + "个key!");
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                String str = it.next();
                jedis.del(str);
            }
        }else{
            System.out.println("根据表达式找到0个key!");
        }
    }
}
