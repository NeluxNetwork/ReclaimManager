package dev.selixe.commands;

import dev.selixe.files.ConfigFile;
import dev.selixe.files.ReclaimFile;
import dev.selixe.utils.CC;
import dev.selixe.utils.command.Command;
import org.bukkit.command.CommandSender;

public class ReloadCommand extends Command {
    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(!sender.hasPermission("reclaimmanager.reload")) {
            sender.sendMessage(CC.NO_PERM);
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(CC.CHAT_BAR);
            sender.sendMessage(CC.translate("&3&lReclaimManager"));
            sender.sendMessage(CC.translate(""));
            sender.sendMessage(CC.translate("&7/Reclaim &8» &7Reclaim Your Rank Rewards"));
            sender.sendMessage(CC.translate("&7/ReclaimManager reload &8» &7Reload ReclaimManager"));
            sender.sendMessage(CC.translate("&7/ResetReclaim <player:all> &8» &7Reset reclaims."));
            sender.sendMessage(CC.CHAT_BAR);
        } else {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    ReclaimFile.getConfig().reload();
                    ConfigFile.getConfig().reload();
                    sender.sendMessage(CC.translate("&aSuccessfully reloaded all configs."));
                } else {
                    sender.sendMessage(CC.translate("&cInvalid Subcommand"));
                }
            }
        }
        return true;
    }
}
