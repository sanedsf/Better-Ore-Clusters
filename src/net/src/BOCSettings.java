package net.minecraft.src;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BOCSettings
{
//	public static int SOME_INT_VALUE = 1;
//	public static String SOME_STRING_VALUE;
//	public static int[] SOME_INT_ARRAY;
//	public static double SOME_DOUBLE_VALUE;
	
	public static double[] coal_clustersize = {0.2, 1.0};
	public static int[] coal_rarity = {8, 4};
	public static int coal_variation = 2;
	public static int[] coal_biomes = {-1};
	
	public static double[] iron_clustersize = {0.2, 1.0};
	public static int[] iron_rarity = {8, 4};
	public static int iron_variation = 2;
	public static int[] iron_biomes = {-1};
	
	public static double hardcoreradius = 2000;

	public BOCSettings() throws IOException
	{
		String[] parts=null;
		Properties props = new Properties();
		props.load(new FileInputStream(new File("config/example.cfg")));

//		SOME_INT_VALUE = Integer.valueOf(props.getProperty("SOME_INT_VALUE", "1"));
//		SOME_STRING_VALUE = props.getProperty("SOME_STRING_VALUE");
//		SOME_DOUBLE_VALUE = Double.valueOf(props.getProperty("SOME_DOUBLE_VALUE", "1.0"));
		
		//COAL
		parts = props.getProperty("coal_clustersize").split(";");
		for (int i = 0; i < parts.length; ++i)
		{
			coal_clustersize[i] = Double.valueOf(parts[i]);
		}
		
		parts = props.getProperty("coal_rarity").split(";");
		for (int i = 0; i < parts.length; ++i)
		{
			coal_rarity[i] = Integer.valueOf(parts[i]);
		}
		
		coal_variation = Integer.valueOf(props.getProperty("coal_variation", "2"));
		
		parts = props.getProperty("coal_biomes").split(";");
		for (int i = 0; i < parts.length; ++i)
		{
			coal_biomes[i] = Integer.valueOf(parts[i]);
		}
		
		//IRON
		parts = props.getProperty("iron_clustersize").split(";");
		for (int i = 0; i < parts.length; ++i)
		{
			iron_clustersize[i] = Double.valueOf(parts[i]);
		}
		
		parts = props.getProperty("iron_rarity").split(";");
		for (int i = 0; i < parts.length; ++i)
		{
			iron_rarity[i] = Integer.valueOf(parts[i]);
		}
		
		iron_variation = Integer.valueOf(props.getProperty("iron_variation", "2"));
		
		parts = props.getProperty("iron_biomes").split(";");
		for (int i = 0; i < parts.length; ++i)
		{
			iron_biomes[i] = Integer.valueOf(parts[i]);
		}
		
		//Hardcore Spawn Radius
		hardcoreradius = Double.valueOf(props.getProperty("hardcoreradius", "2000"));

	}

	public static void main(String[] args) throws IOException
	{
		new BOCSettings();
	}
}