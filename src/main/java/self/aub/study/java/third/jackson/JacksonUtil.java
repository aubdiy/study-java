package self.aub.study.java.third.jackson;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import self.aub.study.java.third.jackson.bean.Json;

import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author liujinxin
 */
public class JacksonUtil {

	/*private static final ObjectMapper MAPPER = generateMapper();

	private static ObjectMapper generateMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		// mapper.configure(SerializationFeature.,false);
		// mapper.configure(DeserializationFeature., false);
		return mapper;
	}

	*//**
	 * <B>Full Data Binding (POJO) Example</B><br/>
	 * 
	 * @throws IOException
	 * @author aub
	 *//*
	public static void fullDataBinding() throws IOException {
		String path = "/com/aub/third/jackson/file/json.json";
		InputStream is = JacksonUtil.class.getResourceAsStream(path);
		Json json = MAPPER.readValue(is, Json.class);
		System.out.println(json);
		String jsonStr = MAPPER.writeValueAsString(json);
		System.out.println(jsonStr);

	}

	*//**
	 * <B>"Raw" Data Binding Example</B><br/>
	 * 
	 * @throws IOException
	 * @author aub
	 *//*
	public static void rawDataBinding() throws IOException {
		*//*String path = "/com/aub/third/jackson/file/map-json.json";
		InputStream is = JacksonUtil.class.getResourceAsStream(path);

		TypeReference<Map<String, Json>> tr = new TypeReference<Map<String, Json>>() {
		};
		Map<String, Json> map = MAPPER.readValue(is, tr);
		Set<Entry<String, Json>> set = map.entrySet();
		for (Entry<String, Json> entry : set) {
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
		String jsonStr = MAPPER.writeValueAsString(map);
		System.out.println(jsonStr);*//*

	}

	*//**
	 * <B>Tree Model Example</B><br/>
	 * 
	 * @throws IOException
	 * @author aub
	 *//*
	public static void treeModel() throws IOException {
		String path = "/com/aub/third/jackson/file/json.json";
		InputStream is = JacksonUtil.class.getResourceAsStream(path);

		JsonNode jsonNode = MAPPER.readTree(is);
		String i = jsonNode.path("i").textValue();
		System.out.println(i);
		((ObjectNode) jsonNode).put("i", 654);
		((ObjectNode) jsonNode).put("s", "modify");
		System.out.println(jsonNode.get("b"));

		String jsonStr = MAPPER.writeValueAsString(jsonNode);
		System.out.println(jsonStr);

	}*/

	public static void main(String[] args) throws IOException {
//		 fullDataBinding();
		// rawDataBinding();
		// treeModel();
//		long s1 = System.currentTimeMillis();
//		String path = "/com/aub/third/jackson/file/tree.json";
//		InputStream is = JacksonUtil.class.getResourceAsStream(path);
//
//		long s2 = System.currentTimeMillis();
//
//		JsonNode jsonNode = MAPPER.readTree(is);
//		long s5 = System.currentTimeMillis();
//
//		System.out.println("size : " + jsonNode.size());
//		JsonNode firstJN = jsonNode.get(0);
//		ArrayNode firstJNContent = (ArrayNode) firstJN.get("content");
//		System.out.println("firstJNContent size : " + firstJNContent.size());
//		String ss = "{\"aa\":\"aavvv\"}";
//		firstJNContent.insert(1, MAPPER.readTree(ss));
//
//		long s3 = System.currentTimeMillis();
//
//		String jsonStr = MAPPER.writeValueAsString(jsonNode);
//		System.out.println(jsonStr);
//		long s4 = System.currentTimeMillis();
//		System.out.println(s2 - s1);
//		System.out.println(s5 - s2);
//		System.out.println(s3 - s2);
//		System.out.println(s4 - s3);
	}
}
