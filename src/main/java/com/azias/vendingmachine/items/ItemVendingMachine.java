package com.azias.vendingmachine.items;

import java.util.List;

import javax.annotation.Nullable;

import com.azias.vendingmachine.VendingMachineMod;
import com.azias.vendingmachine.blocks.VendingMachineBlocks;
import com.azias.vendingmachine.blocks.tileentities.TileEntityCandyMachine;
import com.azias.vendingmachine.blocks.tileentities.TileEntitySodaMachine;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemVendingMachine extends Item {

	////private Block blockVendingSoda = VendingMachineBlocks.sodaVendingMachine;
	//private Block blockVendingCandy = VendingMachineBlocks.candyVendingMachine;
	//private Block blockVendingStand = VendingMachineBlocks.vendingMachineStand;
	////private Block blockVendingFiller = VendingMachineBlocks.vendingMachineFiller;

    public final Block block = VendingMachineBlocks.vendingMachineStand;
	
	protected static final int maxMeta = 3;
	
	protected String name = "vendingMachine";
    
    public ItemVendingMachine() {
        super();
        setHasSubtypes(true);
		setUnlocalizedName(VendingMachineMod.modID + "_" + this.name);
        setRegistryName(new ResourceLocation(VendingMachineMod.modID, this.name));
		setCreativeTab(VendingMachineMod.tabVendingMachines);
		GameRegistry.register(this);
    }
	
	public String getName() {
		return this.name;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
	    for (int i = 0; i < maxMeta; i ++) {
	    	subItems.add(new ItemStack(itemIn, 1, i));
	    }
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
	    return this.getUnlocalizedName() + "_" + stack.getItemDamage();
	}

    public EnumActionResult onItemUse(EntityPlayer stack, World playerIn, BlockPos worldIn, EnumHand pos, EnumFacing hand, float facing, float hitX, float hitY) {
        IBlockState iblockstate = playerIn.getBlockState(worldIn);
        Block block = iblockstate.getBlock();

        if (!block.isReplaceable(playerIn, worldIn)) {
            worldIn = worldIn.offset(hand);
        }

        ItemStack itemstack = stack.getHeldItem(pos);

        if (!itemstack.func_190926_b() && stack.canPlayerEdit(worldIn, hand, itemstack) && playerIn.func_190527_a(this.block, worldIn, false, hand, (Entity)null)) {
            int i = this.getMetadata(itemstack.getMetadata());
            IBlockState iblockstate1 = this.block.getStateForPlacement(playerIn, worldIn, hand, facing, hitX, hitY, i, stack, pos);

            if (placeBlockAt(itemstack, stack, playerIn, worldIn, hand, facing, hitX, hitY, iblockstate1)) {
                SoundType soundtype = playerIn.getBlockState(worldIn).getBlock().getSoundType(playerIn.getBlockState(worldIn), playerIn, worldIn, stack);
                playerIn.playSound(stack, worldIn, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                itemstack.func_190918_g(1);
            }

            return EnumActionResult.SUCCESS;
        } else {
            return EnumActionResult.FAIL;
        }
    }

    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState) {
        if (!world.setBlockState(pos, newState, 11)) return false;

        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() == this.block) {
            setTileEntityNBT(world, player, pos, stack);
            this.block.onBlockPlacedBy(world, pos, state, player, stack);
        }

        return true;
    }

    public static boolean setTileEntityNBT(World worldIn, @Nullable EntityPlayer player, BlockPos pos, ItemStack stackIn) {
        MinecraftServer minecraftserver = worldIn.getMinecraftServer();

        if (minecraftserver == null) {
            return false;
        } else {
            NBTTagCompound nbttagcompound = stackIn.getSubCompound("BlockEntityTag");

            if (nbttagcompound != null) {
                TileEntity tileentity = worldIn.getTileEntity(pos);

                if (tileentity != null) {
                    if (!worldIn.isRemote && tileentity.onlyOpsCanSetNbt() && (player == null || !player.canUseCommandBlock())) {
                        return false;
                    }

                    NBTTagCompound nbttagcompound1 = tileentity.writeToNBT(new NBTTagCompound());
                    NBTTagCompound nbttagcompound2 = nbttagcompound1.copy();
                    nbttagcompound1.merge(nbttagcompound);
                    nbttagcompound1.setInteger("x", pos.getX());
                    nbttagcompound1.setInteger("y", pos.getY());
                    nbttagcompound1.setInteger("z", pos.getZ());

                    if (!nbttagcompound1.equals(nbttagcompound2)) {
                        tileentity.readFromNBT(nbttagcompound1);
                        tileentity.markDirty();
                        return true;
                    }
                }
            }

            return false;
        }
    }
    
	/*public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
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
    }/**/
	
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