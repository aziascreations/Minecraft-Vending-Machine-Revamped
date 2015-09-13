package com.azias.vendingmachine.blocks;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;

public class VendingMachineBlocks {
	public static Block sodaVendingMachine, candyVendingMachine;
	public static Block vendingMachineStand, vendingMachineFiller;
	
	public static void registerBlocks(FMLPreInitializationEvent preEvent) {
		boolean isClient = preEvent.getSide().isClient();
		
		sodaVendingMachine = new BlockSodaVendingMachine("sodaVendingMachine").setBlockName("sodaVendingMachine");
		candyVendingMachine = new BlockCandyVendingMachine("candyVendingMachine").setBlockName("candyVendingMachine");
		
		vendingMachineStand = new BlockMachineStand("vendingMachineStand").setBlockName("vendingMachineStand");
		vendingMachineFiller = new BlockMachineFiller("vendingMachineFiller").setBlockName("vendingMachineFiller");
	}
}