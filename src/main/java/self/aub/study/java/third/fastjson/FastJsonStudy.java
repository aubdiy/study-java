
package self.aub.study.java.third.fastjson;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class  FastJsonStudy {
	private static final Logger LOG = LoggerFactory.getLogger(FastJsonStudy.class);

	public static void main(String[] args) {
		// 基本模式
		BaseObject baseObject = new BaseObject(1, "刘金鑫", new Date());
		String[] phones={"1","2"};
		baseObject.setPhones(phones);
		String text = JSON.toJSONString(baseObject);
		LOG.info("基本模式:{}", text);
		BaseObject parseObject = JSON.parseObject(text, BaseObject.class);
		LOG.info("反序列化基本模式:{}", parseObject);
		// 单引号模式
		BaseObject baseObjectSingleQuotes = new BaseObject(1, "刘金鑫", new Date());
		String textSingleQuotes = JSON.toJSONString(baseObjectSingleQuotes, SerializerFeature.UseSingleQuotes);
		LOG.info("单引号模式:{}", textSingleQuotes);
		BaseObject parseObjectSingleQuotes = JSON.parseObject(textSingleQuotes, BaseObject.class);
		LOG.info("反序列化单引号模式:{}", parseObjectSingleQuotes);
		// 带类信息模式
		BaseObject baseObjectWriteClassName = new BaseObject(1, "刘金鑫", new Date());
		String textWriteClassName = JSON.toJSONString(baseObjectWriteClassName, SerializerFeature.WriteClassName);
		LOG.info("带类信息模式:{}", textWriteClassName);
		BaseObject parseObjectWriteClassName = (BaseObject) JSON.parse(textWriteClassName);
		LOG.info("反序列化带类信息模式:{}", parseObjectWriteClassName);

		// 日期格式化模式(反序列化日期格式固定)
		BaseObject baseObjectDateFmt = new BaseObject(1, "刘金鑫", new Date());
		String textDateFmt = JSON.toJSONStringWithDateFormat(baseObjectDateFmt, "yyyyMMdd");
		LOG.info("日期格式化模式:{}", textDateFmt);
		BaseObject parseObjectDateFmt = JSON.parseObject(textDateFmt, BaseObject.class);
		LOG.info("反序列化日期格式化模式:{}", parseObjectDateFmt);

		// 集合类list
		List<BaseObject> list = new ArrayList<BaseObject>();
		list.add(new BaseObject(1, "集合list--1", new Date()));
		list.add(new BaseObject(1, "集合list--2", new Date(), new SubObject("吼吼")));
		String textList = JSON.toJSONString(list);
		LOG.info("集合类list:{}", textList);
		List<BaseObject> parseObjectList = JSON.parseArray(textList, BaseObject.class);
		LOG.info("反序列化集合类list：{}", parseObjectList);

		// Map
		Map<String, BaseObject> map = new HashMap<String, BaseObject>();
		map.put("map1", new BaseObject(1, "map--1", new Date()));
		map.put("map2", new BaseObject(1, "map--2", new Date(), new SubObject("吼吼")));
		String textMap = JSON.toJSONString(map);
		LOG.info("Map:{}", textMap);
		Map<String, BaseObject> parseObjectMap = JSON.parseObject(textMap, new TypeReference<Map<String, BaseObject>>() {
		});
		LOG.info("反序列化Map：{}", parseObjectMap);

	}
}
