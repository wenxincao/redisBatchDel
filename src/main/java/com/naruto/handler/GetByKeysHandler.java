package com.naruto.handler;

import com.naruto.JedisClient;
import org.kohsuke.args4j.Option;
import org.w3c.dom.views.AbstractView;
import redis.clients.jedis.ScanParams;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2020/10/14.
 */
public class GetByKeysHandler extends AbstractHandler {

    @Option(name = "-keys", usage = "keys pattern", required = true)
    private String keysPattern;

    public GetByKeysHandler(JedisClient jedis, String params) {
        super(jedis, params);
    }

    @Override
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
         /*   Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                String str = it.next();
                System.out.println(str);
            }*/
        }else{
            System.out.println("根据表达式找到0个key!");
        }
    }
}
