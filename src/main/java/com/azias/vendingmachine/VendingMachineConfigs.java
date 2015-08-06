package com.azias.vendingmachine;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

/**
 * @author Azias
 * @ForgeVersion 1.8 - 11.14.3.1493
 */
public class VendingMachineConfigs {
	public static boolean checkUpdate;
	public static boolean checkBetaUpdate;
	public static boolean soundEnabled;
	public static boolean devMode;
	
	protected static void loadConfigurationFile(FMLPreInitializationEvent preEvent) {
		Configuration config = new Configuration(preEvent.getSuggestedConfigurationFile());
		config.load();
		checkUpdate = config.get(Configuration.CATEGORY_GENERAL, "enableCheckUpdate", true, "Write a message in the chat if an update is available").getBoolean(true);
		checkBetaUpdate = config.get(Configuration.CATEGORY_GENERAL, "enableBetaCheckUpdate", false, "Unused at the moment").getBoolean(false);
		soundEnabled = config.get(Configuration.CATEGORY_GENERAL, "enableSounds", true, "Plays a sound when using a vending machine").getBoolean(true);
		devMode = config.get(Configuration.CATEGORY_GENERAL, "enableDevMode", false, "Currently only affect the version checking").getBoolean(false);
		config.save();
	}
}