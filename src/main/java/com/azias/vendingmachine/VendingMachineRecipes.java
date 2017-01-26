package com.azias.vendingmachine;

import com.azias.vendingmachine.blocks.VendingMachineBlocks;
import com.azias.vendingmachine.items.VendingMachineItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class VendingMachineRecipes {
	
	public static void registerRecipes() {
		/* Sodas */
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 0), new Object[]{
		    "A","B","B",
		    'A', Blocks.WOODEN_BUTTON,
		    'B', new ItemStack(Items.POTIONITEM, 1, 0)});
		
		//Cola
		GameRegistry.addShapelessRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 1), new Object[] {
			new ItemStack(VendingMachineItems.sodaBottle, 1, 0),
			new ItemStack(Items.DYE, 1, 3),
			Items.SUGAR,
			Items.SUGAR});
		
		//Diet Cola
		GameRegistry.addShapelessRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 2), new Object[] {
			new ItemStack(VendingMachineItems.sodaBottle, 1, 0),
			new ItemStack(Items.DYE, 1, 3),
			Items.SUGAR});
		
		//Caffeine Free Cola
		GameRegistry.addShapelessRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 3), new Object[] {
			new ItemStack(VendingMachineItems.sodaBottle, 1, 0),
			Items.SUGAR,
			Blocks.DIRT});
		
		//Cola Zero
		GameRegistry.addShapelessRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 4), new Object[] {
			new ItemStack(VendingMachineItems.sodaBottle, 1, 0),
			new ItemStack(Items.DYE, 1, 3),
			Items.REEDS});
		
		//Cherry Cola
		GameRegistry.addShapelessRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 6), new Object[] {
			new ItemStack(VendingMachineItems.sodaBottle, 1, 0),
			new ItemStack(Items.DYE, 1, 3),
			Items.SUGAR,
			Items.APPLE});
		
		//Fanta
		GameRegistry.addShapelessRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 7), new Object[] {
			new ItemStack(VendingMachineItems.sodaBottle, 1, 0),
			new ItemStack(Items.DYE, 1, 14),
			Items.SUGAR,
			Items.APPLE});
		
		//Mountain Dew
		GameRegistry.addShapelessRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 8), new Object[] {
			new ItemStack(VendingMachineItems.sodaBottle, 1, 0),
			new ItemStack(Items.DYE, 1, 10),
			Items.SUGAR,
			Items.GOLDEN_APPLE});
		
		//Sprite
		GameRegistry.addShapelessRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 9), new Object[] {
			new ItemStack(VendingMachineItems.sodaBottle, 1, 0),
			Items.SUGAR});
		
		//Sprite Zero
		GameRegistry.addShapelessRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 10), new Object[] {
			new ItemStack(VendingMachineItems.sodaBottle, 1, 0),
			Items.REEDS});
		
		//Powerade
		GameRegistry.addShapelessRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 11), new Object[] {
			new ItemStack(VendingMachineItems.sodaBottle, 1, 0),
			new ItemStack(Items.DYE, 1, 4),
			Items.SUGAR,
			Items.GOLDEN_APPLE});
		
		//Fruit Juice
		GameRegistry.addShapelessRecipe(new ItemStack(VendingMachineItems.sodaBottle, 1, 12), new Object[] {
			new ItemStack(VendingMachineItems.sodaBottle, 1, 0),
			Items.APPLE,
			Items.SUGAR,
			Items.CARROT});
		
		/* Candies */
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.candy, 1, 0), new Object[]{
			" B ","BAB"," B ",
			'A', Items.SUGAR,
			'B', new ItemStack(Items.DYE, 1, 5)});
		
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.candy, 1, 1), new Object[]{
			" B ","BAB"," B ",
			'A', Items.SUGAR,
			'B', new ItemStack(Items.DYE, 1, 4)});
		
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.candy, 1, 2), new Object[]{
			" B ","BAB"," B ",
			'A', Items.SUGAR,
			'B', new ItemStack(Items.DYE, 1, 10)});
		
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.candy, 1, 3), new Object[]{
			" B ","BAB"," B ",
			'A', Items.SUGAR,
			'B', new ItemStack(Items.DYE, 1, 14)});
		
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.candy, 1, 4), new Object[]{
			" B ","BAB"," B ",
			'A', Items.SUGAR,
			'B', new ItemStack(Items.DYE, 1, 9)});
		
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.candy, 1, 5), new Object[]{
			" B ","BAB"," B ",
			'A', Items.SUGAR,
			'B', new ItemStack(Items.DYE, 1, 11)});
		
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.candy, 1, 6), new Object[]{
			" B ","BAB"," B ",
			'A', Items.SUGAR,
			'B', new ItemStack(Items.DYE, 1, 1)});
		
		/* Coins */
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.coin, 10, 0), new Object[]{
			" A ",
			"ABA",
			" A ",
			'A', Items.CLAY_BALL,
			'B', Blocks.STONE_BUTTON});
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.coin, 10, 1), new Object[]{
			" A ",
			"ABA",
			" A ",
			'A', Items.IRON_INGOT,
			'B', Blocks.STONE_BUTTON});
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.coin, 5, 2), new Object[]{
			" A ",
			"ABA",
			" A ",
			'A', Items.GOLD_INGOT,
			'B', Blocks.STONE_BUTTON});
		
		/* Vending Machines and Parts */
		for(int i=1; i<13; i++) {
			GameRegistry.addRecipe(new ItemStack(VendingMachineItems.vendingMachine, 1, 0), new Object[] {
				"AE","BD","AC",
				'A', Blocks.IRON_BLOCK,
				'B', Blocks.GLASS_PANE,
				'C', new ItemStack(VendingMachineItems.sodaBottle, 1, i),
				'D', Items.REDSTONE,
				'E', Blocks.STONE_BUTTON});
		}
		
		GameRegistry.addRecipe(new ItemStack(VendingMachineItems.vendingMachine, 1, 1), new Object[] {
			"A","B",
			'A', new ItemStack(VendingMachineItems.vendingMachine, 1, 2),
			'B', VendingMachineBlocks.vendingMachineStand});
		
		for(int i=0; i<9; i++) {
			GameRegistry.addRecipe(new ItemStack(VendingMachineItems.vendingMachine, 1, 2), new Object[] {
				"AC","BE","AD",
				'A', Blocks.IRON_BLOCK,
				'B', Blocks.GLASS_PANE,
				'C', new ItemStack(VendingMachineItems.candy, 1, i),
				'D', Items.IRON_INGOT,
				'E', Blocks.STONE_BUTTON});
		}
		
		GameRegistry.addRecipe(new ItemStack(VendingMachineBlocks.vendingMachineStand, 1, 0), new Object[] {
			" A ","ABA",
			'A', Items.IRON_INGOT,
			'B', Blocks.IRON_BLOCK});
	}
}
