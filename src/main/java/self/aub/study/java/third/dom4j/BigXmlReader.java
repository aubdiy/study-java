package self.aub.study.java.third.dom4j;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.ElementPath;
import org.dom4j.io.SAXReader;

public class BigXmlReader implements ElementHandler {

	@Override
	public void onEnd(ElementPath elementPath) {
		Element element = elementPath.getCurrent(); //获得当前节点
		String elementName = element.getName(); 
		if(elementName.equals("root")){
			System.out.println("解析到根节点"); 
		}else{
			System.out.println("解析到子节点：" + elementName); 
		}
	}

	@Override
	public void onStart(ElementPath arg0) {

	}

	public static void main(String[] args) throws Exception {

		File xmlFile = new File("E:/test.xml");
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(xmlFile));
		SAXReader reader = new SAXReader();
		reader.setDefaultHandler(new BigXmlReader());
		reader.read(bis);
	}

}
