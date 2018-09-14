package de.funky33.serverstats;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin
{
	public void onEnable()
	{
		loadConfig();
		
		getLogger().info("Plugin by funky33");
		getLogger().info("Plugin Enabled");
	}
	
	public void loadConfig()
	{
		FileConfiguration cfg = getConfig();
		cfg.options().copyDefaults(true);
		saveConfig();
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		Player player = (Player)sender;
		Server server = getServer();
		
		if (cmd.getName().equalsIgnoreCase("serverstats"))
		{
			if (player.hasPermission("serverstats.stats"))
			{
				if (args.length == 0)
				{
					String whitelist = getConfig().getString("Message.whitelist");
					String whitelisted = getConfig().getString("Message.whitelisted");
					String onlineplayers = getConfig().getString("Message.onlineplayers");
					String serverversion = getConfig().getString("Message.serverversion");
					String bannedplayers = getConfig().getString("Message.bannedplayers");
					String onlinemode = getConfig().getString("Message.onlinemode");
					
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Server Status Monitor:"));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', serverversion + " " + server.getVersion()));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aServer IP: " + server.getIp()));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', onlinemode + " " + server.getOnlineMode()));
					sender.sendMessage("Motd: " + server.getMotd());
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', bannedplayers + " " + server.getBannedPlayers().size()));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', whitelist + " " + server.hasWhitelist()));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', whitelisted + " " + server.getWhitelistedPlayers().size()));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', onlineplayers + " " + server.getOnlinePlayers().size() + "/" + server.getMaxPlayers()));
				}
				if (args.length == 1)
				{
					if (args[0].equalsIgnoreCase("reload"))
					{
						reloadConfig();
						sender.sendMessage(ChatColor.GREEN + "Config reloaded!");
					}
				}
				else if (args.length > 1)
				{
					sender.sendMessage(ChatColor.RED + "Too many arguments Usage: /serverstats");
				}
			}
		}
		
		return false;
	}
}
