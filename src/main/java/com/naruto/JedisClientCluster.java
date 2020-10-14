package com.naruto;

import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2020/10/13.
 */
public class JedisClientCluster implements JedisClient {

    private JedisCluster jedisCluster;

    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }


    @Override
    public String set(String key, String value) {
        return jedisCluster.set(key, value);
    }

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public Boolean exists(String key) {
        return jedisCluster.exists(key);
    }

    @Override
    public Long expire(String key, int seconds) {
        return jedisCluster.expire(key, seconds);
    }

    @Override
    public Long ttl(String key) {
        return jedisCluster.ttl(key);
    }

    @Override
    public Long incr(String key) {
        return jedisCluster.incr(key);
    }

    @Override
    public Long hset(String key, String field, String value) {
        return jedisCluster.hset(key, field, value);
    }

    @Override
    public String hget(String key, String field) {
        return jedisCluster.hget(key, field);
    }

    @Override
    public Long hdel(String key, String... field) {
        return jedisCluster.hdel(key, field);
    }


    @Override
    public ScanResult<String> scan(String cursor, ScanParams scanParams){

        ScanResult<String>  result = jedisCluster.scan(cursor, scanParams);

        return result;
    }

    @Override
    public  Set<String>  findKeys(String cursor, ScanParams scanParams){
        Set<String> allKeys = new HashSet<>();
        jedisCluster.getClusterNodes().values().forEach((pool) -> {
            String cur = ScanParams.SCAN_POINTER_START;
            do {
                try (Jedis jedis = pool.getResource()) {
                    ScanResult<String> scanResult = jedis.scan(cur, scanParams);
                    allKeys.addAll(scanResult.getResult());
                    cur = scanResult.getStringCursor();
                }
            } while (!cur.equals(ScanParams.SCAN_POINTER_START));
        });
        allKeys.stream().forEach(System.out::println);
        return allKeys;
    }

    @Override
    public String type(byte[] key){

        String result = jedisCluster.type(key);

        return result;
    }

    @Override
    public List<byte[]> lrange(byte[] key,Long start,Long end){
        List<byte[]>  result = jedisCluster.lrange(key,start,end);
        return result;
    }

    @Override
    public Set<byte[]> smembers(byte[]key){
        Set<byte[]>  result = jedisCluster.smembers(key);
        return result;
    }
    @Override
    public Set<Tuple>  zrangeWithScores(byte[] key, Long start,Long end){
        Set<Tuple>   result = jedisCluster.zrangeWithScores(key, start, end);
        return result;
    }

    @Override
    public Map<byte[], byte[]> hgetAll(byte[] key){
        Map<byte[], byte[]>  result = jedisCluster.hgetAll(key);
        return result;
    }

    @Override
    public byte[] get(byte[] key) {

        byte[] result = jedisCluster.get(key);

        return result;
    }

    @Override
    public Long del(byte[] key) {
        Long result = jedisCluster.del(key);
        return result;
    }

    @Override
    public Long del(String... keys) {
        Long result = jedisCluster.del(keys);
        return result;
    }

    @Override
    public Set<byte[]> keys(byte[] pattern) {
        //todo:未实现
        return null;
    }
}