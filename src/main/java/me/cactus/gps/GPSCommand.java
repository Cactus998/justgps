package me.cactus.gps;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.cactus.gps.Main.*;

public class GPSCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cЭта команда доступна только для игроков.");
            return true;
        } else if (!sender.hasPermission("gps.use")) {
            sender.sendMessage(noPermissionMessage);
            return true;
        } else if (args.length != 2 && args.length != 3) {
            sender.sendMessage(invalidCoordinatesMessage);
            return true;
        }
        Player player = (Player) sender;
        try {
            int x = Integer.parseInt(args[0]);
            int y = args.length == 3 ? Integer.parseInt(args[1]) : 0;
            int z = args.length == 3 ? Integer.parseInt(args[2]) : Integer.parseInt(args[1]);
            player.setCompassTarget(Bukkit.getWorlds().get(0).getSpawnLocation().add(x, y, z));
            player.sendMessage(compassChangedMessage);
        } catch (NumberFormatException ex) {
            sender.sendMessage(invalidCoordinatesMessage);
        }
        return true;
    }
}