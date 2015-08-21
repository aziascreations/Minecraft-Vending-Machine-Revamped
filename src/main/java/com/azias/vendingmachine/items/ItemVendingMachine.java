package com.azias.vendingmachine.items;

import java.util.List;

import com.azias.vendingmachine.VendingMachineMod;
import com.azias.vendingmachine.blocks.VendingMachineBlocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemVendingMachine extends Item {

	@SideOnly(Side.CLIENT)
	protected IIcon[] itemIcon;
	private final int maxMeta = 3;
	private Block blockVendingSoda = VendingMachineBlocks.vendingMachineLarge;
	private Block blockVendingCandy = VendingMachineBlocks.vendingMachineSmall;
	private Block blockVendingStand = VendingMachineBlocks.vendingMachineStand;
	private final String[] itemsNames = {"vendingMachine", "candyMachine", "candyMachineBase"};
    
    public ItemVendingMachine(String name, boolean isClient) {
        super();
        setHasSubtypes(true);
		setTextureName(VendingMachineMod.modID + ":" + name);
		setUnlocalizedName(VendingMachineMod.modID + "_" + name);
		setCreativeTab(VendingMachineMod.tabVendingMachines);
		if(isClient) {
			itemIcon = new IIcon[maxMeta];
		}
		GameRegistry.registerItem(this, name);
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister reg) {
	    for (int i = 0; i < maxMeta; i ++) {
	        this.itemIcon[i] = reg.registerIcon(VendingMachineMod.modID + ":" + this.itemsNames[i]);
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
	
	@Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
		switch(stack.getItemDamage()) {
		case 0:
			/*Soda Vending Machine*/
			Block block1 = world.getBlock(x, y, z);
			boolean isTopFree1 = world.getBlock(x, y+1, z).isAir(world, x, y+1, z);
	
	        if (block1 == Blocks.snow_layer && (world.getBlockMetadata(x, y, z) & 7) < 1) {
	            p_77648_7_ = 1;
	        }
	        else if (block1 != Blocks.vine && block1 != Blocks.tallgrass && block1 != Blocks.deadbush) {
	            if (p_77648_7_ == 0) {
	                --y;
	            }
	            if (p_77648_7_ == 1) {
	                ++y;
	            }
	            if (p_77648_7_ == 2) {
	                --z;
	            }
	            if (p_77648_7_ == 3) {
	                ++z;
	            }
	            if (p_77648_7_ == 4) {
	                --x;
	            }
	            if (p_77648_7_ == 5) {
	                ++x;
	            }
	        }
	
	        if(!isTopFree1) {
	        	return false;
	        }
	        if (!player.canPlayerEdit(x, y, z, p_77648_7_, stack)) {
	            return false;
	        }
	        else if (stack.stackSize == 0) {
	            return false;
	        }
	        else {
	            if (world.canPlaceEntityOnSide(this.blockVendingSoda, x, y, z, false, p_77648_7_, (Entity)null, stack)) {
	                int i1 = this.blockVendingSoda.onBlockPlaced(world, x, y, z, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_, 0);
	
	                if (world.setBlock(x, y, z, this.blockVendingSoda, i1, 3)) {
	                	if (world.setBlock(x, y+1, z, this.blockVendingSoda, i1, 3)) {
		                    if (world.getBlock(x, y, z) == this.blockVendingSoda && world.getBlock(x, y+1, z) == this.blockVendingSoda) {
		                        this.blockVendingSoda.onBlockPlacedBy(world, x, y, z, player, stack);
		                        this.blockVendingSoda.onPostBlockPlaced(world, x, y, z, i1);
		                        this.blockVendingSoda.onBlockPlacedBy(world, x, y+1, z, player, stack);
		                        this.blockVendingSoda.onPostBlockPlaced(world, x, y+1, z, i1);
		                        
		                        int l = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		                        if (l == 0) {
		                        	world.setBlockMetadataWithNotify(x, y, z, 0, 2);
		                        	world.setBlockMetadataWithNotify(x, y+1, z, 0+4, 2);
		                        }
		                        if (l == 1) {
		                        	world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		                        	world.setBlockMetadataWithNotify(x, y+1, z, 3+4, 2);
		                        }
		                        if (l == 2) {
		                        	world.setBlockMetadataWithNotify(x, y, z, 1, 2);
		                        	world.setBlockMetadataWithNotify(x, y+1, z, 1+4, 2);
		                        }
		                        if (l == 3) {
		                        	world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		                        	world.setBlockMetadataWithNotify(x, y+1, z, 2+4, 2);
		                        }
		                        
		                    }
		                    world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), this.blockVendingSoda.stepSound.func_150496_b(), (this.blockVendingSoda.stepSound.getVolume() + 1.0F) / 2.0F, this.blockVendingSoda.stepSound.getPitch() * 0.8F);
		                    --stack.stackSize;
	                	}
	                }
	            }
	            return true;
	        }
		case 1:
			/*Candy Vending Machine with Stand*/
			Block block2 = world.getBlock(x, y, z);
			boolean isTopFree2 = world.getBlock(x, y+1, z).isAir(world, x, y+1, z);
			
	        if (block2 == Blocks.snow_layer && (world.getBlockMetadata(x, y, z) & 7) < 1) {
	            p_77648_7_ = 1;
	        }
	        else if (block2 != Blocks.vine && block2 != Blocks.tallgrass && block2 != Blocks.deadbush) {
	            if (p_77648_7_ == 0) {
	                --y;
	            }
	            if (p_77648_7_ == 1) {
	                ++y;
	            }
	            if (p_77648_7_ == 2) {
	                --z;
	            }
	            if (p_77648_7_ == 3) {
	                ++z;
	            }
	            if (p_77648_7_ == 4) {
	                --x;
	            }
	            if (p_77648_7_ == 5) {
	                ++x;
	            }
	        }
	
	        if(!isTopFree2) {
	        	return false;
	        }
	        if (!player.canPlayerEdit(x, y, z, p_77648_7_, stack)) {
	            return false;
	        }
	        else if (stack.stackSize == 0) {
	            return false;
	        }
	        else {
	            if (world.canPlaceEntityOnSide(this.blockVendingStand, x, y, z, false, p_77648_7_, (Entity)null, stack)) {
	                int i1 = this.blockVendingStand.onBlockPlaced(world, x, y, z, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_, 0);
	
	                if (world.setBlock(x, y, z, this.blockVendingStand, i1, 3)) {
	                	if (world.setBlock(x, y+1, z, this.blockVendingCandy, i1, 3)) {
		                    if (world.getBlock(x, y, z) == this.blockVendingStand && world.getBlock(x, y+1, z) == this.blockVendingCandy) {
		                        this.blockVendingStand.onBlockPlacedBy(world, x, y, z, player, stack);
		                        this.blockVendingStand.onPostBlockPlaced(world, x, y, z, i1);
		                        this.blockVendingCandy.onBlockPlacedBy(world, x, y+1, z, player, stack);
		                        this.blockVendingCandy.onPostBlockPlaced(world, x, y+1, z, i1);
		                        
		                        int l = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		                        if (l == 0) {
		                        	world.setBlockMetadataWithNotify(x, y+1, z, 0, 2);
		                        }
		                        if (l == 1) {
		                        	world.setBlockMetadataWithNotify(x, y+1, z, 3, 2);
		                        }
		                        if (l == 2) {
		                        	world.setBlockMetadataWithNotify(x, y+1, z, 1, 2);
		                        }
		                        if (l == 3) {;
		                        	world.setBlockMetadataWithNotify(x, y+1, z, 2, 2);
		                        }
		                        
		                    }
		                    world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), this.blockVendingCandy.stepSound.func_150496_b(), (this.blockVendingCandy.stepSound.getVolume() + 1.0F) / 2.0F, this.blockVendingCandy.stepSound.getPitch() * 0.8F);
		                    --stack.stackSize;
	                	}
	                }
	            }
	            return true;
	        }
		case 2:
			/*Candy Vending Machine without Stand*/
			Block block3 = world.getBlock(x, y, z);
			
	        if (block3 == Blocks.snow_layer && (world.getBlockMetadata(x, y, z) & 7) < 1) {
	            p_77648_7_ = 1;
	        }
	        else if (block3 != Blocks.vine && block3 != Blocks.tallgrass && block3 != Blocks.deadbush) {
	            if (p_77648_7_ == 0) {
	                --y;
	            }
	            if (p_77648_7_ == 1) {
	                ++y;
	            }
	            if (p_77648_7_ == 2) {
	                --z;
	            }
	            if (p_77648_7_ == 3) {
	                ++z;
	            }
	            if (p_77648_7_ == 4) {
	                --x;
	            }
	            if (p_77648_7_ == 5) {
	                ++x;
	            }
	        }
	        if (!player.canPlayerEdit(x, y, z, p_77648_7_, stack)) {
	            return false;
	        }
	        else if (stack.stackSize == 0) {
	            return false;
	        }
	        else {
	            if (world.canPlaceEntityOnSide(this.blockVendingCandy, x, y, z, false, p_77648_7_, (Entity)null, stack)) {
	                int i1 = this.blockVendingCandy.onBlockPlaced(world, x, y, z, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_, 0);
	                if (world.setBlock(x, y, z, this.blockVendingCandy, i1, 3)) {
	                	if (world.getBlock(x, y, z) == this.blockVendingCandy) {
		                    this.blockVendingCandy.onBlockPlacedBy(world, x, y, z, player, stack);
		                    this.blockVendingCandy.onPostBlockPlaced(world, x, y, z, i1);
		                    
		                    int l = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		                    if (l == 0) {
		                    	world.setBlockMetadataWithNotify(x, y, z, 0, 2);
		                    }
		                    if (l == 1) {
		                    	world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		                    }
		                    if (l == 2) {
		                    	world.setBlockMetadataWithNotify(x, y, z, 1, 2);
		                    }
		                    if (l == 3) {
		                    	world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		                    }
		                }
		                world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), this.blockVendingCandy.stepSound.func_150496_b(), (this.blockVendingCandy.stepSound.getVolume() + 1.0F) / 2.0F, this.blockVendingCandy.stepSound.getPitch() * 0.8F);
		                --stack.stackSize;
	                }
	            }
	            return true;
	        }
		default:
			return false;
		}
		
    }
}