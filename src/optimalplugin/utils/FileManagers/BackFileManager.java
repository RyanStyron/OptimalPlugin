package optimalplugin.utils.FileManagers;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import optimalplugin.utils.MessageUtils;

public class BackFileManager {

    private static BackFileManager instance = new BackFileManager();

    public static BackFileManager getInstance() {
        return instance;
    }

    private FileConfiguration data;
    private File dataFile;
    private CommandSender console = Bukkit.getConsoleSender();

    public void setup(Plugin plugin) {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        dataFile = new File(plugin.getDataFolder(), "back.yml");

        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                MessageUtils.message(console, "&4&l(!)&c Could not create back.yml!");
            }
        }

        data = YamlConfiguration.loadConfiguration(dataFile);
    }

    public FileConfiguration getData() {
        return data;
    }

    public void saveData() {
        try {
            data.save(dataFile);
        } catch (IOException e) {
            MessageUtils.message(console, "&4&l(!)&c Could not save back.yml!");
        }
    }

    public void reloadData() {
        data = YamlConfiguration.loadConfiguration(dataFile);
    }
}