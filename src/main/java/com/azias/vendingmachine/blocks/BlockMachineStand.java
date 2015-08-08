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
		this.setBlockName(VendingMachineMod.MODID + "_" + name);
        this.setBlockBounds(1.0F/16*5, 0.0F, 1.0F/16*5, 1.0F-1.0F/16*5, 1.0F, 1.0F-1.0F/16*5);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
		this.setCreativeTab(VendingMachineMod.tabVendingMachines);
		GameRegistry.registerBlock(this, this.getUnlocalizedName());
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(VendingMachineMod.MODID + ":" + this.getUnlocalizedName().substring(5));
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int metadata) {
		return blockIcon;
	}
    
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return null;
    }

	@SideOnly(Side.CLIENT)
	@Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

	@SideOnly(Side.CLIENT)
	@Override
    public int getRenderType()
    {
        return 1;
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return null;
    }
	
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return null;
    }
}
