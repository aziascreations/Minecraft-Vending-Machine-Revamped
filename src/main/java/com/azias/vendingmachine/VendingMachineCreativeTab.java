package com.azias.vendingmachine;

import com.azias.vendingmachine.items.VendingMachineItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VendingMachineCreativeTab extends CreativeTabs {

	//TODO: Add a custom background texture or not.
	public VendingMachineCreativeTab(String tabLabel) {
		super(tabLabel);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getTabIconItem() {
		return new ItemStack(VendingMachineItems.coin, 1);
	}
}