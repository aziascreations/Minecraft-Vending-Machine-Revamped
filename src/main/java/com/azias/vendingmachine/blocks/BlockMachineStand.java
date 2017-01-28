package com.azias.vendingmachine.blocks;

import java.util.List;
import java.util.Random;

import com.azias.vendingmachine.VendingMachineMod;
import com.azias.vendingmachine.items.VendingMachineItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMachineStand extends Block {
	protected final String name = "vendingmachinestand";
    protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
    
	protected BlockMachineStand() {
		super(Material.IRON);
		setHardness(1.5F);
		setResistance(10.0F);
		setUnlocalizedName(this.name);
        setRegistryName(new ResourceLocation(VendingMachineMod.modID, this.name));
		GameRegistry.register(this);
	}

	@Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
	
	@Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

	@Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return AABB;
    }

	@Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
        return AABB.offset(pos);
    }
}
