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
                    Random random = new Random(65);
                    int x;
                    int y;
                    int z;

                    x = random.nextInt(5000);
                    y = random.nextInt(5000);
                    z = random.nextInt(5000);
                    Location randomLocation = new Location(player.getWorld(), x, y, z);

                    player.teleport(randomLocation);
                    MessageUtils.configMessage(sender, "CommandRandomTeleport.randomteleport-message", "<coordinates>",
                            x + ", " + y + ", " + z);
                } else
                    MessageUtils.argumentError(sender, "/randomteleport");
            } else
                MessageUtils.consoleError();
        }
        return false;
    }
}
