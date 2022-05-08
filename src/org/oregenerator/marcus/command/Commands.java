package org.oregenerator.marcus.command;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.oregenerator.marcus.OreGenerator;
import org.oregenerator.marcus.config.ConfigConfig;
import org.oregenerator.marcus.config.MessageConfig;

import io.org.texttitle.marcus.color.ColorUtils;
import net.md_5.bungee.api.ChatColor;

public class Commands implements CommandExecutor{
	public static OreGenerator plugin;
public Commands(OreGenerator plugin) {
	this.plugin = plugin;
}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This just works in game !");
			return true;
		}else {
			Player p = (Player) sender;
			if(args.length < 1 || args.length == 1 && args[0].equalsIgnoreCase("help")) {
				if(p.isOp() || p.hasPermission("og.help")) {
				p.sendMessage(ChatColor.GREEN + "{-----------------------------------------}");
				p.sendMessage(ChatColor.RED + "{/og help - xem tất cả lệnh");
				p.sendMessage(ChatColor.RED + "{/og addlist {player} - chặn người chơi không được khai thác}");				
				p.sendMessage(ChatColor.RED + "{/og removelist {player} - xóa người chơi khỏi danh sách");
				p.sendMessage(ChatColor.RED + "{/og reload - tải lại plugin}");
				p.sendMessage(ChatColor.GREEN + "{-----------------------------------------}");
				return true;
				}else {
					p.sendMessage(ColorUtils.colorTranslate(MessageConfig.getConfig().getString("message.not-perm")));
					return true;
				}
			}else if(args.length == 2 && args[0].equalsIgnoreCase("addlist")) {
				if(p.isOp() || p.hasPermission("og.addlist")) {
					Player player = Bukkit.getPlayerExact(args[1]);
					if(player.isOnline() || args[1] != null) {
					plugin.list.add(player.getName());
					p.sendMessage(ColorUtils.colorTranslate(MessageConfig.getConfig().getString("prefix") +MessageConfig.getConfig().getString("message.add-list-complete")));
return true;
					}else {
						p.sendMessage(ColorUtils.colorTranslate(MessageConfig.getConfig().getString("prefix") +MessageConfig.getConfig().getString("message.cant-perform")));
                        return true;
					}
				}else {
					p.sendMessage(ColorUtils.colorTranslate(MessageConfig.getConfig().getString("message.not-perm")));
					return true;
				}
			}else if(args.length == 2 && args[0].equalsIgnoreCase("removelist")){
				if(p.isOp() || p.hasPermission("og.removelist")) {
					Player player = Bukkit.getPlayerExact(args[1]);
					if(player.isOnline() || args[1] != null) {
					plugin.list.remove(player.getName());
					p.sendMessage(ColorUtils.colorTranslate(MessageConfig.getConfig().getString("prefix") +MessageConfig.getConfig().getString("message.remove-list-complete")));
return true;
					}else {
						p.sendMessage(ColorUtils.colorTranslate(MessageConfig.getConfig().getString("prefix") +MessageConfig.getConfig().getString("message.cant-perform")));
                        return true;
					}
				}else {
					p.sendMessage(ColorUtils.colorTranslate(MessageConfig.getConfig().getString("message.not-perm")));
					return true;
				}
				}else if(args.length == 1 && args[0].equalsIgnoreCase("reload")) {
					if(p.isOp() || p.hasPermission("og.reload")) {
						try {
							reloadPlugin();
							p.sendMessage(ColorUtils.colorTranslate(MessageConfig.getConfig().getString("prefix") +MessageConfig.getConfig().getString("message.reload")));
return true;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else {
						p.sendMessage(ColorUtils.colorTranslate(MessageConfig.getConfig().getString("message.not-perm")));
						return true;
					}
				}
				
			}
		
		return true ;
	}
	private void reloadPlugin() throws IOException {
		ConfigConfig.saveConfig();
		MessageConfig.saveConfig();
		
		// TODO Auto-generated method stub
		
	}

}
