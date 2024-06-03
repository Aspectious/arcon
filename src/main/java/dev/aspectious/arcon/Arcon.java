package dev.aspectious.arcon;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import dev.aspectious.arcon.web.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Arcon extends JavaPlugin {
    private static WebServer webinterface = new WebServer(WebServerType.HTTP,80);

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("cv")) {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "say hello");
            sender.sendMessage("MV create and modify ran successfull for 1");
            return true;
        }
        return false;
    }
}
