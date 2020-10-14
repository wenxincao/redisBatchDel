package com.naruto.handler;

import com.naruto.JedisClient;
import com.naruto.Util;
import org.kohsuke.args4j.Option;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 批量迁移key实例
 *
 * @author naruto
 * @date 2019-02-21 09:42
 **/
public class MoveByKeysHandler extends AbstractHandler {


    @Option(name = "-keys", usage = "keys pattern", required = true)
    private String keysPattern;
    @Option(name = "-targetH", usage = "targetHost", required = true)
    private String targetHost;
    @Option(name = "-targetP", usage = "targetPort", required = true)
    private int targetPort;
    @Option(name = "-targetA", usage = "targetPassword", required = false)
    private String targetPassword;
    @Option(name = "-targetN", usage = "targetN", required = true)
    private int targetDbIndex;

    public MoveByKeysHandler(JedisClient jedis, String params) {
        super(jedis, params);
    }

    public void execute() {
        JedisClient sourceJedis = getJedis();

        Set<byte[]> keySet = sourceJedis.keys(keysPattern.getBytes());
        if (keySet == null || keySet.isEmpty()) {
            System.out.println("没有匹配的key列表");
            return;
        }
        System.out.println("根据表达式找到" + keySet.size() + "个key!");

        JedisClient targetJedis = Util.createJedis(targetHost, targetPort, targetPassword, targetDbIndex,0);
        keySet.forEach(key -> {
            String keyStr = new String(key);
            System.out.print("移动key=" + keyStr + ",");
            boolean moveResult = moveKey(key, sourceJedis, targetJedis);
            System.out.print(moveResult ? "成功" : "失败");
            System.out.println("!");
            if (moveResult) {
                doAfterCopyKey(sourceJedis, key);
            }
        });
    }

    private boolean moveKey(byte[] key, JedisClient sourceJedis, JedisClient targetJedis) {
        //todo:待实现
    /*    String keyStr = new String(key);

        if (targetJedis.exists(key)) {
            System.out.print("目标库已存在key=" + keyStr + ".");
            return false;
        }
        String type = sourceJedis.type(key);
        switch (type) {
            case "string": {
                byte[] vlaue = sourceJedis.get(key);
                targetJedis.setnx(key, vlaue);
            }
            break;
            case "list": {
                List<byte[]> list = sourceJedis.lrange(key, 0L, -1L);
                byte[][] arr = new byte[list.size()][];
                list.toArray(arr);
                targetJedis.lpush(key, arr);
            }
            break;
            case "set": {
                Set<byte[]> smembers = sourceJedis.smembers(key);
                byte[][] arr = new byte[smembers.size()][];
                smembers.toArray(arr);
                targetJedis.sadd(key, arr);
            }
            break;
            case "zset": {
                Set<Tuple> zSet = sourceJedis.zrangeWithScores(key, 0L, -1L);
                Map<byte[], Double> scoreMembers = new HashMap<>();
                zSet.forEach(t -> scoreMembers.put(t.getBinaryElement(), t.getScore()));
                targetJedis.zadd(key, scoreMembers);
            }
            break;
            case "hash": {
                Map<byte[], byte[]> map = sourceJedis.hgetAll(key);
                String result = targetJedis.hmset(key, map);
                return "OK".equals(result);
            }
            default:
                System.out.print("不支持的key类型，key=" + keyStr + ",type=" + type + ".");
                break;
        }*/
        return true;
    }

    /**
     * copy完key值后的后续操作
     *
     * @param sourceJedis
     * @param key
     */
    public void doAfterCopyKey(JedisClient sourceJedis, byte[] key) {
        System.out.print("从源db删除key=" + new String(key) + ",");
        Long delCount = sourceJedis.del(key);
        System.out.println(delCount > 0 ? "成功" : "失败");
    }
}
