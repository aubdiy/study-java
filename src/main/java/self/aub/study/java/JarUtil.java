package self.aub.study.java;

import self.aub.study.java.test.TT;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author liujinxin
 * @since 2015-06-12 15:43
 */
public class JarUtil {



    /**
     * 运行jar中的类的方法
     *
     * @throws Exception
     */
    public static void runMethodInJar() throws Exception {
        URL url = new URL("file:/Users/liujinxin/百度云同步盘/Workspace/IdeaWS/study-java/target/study-java-1.0.jar");
        URLClassLoader myClassLoader = new URLClassLoader(new URL[]{url}, Thread.currentThread().getContextClassLoader());
        Class<?> myClass1 = myClassLoader.loadClass("self.aub.study.java.test.TI");

        System.out.println(myClass1.newInstance() instanceof JarUtil);

        TT tt = (TT) myClass1.newInstance();
        String result = tt.tt("啊哈，tt!");
        System.out.println(result);
    }


    public static void main(String[] args) throws Exception {
        runMethodInJar();


    }
}
