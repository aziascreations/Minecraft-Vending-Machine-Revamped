package com.azias.vendingmachine;

import com.azias.vendingmachine.blocks.VendingMachineBlocks;
import com.azias.vendingmachine.gui.GuiHandler;
import com.azias.vendingmachine.items.VendingMachineItems;
import com.azias.vendingmachine.blocks.tileentities.*;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = VendingMachineMod.modID, version = VendingMachineMod.modName, name = VendingMachineMod.version)
public class VendingMachineMod {
    public static final String modID = "aziasvendingmachine";
    public static final String modName = "Vending Machines Revamped";
    public static final String version = "1.1.0";
    
	private static VendingMachineVersion modEvents = new VendingMachineVersion();
    public static CreativeTabs tabVendingMachines = new VendingMachineCreativeTab("VendingMachines");
    
    @Mod.Instance(VendingMachineMod.modID) public static VendingMachineMod instance;
    
    @SidedProxy(serverSide = "com.azias.vendingmachine.CommonProxy", clientSide = "com.azias.vendingmachine.ClientProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void PreLoad(FMLPreInitializationEvent preEvent) {
		FMLCommonHandler.instance().bus().register(modEvents);
		MinecraftForge.EVENT_BUS.register(modEvents);
		VendingMachineConfigs.loadConfigurationFile(preEvent);
		VendingMachineVersion.startVersionCheck();

		GameRegistry.registerTileEntity(TileEntitySodaMachine.class, "tileEntityMachineSoda");
		GameRegistry.registerTileEntity(TileEntityCandyMachine.class, "tileEntityMachineCandy");
		
    	VendingMachineBlocks.registerBlocks(preEvent);
    	VendingMachineItems.registerItems(preEvent);
    	VendingMachineRecipes.registerRecipes();

        proxy.registerRenderers();
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    }
}
