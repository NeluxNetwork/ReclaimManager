package dev.selixe.commands;

import dev.selixe.ReclaimManager;
import dev.selixe.files.ConfigFile;
import dev.selixe.files.ReclaimFile;
import dev.selixe.utils.CC;
import dev.selixe.utils.command.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ReclaimCommand extends Command {

    private final FileConfiguration config = ConfigFile.getConfig();

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        if (!sender.hasPermission("reclaimmanager.reclaim")) {
            sender.sendMessage(CC.NO_PERM);
            return true;
        }
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(CC.NO_CONSOLE);
            return true;
        }
        Player player = (Player) sender;
        String uuid = player.getUniqueId().toString();
        if (ReclaimFile.getConfig().getBoolean("reclaims." + uuid)) {
            player.sendMessage(CC.translate("&cYou have already reclaimed this map."));
        } else {
            String rank = ReclaimManager.getInstance().getPermissionCore().getRank(player);
            if (config.getConfigurationSection("reclaims").getKeys(false).contains(rank)) {
                ReclaimFile.getConfig().set("reclaims." + uuid, true);
                ReclaimFile.getConfig().save();
                ReclaimFile.getConfig().reload();
                player.sendMessage(CC.translate("&aSuccessfully reclaimed your rank rewards."));
                config.getStringList("reclaims." + rank + ".commands").stream()
                        .forEach(m -> Bukkit.getServer().dispatchCommand(console,  m.toString().replace("%player%", player.getDisplayName())));
            } else {
                player.sendMessage(CC.translate("&cThere was no reclaim for your rank..."));
            }
        }
        return true;
    }
}