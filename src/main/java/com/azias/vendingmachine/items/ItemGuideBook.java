package com.azias.vendingmachine.items;

import java.util.List;

import com.azias.vendingmachine.VendingMachineMod;
import com.azias.vendingmachine.gui.GuiBookGuide;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemGuideBook extends Item {
	protected static final int maxMeta = 1;
	
	protected String name = "guidebook";
	
	public ItemGuideBook() {
        super();
        setHasSubtypes(true);
		setUnlocalizedName(VendingMachineMod.modID + "_" + this.name);
        setRegistryName(new ResourceLocation(VendingMachineMod.modID, this.name));
		setCreativeTab(VendingMachineMod.tabVendingMachines);
		GameRegistry.register(this);
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

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        
		if (worldIn.isRemote) {
        	System.out.println("Starting guide GUI...");
        	playerIn.openGui(VendingMachineMod.instance, GuiBookGuide.id, worldIn, (int)playerIn.posX, (int)playerIn.posY, (int)playerIn.posZ);
            return new ActionResult(EnumActionResult.SUCCESS, itemstack);
		} else {
            return new ActionResult(EnumActionResult.PASS, itemstack);
        }
    }
	
}
