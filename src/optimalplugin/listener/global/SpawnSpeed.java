package optimalplugin.listener.global;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import optimalplugin.OptimalPlugin;

public class SpawnSpeed implements Listener {

    public SpawnSpeed(OptimalPlugin plugin) {
        OptimalPlugin.manager.registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location playerLocation = player.getLocation();
        Location spawnLocation = playerLocation.getWorld().getSpawnLocation();
        GameMode gamemode = player.getGameMode();

        if (playerLocation.distanceSquared(spawnLocation) <= 10000 && gamemode == GameMode.ADVENTURE)
            player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(.5);
        else
            player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(.1);
    }
}