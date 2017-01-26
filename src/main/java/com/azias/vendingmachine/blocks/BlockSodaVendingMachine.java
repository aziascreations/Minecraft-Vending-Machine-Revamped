package com.azias.vendingmachine.blocks;

import java.util.Random;

import com.azias.vendingmachine.VendingMachineMod;
import com.azias.vendingmachine.blocks.tileentities.TileEntitySodaMachine;
import com.azias.vendingmachine.items.VendingMachineItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSodaVendingMachine extends BlockContainer {
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	protected String name = "sodaVendingMachine";

	protected BlockSodaVendingMachine() {
		super(Material.iron);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
		this.setUnlocalizedName(this.name);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setCreativeTab(VendingMachineMod.tabVendingMachines);
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
	
	public int getRenderType() {
		return 3;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntitySodaMachine();
	}

	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
		
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (tileentity instanceof TileEntitySodaMachine) {
			((TileEntitySodaMachine)tileentity).setOwner(placer.getName());
			((TileEntitySodaMachine)tileentity).setFacing(placer.getHorizontalFacing().getOpposite());
		}
	}
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) {
			return true;
		} else {
			TileEntity tileentity = worldIn.getTileEntity(pos);
			
			if (tileentity instanceof TileEntitySodaMachine) {
				TileEntitySodaMachine te = (TileEntitySodaMachine)tileentity;
				boolean isCreative = playerIn.capabilities.isCreativeMode;
				
				if(playerIn.inventory.getCurrentItem() != null||isCreative) {
					if(isCreative||playerIn.inventory.getCurrentItem().getItem().getUnlocalizedName().equals(VendingMachineItems.coin.getUnlocalizedName())) {
						int coinTier;
						int rotation = -1;
						ItemStack soda = null;
						ItemStack coin = null;
						
						rotation = te.getFacing();
						
						if(isCreative) {
							soda = te.getCreativeLoot();
						} else {
							coinTier = playerIn.inventory.getCurrentItem().getItemDamage();
							soda = te.getSurvivalLoot(coinTier);
							coin = te.getSurvivalCoin(coinTier);
						}
						
						if(soda!=null) {
							EntityItem dropSoda;
							switch(rotation) {
							case 0:
								dropSoda = new EntityItem(worldIn, pos.getX()+0.5, pos.getY()+0.5, pos.getZ()-0.5, soda);
								dropSoda.motionX=0.0D;
								dropSoda.motionZ=-0.125D;
								break;
							case 2:
								dropSoda = new EntityItem(worldIn, pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+1.5, soda);
								dropSoda.motionX=0.0D;
								dropSoda.motionZ=0.125D;
								break;
							case 3:
								dropSoda = new EntityItem(worldIn, pos.getX()-0.5, pos.getY()+0.5, pos.getZ()+0.5, soda);
								dropSoda.motionX=-0.125D;
								dropSoda.motionZ=0.0D;
								break;
							case 1:
								dropSoda = new EntityItem(worldIn, pos.getX()+1.5, pos.getY()+0.5, pos.getZ()+0.5, soda);
								dropSoda.motionX=0.125D;
								dropSoda.motionZ=0.0D;
								break;
							default:
								dropSoda = new EntityItem(worldIn, pos.getX()+0.5, pos.getY()+3.5, pos.getZ()+0.5, soda);
								break;
							}
							dropSoda.motionY=0.0D;
							worldIn.spawnEntityInWorld(dropSoda);
						}
						if(coin!=null) {
							EntityItem dropCoin;
							switch(rotation) {
							case 0:
								dropCoin = new EntityItem(worldIn, pos.getX()+0.5, pos.getY()+0.5, pos.getZ()-0.5, coin);
								dropCoin.motionX=0.0D;
								dropCoin.motionZ=-0.125D;
								break;
							case 2:
								dropCoin = new EntityItem(worldIn, pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+1.5, coin);
								dropCoin.motionX=0.0D;
								dropCoin.motionZ=0.125D;
								break;
							case 3:
								dropCoin = new EntityItem(worldIn, pos.getX()-0.5, pos.getY()+0.5, pos.getZ()+0.5, coin);
								dropCoin.motionX=-0.125D;
								dropCoin.motionZ=0.0D;
								break;
							case 1:
								dropCoin = new EntityItem(worldIn, pos.getX()+1.5, pos.getY()+0.5, pos.getZ()+0.5, coin);
								dropCoin.motionX=0.125D;
								dropCoin.motionZ=0.0D;
								break;
							default:
								dropCoin = new EntityItem(worldIn, pos.getX()+0.5, pos.getY()+3.5, pos.getZ()+0.5, coin);
								break;
							}
							dropCoin.motionY=0.0D;
							worldIn.spawnEntityInWorld(dropCoin);
						}
						
						if(!isCreative) {
							playerIn.inventory.getCurrentItem().stackSize--;
						}
						return true;
					}
				}
			}
			return false;
		}
	}
	
	
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		super.breakBlock(worldIn, pos, state);
	}

	private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state) {
		if (!worldIn.isRemote) {
			Block block = worldIn.getBlockState(pos.north()).getBlock();
			Block block1 = worldIn.getBlockState(pos.south()).getBlock();
			Block block2 = worldIn.getBlockState(pos.west()).getBlock();
			Block block3 = worldIn.getBlockState(pos.east()).getBlock();
			EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

			if (enumfacing == EnumFacing.NORTH && block.isFullBlock() && !block1.isFullBlock()) {
				enumfacing = EnumFacing.SOUTH;
			} else if (enumfacing == EnumFacing.SOUTH && block1.isFullBlock() && !block.isFullBlock()) {
				enumfacing = EnumFacing.NORTH;
			} else if (enumfacing == EnumFacing.WEST && block2.isFullBlock() && !block3.isFullBlock()) {
				enumfacing = EnumFacing.EAST;
			} else if (enumfacing == EnumFacing.EAST && block3.isFullBlock() && !block2.isFullBlock()) {
				enumfacing = EnumFacing.WEST;
			}
			worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
		}
	}

	public void setState(boolean active, World worldIn, BlockPos pos) {
		IBlockState iblockstate = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (active) {
			worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
			worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
		} else {
			worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
			worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
		}
		if (tileentity != null) {
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}

	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}
	
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getFront(meta);
		if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
			enumfacing = EnumFacing.NORTH;
		}
		return this.getDefaultState().withProperty(FACING, enumfacing);
	}
	
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing)state.getValue(FACING)).getIndex();
	}

	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {FACING});
	}
	
	/*public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		if (!this.canBlockStay(world, x, y, z)) {
			world.setBlockToAir(x, y, z);
		}
	}
	
	public boolean canBlockStay(World world, int x, int y, int z) {
		Block block = VendingMachineBlocks.vendingMachineFiller;
		return (world.getBlock(x, y+1, z) == block);
	}
	
	/*@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z) {
		return VendingMachineItems.vendingMachine;
	}*/

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return VendingMachineItems.vendingMachine;
	}
}
