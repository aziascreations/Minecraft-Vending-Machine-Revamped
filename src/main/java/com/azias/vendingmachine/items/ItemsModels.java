package com.azias.vendingmachine.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
//import net.minecraft.client.renderer.entity.RenderItem;
//import net.minecraft.client.resources.model.ModelBakery;
//import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.azias.vendingmachine.VendingMachineMod;
import com.azias.vendingmachine.items.*;

@Deprecated
public class ItemsModels {
	private static String[] sodaBottleNames = new String[]{"_water","_cola","_dietCola","_cafeFreeCola","_zeroCola","_vanillaCola","_cherryCola","_fanta","_dew","_sprite","_spriteZero","_powerade","_fruitJuice"};
	private static String[] coffeeNames = new String[]{"_0","_1","_2","_3","_4","_5","_6","_7","_8","_9","_10","_11"};
	private static String[] candyNames = new String[]{"_gumPurple","_gumBlue","_gumLime","_gumOrange","_gumPink","_gumYellow","_gumRed","_gingerbreadMan","_chocolateBar"};
	private static String[] vendingMachinesNames = new String[]{"_0","_1","_2"};
	private static String[] coinNames = new String[]{"_clay","_iron","_gold"};
	
	public static void preRegisterModels(FMLPreInitializationEvent preEvent) {
		String sodaBottle = VendingMachineMod.modID+":"+((ItemSodaBottle)VendingMachineItems.sodaBottle).getName();
		String candy = VendingMachineMod.modID+":"+((ItemCandy)VendingMachineItems.candy).getName();
		String coffeeCup = VendingMachineMod.modID+":"+((ItemCoffeeCup)VendingMachineItems.coffeeCup).getName();
		String vendingMachine = VendingMachineMod.modID+":"+((ItemVendingMachine)VendingMachineItems.vendingMachine).getName();
		String coin = VendingMachineMod.modID+":"+((ItemCoin)VendingMachineItems.coin).getName();
		
		//ModelBakery.addVariantName(VendingMachineItems.sodaBottle, sodaBottle+sodaBottleNames[0], sodaBottle+sodaBottleNames[1], sodaBottle+sodaBottleNames[2], sodaBottle+sodaBottleNames[3], sodaBottle+sodaBottleNames[4], sodaBottle+sodaBottleNames[5], sodaBottle+sodaBottleNames[6], sodaBottle+sodaBottleNames[7], sodaBottle+sodaBottleNames[8], sodaBottle+sodaBottleNames[9], sodaBottle+sodaBottleNames[10], sodaBottle+sodaBottleNames[11], sodaBottle+sodaBottleNames[12]);
		//ModelBakery.addVariantName(VendingMachineItems.candy, candy+candyNames[0], candy+candyNames[1], candy+candyNames[2], candy+candyNames[3], candy+candyNames[4], candy+candyNames[5], candy+candyNames[6], candy+candyNames[7], candy+candyNames[8]);
		//ModelBakery.addVariantName(VendingMachineItems.coffeeCup, coffeeCup+coffeeNames[0], coffeeCup+coffeeNames[1], coffeeCup+coffeeNames[2], coffeeCup+coffeeNames[3], coffeeCup+coffeeNames[4], coffeeCup+coffeeNames[5], coffeeCup+coffeeNames[6], coffeeCup+coffeeNames[7], coffeeCup+coffeeNames[8], coffeeCup+coffeeNames[9], coffeeCup+coffeeNames[10], coffeeCup+coffeeNames[11]);
		//ModelBakery.addVariantName(VendingMachineItems.vendingMachine, vendingMachine+vendingMachinesNames[0], vendingMachine+vendingMachinesNames[1], vendingMachine+vendingMachinesNames[2]);
		//ModelBakery.addVariantName(VendingMachineItems.coin, coin+coinNames[0], coin+coinNames[1], coin+coinNames[2]);
	}

	public static void registerModels(FMLInitializationEvent initEvent) {
		String sodaBottle = ((ItemSodaBottle)VendingMachineItems.sodaBottle).getName();
		String candy = ((ItemCandy)VendingMachineItems.candy).getName();
		String coffeeCup = ((ItemCoffeeCup)VendingMachineItems.coffeeCup).getName();
		String vendingMachine = ((ItemVendingMachine)VendingMachineItems.vendingMachine).getName();
		String coin = ((ItemCoin)VendingMachineItems.coin).getName();

		for(int i=0; i<sodaBottleNames.length; i++) {
			register(VendingMachineItems.sodaBottle,i,sodaBottle+sodaBottleNames[i]);
		}
		for(int i=0; i<coffeeNames.length; i++) {
			register(VendingMachineItems.coffeeCup,i,coffeeCup+coffeeNames[i]);
		}
		for(int i=0; i<candyNames.length; i++) {
			register(VendingMachineItems.candy,i,candy+candyNames[i]);
		}
		for(int i=0; i<vendingMachinesNames.length; i++) {
			register(VendingMachineItems.vendingMachine,i,vendingMachine+vendingMachinesNames[i]);
		}
		for(int i=0; i<coinNames.length; i++) {
			register(VendingMachineItems.coin,i,coin+coinNames[i]);
		}
	}
	
	public static void register(Item item, int meta, String file) {
	    Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(VendingMachineMod.modID + ":" + file, "inventory"));
	}
}
