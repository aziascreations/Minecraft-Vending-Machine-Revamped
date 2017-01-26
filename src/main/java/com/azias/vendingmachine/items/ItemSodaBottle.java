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

public class ItemSodaBottle extends ItemFood {
	protected final static int maxMeta = 13;
	
	protected String name = "sodaBottle";
	
	public ItemSodaBottle() {
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
		if (!world.isRemote) {
			switch(stack.getItemDamage()) {
	    	case 5:
	    		if(world.rand.nextInt(100) == 0) {
	    			player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 20*20, 0));
	    		}
				player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 10*20, 0));
				player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 25*20, 1));
	    		break;
	    	case 8:
	    		if(world.rand.nextInt(100) == 0) {
	    			player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 20*20, 0));
	    		}
				player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 30*20, 1));
				player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 30*20, 0));
				player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 30*20, 0));
	    		break;
	    	case 11:
	    		if(world.rand.nextInt(100) == 0) {
	    			player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 20*20, 0));
	    		}
				player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 20*20, 0));
				player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 30*20, 1));
	    		break;
	    	default:
	    		if(stack.getItemDamage()!=0) {
					if(world.rand.nextFloat() < 0.98F) {
						player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 15*20, 0));
					} else {
						player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 20*20, 0));
					}
					if(world.rand.nextFloat() < 0.05F) {
						player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 15*20, 0));
					}
	    		}
	    		break;
	    	}
		}
    }
	
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
    	switch(stack.getItemDamage()) {
    	case 8:
    		//list.add("Dew the Dew");
    		break;
    	default:
    		//list.add("Hi!");
    	}
    }

    public EnumRarity getRarity(ItemStack stack)
    {
    	switch(stack.getItemDamage()) {
    	case 8:
            return EnumRarity.EPIC;
    	case 11:
            return EnumRarity.RARE;
    	default:
            return EnumRarity.COMMON;
    	}
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
