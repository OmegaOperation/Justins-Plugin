package me.schaubie12.plugin;

import org.bukkit.permissions.Permission;

public class Permissions
{
	private Permission admin = new Permission("justin.admin");
	private static Permissions instance = new Permissions();
	
	public static Permissions getInstance()
	{
		return instance;
	}
	
	public Permission getPermission(String name)
	{
		switch(name)
		{
			case "admin" : return admin;
		}
		
		return null;
	}
}
