package dev.aspectious.arcon.web;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;

import java.io.IOException;

public class RootHandler implements HttpHandler {
    private String parseResourceFile(String resourceFile) throws IOException {
        String out = "";
        try (InputStream in = getClass().getResourceAsStream(resourceFile);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                out += line + "\n";
            }
        }
        return out;
    }
    @Override
    public void handle(HttpExchange he) throws IOException {
        String log = "";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("logs/latest.log"));
            String line;
            while ((line = br.readLine()) != null) {
                log = log + "<p>" + line + "</p>";
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        String response = parseResourceFile("/framework/index.html") + log;
        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
