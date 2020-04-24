package optimalplugin.command.commands.world;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import optimalplugin.OptimalPlugin;
import optimalplugin.utils.MessageUtils;

public class CommandRandomTeleport implements CommandExecutor {

	public CommandRandomTeleport(OptimalPlugin plugin) {
		plugin.getCommand("randomteleport").setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("randomteleport")) {
			if (sender instanceof Player) {
				if (args.length == 0) {
					Player player = (Player) sender;
					Location location = player.getLocation();
					int x = location.getBlockX();
					int z = location.getBlockZ();

					if (player.getWorld() != Bukkit.getWorld("world") && Bukkit.getWorld("world") != null)
						player.teleport(Bukkit.getWorld("world").getSpawnLocation());
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
							"minecraft:spreadplayers " + x + " " + z + " 50 5000 false " + player.getName());
					MessageUtils.configMessage(sender, "CommandRandomTeleport.randomteleport-message",
							" to coordinates <coordinates>&e", "");
				} else
					MessageUtils.argumentError(sender, "/randomteleport");
			} else
				MessageUtils.consoleError();
		}
		return false;
	}
}
