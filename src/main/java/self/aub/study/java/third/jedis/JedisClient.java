package self.aub.study.java.third.jedis;

import redis.clients.jedis.Jedis;

public class JedisClient {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("10.8.210.166", 6379);
		String string = jedis.get("aa");
		System.out.println(string);
	}

}
