package optimalplugin.listener.global;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import optimalplugin.OptimalPlugin;

public class ListenerJoin implements Listener {

    public ListenerJoin(OptimalPlugin plugin) {
        OptimalPlugin.manager.registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (Bukkit.getWorld("flatroom") != null)
            player.teleport(Bukkit.getWorld("flatroom").getSpawnLocation());
    }
}