package me.schaubie12.plugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class ClassManager
{
	//holds a players class
	private HashMap<String, Player_Class> classes;
	private Main plugin = new Main();
	private static ClassManager instance = new ClassManager();
	private PlayerClass slave = new PlayerClass(Player_Class.SLAVE);
	private PlayerClass slaveTrader = new PlayerClass(Player_Class.SLAVE_TRADER);
	private PlayerClass slaveMaster = new PlayerClass(Player_Class.SLAVE_MASTER);
	private PlayerClass slaveSupervisor = new PlayerClass(Player_Class.SLAVE_SUPERVISOR);
	private PlayerClass slaveCatcher = new PlayerClass(Player_Class.SLAVE_CATCHER);
	private PlayerClass african = new PlayerClass(Player_Class.AFRICAN);
	public String path = plugin.getDataFolder() + File.separator + "hashmap.bin";
	public File hashmap = new File(path);
	
	public enum Player_Class
	{
		SLAVE,SLAVE_TRADER,SLAVE_MASTER,SLAVE_SUPERVISOR,SLAVE_CATCHER,AFRICAN;
	}
	
	public void loadHashmap()
	{
		try
		{
			if(hashmap.exists())
			{
				classes = load(path);
			}else
			{
				hashmap.createNewFile();
			}
		}catch(Exception e)
		{
			;
		}
	}
	
	public void saveHashmap()
	{
		try
		{
			if(hashmap.exists())
			{
				save(classes, path);
			}else
			{
				hashmap.createNewFile();
				save(classes, path);
			}
		}catch(Exception e)
		{
			;
		}
	}
	
	public static ClassManager getInstance()
	{
		return instance;
	}
	
	public HashMap<String, Player_Class> getPlayerClasses()
	{
		return classes;
	}
	
	public Player_Class getPlayerClass(String name)
	{
		if(!(classes.containsKey(name)))
		{
			return null;
		}
		return classes.get(name);
	}
	
	public PlayerClass getClass(String name)
	{
		if(name.equalsIgnoreCase("slave"))
		{
			return slave;
		}else if(name.equalsIgnoreCase("slave trader"))
		{
			return slaveTrader;
		}else if(name.equalsIgnoreCase("slave master"))
		{
			return slaveMaster;
		}else if(name.equalsIgnoreCase("slave supervisor"))
		{
			return slaveSupervisor;
		}else if(name.equalsIgnoreCase("slave catcher"))
		{
			return slaveCatcher;
		}else if(name.equalsIgnoreCase("african"))
		{
			return african;
		}
		
		return null;
	}
	
	public boolean hasJoinedBefore(String name)
	{
		return classes.containsKey(name);
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Player_Class> load(String path) throws FileNotFoundException, IOException, ClassNotFoundException
	{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
		Object result = ois.readObject();
		ois.close();
		return (HashMap<String, Player_Class>)result;
	}
	
	public void save(HashMap<String, Player_Class> map, String path)
	{
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
			oos.writeObject(map);
			oos.flush();
			oos.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
