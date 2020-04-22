package optimalplugin.command.commands.world.warp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import optimalplugin.OptimalPlugin;
import optimalplugin.utils.MessageUtils;
import optimalplugin.utils.FileManagers.WarpFileManager;

public class CommandDeleteWarp implements CommandExecutor {

    private WarpFileManager warpFileManager = OptimalPlugin.warpFileManager;
    private FileConfiguration warpFile = warpFileManager.getData();

    public CommandDeleteWarp(OptimalPlugin plugin) {
        plugin.getCommand("deletewarp").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("deletewarp")) {
            if (sender.hasPermission("optimal.deletewarp")) {
                if (args.length == 1) {
                    String warp = args[0].toLowerCase();

                    if (warpFile.getString("warps." + warp) != null) {
                        warpFile.set("warps." + warp, null);
                        warpFileManager.saveData();

                        MessageUtils.configMessage(sender, "CommandWarp.warp-deleted-message", "<warp>", warp);
                    } else
                        MessageUtils.configMessage(sender, "CommandWarp.warp-null-error", null, null);
                } else
                    MessageUtils.argumentError(sender, "/deletewarp <warp>");
            } else
                MessageUtils.permissionsError(sender);
        }
        return false;
    }
}