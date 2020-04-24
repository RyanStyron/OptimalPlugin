package optimalplugin.command.commands.general;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import optimalplugin.OptimalPlugin;
import optimalplugin.utils.MessageUtils;

public class CommandRename implements CommandExecutor {

    public CommandRename(OptimalPlugin plugin) {
        plugin.getCommand("rename").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("rename")) {
            if (sender.hasPermission("optimal.rename")) {
                if (sender instanceof Player) {
                    if (args.length > 0) {
                        Player player = (Player) sender;
                        String name = "";

                        for (int i = 0; i < args.length; i++)
                            name += args[i] + " ";

                        PlayerInventory inventory = player.getInventory();
                        ItemStack item = inventory.getItemInMainHand();

                        if (item != new ItemStack(Material.AIR) || item != null) {
                            item.getItemMeta().setDisplayName(name);
                            player.updateInventory();
                            MessageUtils.configMessage(sender, "CommandRename.rename-message", null, null);
                        } else
                            MessageUtils.configMessage(sender, "CommandRename.rename-error", null, null);
                    } else
                        MessageUtils.argumentError(sender, "/rename <name>");
                } else
                    MessageUtils.consoleError();
            } else
                MessageUtils.permissionsError(sender);
        }
        return false;
    }
}