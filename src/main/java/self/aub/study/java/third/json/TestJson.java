package self.aub.study.java.third.json;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

;

/**
 * @author liujinxin
 * @since 2015-07-03 09:56
 */
public class TestJson {

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 基本模式
        int initCount = 1000000;
        int doCount = 10000000;
        HashMap<String, String> stringHashMap = new HashMap<>();
        stringHashMap.put("a", "av");

        System.out.println(JSON.toJSONString(stringHashMap));
        System.out.println(mapper.writeValueAsString(stringHashMap));

        for (int i = 0; i < initCount; i++) {
            mapper.writeValueAsString(stringHashMap);
        }

        for (int i = 0; i < initCount; i++) {
            JSON.toJSONString(stringHashMap);
        }

        for (int i = 0; i < initCount; i++) {
            mapper.writeValueAsString(stringHashMap);
        }





        for (int i = 0; i < initCount; i++) {
            mapper.writeValueAsString(stringHashMap);
        }

        long l2 = System.currentTimeMillis();
        for (int i = 0; i < doCount; i++) {
            mapper.writeValueAsString(stringHashMap);

        }
        System.out.println(System.currentTimeMillis() - l2);




        for (int i = 0; i < initCount; i++) {
            JSON.toJSONString(stringHashMap);
        }
        long l1 = System.currentTimeMillis();
        for (int i = 0; i < doCount; i++) {
            JSON.toJSONString(stringHashMap);
        }
        System.out.println(System.currentTimeMillis() - l1);



    }
}
