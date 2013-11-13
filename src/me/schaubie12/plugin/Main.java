package me.schaubie12.plugin;

import java.util.logging.Logger;
import me.schaubie12.plugin.listener.Listeners;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{	
	private Listeners listeners = new Listeners();
	public static Permission permission = null;
    public static Economy econ = null;

    private boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }
    
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

	
	@Override
	public void onEnable()
	{
		getServer().getPluginManager().addPermission(Permissions.getInstance().getPermission("admin"));
		getCommand("spawn").setExecutor(new PluginCommandExecutor());
		getServer().getPluginManager().registerEvents(listeners, this);
		ClassManager.getInstance().loadHashmap();
		
		if (!setupEconomy() ) 
		{
            Logger.getLogger("Minecraft").severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
		setupPermissions();
	}
	
	@Override
	public void onDisable()
	{
		
	}
	
}
