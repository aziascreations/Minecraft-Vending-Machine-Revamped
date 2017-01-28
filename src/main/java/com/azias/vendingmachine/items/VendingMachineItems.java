package com.azias.vendingmachine.items;

import com.azias.vendingmachine.VendingMachineMod;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class VendingMachineItems {
	public static Item sodaBottle, coffeeCup, candy;
	public static Item vendingMachine;
	public static Item coin;
	public static Item guide;
	
	public static void registerItems(FMLPreInitializationEvent preEvent) {
		sodaBottle = new ItemSodaBottle();
		coffeeCup = new ItemCoffeeCup();
		candy = new ItemCandy();
		vendingMachine = new ItemVendingMachine();
		coin = new ItemCoin();
		guide = new ItemGuideBook();
		
		registerModels();
	}
	
	private static void registerModels() {
		String[] sodaBottleNames = new String[]{"_water","_cola","_dietcola","_cafefreecola","_zerocola","_vanillacola","_cherrycola","_fanta","_dew","_sprite","_spritezero","_powerade","_fruitjuice"};
		String[] candyNames = new String[]{"_gumpurple","_gumblue","_gumlime","_gumorange","_gumpink","_gumyellow","_gumred","_gingerbreadman","_chocolatebar"};
		
		for(int i = 0; i < ItemSodaBottle.maxMeta; i++)
			ModelLoader.setCustomModelResourceLocation(sodaBottle, i, new ModelResourceLocation(new ResourceLocation(VendingMachineMod.modID, sodaBottle.getUnlocalizedName().substring(5 + VendingMachineMod.modID.length() + 1)+sodaBottleNames[i]), "inventory"));
		for(int i = 0; i < ItemCoffeeCup.maxMeta; i++)
			ModelLoader.setCustomModelResourceLocation(coffeeCup, i, new ModelResourceLocation(new ResourceLocation(VendingMachineMod.modID, coffeeCup.getUnlocalizedName().substring(5 + VendingMachineMod.modID.length() + 1)+"_"+i), "inventory"));
		for(int i = 0; i < ItemCandy.maxMeta; i++)
			ModelLoader.setCustomModelResourceLocation(candy, i, new ModelResourceLocation(new ResourceLocation(VendingMachineMod.modID, candy.getUnlocalizedName().substring(5 + VendingMachineMod.modID.length() + 1)+candyNames[i]), "inventory"));
		for(int i = 0; i < ItemVendingMachine.maxMeta; i++)
			ModelLoader.setCustomModelResourceLocation(vendingMachine, i, new ModelResourceLocation(new ResourceLocation(VendingMachineMod.modID, vendingMachine.getUnlocalizedName().substring(5 + VendingMachineMod.modID.length() + 1)+"_"+i), "inventory"));
		for(int i = 0; i < ItemCoin.maxMeta; i++)
			ModelLoader.setCustomModelResourceLocation(coin, i, new ModelResourceLocation(new ResourceLocation(VendingMachineMod.modID, coin.getUnlocalizedName().substring(5 + VendingMachineMod.modID.length() + 1)+"_"+i), "inventory"));
		for(int i = 0; i < ItemGuideBook.maxMeta; i++)
			ModelLoader.setCustomModelResourceLocation(guide, i, new ModelResourceLocation(new ResourceLocation(VendingMachineMod.modID, guide.getUnlocalizedName().substring(5 + VendingMachineMod.modID.length() + 1)+"_"+i), "inventory"));
	}
}