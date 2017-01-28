package com.azias.vendingmachine.blocks;

import java.util.List;
import java.util.Random;

import com.azias.vendingmachine.VendingMachineMod;
import com.azias.vendingmachine.items.VendingMachineItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMachineFiller extends Block {

	//@SideOnly(Side.CLIENT)
	//protected IIcon blockIcon;
	
	protected BlockMachineFiller(String name) {
		super(Material.IRON);
		setUnlocalizedName(VendingMachineMod.modID + "_" + name);
		setHardness(1.5F);
		setResistance(10.0F);
		GameRegistry.registerBlock(this, this.getUnlocalizedName());
	}
	
    /*public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (!this.canBlockStay(world, x, y, z)) {
        	world.setBlockToAir(x, y, z);
        }
    }
    
    public boolean canBlockStay(World world, int x, int y, int z) {
    	//0-Soda / 1-Candy-Useless /  2-Coffee-Later
    	int a = world.getBlockMetadata(x, y, z);
    	if(a==0) {
    		Block block = VendingMachineBlocks.sodaVendingMachine;
    		boolean bool = (world.getBlock(x, y-1, z) == block);
    		return bool;
    	} else {
    		return false;
    	}
    }
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		int a = world.getBlockMetadata(x, y, z);
		if(a==0) {
			System.out.println("Activating Filler...");
			return world.getBlock(x, y-1, z).onBlockActivated(world, x, y-1, z, player, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
		} else {
			return false;
		}
    }*/

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return new ItemStack(VendingMachineItems.vendingMachine, 1, 1).getItem();
    }
	
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return new ItemStack(VendingMachineItems.vendingMachine, 1, 1).getItem();
    }
	
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    /*@Override
    public boolean renderAsNormalBlock() {
        return false;
    }*/
}
