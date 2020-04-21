package optimalplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import optimalplugin.command.commands.general.CommandHead;
import optimalplugin.command.commands.world.CommandBack;
import optimalplugin.command.commands.world.CommandHub;
import optimalplugin.command.commands.world.CommandSpawn;
import optimalplugin.utils.MessageUtils;
import optimalplugin.utils.FileManagers.BackFileManager;
import optimalplugin.utils.FileManagers.HomeFileManager;
import optimalplugin.utils.FileManagers.WarpFileManager;

public class OptimalPlugin extends JavaPlugin {

	private static OptimalPlugin plugin;

	public static OptimalPlugin getInstance() {
		return plugin;
	}

	public static PluginManager manager = Bukkit.getPluginManager();
	public static BackFileManager backFileManager = BackFileManager.getInstance();
	public static HomeFileManager homeFileManager = HomeFileManager.getInstance();
	public static WarpFileManager warpFileManager = WarpFileManager.getInstance();

	@Override
	public void onEnable() {
		plugin = this;
		plugin.saveDefaultConfig();
		backFileManager.setup(this);
		homeFileManager.setup(this);
		warpFileManager.setup(this);

		// General commands.
		new CommandHead(this);

		// World commands.
		new CommandHub(this);
		new CommandSpawn(this);
		new CommandBack(this);

		System.out.println(MessageUtils.convertChatColors("&6&l(!)&e OptimalPlugin enabled!"));
	}

	@Override
	public void onDisable() {
		System.out.println(MessageUtils.convertChatColors("&4&l(!)&c OptimalPlugin disabled!"));
	}
}
