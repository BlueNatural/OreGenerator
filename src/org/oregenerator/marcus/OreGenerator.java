package org.oregenerator.marcus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.oregenerator.marcus.command.Commands;
import org.oregenerator.marcus.config.ConfigConfig;
import org.oregenerator.marcus.config.MessageConfig;
import org.oregenerator.marcus.listener.OGListener;

import net.md_5.bungee.api.ChatColor;

public class OreGenerator extends JavaPlugin{
public static Plugin plugin;
public static List<String> list = new ArrayList<>();

public void loadPlugin() {
	ConfigConfig.getConfig().options().copyDefaults(true);
	MessageConfig.getConfig().options().copyDefaults(true);
	try {
		ConfigConfig.saveConfig();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {
		MessageConfig.saveConfig();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	getCommand("oregenerator").setExecutor(new Commands(this));
	plugin.getServer().getPluginManager().registerEvents(new OGListener(this), plugin);
	
}
PluginDescriptionFile pdf = plugin.getDescription();
@Override
public void onEnable() {
	if(Bukkit.getBukkitVersion().contains("1.12") && hasTextTitle()) {
		plugin = this;
		Commands.plugin = this;
		OGListener.plugin = this;
		Bukkit.getConsoleSender().sendMessage("[OreGenerator] " + ChatColor.YELLOW + "The plugin will start in a few seconds");
		loadPlugin();
		Bukkit.getConsoleSender().sendMessage("[OreGenerator] " + ChatColor.GREEN + "Complete !");
		Bukkit.getConsoleSender().sendMessage("[OreGenerator] " + ChatColor.WHITE + "---------------------------------------");
		Bukkit.getConsoleSender().sendMessage("[OreGenerator] " + ChatColor.RED + "Name: "+ pdf.getName());
		Bukkit.getConsoleSender().sendMessage("[OreGenerator] " + ChatColor.RED + "Version: "+ pdf.getVersion());
		Bukkit.getConsoleSender().sendMessage("[OreGenerator] " + ChatColor.RED + "Author: BlueNatural");
		Bukkit.getConsoleSender().sendMessage("[OreGenerator] " + ChatColor.RED + "Description: "+ pdf.getDescription());
		Bukkit.getConsoleSender().sendMessage("[OreGenerator] " + ChatColor.RED + "Website: "+ pdf.getWebsite());
		Bukkit.getConsoleSender().sendMessage("[OreGenerator] " + ChatColor.WHITE + "---------------------------------------");


	}
}
private boolean hasTextTitle() {
	if(plugin.getServer().getPluginManager().getPlugin("TextTitle") == null) {
		return false;
	}
	return true;
}
}
