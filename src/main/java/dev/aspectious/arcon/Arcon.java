package dev.aspectious.arcon;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import dev.aspectious.arcon.web.*;


public class Arcon extends JavaPlugin {
    private WebServer webinterface;
    private WSServer wsserver;

    @Override
    public void onEnable() {
        webinterface = new WebServer(WebServerType.HTTP,80);
        wsserver = new WSServer(81);
        wsserver.start();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("cv")) {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "say hello");
            sender.sendMessage("MV create and modify ran successfull for 1");
            return true;
        }
        return false;
    }
}
