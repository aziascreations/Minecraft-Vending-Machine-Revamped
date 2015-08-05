package com.azias.vendingmachine.blocks;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;

public class VendingMachineBlocks {
	public static Block vendingMachineLarge,vendingMachineSmall;
	public static Block vendingMachineStand;
	
	public static void registerBlocks(FMLPreInitializationEvent preEvent) {
		boolean isClient = preEvent.getSide().isClient();
		
		vendingMachineLarge = new BlockVendingMachine("vendingMachine", isClient).setBlockName("vendingMachine");
		vendingMachineSmall = new BlockVendingMachineSmall("vendingMachineSmall", isClient).setBlockName("vendingMachineSmall");
		
		vendingMachineStand = new BlockMachineStand("vendingMachineStand").setBlockName("vendingMachineStand");
		
	}
}