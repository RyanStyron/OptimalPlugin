package optimalplugin.command.commands.world.home;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import optimalplugin.OptimalPlugin;
import optimalplugin.utils.MessageUtils;
import optimalplugin.utils.FileManagers.HomeFileManager;

public class CommandHome implements CommandExecutor {

    private HomeFileManager homeFileManager = OptimalPlugin.homeFileManager;
    private FileConfiguration homeFile = homeFileManager.getData();

    public CommandHome(OptimalPlugin plugin) {
        plugin.getCommand("home").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("home")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                UUID playerId = player.getUniqueId();

                if (args.length == 0) {
                    if (homeFile.getString("players." + playerId + ".home") != null) {
                        String homeWorld = homeFile.getString("players." + playerId + ".home.world");
                        double homeX = homeFile.getDouble("players." + playerId + ".home.x");
                        double homeY = homeFile.getDouble("players." + playerId + ".home.y");
                        double homeZ = homeFile.getDouble("players." + playerId + ".home.z");
                        float homeYaw = (float) homeFile.getDouble("players." + playerId + ".home.yaw");
                        float homePitch = (float) homeFile.getDouble("players." + playerId + ".home.pitch");
                        Location homeLocation = new Location(Bukkit.getWorld(homeWorld), homeX, homeY, homeZ);
                        homeLocation.setYaw(homeYaw);
                        homeLocation.setPitch(homePitch);

                        MessageUtils.configMessage(sender, "CommandHome.home-teleport-message", null, null);
                        player.teleport(homeLocation);
                    } else
                        MessageUtils.configMessage(sender, "CommandHome.home-null-error", null, null);
                } else
                    MessageUtils.argumentError(sender, "/home");
            } else
                MessageUtils.consoleError();
        }
        return false;
    }
}