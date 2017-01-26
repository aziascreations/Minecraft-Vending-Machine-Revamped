package com.azias.vendingmachine.blocks;

import java.util.List;
import java.util.Random;

import com.azias.vendingmachine.VendingMachineMod;
import com.azias.vendingmachine.items.VendingMachineItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMachineStand extends Block {
	protected String name = "vendingMachineStand";
	
	protected BlockMachineStand() {
		super(Material.iron);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
		this.setUnlocalizedName(this.name);
		this.setCreativeTab(VendingMachineMod.tabVendingMachines);
		//this.setBlockBoundsForItemRender();
		GameRegistry.registerBlock(this, this.name);
	}
	
	public String getName() {
		return name;
	}

	@Override
    public boolean isOpaqueCube() {
        return false;
    }

	@SideOnly(Side.CLIENT)
	@Override
    public boolean isFullCube() {
        return false;
    }
	
    public void setBlockBoundsForItemRender() {
    	//Broken
        float f = 1F/16F;
        this.setBlockBounds(4*f, 0F, 4*f, 4*f, 1F, 4*f);
    }
}
