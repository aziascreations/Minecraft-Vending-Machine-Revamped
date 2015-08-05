package com.azias.vendingmachine;

import com.azias.vendingmachine.items.VendingMachineItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class VendingMachineCreativeTab extends CreativeTabs {

	public VendingMachineCreativeTab(String tabLabel) {
		super(tabLabel);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return new ItemStack(VendingMachineItems.vendingMachine, 1).getItem();
	}

}