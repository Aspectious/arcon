package dev.aspectious.arcon.web.handlers;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;

import dev.aspectious.arcon.util.*;

public class RootHandler implements HttpHandler {
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

        String response = Resources.parseResourceFile("/framework/html/index.html") + log;
        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
