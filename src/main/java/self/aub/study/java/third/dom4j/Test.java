package self.aub.study.java.third.dom4j;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Test {
	public static void main(String[] args) throws Exception {
		SAXReader reader =new SAXReader();
		Document document =reader.read(new File("E:/tmp/xml/smil1.xml"));
		Element root=document.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> nodes=root.elements();
		String s = nodes.get(0).element("text").attributeValue("src");
		System.out.println(s);
		
	}
}
