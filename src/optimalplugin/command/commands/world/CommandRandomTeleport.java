package optimalplugin.command.commands.world;

import java.util.Random;

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
					Random random = new Random(65);
					int x = 0;
					int y = 25;
					int z = 0;

					while (x > 5000 || x < 150)
						x = random.nextInt((Math.abs(location.getBlockX()) + 12) * 10);
					while (z > 5000 || z < 150)
						z = random.nextInt((Math.abs(location.getBlockZ()) + 12) * 10);
					Location randomLocation = new Location(player.getWorld(), x, y, z);

					player.teleport(randomLocation);
					player.teleport(player.getWorld().getHighestBlockAt(location).getLocation().add(0, 1, 0));
					MessageUtils.configMessage(sender, "CommandRandomTeleport.randomteleport-message", "<coordinates>",
							x + ", " + location.getBlockY() + ", " + z);
				} else
					MessageUtils.argumentError(sender, "/randomteleport");
			} else
				MessageUtils.consoleError();
		}
		return false;
	}
}
