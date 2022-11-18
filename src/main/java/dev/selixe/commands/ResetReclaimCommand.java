package dev.selixe.commands;

import dev.selixe.files.ReclaimFile;
import dev.selixe.utils.CC;
import dev.selixe.utils.command.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetReclaimCommand extends Command {

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!sender.hasPermission("reclaimmanager.reset")) {
            sender.sendMessage(CC.NO_PERM);
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(CC.translate("&cUsage: /Reclaimreset <player:all>"));
        } else {
            if (args.length == 1) {
                if (!args[0].equalsIgnoreCase("all")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        String uuid = target.getUniqueId().toString();
                        if (ReclaimFile.getConfig().getBoolean("reclaims." + uuid)) {
                            ReclaimFile.getConfig().set("reclaims." + uuid, false);
                            sender.sendMessage(CC.translate("&aSuccessfully reset " + target.getDisplayName() + " reclaim."));
                        }
                    } else {
                        sender.sendMessage(CC.translate("&cThis player does not exist..."));
                    }
                } else {
                    sender.sendMessage(CC.translate("&aSuccessfully reset all reclaims"));
                    ReclaimFile.getConfig().set("reclaims", null);
                }
                ReclaimFile.getConfig().save();
                ReclaimFile.getConfig().reload();
            }
        }
        return true;
    }
}
