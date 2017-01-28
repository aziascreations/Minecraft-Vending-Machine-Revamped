package com.azias.vendingmachine.blocks;

import com.azias.vendingmachine.VendingMachineMod;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class BlocksModels {
	
	public static void preRegisterModels(FMLPreInitializationEvent preEvent) {
		
	}
	
	public static void registerModels(FMLInitializationEvent initEvent) {
		register(VendingMachineBlocks.candyVendingMachine, 0, ((BlockCandyVendingMachine)VendingMachineBlocks.candyVendingMachine).getName());
		register(VendingMachineBlocks.vendingMachineStand, 0, ((BlockMachineStand)VendingMachineBlocks.vendingMachineStand).getName());
		
	}
	
	public static void register(Block block, int meta, String file) {
		//Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(VendingMachineMod.modID + ":" + file, "inventory"));
	}
}
