package optimalplugin.command.commands.world;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import optimalplugin.OptimalPlugin;
import optimalplugin.utils.MessageUtils;

public class CommandHub implements CommandExecutor {

    public CommandHub(OptimalPlugin plugin) {
        plugin.getCommand("hub").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("hub")) {
            if (sender instanceof Player) {
                if (args.length == 0) {
                    Player player = (Player) sender;

                    if (Bukkit.getWorld("flatroom") != null)
                        player.teleport(Bukkit.getWorld("flatroom").getSpawnLocation());
                    else
                        player.teleport(player.getWorld().getSpawnLocation());
                    MessageUtils.configMessage(sender, "CommandHub.hub-message", null, null);
                } else
                    MessageUtils.argumentError(sender, "/hub");
            } else
                MessageUtils.consoleError();
        }
        return false;
    }
}