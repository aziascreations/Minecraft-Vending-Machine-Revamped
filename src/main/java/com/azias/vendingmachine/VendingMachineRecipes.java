package com.azias.vendingmachine;

import com.azias.vendingmachine.items.VendingMachineItems;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VendingMachineRecipes {
	
	public static void registerRecipes() {
		/* Sodas */
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 0), new Object[]{
		    "A",
		    "B",
		    "B",
		    'A', Blocks.wooden_button,
		    'B', new ItemStack(Items.potionitem, 1, 0)});
		
		GameRegistry.addShapelessRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 1), new Object[] {
			new ItemStack(VendingMachineItems.sodaBottle, 1, 0),
			new ItemStack(Items.dye, 1, 3), Items.sugar, Items.sugar});
		
		GameRegistry.addShapelessRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 2), new Object[] {
			new ItemStack(VendingMachineItems.sodaBottle, 1, 0),
			new ItemStack(Items.dye, 1, 3), Items.sugar});
		
		GameRegistry.addShapelessRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 3), new Object[] {
			new ItemStack(VendingMachineItems.sodaBottle, 1, 0),
			Items.sugar, Blocks.dirt});
		
		GameRegistry.addShapelessRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 4), new Object[] {
			new ItemStack(VendingMachineItems.sodaBottle, 1, 0),
			new ItemStack(Items.dye, 1, 3), Items.reeds});
		
		GameRegistry.addShapelessRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 5), new Object[] {
			new ItemStack(VendingMachineItems.sodaBottle, 1, 0),
			new ItemStack(Items.dye, 1, 3),
			Items.bone,});
		
		/* Candies */
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.candy, 1, 0), new Object[]{
			" B ",
			"BAB",
			" B ",
			'A', Items.sugar,
			'B', new ItemStack(Items.dye, 1, 5)});
		
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.candy, 1, 1), new Object[]{
			" B ",
			"BAB",
			" B ",
			'A', Items.sugar,
			'B', new ItemStack(Items.dye, 1, 4)});
		
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.candy, 1, 2), new Object[]{
			" B ",
			"BAB",
			" B ",
			'A', Items.sugar,
			'B', new ItemStack(Items.dye, 1, 10)});
		
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.candy, 1, 3), new Object[]{
			" B ",
			"BAB",
			" B ",
			'A', Items.sugar,
			'B', new ItemStack(Items.dye, 1, 14)});
		
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.candy, 1, 4), new Object[]{
			" B ",
			"BAB",
			" B ",
			'A', Items.sugar,
			'B', new ItemStack(Items.dye, 1, 9)});
		
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.candy, 1, 5), new Object[]{
			" B ",
			"BAB",
			" B ",
			'A', Items.sugar,
			'B', new ItemStack(Items.dye, 1, 11)});
		
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.candy, 1, 6), new Object[]{
			" B ",
			"BAB",
			" B ",
			'A', Items.sugar,
			'B', new ItemStack(Items.dye, 1, 1)});
		
		/* Coins */
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.coin, 1, 0), new Object[]{
			" A ",
			"ABA",
			" A ",
			'A', Items.clay_ball,
			'B', Blocks.stone_button});
		/*GameRegistry.addRecipe(new ItemStack(VendingMachineItems.coin, 1, 1), new Object[]{
			" A ",
			"ABA",
			" A ",
			'A', Items.,
			'B', Blocks.stone_button});*/
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.coin, 1, 2), new Object[]{
			" A ",
			"ABA",
			" A ",
			'A', Items.gold_nugget,
			'B', Blocks.stone_button});
		
		/* Vending Machines */
		for(int i=1; i<9; i++) {
			GameRegistry.addRecipe(new ItemStack(VendingMachineItems.vendingMachine, 1, 0), new Object[] {
				"AE","BD","AC",
				'A', Blocks.iron_block,
				'B', Blocks.glass_pane,
				'C', new ItemStack(VendingMachineItems.sodaBottle, 1, i),
				'D', Items.redstone,
				'E', Blocks.stone_button});
		}
		
		for(int i=1; i<9; i++) {
			GameRegistry.addRecipe(new ItemStack(VendingMachineItems.vendingMachine, 1, 1), new Object[] {
				"AC","BE","AD",
				'A', Blocks.iron_block,
				'B', Blocks.glass_pane,
				'C', new ItemStack(VendingMachineItems.candy, 1, i),
				'D', Items.iron_ingot,
				'E', Blocks.stone_button});
		}
	}
}
