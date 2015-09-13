package com.azias.vendingmachine.blocks.tileentities;

import com.azias.vendingmachine.items.VendingMachineItems;

import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCandyMachine extends TileEntity {
	public byte facing;

    @Override
    public void updateEntity() {
    	
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setByte("facing", (byte)this.getFacing());
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
		this.setFacing(nbtTagCompound.getByte("facing"));
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound compound = new NBTTagCompound();
        this.writeToNBT(compound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 2, compound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        this.readFromNBT(pkt.func_148857_g());
    }
    
    public byte getFacing()
    {
        return this.facing;
    }
    
	public void setFacing(byte par1) {
        this.facing = par1;
    }

	public ItemStack getSurvivalCoin(int coinTier) {
		int rand = new Random().nextInt(100);
		if(rand==69||rand==42) {
			return new ItemStack(VendingMachineItems.coin, 1, coinTier);
		} else {
			return null;
		}
	}
	
	public ItemStack getSurvivalLoot(int coinTier) {
		switch(coinTier) {
		case 0:
			int rand1 = new Random().nextInt(100);
			if(rand1<=64) {
				return null;
			} else if(rand1>=65&&rand1<=99) {
				int[] a = {0,1,2,3,4,5,6};
				return new ItemStack(VendingMachineItems.candy, 1, a[new Random().nextInt(a.length)]);
			}
		case 1:
			int rand2 = new Random().nextInt(100);
			if(rand2<=84) {
				int[] a = {0,1,2,3,4,5,6};
				return new ItemStack(VendingMachineItems.candy, 1, a[new Random().nextInt(a.length)]);
			} else if(rand2>=85&&rand2<=96) {
				int[] a = {7,8};
				return new ItemStack(VendingMachineItems.candy, 1, a[new Random().nextInt(a.length)]);
			} else {
				return null;
			}
		case 2:
			int rand3 = new Random().nextInt(100);
			if(rand3<=74) {
				int[] a = {0,1,2,3,4,5,6};
				return new ItemStack(VendingMachineItems.candy, 1, a[new Random().nextInt(a.length)]);
			} else if(rand3>=75&&rand3<=96) {
				int[] a = {7,8};
				return new ItemStack(VendingMachineItems.candy, 1, a[new Random().nextInt(a.length)]);
			} else {
				return null;
			}
		default:
	    	return null;
		}
	}

    public ItemStack getCreativeLoot() {
		int rand = new Random().nextInt(9);
		return new ItemStack(VendingMachineItems.candy, 1, rand);
    }
}
