package optimalplugin.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import optimalplugin.OptimalPlugin;

public class MessageUtils {

	private static FileConfiguration config = OptimalPlugin.getInstance().getConfig();

	public static String convertChatColors(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	public static void message(CommandSender sender, String message) {
		sender.sendMessage(convertChatColors(message));
	}

	public static void configMessage(CommandSender sender, String configString, String regex, String replacement) {
		if (regex == null || replacement == null)
			message(sender, config.getString(configString));
		else
			message(sender, config.getString(configString).replaceAll(regex, replacement));
	}

	public static void argumentError(CommandSender sender, String usage) {
		configMessage(sender, "argument-error", "<usage>", usage);
	}

	public static void permissionsError(CommandSender sender) {
		configMessage(sender, "no-permissions-error", null, null);
	}

	public static void invalidPlayerError(CommandSender sender) {
		configMessage(sender, "invalid-player-error", null, null);
	}

	public static void consoleError() {
		configMessage(Bukkit.getConsoleSender(), "console-error", null, null);
	}
}