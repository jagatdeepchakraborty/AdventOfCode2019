package adevnt.of.code.Utils;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Util {

    public static String loadResource(String fileName) throws Exception {
        if (fileName != null) {
            URI uri = Util.class.getClassLoader().getResource(fileName).toURI();
            return new String(Files.readAllBytes(Paths.get(uri)));
        }
        return null;
    }
}
