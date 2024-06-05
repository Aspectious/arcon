package dev.aspectious.arcon.util;

import java.io.*;

public class Resources {
    public static String parseResourceFile(String resourceFile) throws IOException {
        String out = "";
        try (InputStream in = Resources.class.getResourceAsStream(resourceFile);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                out += line + "\n";
            }
        }
        return out;
    }
}
