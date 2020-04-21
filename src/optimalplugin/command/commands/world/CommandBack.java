package optimalplugin.command.commands.world;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import optimalplugin.OptimalPlugin;
import optimalplugin.utils.MessageUtils;
import optimalplugin.utils.FileManagers.BackFileManager;

public class CommandBack implements CommandExecutor, Listener {

    private BackFileManager backFileManager = OptimalPlugin.backFileManager;
    private FileConfiguration backFile = backFileManager.getData();

    public CommandBack(OptimalPlugin plugin) {
        plugin.getCommand("back").setExecutor(this);
        OptimalPlugin.manager.registerEvents(this, plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("back")) {
            if (sender.hasPermission("optimal.back")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    UUID playerId = player.getUniqueId();

                    if (backFile.getString("users." + playerId + ".location") != null) {
                        World world = Bukkit.getWorld(backFile.getString("users." + playerId + ".location.world"));
                        double x = backFile.getDouble("users." + playerId + ".location.x");
                        double y = backFile.getDouble("users." + playerId + ".location.y");
                        double z = backFile.getDouble("users." + playerId + ".location.z");
                        float yaw = (float) backFile.getDouble("users." + playerId + ".location.yaw");
                        float pitch = (float) backFile.getDouble("users." + playerId + ".location.pitch");
                        Location lastLocation = new Location(world, x, y, z);

                        lastLocation.setPitch(pitch);
                        lastLocation.setYaw(yaw);

                        player.teleport(lastLocation);
                        MessageUtils.configMessage(sender, "CommandBack.back-message", null, null);
                    } else
                        MessageUtils.configMessage(sender, "CommandBack.back-error", null, null);
                } else
                    MessageUtils.consoleError();
            } else
                MessageUtils.permissionsError(sender);
        }
        return false;
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        Location fromLocation = event.getFrom();
        Location toLocation = event.getTo();

        if (!(fromLocation.getWorld() == toLocation.getWorld() && fromLocation.distanceSquared(toLocation) <= 4))
            setLastLocation(player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        setLastLocation(player);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        setLastLocation(player);
    }

    private void setLastLocation(Player player) {
        UUID playerId = player.getUniqueId();
        Location location = player.getLocation();
        World world = player.getWorld();
        double locationX = location.getX();
        double locationY = location.getY();
        double locationZ = location.getZ();
        float pitch = location.getPitch();
        float yaw = location.getYaw();
        String locationConfigString = "users." + playerId + ".location.";

        backFile.set(locationConfigString + "world", world.getName());
        backFile.set(locationConfigString + "x", locationX);
        backFile.set(locationConfigString + "y", locationY);
        backFile.set(locationConfigString + "z", locationZ);
        backFile.set(locationConfigString + "pitch", pitch);
        backFile.set(locationConfigString + "yaw", yaw);
        backFileManager.saveData();
    }
}