package org.oregenerator.marcus.ore;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.oregenerator.marcus.config.ConfigConfig;

import io.netty.util.internal.ThreadLocalRandom;

public class OreBuilder {
	public Location loc;
	public OreBuilder(Location loc) {
		this.loc = loc;
	}
	public void setBlock(Material m) {
		Block b = loc.getBlock();
		b.setType(m);
	}
   public void buildOre() {
	   List<String> banworlds = ConfigConfig.getConfig().getStringList("ban-worlds");
	   if(banworlds.contains(loc.getWorld().getName())) {
		   setBlock(Material.COBBLESTONE);
	   }
	   double chancecobblestone = ConfigConfig.getConfig().getDouble("chance-cobblestone");
	   double chanceiron = ConfigConfig.getConfig().getDouble("chance-iron");
	   double chancegold = ConfigConfig.getConfig().getDouble("chance-gold");
	   double chancequartz = ConfigConfig.getConfig().getDouble("chance-quartz");
	   double chancediamond = ConfigConfig.getConfig().getDouble("chance-diamond");
	   double chanceemerald = ConfigConfig.getConfig().getDouble("chance-emerald");
	   ThreadLocalRandom tlr = ThreadLocalRandom.current();
	   double chancerandom = tlr.nextDouble(getTotalChance());
	   if(chancerandom > 0 && chancerandom <= chanceemerald) {
		   setBlock(Material.EMERALD_BLOCK);
	   }else if(chancerandom > chanceemerald && chancerandom <= chancediamond){
		   setBlock(Material.DIAMOND_BLOCK);
	   }else if(chancerandom > chancediamond && chancerandom <= chancequartz) {
		   setBlock(Material.QUARTZ_BLOCK);
		   
	   }else if(chancerandom > chancequartz && chancerandom <= chancegold) {
		   setBlock(Material.GOLD_BLOCK);
	   }else if(chancerandom > chancegold && chancerandom <= chanceiron) {
		   setBlock(Material.IRON_BLOCK);
	   }else if(chancerandom > chanceiron && chancerandom <= chancecobblestone) {
		   setBlock(Material.COBBLESTONE);
	   }
   }
private double getTotalChance() {
	double d = 0;
	if(ConfigConfig.getConfig().getString("total-chance-type").equalsIgnoreCase("ONE_HUNDRED_PERCENT")) {
		d= 100.0;
		return 100.0;
	}else if(ConfigConfig.getConfig().getString("total-chance-type").equalsIgnoreCase("EDIT")) {
		d = ConfigConfig.getConfig().getDouble("total-chance");
	return ConfigConfig.getConfig().getDouble("total-chance");
	}
	return d;
}

}
