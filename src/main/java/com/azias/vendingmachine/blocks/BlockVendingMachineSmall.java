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
		this.setBlockName(VendingMachineMod.modID + "_" + name);
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
    	if(a>=0 && a<=3) {
    		//TODO: Check if the block under is solid.
    		return true;
    	} else {
    		return true;
    	}
    }
	
	public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		if(world.isRemote) {
            return true;
        } else {
			boolean isCreative = player.capabilities.isCreativeMode;
			if(player.inventory.getCurrentItem() != null||isCreative) {
				if(world.getBlockMetadata(par2, par3, par4) >= 0 && world.getBlockMetadata(par2, par3, par4) <= 3) {
					if(isCreative||player.inventory.getCurrentItem().getItem().getUnlocalizedName().equals(VendingMachineItems.coin.getUnlocalizedName())) {
						
						boolean isDroppingFood = true;
						boolean isDroppingCoin = false;
						int randBase = new Random().nextInt(9);
						ItemStack itemFood = new ItemStack(VendingMachineItems.candy, 1, randBase);
						ItemStack itemCoin = new ItemStack(VendingMachineItems.coin, 1, 0);
						
						if(!isCreative) {
							switch(player.inventory.getCurrentItem().getItemDamage()) {
							case 0: //Clay coin
								int rand1 = new Random().nextInt(100);
								if(rand1<=64) {
									isDroppingFood=false;
								} else if(rand1>=65&&rand1<=99) {
									int[] a = {0,1,2,3,4,5,6};
									itemFood = new ItemStack(VendingMachineItems.candy, 1, a[new Random().nextInt(a.length)]);
								}
								rand1 = new Random().nextInt(100);
								if(rand1==69||rand1==42) {
									isDroppingCoin=true;
									itemCoin = new ItemStack(VendingMachineItems.coin, 1, 0);
								}
								break;
							case 1: //Iron coin
								int rand2 = new Random().nextInt(100);
								if(rand2<=84) {
									int[] a = {0,1,2,3,4,5,6};
									itemFood = new ItemStack(VendingMachineItems.candy, 1, a[new Random().nextInt(a.length)]);
								} else if(rand2>=85&&rand2<=96) {
									int[] a = {7,8};
									itemFood = new ItemStack(VendingMachineItems.candy, 1, a[new Random().nextInt(a.length)]);
								} else {
									isDroppingFood=false;
								}
								rand2 = new Random().nextInt(100);
								if(rand2==69||rand2==42) {
									isDroppingCoin=true;
									itemCoin = new ItemStack(VendingMachineItems.coin, 1, new Random().nextInt(2));
								}
								break;
							case 2: //Gold coin
								int rand3 = new Random().nextInt(100);
								if(rand3<=74) {
									int[] a = {0,1,2,3,4,5,6};
									itemFood = new ItemStack(VendingMachineItems.candy, 1, a[new Random().nextInt(a.length)]);
								} else if(rand3>=75&&rand3<=96) {
									int[] a = {7,8};
									itemFood = new ItemStack(VendingMachineItems.candy, 1, a[new Random().nextInt(a.length)]);
								} else {
									isDroppingFood=false;
								}
								rand3 = new Random().nextInt(100);
								if(rand3==69||rand3==42) {
									isDroppingCoin=true;
									itemCoin = new ItemStack(VendingMachineItems.coin, 1, new Random().nextInt(3));
								}
								break;
							}
						}
					
						if(isDroppingFood) {
							int blockMeta = world.getBlockMetadata(par2, par3, par4);
							EntityItem b;
							if(blockMeta==0||blockMeta==4) {
								b = new EntityItem(world, par2+0.5, par3+0.5, par4-0.5, itemFood);
								b.motionX=0.0D;
								b.motionZ=-0.125D;
					        	b.setVelocity(0.0D, 0.0D, b.motionZ);
				        	} else if(blockMeta==1||blockMeta==5) {
				        		b = new EntityItem(world, par2+0.5, par3+0.5, par4+1.5, itemFood);
						        b.motionX=0.0D;
						        b.motionZ=0.125D;
								b.setVelocity(0.0D, 0.0D, b.motionZ);
				        	} else if(blockMeta==2||blockMeta==6) {
				        		b = new EntityItem(world, par2-0.5, par3+0.5, par4+0.5, itemFood);
						        b.motionX=-0.125D;
						        b.motionZ=0.0D;
								b.setVelocity(b.motionX, 0.0D, 0.0D);
				        	} else if(blockMeta==3||blockMeta==7) {
				        		b = new EntityItem(world, par2+1.5, par3+0.5, par4+0.5, itemFood);
						        b.motionX=0.125D;
						        b.motionZ=0.0D;
								b.setVelocity(b.motionX, 0.0D, 0.0D);
				        	} else {
				        		b = new EntityItem(world, par2+0.5, par3+3.5, par4+0.5, itemFood);
				        	}
					        b.motionY=0.0D;
				        	if(blockMeta>3) {
				        		b.setPosition(b.posX, b.posY-1.0D, b.posZ);
				        	}
				        	world.spawnEntityInWorld(b);
						}
						if(isDroppingCoin) {
				        	int blockMeta = world.getBlockMetadata(par2, par3, par4);
				        	EntityItem b;
				        	if(blockMeta==0||blockMeta==4) {
				        		 b = new EntityItem(world, par2+0.5, par3+0.5, par4-0.5, itemCoin);
				        	} else if(blockMeta==1||blockMeta==5) {
				        		 b = new EntityItem(world, par2+0.5, par3+0.5, par4+1.5, itemCoin);
				        	} else if(blockMeta==2||blockMeta==6) {
				        		 b = new EntityItem(world, par2-0.5, par3+0.5, par4+0.5, itemCoin);
				        	} else if(blockMeta==3||blockMeta==7) {
				        		 b = new EntityItem(world, par2+1.5, par3+0.5, par4+0.5, itemCoin);
				        	} else {
				        		 b = new EntityItem(world, par2+0.5, par3+3.5, par4+0.5, itemCoin);
				        	}
				        	if(blockMeta>3) {
				        		b.setPosition(b.posX, b.posY-1.0D, b.posZ);
				        	}
				        	b.motionX=0.0D;
				        	b.motionY=0.0D;
				        	b.motionZ=0.0D;
							b.setVelocity(0.0D, 0.0D, 0.0D);
							world.spawnEntityInWorld(b);
						}
						
						if(!isCreative) {
							player.inventory.getCurrentItem().stackSize--;
						}
						return true;
					}
				}
			} else {
				return false;
			}
			return false;
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
			blockIcon[i] = iconRegister.registerIcon(VendingMachineMod.modID + ":" + this.getUnlocalizedName().substring(5)+"_"+i);
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
