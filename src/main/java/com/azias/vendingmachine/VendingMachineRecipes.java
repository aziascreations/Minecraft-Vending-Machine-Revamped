package com.azias.vendingmachine;

import com.azias.vendingmachine.blocks.VendingMachineBlocks;
import com.azias.vendingmachine.items.VendingMachineItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VendingMachineRecipes {
	
	public static void registerRecipes() {
		/* Sodas */
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 0), new Object[]{
		    "A","B","B",
		    'A', Blocks.wooden_button,
		    'B', new ItemStack(Items.potionitem, 1, 0)});
		
		//Cola
		GameRegistry.addShapelessRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 1), new Object[] {
			new ItemStack(VendingMachineItems.sodaBottle, 1, 0),
			new ItemStack(Items.dye, 1, 3),
			Items.sugar,
			Items.sugar});
		
		//Diet Cola
		GameRegistry.addShapelessRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 2), new Object[] {
			new ItemStack(VendingMachineItems.sodaBottle, 1, 0),
			new ItemStack(Items.dye, 1, 3),
			Items.sugar});
		
		//Caffeine Free Cola
		GameRegistry.addShapelessRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 3), new Object[] {
			new ItemStack(VendingMachineItems.sodaBottle, 1, 0),
			Items.sugar,
			Blocks.dirt});
		
		//Cola Zero
		GameRegistry.addShapelessRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 4), new Object[] {
			new ItemStack(VendingMachineItems.sodaBottle, 1, 0),
			new ItemStack(Items.dye, 1, 3),
			Items.reeds});
		
		//Cherry Cola
		GameRegistry.addShapelessRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 6), new Object[] {
			new ItemStack(VendingMachineItems.sodaBottle, 1, 0),
			new ItemStack(Items.dye, 1, 3),
			Items.sugar,
			Items.apple});
		
		//Fanta
		GameRegistry.addShapelessRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 7), new Object[] {
			new ItemStack(VendingMachineItems.sodaBottle, 1, 0),
			new ItemStack(Items.dye, 1, 14),
			Items.sugar,
			Items.apple});
		
		//Mountain Dew
		GameRegistry.addShapelessRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 8), new Object[] {
			new ItemStack(VendingMachineItems.sodaBottle, 1, 0),
			new ItemStack(Items.dye, 1, 10),
			Items.sugar,
			Items.golden_apple});
		
		//Sprite
		GameRegistry.addShapelessRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 9), new Object[] {
			new ItemStack(VendingMachineItems.sodaBottle, 1, 0),
			Items.sugar});
		
		//Sprite Zero
		GameRegistry.addShapelessRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 10), new Object[] {
			new ItemStack(VendingMachineItems.sodaBottle, 1, 0),
			Items.reeds});
		
		//Powerade
		GameRegistry.addShapelessRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 11), new Object[] {
			new ItemStack(VendingMachineItems.sodaBottle, 1, 0),
			new ItemStack(Items.dye, 1, 4),
			Items.sugar,
			Items.golden_apple});
		
		//Fruit Juice
		GameRegistry.addShapelessRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 12), new Object[] {
			new ItemStack(VendingMachineItems.sodaBottle, 1, 0),
			Items.apple,
			Items.sugar,
			Items.carrot});
		
		/* Candies */
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.candy, 1, 0), new Object[]{
			" B ","BAB"," B ",
			'A', Items.sugar,
			'B', new ItemStack(Items.dye, 1, 5)});
		
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.candy, 1, 1), new Object[]{
			" B ","BAB"," B ",
			'A', Items.sugar,
			'B', new ItemStack(Items.dye, 1, 4)});
		
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.candy, 1, 2), new Object[]{
			" B ","BAB"," B ",
			'A', Items.sugar,
			'B', new ItemStack(Items.dye, 1, 10)});
		
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.candy, 1, 3), new Object[]{
			" B ","BAB"," B ",
			'A', Items.sugar,
			'B', new ItemStack(Items.dye, 1, 14)});
		
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.candy, 1, 4), new Object[]{
			" B ","BAB"," B ",
			'A', Items.sugar,
			'B', new ItemStack(Items.dye, 1, 9)});
		
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.candy, 1, 5), new Object[]{
			" B ","BAB"," B ",
			'A', Items.sugar,
			'B', new ItemStack(Items.dye, 1, 11)});
		
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.candy, 1, 6), new Object[]{
			" B ","BAB"," B ",
			'A', Items.sugar,
			'B', new ItemStack(Items.dye, 1, 1)});
		
		/* Coins */
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.coin, 10, 0), new Object[]{
			" A ",
			"ABA",
			" A ",
			'A', Items.clay_ball,
			'B', Blocks.stone_button});
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.coin, 10, 1), new Object[]{
			" A ",
			"ABA",
			" A ",
			'A', Items.iron_ingot,
			'B', Blocks.stone_button});
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.coin, 5, 2), new Object[]{
			" A ",
			"ABA",
			" A ",
			'A', Items.gold_ingot,
			'B', Blocks.stone_button});
		
		/* Vending Machines and Parts */
		for(int i=1; i<13; i++) {
			GameRegistry.addRecipe(new ItemStack(VendingMachineItems.vendingMachine, 1, 0), new Object[] {
				"AE","BD","AC",
				'A', Blocks.iron_block,
				'B', Blocks.glass_pane,
				'C', new ItemStack(VendingMachineItems.sodaBottle, 1, i),
				'D', Items.redstone,
				'E', Blocks.stone_button});
		}
		
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.vendingMachine, 1, 1), new Object[] {
			"A","B",
			'A', new ItemStack(VendingMachineItems.vendingMachine, 1, 2),
			'B', VendingMachineBlocks.vendingMachineStand});
		
		for(int i=0; i<9; i++) {
			GameRegistry.addRecipe(new ItemStack(VendingMachineItems.vendingMachine, 1, 2), new Object[] {
				"AC","BE","AD",
				'A', Blocks.iron_block,
				'B', Blocks.glass_pane,
				'C', new ItemStack(VendingMachineItems.candy, 1, i),
				'D', Items.iron_ingot,
				'E', Blocks.stone_button});
		}
		
		GameRegistry.addRecipe(new ItemStack(VendingMachineBlocks.vendingMachineStand, 1, 0), new Object[] {
			" A ","ABA",
			'A', Items.iron_ingot,
			'B', Blocks.iron_block});
	}
}
