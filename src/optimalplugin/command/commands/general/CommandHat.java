package optimalplugin.command.commands.general;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import optimalplugin.OptimalPlugin;
import optimalplugin.utils.MessageUtils;

public class CommandHat implements CommandExecutor {

    public CommandHat(OptimalPlugin plugin) {
        plugin.getCommand("hat").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("hat")) {
            if (sender.hasPermission("optimal.hat")) {
                Player target = null;

                if (args.length <= 1) {
                    if (args.length > 0) {
                        if (sender.hasPermission("optimal.hat.other"))
                            target = Bukkit.getPlayer(args[0]);
                        else
                            MessageUtils.argumentError(sender, "/hat");
                    } else if (sender instanceof Player)
                        target = (Player) sender;

                    if (target == null)
                        MessageUtils.invalidPlayerError(sender);
                    else {
                        PlayerInventory inventory = target.getInventory();
                        ItemStack mainHandItem = inventory.getItemInMainHand();

                        if (target.getInventory().getItemInMainHand() != new ItemStack(Material.AIR)) {
                            inventory.removeItem(mainHandItem);
                            inventory.setHelmet(mainHandItem);
                            target.updateInventory();
                            if (target != sender)
                                MessageUtils.configMessage(sender, "CommandHat.hat-sender-message", "<player>",
                                        target.getName());
                            MessageUtils.configMessage(target, "CommandHat.hat-message", null, null);
                        } else
                            MessageUtils.configMessage(sender, "CommandHat.hat-error", null, null);
                    }
                } else
                    MessageUtils.argumentError(sender, "/hat [player]");
            } else
                MessageUtils.permissionsError(sender);
        }
        return false;
    }
}