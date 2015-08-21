package com.azias.vendingmachine;

import java.util.Arrays;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

import com.azias.vendingmachine.blocks.VendingMachineBlocks;
import com.azias.vendingmachine.items.VendingMachineItems;

@Mod(modid = VendingMachineMod.modID, version = VendingMachineMod.modName, name = VendingMachineMod.version)
public class VendingMachineMod {
    public static final String modID = "aziasvendingmachine";
    public static final String modName = "Vending Machines Revamped";
    public static final String version = "1.0.2";
    
	private static VendingMachineVersion modEvents = new VendingMachineVersion();
    public static CreativeTabs tabVendingMachines = new VendingMachineCreativeTab("VendingMachines");
    
    @EventHandler
    public static void PreLoad(FMLPreInitializationEvent preEvent) {
		FMLCommonHandler.instance().bus().register(modEvents);
		MinecraftForge.EVENT_BUS.register(modEvents);
		VendingMachineConfigs.loadConfigurationFile(preEvent);
		VendingMachineVersion.startVersionCheck();
		
    	VendingMachineBlocks.registerBlocks(preEvent);
    	VendingMachineItems.registerItems(preEvent);
    	VendingMachineRecipes.registerRecipes();
    }
}
