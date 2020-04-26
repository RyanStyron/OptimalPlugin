package optimalplugin.listener.global;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import optimalplugin.OptimalPlugin;

public class ListenerPvP implements Listener {

    public ListenerPvP(OptimalPlugin plugin) {
        OptimalPlugin.manager.registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        Location location = entity.getLocation();
        World world = location.getWorld();

        if (world.equals(Bukkit.getWorld("pvp")))
            if (location.distanceSquared(world.getSpawnLocation()) <= 100)
                event.setCancelled(true);
    }
}