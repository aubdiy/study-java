package self.aub.study.java;

public class NumberUtil {

	private static long lastTimeMillis = 0;
	private static long increment = 0;
	
	/**
	 *@return String
	 *@author LJX
	 *@date 2011-3-15 下午06:12:05
	 *@comment 根据时间来生成唯一数
	 */
	public synchronized static String getUniqueId(){
		long nowTimeMiilis = System.currentTimeMillis();
		if (lastTimeMillis != nowTimeMiilis){
			lastTimeMillis = nowTimeMiilis;
			increment = 0;
		}else{
			increment ++ ;
		}
		return String.valueOf(lastTimeMillis).concat(String.valueOf(increment));
	}
}
