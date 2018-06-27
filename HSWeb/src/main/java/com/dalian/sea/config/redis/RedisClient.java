package com.dalian.sea.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.crazycake.shiro.RedisManager;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 *
 * @author 杨文波
 * @date 2017/12/25
 */
@Slf4j
public class RedisClient extends RedisManager{
    private static JedisPool jedisPool = null;

    public RedisClient(JedisPool jedisPool) {
        RedisClient.jedisPool = jedisPool;
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public byte[] get(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        byte[] value;

        try {
            value = jedis.get(key);
        } catch (Exception e) {
            log.error("redis key:{} get value occur exception", new String(key));
            throw new RuntimeException("redis operation error:", e);
        } finally {
            jedis.close();
        }

        return value;
    }

    @Override
    public byte[] set(byte[] key, byte[] value) {
        Jedis jedis = jedisPool.getResource();

        try {
            jedis.set(key, value);
            Integer expire = getExpire();
            if(expire != 0) {
                jedis.expire(key, expire);
            }
        } catch (Exception e) {
            log.error("redis key:{} set value:{} occur exception", new String(key), new String(value));
            throw new RuntimeException("redis operation error:", e);
        } finally {
            jedis.close();
        }

        return value;
    }

    @Override
    public byte[] set(byte[] key, byte[] value, int expire) {
        Jedis jedis = jedisPool.getResource();

        try {
            jedis.set(key, value);
            if(expire != 0) {
                jedis.expire(key, expire);
            }
        } catch (Exception e) {
            log.error("redis key:{} set value:{} in expire:{} occur exception", new String(key), new String(value), expire);
            throw new RuntimeException("redis operation error:", e);
        } finally {
            jedis.close();
        }

        return value;
    }

    @Override
    public void del(byte[] key) {
        Jedis jedis = jedisPool.getResource();

        try {
            jedis.del(key);
        } catch (Exception e) {
            log.error("redis key:{} del value occur exception", new String(key));
            throw new RuntimeException("redis operation error:", e);
        } finally {
            jedis.close();
        }
    }

    @Override
    public void flushDB() {
        Jedis jedis = jedisPool.getResource();

        try {
            jedis.flushDB();
        } catch (Exception e) {
            log.error("redis flushDB occur exception");
            throw new RuntimeException("redis operation error:", e);
        } finally {
            jedis.close();
        }

    }

    @Override
    public Long dbSize() {
        Long dbSize = Long.valueOf(0L);
        Jedis jedis = jedisPool.getResource();

        try {
            dbSize = jedis.dbSize();
        } catch (Exception e) {
            log.error("redis get dbSize occur exception");
            throw new RuntimeException("redis operation error:", e);
        } finally {
            jedis.close();
        }

        return dbSize;
    }

    @Override
    public Set<byte[]> keys(String pattern) {
        Set keys = null;
        Jedis jedis = jedisPool.getResource();

        try {
            keys = jedis.keys(pattern.getBytes());
        } catch (Exception e) {
            log.error("redis get keys in pattern:{} occur exception", pattern);
            throw new RuntimeException("redis operation error:", e);
        } finally {
            jedis.close();
        }

        return keys;
    }
}
