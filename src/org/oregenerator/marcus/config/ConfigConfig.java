package org.oregenerator.marcus.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.oregenerator.marcus.OreGenerator;

import net.md_5.bungee.api.ChatColor;

public class ConfigConfig {
	private static FileConfiguration fc = null;
	private static File customCC = null;
	
	public static void reloadConfig() {
		if(customCC == null) {
		customCC = new File(OreGenerator.plugin.getDataFolder(),"config.yml");
		
		
		}
		fc = YamlConfiguration.loadConfiguration(customCC);
		try {
			Reader defaultFile = new InputStreamReader(OreGenerator.plugin.getResource("config.yml"),"UTF8");
			if(defaultFile != null) {
				YamlConfiguration defFile = YamlConfiguration.loadConfiguration(defaultFile);
				fc.setDefaults(defFile);
			}
			
			
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
public static FileConfiguration getConfig() {
	if(fc== null) {
		reloadConfig();
	}
	return fc;
}
public static void saveConfig() throws IOException {
	if(fc == null && customCC == null) {
		return;
	}
	try {
	getConfig().save(customCC);
	}finally {
		System.out.println(ChatColor.GREEN + "Complete !");
		
	}
}
}
