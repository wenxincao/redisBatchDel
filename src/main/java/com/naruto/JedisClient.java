package com.naruto;

import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2020/10/13.
 */
public interface JedisClient {


    Set<byte[]> keys(byte[] pattern);
    String set(String key, String value);
    String get(String key);
    byte[]  get(byte[] key);
    Long del(byte[] key);
    Long  del(String... keys);
    Boolean exists(String key);
    Long expire(String key, int seconds);
    Long ttl(String key);
    Long incr(String key);
    Long hset(String key, String field, String value);
    String hget(String key, String field);
    Long hdel(String key, String... field);
    ScanResult<String> scan(String cursor, ScanParams scanParams);
    Set<String>  findKeys(String cursor, ScanParams scanParams);
    String type(byte[] key);
    List<byte[]> lrange(byte[] key,Long start,Long end);
    Set<byte[]> smembers(byte[]key);
    Set<Tuple>  zrangeWithScores(byte[] key, Long start,Long end);
    Map<byte[], byte[]> hgetAll(byte[] key);
}
