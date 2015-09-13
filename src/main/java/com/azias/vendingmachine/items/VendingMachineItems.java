package com.azias.vendingmachine.items;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.item.Item;

public class VendingMachineItems {
	
	public static Item sodaBottle, coffeeCup, candy;
	public static Item vendingMachine;
	public static Item coin;
	public static Item guide;
	
	public static void registerItems(FMLPreInitializationEvent preEvent) {
		boolean isClient = preEvent.getSide().isClient();
		
		sodaBottle = new ItemSodaBottle("sodaBottle", isClient);
		coffeeCup = new ItemCoffeeCup("coffeeCup", isClient);
		candy = new ItemCandy("candy", isClient);
		
		vendingMachine = new ItemVendingMachine("vendingMachine", isClient);
		
		coin = new ItemCoin("coin", isClient);
		
		guide = new ItemGuideBook("guidebook", isClient);
		//craftingMaterials = new ItemCraftingMaterials("craftingMaterials", isClient);
	}
}