package com.azias.vendingmachine.items;

import java.util.List;

import com.azias.vendingmachine.VendingMachineMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCoin extends Item {
	protected static final int maxMeta = 3;
	
	protected String name = "coin";
	
	public ItemCoin() {
        super();
        setHasSubtypes(true);
        setUnlocalizedName(VendingMachineMod.modID + "_" + name);
        setRegistryName(new ResourceLocation(VendingMachineMod.modID, this.name));
        setCreativeTab(VendingMachineMod.tabVendingMachines);
		GameRegistry.register(this);
	}
	
	public String getName() {
		return this.name;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
	    for (int i = 0; i < maxMeta; i ++) {
	    	subItems.add(new ItemStack(itemIn, 1, i));
	    }
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
	    return this.getUnlocalizedName() + "_" + stack.getItemDamage();
	}
}
