package DevCache;

import Extend.StringExtend;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2017-08-07.
 */
public class RedisUtil {
    private static RedisUtil item;
    public static RedisUtil Instance() {
        if (item == null) {
            item = new RedisUtil();
        }
        return item;
    }

//    private static final Logger logger = Logger.getLogger(RedisUtil.class);
    //    private static JedisPool pool = null;
    private final long serialVersionUID = -1149678082569464779L;
    //Redis服务器IP
    private static String addr;
    //Redis的端口号
    private static int port;
    //访问密码
    private static String auth;
    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int maxActive;
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int maxIdle;
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int maxWait;
    //连接超时
    private static int timeOut;
    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean testOnBorrow;
    public Jedis jedis;//非切片额客户端连接
    public static JedisPool jedisPool = null;//非切片连接池
    public ShardedJedis shardedJedis;//切片额客户端连接
    public ShardedJedisPool shardedJedisPool;//切片连接池

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    /**
     * 初始化切片池
     */
    public ShardedJedisPool getShardedPool() {
        if (shardedJedisPool == null) {
            // 池基本配置
            JedisPoolConfig config = new JedisPoolConfig();
//            config.setMaxActive(maxActive); //最大连接数
            config.setMaxIdle(maxIdle);//最大空闲连接
//            config.setMaxWait(maxWait);
            config.setTestOnBorrow(testOnBorrow);

            config.setTestWhileIdle(true);
            config.setMinEvictableIdleTimeMillis(60000L);
            config.setNumTestsPerEvictionRun(1);
            config.setTimeBetweenEvictionRunsMillis(30000L);

            // slave链接
            List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
//            logger.info("ip=" + addr + "----" + "port=" + port);
            shards.add(new JedisShardInfo(addr, port, "master"));

            // 构造池
            shardedJedisPool = new ShardedJedisPool(config, shards);
        }
        return shardedJedisPool;
    }


    /**
     * 获取数据
     * @param key
     */
    public void delKey(String... key) {
        Jedis jedis = null;
        try {
            jedis = getConn();
            jedis.del(key);
        } catch (Exception e) {
            //释放redis对象
//            logger.error("" + e.getStackTrace());
            returnJedis(jedis);
        } finally {
            //返还到连接池
            returnResource(jedis);
        }
    }
    public void delKey(String key,String... array) {
        Jedis jedis = null;
        try {
            jedis = getConn();
            jedis.hdel(key,array);
        } catch (Exception e) {
            //释放redis对象
//            logger.error("" + e.getStackTrace());
            returnJedis(jedis);
        } finally {
            //返还到连接池
            returnResource(jedis);
        }
    }

    /**
     * 获取数据
     * @param key
     */
    public void delSKey(String key) {
        ShardedJedis jedis = null;
        try {
            jedis = getShardedConn();
            jedis.del(key);
        } catch (Exception e) {
            //释放redis对象
//            logger.error("" + e.getStackTrace());
            returnShardedJedis(jedis);
        } finally {
            //返还到连接池
            returnShardedResource(jedis);
        }
    }
    /**
     * 获取数据
     * @param key
     */
    public void delSKey(String key,String... array) {
        ShardedJedis jedis = null;
        try {
            jedis = getShardedConn();
            jedis.hdel(key,array);
        } catch (Exception e) {
            //释放redis对象
//            logger.error("" + e.getStackTrace());
            returnShardedJedis(jedis);
        } finally {
            //返还到连接池
            returnShardedResource(jedis);
        }
    }

    /**
     * 获取数据
     * @param key
     */
    public void setValue(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getConn();
            value = jedis.set(key, value);
        } catch (Exception e) {
            //释放redis对象
//            logger.error("" + e.getStackTrace());
            returnJedis(jedis);
        } finally {
            //返还到连接池
            returnResource(jedis);
        }
    }

    /**
     * 设置数据
     * @param key
     */
    public void setValue(String key, Map<String, String> value) {
        Jedis jedis = null;
        try {
            jedis = getConn();
            jedis.hmset(key, value);
        } catch (Exception e) {
            //释放redis对象
//            logger.error("" + e.getStackTrace());
            returnJedis(jedis);
        } finally {
            //返还到连接池
            returnResource(jedis);
        }
    }

    /**
     * 获取数据
     * @param key
     */
    public void setSValue(String key, String value) {
        ShardedJedis jedis = null;
        try {
            jedis = getShardedConn();
            value = jedis.set(key, value);
        } catch (Exception e) {
            //释放redis对象
//            logger.error("" + e.getStackTrace());
            returnShardedJedis(jedis);
        } finally {
            //返还到连接池
            returnShardedResource(jedis);
        }
    }
    /**
     * 获取数据
     * @param key
     */
    public void setSValue(String key,  Map<String, String> value) {
        ShardedJedis jedis = null;
        try {
            jedis = getShardedConn();
            jedis.hmset(key, value);
        } catch (Exception e) {
            //释放redis对象
//            logger.error("" + e.getStackTrace());
            returnShardedJedis(jedis);
        } finally {
            //返还到连接池
            returnShardedResource(jedis);
        }
    }

    /**
     * 获取数据
     * @param key
     */
    public String getValue(String key) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = getConn();
            value = jedis.get(key);
        } catch (Exception e) {
            //释放redis对象
//            logger.info("" + e);
            returnJedis(jedis);
        } finally {
            //返还到连接池
            returnResource(jedis);
        }
        return value;
    }
    /**
     * 获取数据
     * @param key
     */
    public List<String> getValue(String key,String... array) {
        List<String> value = null;
        Jedis jedis = null;
        try {
            jedis = getConn();
            value = jedis.hmget(key,array );
        } catch (Exception e) {
            //释放redis对象
//            logger.info("" + e);
            returnJedis(jedis);
        } finally {
            //返还到连接池
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 获取数据
     * @param key
     */
    public String getSValue(String key) {
        String value = null;
        ShardedJedis jedis = null;
        try {
            jedis = getShardedConn();
            value = jedis.get(key);
        } catch (Exception e) {
            //释放redis对象
//            logger.info("" + e);
            returnShardedJedis(jedis);
        } finally {
            //返还到连接池
            returnShardedResource(jedis);
        }
        return value;
    }
    /**
     * 获取数据
     * @param key
     */
    public List<String> getSValue(String key,String... array) {
        List<String> value = null;
        ShardedJedis jedis = null;
        try {
            jedis = getShardedConn();
            value = jedis.hmget(key, array);
        } catch (Exception e) {
            //释放redis对象
//            logger.info("" + e);
            returnShardedJedis(jedis);
        } finally {
            //返还到连接池
            returnShardedResource(jedis);
        }
        return value;
    }

    private void returnJedis(Jedis jedis) {
        if (jedis != null)
            jedisPool.returnResource(jedis);
    }

    private void returnShardedJedis(ShardedJedis jedis) {
        if (jedis != null)
            shardedJedisPool.returnResource(jedis);
    }

    /**
     * 返还到连接池
     * @param redis
     */
    public void returnResource(Jedis redis) {
        if (redis != null) {
            jedisPool.returnResourceObject(redis);
        }
    }

    /**
     * 返还到连接池
     * @param redis
     */
    public void returnShardedResource(ShardedJedis redis) {
        if (redis != null) {
            shardedJedisPool.returnResourceObject(redis);
        }
    }

    /**
     * 获取jedis连接池
     */
    public JedisPool getPool() {
        if (jedisPool == null) {
            // 池基本配置
            JedisPoolConfig config = new JedisPoolConfig();
//            config.setMaxActive(maxActive); //最大连接数
            config.setMaxIdle(maxIdle);//最大空闲连接
//            config.setMaxWait(maxWait);
            config.setTestOnBorrow(testOnBorrow);

            config.setTestWhileIdle(true);
            config.setMinEvictableIdleTimeMillis(60000L);
            config.setNumTestsPerEvictionRun(1);
            config.setTimeBetweenEvictionRunsMillis(30000L);

            if (!StringExtend.Empty(auth)) {
                jedisPool = new JedisPool(config, addr, port, timeOut, auth);
            } else {
                jedisPool = new JedisPool(config, addr, port, timeOut);
            }
        }
        return jedisPool;
    }

    /**
     * 获取jedis连接
     */
    public Jedis getConn() {
        return getPool().getResource();
    }

    /**
     * 获取ShardedJedis连接
     */
    public ShardedJedis getShardedConn()
    {
        return getShardedPool().getResource();
    }
}
