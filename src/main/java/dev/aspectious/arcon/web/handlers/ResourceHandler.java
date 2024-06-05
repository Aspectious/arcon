package dev.aspectious.arcon.web.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import dev.aspectious.arcon.util.Resources;
import dev.aspectious.arcon.util.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ResourceHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();

        System.out.println("Notice: Web Resource " + path + " requested by client " + exchange.getRemoteAddress());
        Document doc;
        try {
            doc = XML.parseXMLDocument("/PathMap.xml");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        NodeList pathList = doc.getElementsByTagName("Path");

        String response = "404 NOT FOUND";

        for (int i=0; i<pathList.getLength(); i++) {
            Node pathNode = pathList.item(i);
            String urlPath = pathNode.getAttributes().getNamedItem("urlPath").getNodeValue();
            String resourcePath = pathNode.getAttributes().getNamedItem("resourcePath").getNodeValue();
            if (urlPath.equals(path)) {
                response = Resources.parseResourceFile(path);
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
                return;
            }
        }


        exchange.sendResponseHeaders(404, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
