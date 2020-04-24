package optimalplugin.listener.global;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import optimalplugin.OptimalPlugin;
import optimalplugin.utils.MessageUtils;

public class ListenerSpawn implements Listener {

    public ListenerSpawn(OptimalPlugin plugin) {
        OptimalPlugin.manager.registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location playerLocation = player.getLocation();
        World playerWorld = playerLocation.getWorld();
        Location spawnLocation = playerWorld.getSpawnLocation();
        GameMode gamemode = player.getGameMode();

        if (playerLocation.distanceSquared(spawnLocation) <= 10000 && gamemode == GameMode.SURVIVAL) {
            if (playerWorld == Bukkit.getWorld("flatroom"))
                player.setGameMode(GameMode.ADVENTURE);
        } else if (playerLocation.distanceSquared(spawnLocation) > 10000 && gamemode == GameMode.ADVENTURE)
            player.setGameMode(GameMode.SURVIVAL);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        World world = location.getWorld();
        Location spawnLocation = world.getSpawnLocation();

        if (world == Bukkit.getWorld("world"))
            if (location.distanceSquared(spawnLocation) <= 2500 && player.getGameMode() == GameMode.SURVIVAL) {
                event.setCancelled(true);
                MessageUtils.configMessage(player, "CommandHub.block-error", null, null);
            }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        World world = location.getWorld();
        Location spawnLocation = world.getSpawnLocation();

        if (world == Bukkit.getWorld("world"))
            if (location.distanceSquared(spawnLocation) <= 2500 && player.getGameMode() == GameMode.SURVIVAL) {
                event.setCancelled(true);
                MessageUtils.configMessage(player, "CommandHub.block-error", null, null);
            }
    }

    @EventHandler
    public void onPlayerBucket(PlayerBucketEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        World world = location.getWorld();
        Location spawnLocation = world.getSpawnLocation();

        if (world == Bukkit.getWorld("world"))
            if (location.distanceSquared(spawnLocation) <= 2500 && player.getGameMode() == GameMode.SURVIVAL) {
                event.setCancelled(true);
                MessageUtils.configMessage(player, "CommandHub.block-error", null, null);
            }
    }
}