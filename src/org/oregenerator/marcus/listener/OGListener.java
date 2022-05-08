package org.oregenerator.marcus.listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.oregenerator.marcus.OreGenerator;
import org.oregenerator.marcus.config.ConfigConfig;
import org.oregenerator.marcus.config.MessageConfig;
import org.oregenerator.marcus.ore.OreBuilder;

import io.org.texttitle.marcus.TextTitle;
import io.org.texttitle.marcus.color.ColorUtils;
import io.org.texttitle.marcus.text.ActionBar;

public class OGListener implements Listener{
	
	public static OreGenerator plugin;
	private BlockFace[] faces = {BlockFace.SELF, BlockFace.UP, BlockFace.DOWN, BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST};
	public OGListener(OreGenerator plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	public void onBlockFace(BlockFromToEvent event) {
		Block b = event.getBlock();
		Block to = event.getToBlock();
		if(checkBlock(b,to)){
		   OreBuilder ob = new OreBuilder(event.getToBlock().getLocation());
		   ob.buildOre();
		}
	}
	public boolean checkBlock(Block from,Block to) {
		Material m = from.getType();
		for(BlockFace f : faces ) {
		Material material = to.getRelative(f).getType();
		if(m.equals(Material.WATER) && material.equals(Material.LAVA)) {
			return true;
		}else if(m.equals(Material.LAVA) && material.equals(Material.WATER)) {
			return true;
		}else if(m.equals(Material.WATER) && material.equals(Material.valueOf(ConfigConfig.getConfig().getString("block-second-create")))){
			return true;
		}else if(m.equals(Material.valueOf(ConfigConfig.getConfig().getString("block-second-create"))) && material.equals(Material.WATER)){
			return true;
		}
		}
		return false;
	}
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		int d = MessageConfig.getConfig().getInt("time-disappear") * 20;

		if(e.getBlock().getType().equals(Material.COAL_BLOCK)) {
			if(!plugin.list.contains(p.getName())) {
				e.setCancelled(false);
			if(ConfigConfig.getConfig().getBoolean("sound.enable")) {
				p.playSound(p.getLocation(), Sound.valueOf(ConfigConfig.getConfig().getString("sound.break-coal-block")), 4f, 1f);
				
			}else {
				return;
			}
			if(MessageConfig.getConfig().getBoolean("actionbar.enable")) {
				ActionBar.sendActionBar(p, ColorUtils.colorTranslate(MessageConfig.getConfig().getString("actionbar.break-coal-block")),d);
				
			}else {
				p.sendMessage(ColorUtils.colorTranslate(MessageConfig.getConfig().getString("prefix") + MessageConfig.getConfig().getString("message.break-coal")));
				
			}
			}else {
				e.setCancelled(true);
				p.sendMessage(ColorUtils.colorTranslate(MessageConfig.getConfig().getString("prefix") + MessageConfig.getConfig().getString("message.cant-break")));
			}
		}else if(e.getBlock().getType().equals(Material.IRON_BLOCK)) {
			if(!plugin.list.contains(p.getName())){
				
			if(ConfigConfig.getConfig().getBoolean("sound.enable")) {
				p.playSound(p.getLocation(), Sound.valueOf(ConfigConfig.getConfig().getString("sound.break-iron-block")), 4f, 1f);
				
			}else {
				return;
			}
			if(MessageConfig.getConfig().getBoolean("actionbar.enable")) {
				ActionBar.sendActionBar(p, ColorUtils.colorTranslate(MessageConfig.getConfig().getString("actionbar.break-iron-block")),d);
				
			}else {
				p.sendMessage(ColorUtils.colorTranslate(ColorUtils.colorTranslate(MessageConfig.getConfig().getString("prefix") + MessageConfig.getConfig().getString("message.break-iron"))));
				
								
			}
			
			}else {
				e.setCancelled(true);
				p.sendMessage(ColorUtils.colorTranslate(MessageConfig.getConfig().getString("prefix") + MessageConfig.getConfig().getString("message.cant-break")));

			}
			}else if(e.getBlock().getType().equals(Material.GOLD_BLOCK)) {
				if(!plugin.list.contains(p.getName())) {
				if(ConfigConfig.getConfig().getBoolean("sound.enable")) {
					p.playSound(p.getLocation(), Sound.valueOf(ConfigConfig.getConfig().getString("sound.break-gold-block")), 4f, 1f);
					
				}else {
					return;
				}
				if(MessageConfig.getConfig().getBoolean("actionbar.enable")) {
					ActionBar.sendActionBar(p, ColorUtils.colorTranslate(MessageConfig.getConfig().getString("actionbar.break-gold-block")),d);
					
				}else {
					p.sendMessage(ColorUtils.colorTranslate(ColorUtils.colorTranslate(MessageConfig.getConfig().getString("prefix") + MessageConfig.getConfig().getString("message.break-gold"))));
					
									
				}
				}else {
					e.setCancelled(true);
					p.sendMessage(ColorUtils.colorTranslate(MessageConfig.getConfig().getString("prefix") + MessageConfig.getConfig().getString("message.cant-break")));

				}
			}else if(e.getBlock().getType().equals(Material.QUARTZ_BLOCK)) {
				if(!plugin.list.contains(p.getName())) {
					if(ConfigConfig.getConfig().getBoolean("sound.enable")) {
						p.playSound(p.getLocation(), Sound.valueOf(ConfigConfig.getConfig().getString("sound.break-quartz-block")), 4f, 1f);
						
					}else {
						return;
					}
					if(MessageConfig.getConfig().getBoolean("actionbar.enable")) {
						ActionBar.sendActionBar(p, ColorUtils.colorTranslate(MessageConfig.getConfig().getString("actionbar.break-quartz-block")),d);
						
					}else {
						p.sendMessage(ColorUtils.colorTranslate(ColorUtils.colorTranslate(MessageConfig.getConfig().getString("prefix") + MessageConfig.getConfig().getString("message.break-quartz"))));
						
										
					}
					}else {
						e.setCancelled(true);
						p.sendMessage(ColorUtils.colorTranslate(MessageConfig.getConfig().getString("prefix") + MessageConfig.getConfig().getString("message.cant-break")));

					}
			}else if(e.getBlock().getType().equals(Material.DIAMOND_BLOCK)) {
				if(!plugin.list.contains(p.getName())) {
					if(ConfigConfig.getConfig().getBoolean("sound.enable")) {
						p.playSound(p.getLocation(), Sound.valueOf(ConfigConfig.getConfig().getString("sound.break-diamond-block")), 4f, 1f);
						
					}else {
						return;
					}
					if(MessageConfig.getConfig().getBoolean("actionbar.enable")) {
						ActionBar.sendActionBar(p, ColorUtils.colorTranslate(MessageConfig.getConfig().getString("actionbar.break-diamond-block")),d);
						
					}else {
						p.sendMessage(ColorUtils.colorTranslate(ColorUtils.colorTranslate(MessageConfig.getConfig().getString("prefix") + MessageConfig.getConfig().getString("message.break-diamond"))));
						
										
					}
					}else {
						e.setCancelled(true);
						p.sendMessage(ColorUtils.colorTranslate(MessageConfig.getConfig().getString("prefix") + MessageConfig.getConfig().getString("message.cant-break")));

					}
			}else if(e.getBlock().getType().equals(Material.COBBLESTONE)) {
				if(!plugin.list.contains(p.getName())) {
					if(ConfigConfig.getConfig().getBoolean("sound.enable")) {
						p.playSound(p.getLocation(), Sound.valueOf(ConfigConfig.getConfig().getString("sound.break-cobblestone-block")), 4f, 1f);
						
					}else {
						return;
					}
					if(MessageConfig.getConfig().getBoolean("actionbar.enable")) {
						ActionBar.sendActionBar(p, ColorUtils.colorTranslate(MessageConfig.getConfig().getString("actionbar.break-cobblestone-block")),d);
						
					}else {
						p.sendMessage(ColorUtils.colorTranslate(ColorUtils.colorTranslate(MessageConfig.getConfig().getString("prefix") + MessageConfig.getConfig().getString("message.break-cobblestone"))));
						
										
					}
					}else {
						e.setCancelled(true);
						p.sendMessage(ColorUtils.colorTranslate(MessageConfig.getConfig().getString("prefix") + MessageConfig.getConfig().getString("message.cant-break")));

					}
		}else if(e.getBlock().getType().equals(Material.EMERALD_BLOCK)) {
			if(!plugin.list.contains(p.getName())) {
				if(ConfigConfig.getConfig().getBoolean("sound.enable")) {
					p.playSound(p.getLocation(), Sound.valueOf(ConfigConfig.getConfig().getString("sound.break-emerald-block")), 4f, 1f);
					
				}else {
					return;
				}
				if(MessageConfig.getConfig().getBoolean("actionbar.enable")) {
					ActionBar.sendActionBar(p, ColorUtils.colorTranslate(MessageConfig.getConfig().getString("actionbar.break-emerald-block")),d);
					
				}else {
					p.sendMessage(ColorUtils.colorTranslate(ColorUtils.colorTranslate(MessageConfig.getConfig().getString("prefix") + MessageConfig.getConfig().getString("message.break-emerald"))));
					
									
				}
				}else {
					e.setCancelled(true);
					p.sendMessage(ColorUtils.colorTranslate(MessageConfig.getConfig().getString("prefix") + MessageConfig.getConfig().getString("message.cant-break")));

				}
	}
	}
}
