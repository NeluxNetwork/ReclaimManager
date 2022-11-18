package dev.selixe.permissions;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.entity.Player;

public class LuckPermsPermissionCore implements PermissionCore {


    @Override
    public String getRankColor(Player player) {
        User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(player);
        return user.getCachedData().getMetaData().getPrimaryGroup();
    }


    @Override
    public String getRank(Player player) {
        User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(player);
        return user.getCachedData().getMetaData().getPrimaryGroup();
    }
}
