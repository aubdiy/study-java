package self.aub.study.java.jdk7;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author liujinxin
 * @since 2015-12-01 14:14
 */
public class ExceptionDeal {

    public static void main(String[] args) {
        Path tmp = Paths.get("/Users/liujinxin/", "tmp", "app.log");
        Logger global = Logger.getGlobal();
        global.setLevel(Level.ALL);
        global.warning(tmp.getRoot().toString());
        global.warning(tmp.getParent().toString());
        global.warning(tmp.toAbsolutePath().toString());

        try {
            byte[] bytes = Files.readAllBytes(tmp);
            global.info(new String(bytes, StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        global.info(String.valueOf(Objects.equals(null, null)));

    }
}
