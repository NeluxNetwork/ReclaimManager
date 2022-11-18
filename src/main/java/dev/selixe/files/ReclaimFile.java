package dev.selixe.files;

import dev.selixe.ReclaimManager;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;



public class ReclaimFile extends YamlConfiguration {

    private File file;
    private static ReclaimFile config;

    public ReclaimFile() {
        file = new File(ReclaimManager.getInstance().getDataFolder(), "reclaims.yml");
        if(!file.exists()) ReclaimManager.getInstance().saveResource("reclaims.yml", false);
        this.reload();
    }

    public static ReclaimFile getConfig() {
        if(config == null) {
            config = new ReclaimFile();
        }
        return config;
    }

    public void save() {
        try {
            super.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        try {
            super.load(this.file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
