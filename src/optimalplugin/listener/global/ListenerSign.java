package optimalplugin.listener.global;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import optimalplugin.OptimalPlugin;
import optimalplugin.utils.MessageUtils;

public class ListenerSign implements Listener {

    public ListenerSign(OptimalPlugin plugin) {
        OptimalPlugin.manager.registerEvents(this, plugin);
    }

    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        String[] lines = event.getLines();

        for (int i = 0; i <= 3; i++)
            event.setLine(i, MessageUtils.convertChatColors(lines[i]));
    }
}