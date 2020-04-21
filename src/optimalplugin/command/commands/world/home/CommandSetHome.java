package optimalplugin.command.commands.world.home;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import optimalplugin.OptimalPlugin;
import optimalplugin.utils.MessageUtils;
import optimalplugin.utils.FileManagers.HomeFileManager;

public class CommandSetHome implements CommandExecutor {

    private HomeFileManager homeFileManager = OptimalPlugin.homeFileManager;
    private FileConfiguration homeFile = homeFileManager.getData();

    public CommandSetHome(OptimalPlugin plugin) {
        plugin.getCommand("sethome").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("sethome")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                UUID playerId = player.getUniqueId();

                if (args.length == 0) {
                    if (homeFile.getString("players." + playerId + ".home") == null) {
                        Location location = player.getLocation();
                        String locationWorldName = location.getWorld().getName();
                        double locationX = location.getX();
                        double locationY = location.getY();
                        double locationZ = location.getZ();
                        float locationYaw = location.getYaw();
                        float locationPitch = location.getPitch();

                        homeFile.set("players." + playerId + ".home.world", locationWorldName);
                        homeFile.set("players." + playerId + ".home.x", locationX);
                        homeFile.set("players." + playerId + ".home.y", locationY);
                        homeFile.set("players." + playerId + ".home.z", locationZ);
                        homeFile.set("players." + playerId + ".home.yaw", locationYaw);
                        homeFile.set("players." + playerId + ".home.pitch", locationPitch);
                        homeFileManager.saveData();

                        MessageUtils.configMessage(sender, "CommandHome.home-created-message", null, null);
                    } else
                        MessageUtils.configMessage(sender, "CommandHome.home-exists-error", null, null);
                } else
                    MessageUtils.argumentError(sender, "/sethome");
            } else
                MessageUtils.consoleError();
        }
        return false;
    }
}