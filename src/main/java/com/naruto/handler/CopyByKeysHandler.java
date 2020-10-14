package com.naruto.handler;

import com.naruto.JedisClient;
import redis.clients.jedis.Jedis;

/**
 * 复制key值
 *
 * @author Min.Xu
 * @date 2019-02-21 11:51
 **/
public class CopyByKeysHandler extends MoveByKeysHandler {

    public CopyByKeysHandler(JedisClient jedis, String params) {
        super(jedis, params);
    }

    @Override
    public void doAfterCopyKey(JedisClient sourceJedis, byte[] key) {
    }
}
