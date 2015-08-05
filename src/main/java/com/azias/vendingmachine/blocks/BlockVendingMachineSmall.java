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
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockVendingMachineSmall extends Block {

	@SideOnly(Side.CLIENT)
	protected IIcon[] blockIcon;
	
	protected BlockVendingMachineSmall(String name, boolean isClient) {
		super(Material.iron);
		this.setBlockName(VendingMachineMod.MODID + "_" + name);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
		if(isClient) {
			blockIcon = new IIcon[4];
		}
		GameRegistry.registerBlock(this, this.getUnlocalizedName());
	}
	
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (!this.canBlockStay(world, x, y, z)) {
        	world.setBlockToAir(x, y, z);
        }
    }
    
    public boolean canBlockStay(World world, int x, int y, int z) {
    	int a = world.getBlockMetadata(x, y, z);
    	if(a==0||a==1||a==2||a==3) {
    		Block requiredBlock = VendingMachineBlocks.vendingMachineStand;
	    	if(world.getBlock(x, y-1, z) == requiredBlock) {
	    		return true;
	    	} else {
	    		return false;
	    	}
    	} else {
    		return true;
    	}
    }
	
	public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		if(world.isRemote) {
            return true;
        } else {
			boolean isCreative = player.capabilities.isCreativeMode;
			if(player.inventory.hasItem(VendingMachineItems.coin)||isCreative) {
	        	int i = new Random().nextInt(9);
	        	int blockMeta = world.getBlockMetadata(par2, par3, par4);
	        	
	        	ItemStack a = new ItemStack(VendingMachineItems.candy, 1, i);
	        	EntityItem b;
	        	if(blockMeta==0) {
	        		 b = new EntityItem(world, par2+0.5, par3+0.5, par4-0.5, a);
	        	} else if(blockMeta==1) {
	        		 b = new EntityItem(world, par2+0.5, par3+0.5, par4+1.5, a);
	        	} else if(blockMeta==2) {
	        		 b = new EntityItem(world, par2-0.5, par3+0.5, par4+0.5, a);
	        	} else if(blockMeta==3) {
	        		 b = new EntityItem(world, par2+1.5, par3+0.5, par4+0.5, a);
	        	} else {
	        		 b = new EntityItem(world, par2+0.5, par3+3.5, par4+0.5, a);
	        	}
	        	b.motionX=0.0D;
	        	b.motionY=0.0D;
	        	b.motionZ=0.0D;
				b.setVelocity(0.0D, 0.0D, 0.0D);
				world.spawnEntityInWorld(b);
				
				player.inventory.consumeInventoryItem(Items.stick);
	        }
			return true;
        }
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return new ItemStack(VendingMachineItems.vendingMachine, 1, 1).getItem();
    }
	
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return new ItemStack(VendingMachineItems.vendingMachine, 1, 1).getItem();
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		for(int i=0; i<blockIcon.length; i++) {
			blockIcon[i] = iconRegister.registerIcon(VendingMachineMod.MODID + ":" + this.getUnlocalizedName().substring(5)+"_"+i);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int metadata) {
		if(side==1||side==6) {
			return blockIcon[0];
		}
		if(metadata==0) {
			if(side==2) {
				return blockIcon[1];
			} else {
				return blockIcon[0];
			}
		}
		if(metadata==1) {
			if(side==3) {
				return blockIcon[1];
			} else {
				return blockIcon[0];
			}
		}
		if(metadata==2) {
			if(side==4) {
				return blockIcon[1];
			} else {
				return blockIcon[0];
			}
		}
		if(metadata==3) {
			if(side==5) {
				return blockIcon[1];
			} else {
				return blockIcon[0];
			}
		}
		return blockIcon[0];
	}
}
