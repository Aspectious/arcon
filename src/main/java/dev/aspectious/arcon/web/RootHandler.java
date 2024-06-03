package dev.aspectious.arcon.web;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;

import java.io.IOException;

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

        String response = "<h1>Server start success if you see this message</h1>" + "<h1>Port: " + 80 + "</h1>" + "<div>" + log + "</div>";

        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
