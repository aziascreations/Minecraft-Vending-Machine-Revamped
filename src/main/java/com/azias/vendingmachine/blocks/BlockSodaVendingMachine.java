package com.azias.vendingmachine.blocks;

import java.util.Random;

import com.azias.vendingmachine.VendingMachineMod;
import com.azias.vendingmachine.blocks.tileentities.TileEntitySodaMachine;
import com.azias.vendingmachine.items.VendingMachineItems;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockSodaVendingMachine extends BlockContainer {

	@SideOnly(Side.CLIENT)
	protected IIcon blockIcon;

	protected BlockSodaVendingMachine(String name) {
		super(Material.iron);
		this.setBlockName(VendingMachineMod.modID + "_" + name);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
		GameRegistry.registerBlock(this, this.getUnlocalizedName());
	}
	
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (!this.canBlockStay(world, x, y, z)) {
        	world.setBlockToAir(x, y, z);
        }
    }
    
    public boolean canBlockStay(World world, int x, int y, int z) {
    	Block block = VendingMachineBlocks.vendingMachineFiller;
    	return (world.getBlock(x, y+1, z) == block);
    }
	
	@Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack placed) {
		int facing = MathHelper.floor_double((double) ((placer.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		
		TileEntity te = world.getTileEntity(x, y, z);
        if (te != null && te instanceof TileEntitySodaMachine) {
        	TileEntitySodaMachine tec = (TileEntitySodaMachine) te;
        	tec.setFacing((byte)facing);
            world.markBlockForUpdate(x, y, z);
        }
    }
    
    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
        par1World.removeTileEntity(par2, par3, par4);
    }
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		if (world.isRemote) {
            return true;
        } else {
			boolean isCreative = player.capabilities.isCreativeMode;
			
			if(player.inventory.getCurrentItem() != null||isCreative) {
				if(isCreative||player.inventory.getCurrentItem().getItem().getUnlocalizedName().equals(VendingMachineItems.coin.getUnlocalizedName())) {
					int coinTier;
					int rotation = -1;
					ItemStack soda = null;
					ItemStack coin = null;
					
					TileEntity te = world.getTileEntity(x, y, z);
			        if (te != null && te instanceof TileEntitySodaMachine) {
			        	TileEntitySodaMachine tec = (TileEntitySodaMachine) te;
			        	rotation = tec.getFacing();
			        	if(isCreative) {
			        		soda = tec.getCreativeLoot();
			        	} else {
			        		coinTier = player.inventory.getCurrentItem().getItemDamage();
			        		soda = tec.getSurvivalLoot(coinTier);
			        		coin = tec.getSurvivalCoin(coinTier);
			        	}
			        }
			        
			        if(soda!=null) {
			        	EntityItem dropSoda;
						switch(rotation) {
						case 0:
							dropSoda = new EntityItem(world, x+0.5, y+0.5, z-0.5, soda);
							dropSoda.motionX=0.0D;
							dropSoda.motionZ=-0.125D;
							break;
						case 2:
							dropSoda = new EntityItem(world, x+0.5, y+0.5, z+1.5, soda);
							dropSoda.motionX=0.0D;
							dropSoda.motionZ=0.125D;
			        		break;
						case 3:
							dropSoda = new EntityItem(world, x-0.5, y+0.5, z+0.5, soda);
							dropSoda.motionX=-0.125D;
							dropSoda.motionZ=0.0D;
			        		break;
						case 1:
							dropSoda = new EntityItem(world, x+1.5, y+0.5, z+0.5, soda);
							dropSoda.motionX=0.125D;
							dropSoda.motionZ=0.0D;
			        		break;
			        	default:
			        		dropSoda = new EntityItem(world, x+0.5, y+3.5, z+0.5, soda);
			        		break;
						}
						dropSoda.motionY=0.0D;
						world.spawnEntityInWorld(dropSoda);
			        }
			        
			        if(coin!=null) {
			        	EntityItem dropCoin;
						switch(rotation) {
						case 0:
							dropCoin = new EntityItem(world, x+0.5, y+0.5, z-0.5, coin);
							dropCoin.motionX=0.0D;
							dropCoin.motionZ=-0.125D;
							break;
						case 2:
							dropCoin = new EntityItem(world, x+0.5, y+0.5, z+1.5, coin);
							dropCoin.motionX=0.0D;
							dropCoin.motionZ=0.125D;
			        		break;
						case 3:
							dropCoin = new EntityItem(world, x-0.5, y+0.5, z+0.5, coin);
							dropCoin.motionX=-0.125D;
							dropCoin.motionZ=0.0D;
			        		break;
						case 1:
							dropCoin = new EntityItem(world, x+1.5, y+0.5, z+0.5, coin);
							dropCoin.motionX=0.125D;
							dropCoin.motionZ=0.0D;
			        		break;
			        	default:
			        		dropCoin = new EntityItem(world, x+0.5, y+3.5, z+0.5, coin);
			        		break;
						}
						dropCoin.motionY=0.0D;
						world.spawnEntityInWorld(dropCoin);
			        }
					
					if(!isCreative) {
						player.inventory.getCurrentItem().stackSize--;
					}
			        return true;
				}
			}
        }
		return false;
    }

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TileEntitySodaMachine();
	}
	
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return -1;
    }
	
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return VendingMachineItems.vendingMachine;
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return VendingMachineItems.vendingMachine;
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
}
