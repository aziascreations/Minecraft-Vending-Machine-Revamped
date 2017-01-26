package com.azias.vendingmachine;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameData;

public class Utils {
	public static Item getItem(int itemId) {
		return GameData.getItemRegistry().getObjectById(itemId);
	}

	public static int getItemId(Item item){
		return GameData.getItemRegistry().getId(item);
	}
	
	public EntityItem createEntityItem(World worldIn, BlockPos pos, ItemStack itemStack) {
		return new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemStack);
	}
}
