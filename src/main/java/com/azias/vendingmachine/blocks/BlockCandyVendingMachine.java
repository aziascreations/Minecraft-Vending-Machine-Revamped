package com.azias.vendingmachine.blocks;

import java.util.Random;

import com.azias.vendingmachine.VendingMachineMod;
import com.azias.vendingmachine.blocks.tileentities.TileEntityCandyMachine;
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

public class BlockCandyVendingMachine extends BlockContainer {

	@SideOnly(Side.CLIENT)
	protected IIcon blockIcon;

	protected BlockCandyVendingMachine(String name) {
		super(Material.iron);
		this.setBlockName(VendingMachineMod.modID + "_" + name);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
		this.setCreativeTab(VendingMachineMod.tabVendingMachines);
		
		GameRegistry.registerBlock(this, this.getUnlocalizedName());
	}
	
	@Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack placed) {
		int facing = MathHelper.floor_double((double) ((placer.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		
		TileEntity te = world.getTileEntity(x, y, z);
        if (te != null && te instanceof TileEntityCandyMachine) {
        	TileEntityCandyMachine tec = (TileEntityCandyMachine) te;
        	tec.setFacing((byte)facing);
            world.markBlockForUpdate(x, y, z);
        }
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
					ItemStack candy = null;
					ItemStack coin = null;
					
					TileEntity te = world.getTileEntity(x, y, z);
			        if (te != null && te instanceof TileEntityCandyMachine) {
			        	TileEntityCandyMachine tec = (TileEntityCandyMachine) te;
			        	rotation = tec.getFacing();
			        	if(isCreative) {
			        		candy = tec.getCreativeLoot();
			        	} else {
			        		coinTier = player.inventory.getCurrentItem().getItemDamage();
			        		candy = tec.getSurvivalLoot(coinTier);
			        		coin = tec.getSurvivalCoin(coinTier);
			        	}
			        }
			        
			        if(candy!=null) {
			        	EntityItem dropSoda;
						switch(rotation) {
						case 0:
							dropSoda = new EntityItem(world, x+0.5, y+0.5, z-0.5, candy);
							dropSoda.motionX=0.0D;
							dropSoda.motionZ=-0.125D;
							break;
						case 2:
							dropSoda = new EntityItem(world, x+0.5, y+0.5, z+1.5, candy);
							dropSoda.motionX=0.0D;
							dropSoda.motionZ=0.125D;
			        		break;
						case 3:
							dropSoda = new EntityItem(world, x-0.5, y+0.5, z+0.5, candy);
							dropSoda.motionX=-0.125D;
							dropSoda.motionZ=0.0D;
			        		break;
						case 1:
							dropSoda = new EntityItem(world, x+1.5, y+0.5, z+0.5, candy);
							dropSoda.motionX=0.125D;
							dropSoda.motionZ=0.0D;
			        		break;
			        	default:
			        		dropSoda = new EntityItem(world, x+0.5, y+3.5, z+0.5, candy);
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
    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
        par1World.removeTileEntity(par2, par3, par4);
    }

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TileEntityCandyMachine();
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
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(VendingMachineMod.modID + ":" + this.getUnlocalizedName().substring(5));
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int metadata) {
		return blockIcon;
	}

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return new ItemStack(VendingMachineItems.vendingMachine, 1, 1).getItem();
    }
	
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return new ItemStack(VendingMachineItems.vendingMachine, 1, 1).getItem();
    }
    
    @Override
    public int damageDropped(int metadata) {
        return 2;
    }
}
