package dev.selixe.reclaims;

import dev.selixe.files.ConfigFile;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RewardManager {


    public static void getCommands(Player player) {
        FileConfiguration config = ConfigFile.getConfig();
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        for (String ranks : config.getConfigurationSection("reclaim-rewards").getKeys(false)) {
            config.getStringList("reclaim-rewards." + ranks + ".commands").stream()
                    .forEach(m -> Bukkit.getServer().dispatchCommand(console, m.toString()));
        }
    }
}
