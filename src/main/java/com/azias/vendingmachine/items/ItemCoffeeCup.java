package com.azias.vendingmachine.items;

import java.util.List;

import com.azias.vendingmachine.VendingMachineMod;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemCoffeeCup extends ItemFood {
	
	@SideOnly(Side.CLIENT)
	protected IIcon[] itemIcon;
	private final int maxMeta = 12;
	
	public ItemCoffeeCup(String name, boolean isClient) {
        super(5, 0.25f, false);
        setHasSubtypes(true);
		setTextureName(VendingMachineMod.MODID + ":" + name);
		setUnlocalizedName(VendingMachineMod.MODID + "_" + name);
		setCreativeTab(VendingMachineMod.tabVendingMachines);
		if(isClient) {
			itemIcon = new IIcon[maxMeta];
		}
		GameRegistry.registerItem(this, name);
	}
	
	@Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.drink;
    }
	
	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		if(stack.getItemDamage()!=0) {
			if(world.rand.nextFloat() < 0.95F) {
				player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 15*20, 1));
			} else {
				player.addPotionEffect(new PotionEffect(Potion.confusion.id, 20*20, 0));
			}
			if(world.rand.nextFloat() < 0.05F) {
				player.addPotionEffect(new PotionEffect(Potion.jump.id, 15*20, 0));
			}
		}
    }
	
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
    	//list.add("Hi!");
    }

    public EnumRarity getRarity(ItemStack stack) {
    	return EnumRarity.common;
    }
	

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister reg) {
	    for (int i = 0; i < maxMeta; i ++) {
	        this.itemIcon[i] = reg.registerIcon(VendingMachineMod.MODID + ":"+this.getUnlocalizedName().substring(25) +"_"+i);
	    }
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int meta) {
	    if (meta > maxMeta-1)
	        meta = 0;
	    return this.itemIcon[meta];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
	    for (int i = 0; i < maxMeta; i ++) {
	        list.add(new ItemStack(item, 1, i));
	    }
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
	    return this.getUnlocalizedName() + "_" + stack.getItemDamage();
	}
}
