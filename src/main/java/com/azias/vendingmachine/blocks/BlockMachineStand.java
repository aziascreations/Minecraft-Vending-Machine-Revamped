package com.azias.vendingmachine.blocks;

import java.util.List;
import java.util.Random;

import com.azias.vendingmachine.VendingMachineMod;
import com.azias.vendingmachine.items.VendingMachineItems;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockMachineStand extends Block {

	@SideOnly(Side.CLIENT)
	protected IIcon blockIcon;
	
	protected BlockMachineStand(String name) {
		super(Material.iron);
		this.setBlockName(VendingMachineMod.modID + "_" + name);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
		this.setCreativeTab(VendingMachineMod.tabVendingMachines);
		GameRegistry.registerBlock(this, this.getUnlocalizedName());
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(VendingMachineMod.modID + ":" + this.getUnlocalizedName().substring(5));
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int metadata) {
		return blockIcon;
	}
    
    public boolean isOpaqueCube() {
        return false;
    }

	@SideOnly(Side.CLIENT)
	@Override
    public boolean renderAsNormalBlock() {
        return false;
    }

	@SideOnly(Side.CLIENT)
	@Override
    public int getRenderType() {
        return 1;
    }
}
