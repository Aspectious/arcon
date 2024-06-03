package dev.aspectious.arcon.web;

import com.sun.net.httpserver.*;
import jdk.jshell.spi.ExecutionControl;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.net.*;


public class WebServer  {
    private WebServerType type;
    private int port;
    private HttpServer server;

    public WebServer(WebServerType type, int port)  {
        if (type == WebServerType.HTTP) {
            this.port = port;
            this.type = WebServerType.HTTP;
            try {
                this.server = HttpServer.create(new InetSocketAddress(port), 0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("server started at " + port);
            this.server.createContext("/", new RootHandler());
            this.server.setExecutor(null);
            this.server.start();
        } else {
            try {
                throw new ExecutionControl.NotImplementedException("Method not Implemented");
            } catch (ExecutionControl.NotImplementedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
