package com.azias.vendingmachine;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class VendingMachineConfigs {
	public static boolean checkUpdate;
	public static boolean checkBetaUpdate;
	public static boolean soundEnabled;
	public static boolean devMode;
	
	protected static void loadConfigurationFile(FMLPreInitializationEvent preEvent) {
		Configuration config = new Configuration(preEvent.getSuggestedConfigurationFile());
		config.load();
		checkUpdate = config.get(Configuration.CATEGORY_GENERAL, "enableCheckUpdate", true, "Write a message in the chat if an update is available").getBoolean(true);
		checkBetaUpdate = config.get(Configuration.CATEGORY_GENERAL, "enableBetaCheckUpdate", false, "Check for dev updates instead of stable updates").getBoolean(false);
		soundEnabled = config.get(Configuration.CATEGORY_GENERAL, "enableSounds", false, "Unused - Plays a sound when using a vending machine").getBoolean(false);
		devMode = config.get(Configuration.CATEGORY_GENERAL, "enableDevMode", false, "Unused - Used to enable some debug features").getBoolean(false);
		config.save();
	}
}