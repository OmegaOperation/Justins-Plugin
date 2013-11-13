package me.schaubie12.plugin.listener;

import me.schaubie12.plugin.ClassManager;
import me.schaubie12.plugin.Permissions;
import me.schaubie12.plugin.ClassManager.Player_Class;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Listeners implements Listener
{
	public String classname;
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event)
	{
		Player p = event.getPlayer();
		
		if(!(p.hasPermission(Permissions.getInstance().getPermission("admin"))) || event.getAction() != Action.LEFT_CLICK_BLOCK || p.getItemInHand().getType() != Material.BLAZE_ROD)
		{
			return;
		}
		
		Location l = event.getClickedBlock().getLocation();
		p.sendMessage(ChatColor.DARK_AQUA.toString() + "Do /spawn classname");
		switch(classname)
		{
			case "slave" : ClassManager.getInstance().getClass("slave").initializeSpawn(l);
			case "slave trader" : ClassManager.getInstance().getClass("slave trader").initializeSpawn(l);
			case "slave master" : ClassManager.getInstance().getClass("slave master").initializeSpawn(l);
			case "slave supervisor" : ClassManager.getInstance().getClass("slave supervisor").initializeSpawn(l);
			case "african" : ClassManager.getInstance().getClass("african").initializeSpawn(l);
			case "slave catcher" : ClassManager.getInstance().getClass("slave catcher").initializeSpawn(l);
		}
		
		
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event)
	{
		Player damaged = (Player) event.getEntity();
		Player damager = (Player) event.getDamager();
		if(ClassManager.getInstance().getPlayerClass(damager.getName()) != Player_Class.SLAVE_CATCHER)
		{
			return;
		}
		
		if(ClassManager.getInstance().getPlayerClass(damaged.getName()) != Player_Class.AFRICAN || ClassManager.getInstance().getPlayerClass(damaged.getName()) != Player_Class.SLAVE)
		{
			return;
		}
		
		damaged.damage(2, damager);
		damaged.sendMessage(ChatColor.DARK_AQUA.toString() + "You have been whipped by: " + damager.getName());
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player p = event.getPlayer();
		
		if(!(ClassManager.getInstance().getPlayerClasses().containsKey(p.getName())))
		{
				ClassManager.getInstance().getPlayerClasses().put(p.getName(), Player_Class.AFRICAN);
				p.teleport(ClassManager.getInstance().getClass("african").getSpawn());
				p.getInventory().clear();
				p.getInventory().addItem(new ItemStack(Material.APPLE, 16));
		}	
	}
	
	
	@EventHandler
	public void onItemPickup(PlayerPickupItemEvent event)
	{
		ItemStack item = event.getItem().getItemStack();
		ItemMeta meta = item.getItemMeta();
		if(item.getType() == Material.WHEAT)
		{
			meta.setDisplayName("Tobacco");
			item.setItemMeta(meta);
			return;
		}else if(item.getType() == Material.SUGAR)
		{
			meta.setDisplayName("Cotton");
			item.setItemMeta(meta);
			return;
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)
	{
		if(event.getBlock().getType() == Material.SUGAR_CANE_BLOCK)
		{
			Block block = event.getBlock();
			block.setType(Material.AIR);
			ItemStack item = new ItemStack(Material.SUGAR, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("Cotton");
			item.setItemMeta(meta);
			block.getWorld().dropItemNaturally(block.getLocation(), item);
			event.setCancelled(true);
		}
	}
}
