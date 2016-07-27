package self.aub.study.java.third.jedis;

import java.util.HashMap;

public class RedisClientFactory {

	private static HashMap<String, RedisClient> redisServiceMap = new HashMap<String, RedisClient>();
	
	public static RedisClient GetJRedisService(String redisIP, int redisPort, int index) {
		String key = getKey(redisIP, redisPort, index);
		if (!redisServiceMap.containsKey(key) || redisServiceMap.get(key) == null) {
			RedisClient redisService = new RedisClient(redisIP, redisPort, index);
			redisServiceMap.put(key, redisService);
		}
		return redisServiceMap.get(key);
	}

	private static String getKey(String redisIP, int redisPort, int index) {
		StringBuffer key = new StringBuffer(redisIP);
		key.append(redisPort);
		key.append(index);
		return key.toString();
	}
	
}
