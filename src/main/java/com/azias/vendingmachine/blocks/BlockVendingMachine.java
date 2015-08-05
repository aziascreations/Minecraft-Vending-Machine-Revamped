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

public class BlockVendingMachine extends Block {

	@SideOnly(Side.CLIENT)
	protected IIcon[] blockIcon;
	
	protected BlockVendingMachine(String name, boolean isClient) {
		super(Material.iron);
		this.setBlockName(VendingMachineMod.MODID + "_" + name);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
		if(isClient) {
			blockIcon = new IIcon[5];
		}
		GameRegistry.registerBlock(this, this.getUnlocalizedName());
	}
	
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (!this.canBlockStay(world, x, y, z)) {
        	world.setBlockToAir(x, y, z);
        }
    }
    
    public boolean canBlockStay(World world, int x, int y, int z) {
    	Block field_150935_a = VendingMachineBlocks.vendingMachineLarge;
    	if(world.getBlock(x, y+1, z) == field_150935_a) {
    		return true;
    	} else if(world.getBlock(x, y-1, z) == field_150935_a) {
    		return true;
    	} else {
    		return false;
    	}
    }
	
	public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		if (world.isRemote) {
            return true;
        } else {
			boolean isCreative = player.capabilities.isCreativeMode;
			if(player.inventory.getCurrentItem() != null||isCreative) {
				if(isCreative||player.inventory.getCurrentItem().getItem().getUnlocalizedName().equals(VendingMachineItems.coin.getUnlocalizedName())) {
					int i = new Random().nextInt(9);
		        	int blockMeta = world.getBlockMetadata(par2, par3, par4);
		        	
		        	ItemStack a = new ItemStack(VendingMachineItems.sodaBottle, 1, i);
		        	EntityItem b;
		        	if(blockMeta==0||blockMeta==4) {
		        		 b = new EntityItem(world, par2+0.5, par3+0.5, par4-0.5, a);
		        	} else if(blockMeta==1||blockMeta==5) {
		        		 b = new EntityItem(world, par2+0.5, par3+0.5, par4+1.5, a);
		        	} else if(blockMeta==2||blockMeta==6) {
		        		 b = new EntityItem(world, par2-0.5, par3+0.5, par4+0.5, a);
		        	} else if(blockMeta==3||blockMeta==7) {
		        		 b = new EntityItem(world, par2+1.5, par3+0.5, par4+0.5, a);
		        	} else {
		        		 b = new EntityItem(world, par2+0.5, par3+3.5, par4+0.5, a);
		        	}
		        	if(blockMeta>3) {
		        		b.setPosition(b.posX, b.posY-1.0D, b.posZ);
		        	}
		        	b.motionX=0.0D;
		        	b.motionY=0.0D;
		        	b.motionZ=0.0D;
					b.setVelocity(0.0D, 0.0D, 0.0D);
					world.spawnEntityInWorld(b);
					
					if(!isCreative) {
						player.inventory.getCurrentItem().stackSize--;
					}
					return true;
		        }
			} else {
				return false;
			}
			return false;
        }
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return VendingMachineItems.vendingMachine;
    }
	
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return VendingMachineItems.vendingMachine;
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
		if(metadata==0||metadata==4) {
			if(side==2) {
				if(metadata>=3) {
					return blockIcon[1];
				} else {
					return blockIcon[2];
				}
			}
			if(metadata>=3) {
				return blockIcon[3];
			} else {
				return blockIcon[4];
			}
		}
		if(metadata==1||metadata==5) {
			if(side==3) {
				if(metadata>=3) {
					return blockIcon[1];
				} else {
					return blockIcon[2];
				}
			}
			if(metadata>=3) {
				return blockIcon[3];
			} else {
				return blockIcon[4];
			}
		}
		if(metadata==2||metadata==6) {
			if(side==4) {
				if(metadata>=3) {
					return blockIcon[1];
				} else {
					return blockIcon[2];
				}
			}
			if(metadata>=3) {
				return blockIcon[3];
			} else {
				return blockIcon[4];
			}
		}
		if(metadata==3||metadata==7) {
			if(side==5) {
				if(metadata>=4) {
					return blockIcon[1];
				} else {
					return blockIcon[2];
				}
			}
			if(metadata>=4) {
				return blockIcon[3];
			} else {
				return blockIcon[4];
			}
		}
		return blockIcon[2];
	}
}
