package me.cactus.gps;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {

    private Configuration config;

    @Override
    public void onEnable() {
        config = getConfig();
        saveDefaultConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cЭта команда доступна только для игроков.");
            return true;
        }
        if (command.getName().equalsIgnoreCase("gps")) {
            if (args.length != 2 && args.length != 3) {
                sender.sendMessage(config.getString("invalid_coordinates")
                        .replace("&", "§"));
                return true;
            }
            Player player = (Player) sender;
            try {
                int x = Integer.parseInt(args[0]);
                int y = args.length == 3 ? Integer.parseInt(args[1]) : 0;
                int z = args.length == 3 ? Integer.parseInt(args[2]) : Integer.parseInt(args[1]);
                player.setCompassTarget(Bukkit.getWorlds().get(0).getSpawnLocation().add(x, y, z));
                player.sendMessage(config.getString("compass_changed")
                        .replace("&", "§"));
            } catch (NumberFormatException ex) {
                sender.sendMessage(config.getString("invalid_coordinates")
                        .replace("&", "§"));
            }
            return true;
        }
        return false;
    }

}
