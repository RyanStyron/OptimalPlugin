package optimalplugin.command.commands.world.home;

import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import optimalplugin.OptimalPlugin;
import optimalplugin.utils.MessageUtils;
import optimalplugin.utils.FileManagers.HomeFileManager;

public class CommandDeleteHome implements CommandExecutor {

    private HomeFileManager homeFileManager = OptimalPlugin.homeFileManager;
    private FileConfiguration homeFile = homeFileManager.getData();

    public CommandDeleteHome(OptimalPlugin plugin) {
        plugin.getCommand("deletehome").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("deletehome")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                UUID playerId = player.getUniqueId();

                if (args.length == 0) {
                    if (homeFile.getString("players." + playerId + ".home") != null) {
                        homeFile.set("players." + playerId + ".home", null);
                        homeFileManager.saveData();

                        MessageUtils.configMessage(sender, "CommandHome.home-deleted-message", null, null);
                    } else
                        MessageUtils.configMessage(sender, "CommandHome.home-null-error", null, null);
                } else
                   MessageUtils.argumentError(sender, "/deletehome");
            } else
                MessageUtils.consoleError();
        }
        return false;
    }
}