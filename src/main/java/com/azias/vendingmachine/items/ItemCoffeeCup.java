package com.azias.vendingmachine.items;

import java.util.List;

import com.azias.vendingmachine.VendingMachineMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCoffeeCup extends ItemFood {
	
	protected static final int maxMeta = 12;
	protected String name = "coffeeCup";
	
	public ItemCoffeeCup() {
        super(5, 0.25f, false);
        setHasSubtypes(true);
        setUnlocalizedName(VendingMachineMod.modID + "_" + this.name);
        setRegistryName(new ResourceLocation(VendingMachineMod.modID, this.name));
        setCreativeTab(VendingMachineMod.tabVendingMachines);
		GameRegistry.register(this);
	}
	
	@Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }
	
	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		if(stack.getItemDamage()!=0) {
			if(world.rand.nextFloat() < 0.99F) {
				player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 15*20, 1));
			} else {
				player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 20*20, 0));
			}
			if(world.rand.nextFloat() < 0.04F) {
				player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 15*20, 0));
			}
		}
    }
	
    /*@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
    	list.add("Hi!");
    }/**/

    @Override
    public EnumRarity getRarity(ItemStack stack) {
    	return EnumRarity.COMMON;
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
	
	public String getName() {
		return this.name;
	}
}
