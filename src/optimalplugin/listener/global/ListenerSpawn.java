package optimalplugin.listener.global;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import optimalplugin.OptimalPlugin;

public class ListenerSpawn implements Listener {

    public ListenerSpawn(OptimalPlugin plugin) {
        OptimalPlugin.manager.registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location playerLocation = player.getLocation();
        Location spawnLocation = playerLocation.getWorld().getSpawnLocation();
        GameMode gamemode = player.getGameMode();

        if (playerLocation.distanceSquared(spawnLocation) <= 10000 && gamemode == GameMode.SURVIVAL)
            player.setGameMode(GameMode.ADVENTURE);
        else if (playerLocation.distanceSquared(spawnLocation) > 10000 && gamemode == GameMode.ADVENTURE)
            player.setGameMode(GameMode.SURVIVAL);
    }
}