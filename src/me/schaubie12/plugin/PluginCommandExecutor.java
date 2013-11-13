package me.schaubie12.plugin;

import me.schaubie12.plugin.listener.Listeners;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PluginCommandExecutor implements CommandExecutor
{
	private Listeners listeners = new Listeners();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(cmd.getName().equalsIgnoreCase("spawn"))
		{
			try
			{
				if(args[0].equalsIgnoreCase("slave"))
				{
					listeners.classname = "slave";
				}else if(args[0].equalsIgnoreCase("slave trader"))
				{
					listeners.classname = "slave trader";
				}else if(args[0].equalsIgnoreCase("slave master"))
				{
					listeners.classname = "slave master";
				}else if(args[0].equalsIgnoreCase("slave supervisor"))
				{
					listeners.classname = "slave supervisor";
				}else if(args[0].equalsIgnoreCase("slave catcher"))
				{
					listeners.classname = "slave catcher";
				}else if(args[0].equalsIgnoreCase("african"))
				{
					listeners.classname = "african";
				}
			}catch(Exception e)
			{
				
			}
		}
		
		return false;
	}
	
}
