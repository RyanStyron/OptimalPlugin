package optimalplugin.command.commands.world;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import optimalplugin.OptimalPlugin;
import optimalplugin.utils.MessageUtils;

public class CommandSpawn implements CommandExecutor {

    public CommandSpawn(OptimalPlugin plugin) {
        plugin.getCommand("spawn").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("spawn")) {
            if (sender instanceof Player) {
                if (args.length == 0) {
                    Player player = (Player) sender;
                    Location spawnLocation = player.getWorld().getSpawnLocation();

                    player.teleport(spawnLocation);
                    MessageUtils.configMessage(sender, "CommandHub.spawn-message", null, null);
                } else
                    MessageUtils.argumentError(sender, "/spawn");
            } else
                MessageUtils.consoleError();
        }
        return false;
    }
}