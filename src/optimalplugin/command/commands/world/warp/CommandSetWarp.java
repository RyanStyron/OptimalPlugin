package optimalplugin.command.commands.world.warp;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import optimalplugin.OptimalPlugin;
import optimalplugin.utils.MessageUtils;
import optimalplugin.utils.FileManagers.WarpFileManager;

public class CommandSetWarp implements CommandExecutor, TabCompleter {

    private WarpFileManager warpFileManager = OptimalPlugin.warpFileManager;
    private FileConfiguration warpFile = warpFileManager.getData();

    public CommandSetWarp(OptimalPlugin plugin) {
        plugin.getCommand("setwarp").setExecutor(this);
        plugin.getCommand("setwarp").setTabCompleter(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("setwarp")) {
            if (sender.hasPermission("optimal.setwarp")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    String warpName;
                    Location location = player.getLocation();
                    String worldName = location.getWorld().getName();
                    double x = location.getX();
                    double y = location.getY();
                    double z = location.getZ();
                    float pitch = location.getPitch();
                    float yaw = location.getYaw();

                    if (args.length == 1 || args.length == 2) {
                        warpName = args[0].toLowerCase();

                        if (warpFile.getString("warps." + warpName) == null) {
                            String warpFileString = "warps." + warpName;
                            String warpFileLocationString = warpFileString + ".location.";

                            warpFile.set(warpFileLocationString + "world", worldName);
                            warpFile.set(warpFileLocationString + "x", x);
                            warpFile.set(warpFileLocationString + "y", y);
                            warpFile.set(warpFileLocationString + "z", z);
                            warpFile.set(warpFileLocationString + "pitch", pitch);
                            warpFile.set(warpFileLocationString + "yaw", yaw);
                            if (args.length == 2)
                                warpFile.set(warpFileString + ".permission", args[1].toLowerCase());
                            warpFileManager.saveData();

                            MessageUtils.configMessage(sender, "CommandWarp.warp-set-message", null, null);
                        } else
                            MessageUtils.configMessage(sender, "CommandWarp.warp-exists-error", null, null);
                    } else
                        MessageUtils.argumentError(sender, "/setwarp <name> [permission]");
                } else
                    MessageUtils.consoleError();
            } else
                MessageUtils.permissionsError(sender);
        }
        return false;
    }
    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            List<String> warps = new ArrayList<>();

            if (warpFile.getConfigurationSection("warps") != null) {
                for (String key : warpFile.getConfigurationSection("warps").getKeys(false))
                    warps.add(key);
                return warps;
            }
        }
        return null;
    }
}