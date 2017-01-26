package com.azias.vendingmachine.items;

import java.util.List;

import com.azias.vendingmachine.VendingMachineMod;
import com.azias.vendingmachine.blocks.VendingMachineBlocks;
import com.azias.vendingmachine.blocks.tileentities.TileEntityCandyMachine;
import com.azias.vendingmachine.blocks.tileentities.TileEntitySodaMachine;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemVendingMachine extends Item {

	//private Block blockVendingSoda = VendingMachineBlocks.sodaVendingMachine;
	private Block blockVendingCandy = VendingMachineBlocks.candyVendingMachine;
	private Block blockVendingStand = VendingMachineBlocks.vendingMachineStand;
	//private Block blockVendingFiller = VendingMachineBlocks.vendingMachineFiller;
	protected String name = "vendingMachine";
	protected final int maxMeta = 3;
    
    public ItemVendingMachine() {
        super();
        setHasSubtypes(true);
		setUnlocalizedName(VendingMachineMod.modID + "_" + this.name);
		setCreativeTab(VendingMachineMod.tabVendingMachines);
		GameRegistry.registerItem(this, this.name);
    }
	
	public String getName() {
		return this.name;
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
	
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();

        if (block == Blocks.snow_layer && ((Integer)iblockstate.getValue(BlockSnow.LAYERS)).intValue() < 1) {
            side = EnumFacing.UP;
        } else if (!block.isReplaceable(worldIn, pos)) {
            pos = pos.offset(side);
        }

        if (!playerIn.canPlayerEdit(pos, side, stack)) {
            return false;
        } else if (stack.stackSize == 0) {
            return false;
        } else {
            switch(stack.getMetadata()) {
            case 0:
    			//Soda Vending Machine
            	break;
            case 1:
    			//Candy Vending Machine with Stand
            	if (worldIn.canBlockBePlaced(this.blockVendingStand, pos, false, side, (Entity)null, stack)) {
                    IBlockState iblockstate1 = this.blockVendingStand.onBlockPlaced(worldIn, pos, side, hitX, hitY, hitZ, 0, playerIn);

                    if (worldIn.setBlockState(pos, iblockstate1, 3))  {
                        iblockstate1 = worldIn.getBlockState(pos);

                        if (iblockstate1.getBlock() == this.blockVendingStand) {
                            ItemBlock.setTileEntityNBT(worldIn, pos, stack, playerIn);
                            iblockstate1.getBlock().onBlockPlacedBy(worldIn, pos, iblockstate1, playerIn, stack);
                        }
                        worldIn.playSoundEffect((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), this.blockVendingStand.stepSound.getPlaceSound(), (this.blockVendingStand.stepSound.getVolume() + 1.0F) / 2.0F, this.blockVendingStand.stepSound.getFrequency() * 0.8F);
                        --stack.stackSize;
                    }
                }
            	BlockPos altPos = pos.up();
            	if (worldIn.canBlockBePlaced(this.blockVendingCandy, altPos, false, side, (Entity)null, stack)) {
                    IBlockState iblockstate1 = this.blockVendingCandy.onBlockPlaced(worldIn, altPos, side, hitX, hitY+1, hitZ, 0, playerIn);
                    
                    if (worldIn.setBlockState(altPos, iblockstate1))  {
                        iblockstate1 = worldIn.getBlockState(altPos);
                        
                        if (iblockstate1.getBlock() == this.blockVendingCandy) {
                            ItemBlock.setTileEntityNBT(worldIn, altPos, stack, playerIn);
                            iblockstate1.getBlock().onBlockPlacedBy(worldIn, altPos, iblockstate1, playerIn, stack);
                        }
                        worldIn.playSoundEffect((double)((float)altPos.getX() + 0.5F), (double)((float)altPos.getY() + 0.5F), (double)((float)altPos.getZ() + 0.5F), this.blockVendingStand.stepSound.getPlaceSound(), (this.blockVendingStand.stepSound.getVolume() + 1.0F) / 2.0F, this.blockVendingStand.stepSound.getFrequency() * 0.8F);
                        --stack.stackSize;
                        return true;
                    }
                }
            	break;
            case 2:
    			//Candy Vending Machine without Stand
            	if (worldIn.canBlockBePlaced(this.blockVendingCandy, pos, false, side, (Entity)null, stack)) {
                    IBlockState iblockstate1 = this.blockVendingCandy.onBlockPlaced(worldIn, pos, side, hitX, hitY, hitZ, 0, playerIn);

                    if (worldIn.setBlockState(pos, iblockstate1, 3))  {
                        iblockstate1 = worldIn.getBlockState(pos);

                        if (iblockstate1.getBlock() == this.blockVendingCandy) {
                            ItemBlock.setTileEntityNBT(worldIn, pos, stack, playerIn);
                            iblockstate1.getBlock().onBlockPlacedBy(worldIn, pos, iblockstate1, playerIn, stack);
                        }
                        worldIn.playSoundEffect((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), this.blockVendingStand.stepSound.getPlaceSound(), (this.blockVendingStand.stepSound.getVolume() + 1.0F) / 2.0F, this.blockVendingStand.stepSound.getFrequency() * 0.8F);
                        --stack.stackSize;
                        return true;
                    }
                }
            	break;
            default:
            	return false;
            }

            return false;
        }
    }
	
	/*@Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
		switch(stack.getItemDamage()) {
		case 0:
			//Soda Vending Machine
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
	            if(world.canPlaceEntityOnSide(this.blockVendingSoda, x, y, z, false, p_77648_7_, (Entity)null, stack)) {
	                int i1 = this.blockVendingSoda.onBlockPlaced(world, x, y, z, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_, 0);
	
	                if(world.setBlock(x, y, z, this.blockVendingSoda, i1, 3)) {
	                	if(world.setBlock(x, y+1, z, this.blockVendingFiller, i1, 3)) {
		                    if(world.getBlock(x, y, z) == this.blockVendingSoda && world.getBlock(x, y+1, z) == this.blockVendingFiller) {
		                        this.blockVendingSoda.onBlockPlacedBy(world, x, y, z, player, stack);
		                        this.blockVendingSoda.onPostBlockPlaced(world, x, y, z, i1);
		                        this.blockVendingFiller.onBlockPlacedBy(world, x, y+1, z, player, stack);
		                        this.blockVendingFiller.onPostBlockPlaced(world, x, y+1, z, i1);
		                        
		                        int rotation = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		                        
		                        TileEntity te = world.getTileEntity(x, y, z);
		    			        if (te != null && te instanceof TileEntitySodaMachine) {
		    			        	TileEntitySodaMachine tec = (TileEntitySodaMachine) te;
		    			        	tec.setFacing((byte)rotation);
		    			            world.markBlockForUpdate(x, y, z);
		    			        } else {
		    			        	System.out.println("Error: TileEntity don't exist !");
		    			        }
		                        world.setBlockMetadataWithNotify(x, y+1, z, 0, 2);
		                        
		                    }
		                    world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), this.blockVendingSoda.stepSound.func_150496_b(), (this.blockVendingSoda.stepSound.getVolume() + 1.0F) / 2.0F, this.blockVendingSoda.stepSound.getPitch() * 0.8F);
		                    --stack.stackSize;
	                	}
	                }
	            }
	            return true;
	        }
		case 1:
			null
		case 2:
			null
		default:
			return false;
		}
		
    }*/
}