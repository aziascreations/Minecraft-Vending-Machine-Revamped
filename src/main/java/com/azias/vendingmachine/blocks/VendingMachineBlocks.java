package com.azias.vendingmachine.blocks;

import com.azias.vendingmachine.VendingMachineMod;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class VendingMachineBlocks {
	public static Block sodaVendingMachine, candyVendingMachine;
	public static Block vendingMachineStand;//, vendingMachineFiller;
	
	public static void registerBlocks(FMLPreInitializationEvent preEvent) {
		//sodaVendingMachine = new BlockSodaVendingMachine();
		//candyVendingMachine = new BlockCandyVendingMachine();
		
		vendingMachineStand = new BlockMachineStand();
		////vendingMachineFiller = new BlockMachineFiller("vendingMachineFiller").setUnlocalizedName("vendingMachineFiller");
	}

	@Deprecated
	private static void registerModels() {
		register(vendingMachineStand, 0);
	}
	
	@Deprecated
	public static void register(Block block, int meta) {
		//Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(VendingMachineMod.modID + ":" + file, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(
				Item.getItemFromBlock(block),
				meta,
				new ModelResourceLocation(new ResourceLocation(VendingMachineMod.modID, block.getUnlocalizedName().substring(5 + VendingMachineMod.modID.length() + 1)+"_"+meta), "inventory")
		);
	}
}