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
        Entity damager = event.getDamager();
        Location entityLocation = entity.getLocation();
        Location damagerLocation = damager.getLocation();
        World world = entityLocation.getWorld();

        if (world.equals(Bukkit.getWorld("pvp")))
            if (entityLocation.distanceSquared(world.getSpawnLocation()) <= 100
                    || damagerLocation.distanceSquared(world.getSpawnLocation()) <= 100)
                event.setCancelled(true);
    }
}