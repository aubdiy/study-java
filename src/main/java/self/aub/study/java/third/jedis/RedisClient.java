package self.aub.study.java.third.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.List;
import java.util.Set;

public class RedisClient {

	private int index = 0;
	private JedisPool pool = null;
	private final static int WAIT_TIME = 3000;

	public RedisClient(String address, int port, int index) {
		boolean isOK = false;
		while (!isOK) {
			try {
				JedisPoolConfig config = new JedisPoolConfig();
				config.setMaxTotal(100);
				config.setMinIdle(10);
				config.setMaxIdle(10);
				config.setTestOnBorrow(true);
				this.pool = new JedisPool(config, address, port, WAIT_TIME);
				this.index = index;
				isOK = true;
			} catch (Exception e) {
				System.out.println("------------init redis fail.-----------");
				isOK = false;
			}
		}

	}

	public RedisClient(JedisPoolConfig cfg, String address, int port, int waitTime, int index) {
		boolean isOK = false;
		while (!isOK) {
			try {
				this.pool = new JedisPool(cfg, address, port, waitTime);
				this.index = index;
				isOK = true;
			} catch (Exception e) {
				System.out.println("------------init redis fail.-----------");
				isOK = false;
			}
		}

	}

	public boolean exists(String key) {
		try {
			Jedis jedis = pool.getResource();
			jedis.select(index);
			boolean result = jedis.exists(key);
			pool.returnResource(jedis);
			return result;
		} catch (JedisConnectionException e) {
			sleep();
			return exists(key);
		}
	}

	public void rpush(String key, String value) {
		try {
			Jedis jedis = pool.getResource();
			jedis.select(index);
			jedis.rpush(key, value);
			pool.returnResource(jedis);
		} catch (JedisConnectionException e) {
			sleep();
			rpush(key, value);
		}
	}

	public void lpush(String key, String value) {
		try {
			Jedis jedis = pool.getResource();
			jedis.select(index);
			jedis.lpush(key, value);
			pool.returnResource(jedis);
		} catch (JedisConnectionException e) {
			sleep();
			lpush(key, value);
		}
	}

	public String lpop(String key) {
		try {
			Jedis jedis = pool.getResource();
			jedis.select(index);
			String result = jedis.lpop(key);
			pool.returnResource(jedis);
			return result;
		} catch (JedisConnectionException e) {
			sleep();
			return lpop(key);
		}
	}

	public String rpop(String key) {
		try {
			Jedis jedis = pool.getResource();
			jedis.select(index);
			String result = jedis.rpop(key);
			pool.returnResource(jedis);
			return result;
		} catch (JedisConnectionException e) {
			sleep();
			return rpop(key);
		}
	}

	public List<String> lrange(String key, int start, int end) {
		try {
			Jedis jedis = pool.getResource();
			jedis.select(index);
			List<String> result = jedis.lrange(key, start, end);
			pool.returnResource(jedis);
			return result;
		} catch (JedisConnectionException e) {
			sleep();
			return lrange(key, start, end);
		}
	}

	public void lrem(String key, int count, String value) {
		try {
			Jedis jedis = pool.getResource();
			jedis.select(index);
			jedis.lrem(key, count, value);
			pool.returnResource(jedis);
		} catch (JedisConnectionException e) {
			sleep();
			lrem(key, count, value);
		}
	}

	public Long llen(String key) {
		try {
			Jedis jedis = pool.getResource();
			jedis.select(index);
			Long result = jedis.llen(key);
			pool.returnResource(jedis);
			return result;
		} catch (JedisConnectionException e) {
			sleep();
			return llen(key);
		}
	}

	public Set<String> keys(String key) {
		try {
			Jedis jedis = pool.getResource();
			jedis.select(index);
			Set<String> result = jedis.keys(key);
			pool.returnResource(jedis);
			return result;
		} catch (JedisConnectionException e) {
			sleep();
			return keys(key);
		}
	}

	public Long del(String key) {
		try {
			Jedis jedis = pool.getResource();
			jedis.select(index);
			Long result = jedis.del(key);
			pool.returnResource(jedis);
			return result;
		} catch (JedisConnectionException e) {
			sleep();
			return del(key);
		}
	}

	public void set(String key, String value) {
		try {
			Jedis jedis = pool.getResource();
			jedis.select(index);
			jedis.set(key, value);
			pool.returnResource(jedis);
		} catch (JedisConnectionException e) {
			sleep();
			set(key, value);
		}
	}

	public String get(String key) {
		try {
			Jedis jedis = pool.getResource();
			jedis.select(index);
			String result = jedis.get(key);
			pool.returnResource(jedis);
			return result;
		} catch (JedisConnectionException e) {
			sleep();
			return get(key);
		}
	}

	public void SetExpire(String key, int time) {
		try {
			Jedis jedis = pool.getResource();
			jedis.select(index);
			jedis.expire(key, time);
			pool.returnResource(jedis);
		} catch (JedisConnectionException e) {
			sleep();
			SetExpire(key, time);
		}
	}

	/**
	 *  dangerous
	 * @return
	 */
	public Jedis getResource() {
		return this.pool.getResource();
	}

	public void returnResource(Jedis jedis) {
		this.pool.returnResource(jedis);
	}

	private void sleep() {
		System.out.println("------>redis reconnect.");
		try {
			Thread.sleep(500);
		} catch (Exception e) {

		}
	}
}
