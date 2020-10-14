package com.naruto;

/**
 * Created by Administrator on 2020/10/13.
 */
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisClientPool implements JedisClient {

    private JedisPool jedisPool;

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
    @Override
    public Set<byte[]> keys(byte[] pattern) {
        Jedis jedis = jedisPool.getResource();
        Set<byte[]> result =  jedis.keys(pattern);
        jedis.close();
        return result;
    }
    @Override
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.set(key, value);
        jedis.close();
        return result;
    }

    @Override
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.get(key);
        jedis.close();
        return result;
    }

    @Override
    public byte[] get(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        byte[] result = jedis.get(key);
        jedis.close();
        return result;
    }

    @Override
    public Long del(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.del(key);
        jedis.close();
        return result;
    }

    @Override
    public Long del(String... keys) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.del(keys);
        jedis.close();
        return result;
    }

    @Override
    public Boolean exists(String key) {
        Jedis jedis = jedisPool.getResource();
        Boolean result = jedis.exists(key);
        jedis.close();
        return result;
    }

    @Override
    public Long expire(String key, int seconds) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.expire(key, seconds);
        jedis.close();
        return result;
    }

    @Override
    public Long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.ttl(key);
        jedis.close();
        return result;
    }

    @Override
    public Long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.incr(key);
        jedis.close();
        return result;
    }

    @Override
    public Long hset(String key, String field, String value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hset(key, field, value);
        jedis.close();
        return result;
    }

    @Override
    public String hget(String key, String field) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.hget(key, field);
        jedis.close();
        return result;
    }

    @Override
    public Long hdel(String key, String... field) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hdel(key, field);
        jedis.close();
        return result;
    }

   @Override
    public ScanResult<String> scan(String cursor, ScanParams scanParams){
       Jedis jedis = jedisPool.getResource();
       ScanResult<String>  result = jedis.scan(cursor, scanParams);
       jedis.close();
       return result;
   }

    @Override
    public String type(byte[] key){
        Jedis jedis = jedisPool.getResource();
        String result = jedis.type(key);
        jedis.close();
        return result;
    }

    @Override
    public List<byte[]> lrange(byte[] key,Long start,Long end){
        Jedis jedis = jedisPool.getResource();
        List<byte[]>  result = jedis.lrange(key,start,end);
        jedis.close();
        return result;
    }

    @Override
    public Set<byte[]> smembers(byte[]key){
        Jedis jedis = jedisPool.getResource();
        Set<byte[]>  result = jedis.smembers(key);
        jedis.close();
        return result;
    }
    @Override
    public Set<Tuple>  zrangeWithScores(byte[] key, Long start,Long end){
        Jedis jedis = jedisPool.getResource();
        Set<Tuple>   result = jedis.zrangeWithScores(key, start, end);
        jedis.close();
        return result;
    }

    @Override
    public Map<byte[], byte[]> hgetAll(byte[] key){
        Jedis jedis = jedisPool.getResource();
        Map<byte[], byte[]>  result = jedis.hgetAll(key);
        jedis.close();
        return result;
    }


    @Override
    public  Set<String>  findKeys(String cursor, ScanParams scanParams){
        Set<String> allKeys = new HashSet<>();
        String cur = ScanParams.SCAN_POINTER_START;
            do {
                try (Jedis jedis = jedisPool.getResource()) {
                    ScanResult<String> scanResult = jedis.scan(cur, scanParams);
                    allKeys.addAll(scanResult.getResult());
                    cur = scanResult.getStringCursor();
                }
            } while (!cur.equals(ScanParams.SCAN_POINTER_START));

        allKeys.stream().forEach(System.out::println);
        return allKeys;
    }
}