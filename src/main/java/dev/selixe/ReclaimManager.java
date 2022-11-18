package dev.selixe;

import dev.selixe.commands.ReclaimCommand;
import dev.selixe.commands.ReloadCommand;
import dev.selixe.commands.ResetReclaimCommand;
import dev.selixe.files.ConfigFile;
import dev.selixe.files.ReclaimFile;
import dev.selixe.permissions.LuckPermsPermissionCore;
import dev.selixe.permissions.PermissionCore;
import dev.selixe.permissions.VaultPermissionCore;
import dev.selixe.utils.command.PluginCommand;
import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("InstantiationOfUtilityClass")
@Getter
public final class ReclaimManager extends JavaPlugin {

    private PermissionCore permissionCore;
    private List<YamlConfiguration> configFiles;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.files();
        this.commands();
        this.permissions();
    }

    private void commands() {
        new PluginCommand("reclaim", ReclaimCommand.class);
        new PluginCommand("reclaimmanager", ReloadCommand.class);
        new PluginCommand("resetreclaim", ResetReclaimCommand.class);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private String permissions() {
        String core = ConfigFile.getConfig().getString("rank-system");
        switch (core) {
            case "Vault":
                permissionCore = new VaultPermissionCore();
                return "Vault";
            case "LuckPerms":
                permissionCore = new LuckPermsPermissionCore();
                return "LuckPerms";
        }
        return "Nothing";
    }

    private void files() {
        configFiles = Arrays.asList(ConfigFile.getConfig(), ReclaimFile.getConfig());
    }

    public static ReclaimManager getInstance() {
        return ReclaimManager.getPlugin(ReclaimManager.class);
    }
}
