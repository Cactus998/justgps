package me.cactus.gps;

import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Configuration config;

    public static String invalidCoordinatesMessage = ChatColor.translateAlternateColorCodes('&', config.getString("invalid_coordinates"));
    public static String compassChangedMessage = ChatColor.translateAlternateColorCodes('&', config.getString("compass_changed"));
    public static String noPermissionMessage = ChatColor.translateAlternateColorCodes('&', config.getString("no_permission"));

    @Override
    public void onEnable() {
        config = getConfig();
        saveDefaultConfig();
        getCommand("gps").setExecutor(new GPSCommand());
    }
}
