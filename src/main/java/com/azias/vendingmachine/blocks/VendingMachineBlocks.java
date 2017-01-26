package com.azias.vendingmachine.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class VendingMachineBlocks {
	public static Block sodaVendingMachine, candyVendingMachine;
	public static Block vendingMachineStand;//, vendingMachineFiller;
	
	public static void registerBlocks(FMLPreInitializationEvent preEvent) {
		boolean isClient = preEvent.getSide().isClient();
		
		sodaVendingMachine = new BlockSodaVendingMachine();
		candyVendingMachine = new BlockCandyVendingMachine();
		
		vendingMachineStand = new BlockMachineStand();
		//vendingMachineFiller = new BlockMachineFiller("vendingMachineFiller").setUnlocalizedName("vendingMachineFiller");
	}
}