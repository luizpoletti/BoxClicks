package com.boxstore.clicks.utils;

import com.boxstore.clicks.Main;
import com.google.common.base.Strings;
import lombok.val;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class TangramUtils {

    protected static Main main = Main.getPlugin(Main.class);

    private static final FileConfiguration config = main.getConfig();
    private static final FileConfiguration messages = main.getMessagesFile().getConfig();
    private static final FileConfiguration sounds = main.getSoundsFile().getConfig();

    public static void sendMessage(CommandSender sender, String message) {
        message = message.replace("&", "§");

        if (sender instanceof ConsoleCommandSender) {
            val name = main.getName();
            sender.sendMessage("§b[" + name + "] " + message);
            return;

        }
        val prefix = config.getString("Prefix.Model").replace("&", "§");
        val prefixEnabled = config.getBoolean("Prefix.Enable");
        if (prefixEnabled) {
            if (message.isEmpty())
                sender.sendMessage(message);

            else
                sender.sendMessage(prefix + " " + message);

        } else
            sender.sendMessage(message);
    }

    public static String sendProgressBar(double current, double max) {
        val symbol = config.getString("ProgressBar.Symbol");
        val completedColor = ChatColor.valueOf(config.getString("ProgressBar.Symbol-Colors.Completed"));
        val notCompleteColor = ChatColor.valueOf(config.getString("ProgressBar.Symbol-Colors.NotComplete"));
        val totalBars = config.getInt("ProgressBar.Amount-Bars");

        val percent = (float) ((float) current / max);
        val progressBars = (int) (totalBars * percent);

        return Strings.repeat(completedColor + symbol, progressBars) + Strings.repeat(notCompleteColor + symbol, totalBars - progressBars);
    }

    public static void playSound(CommandSender sender, Sound sound) {
        if (!(sender instanceof Player))
            return;

        val player = (Player) sender;
        player.playSound(player.getLocation(), sound, 1.0F, 1.0F);
    }

    public static boolean hasPermission(CommandSender sender, String permission) {
        if (!sender.hasPermission(permission)) {
            sendMessage(sender, messages.getString("No-Permission"));
            playSound(sender, Sound.valueOf(sounds.getString("No-Permission").toUpperCase()));
            return false;
        }
        return true;
    }

    public static boolean playerIsOnline(CommandSender sender, Player target) {
        if (target == null) {
            sendMessage(sender, messages.getString("Offline-Player"));
            playSound(sender, Sound.valueOf(sounds.getString("Offline-Player").toUpperCase()));
            return false;
        }
        return true;
    }

    public static boolean isNumber(CommandSender sender, String number) {
        if (!NumberUtils.isNumber(number)) {
            sendMessage(sender, messages.getString("Invalid-Number"));
            playSound(sender, Sound.valueOf(sounds.getString("Invalid-Number").toUpperCase()));
            return false;
        }
        return true;
    }

    public static boolean isZero(CommandSender sender, double number) {
        if (number <= 0) {
            sendMessage(sender, messages.getString("Invalid-Number"));
            playSound(sender, Sound.valueOf(sounds.getString("Invalid-Number").toUpperCase()));
            return true;
        }
        return false;
    }

    public static void runCommand(Player player, String command) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("{player}", player.getName()));
    }

    public static void runCommandList(Player player, List<String> commands) {
        commands.forEach(command -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("{player}", player.getName())));
    }

    public static ItemStack getPlayerHead(String playerName) {
        return new ItemBuilder(Material.SKULL_ITEM, 1, 3).setOwner(playerName).build();
    }

    public static String locationSerializer(Location location) {
        val world = location.getWorld().getName();

        val x = location.getX();
        val y = location.getY();
        val z = location.getZ();

        val yaw = location.getYaw();
        val pitch = location.getPitch();

        return world + ";" + x + ";" + y + ";" + z + ";" + yaw + ";" + pitch;
    }

    public static Location locationDeserializer(String location) {
        val split = location.split(";");

        val world = Bukkit.getWorld(split[0]);

        val x = Double.parseDouble(split[1]);
        val y = Double.parseDouble(split[2]);
        val z = Double.parseDouble(split[3]);

        val yaw = Float.parseFloat(split[4]);
        val pitch = Float.parseFloat(split[5]);

        return new Location(world, x, y, z, yaw, pitch);
    }

}
