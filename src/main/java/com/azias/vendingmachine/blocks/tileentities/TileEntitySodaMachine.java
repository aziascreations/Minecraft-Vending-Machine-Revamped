package com.azias.vendingmachine.blocks.tileentities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.azias.vendingmachine.items.VendingMachineItems;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntitySodaMachine extends TileEntity {
	//public List<String> playerHistory = new ArrayList<String>();
	public byte facing;
	
	public void addPlayer(String playerName) {
		//playerHistory.add(playerName);
	}

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

    public ItemStack getSurvivalLoot(int coinTier) {
    	switch(coinTier) {
		case 0: //Clay coin
			int rand1 = new Random().nextInt(100);
			if(rand1<=44) {
				return null;
			} else if(rand1>=45&&rand1<=74) {
				int[] a = {2,3,4};
				return new ItemStack(VendingMachineItems.sodaBottle, 1, a[new Random().nextInt(3)]);
			} else if(rand1>=75&&rand1<=94) {
				return new ItemStack(VendingMachineItems.sodaBottle, 1, 0);
			} else {
				return new ItemStack(VendingMachineItems.sodaBottle, 1, 12);
			}
		case 1: //Iron coin
			int rand2 = new Random().nextInt(100);
			if(rand2<=84) {
				int[] a = {1,6,7,9,10,12};
				return new ItemStack(VendingMachineItems.sodaBottle, 1, a[new Random().nextInt(a.length)]);
			} else if(rand2>=85&&rand2<=89) {
				int[] a = {5,8,11};
				return new ItemStack(VendingMachineItems.sodaBottle, 1, a[new Random().nextInt(a.length)]);
			} else if(rand2>=90&&rand2<=96) {
				int[] a = {0,2,3,4};
				return new ItemStack(VendingMachineItems.sodaBottle, 1, a[new Random().nextInt(a.length)]);
			} else {
				return null;
			}
		case 2: //Gold coin
			int rand3 = new Random().nextInt(100);
			if(rand3<=69) {
				int[] a = {1,6,7,9,10,12};
				return new ItemStack(VendingMachineItems.sodaBottle, 1, a[new Random().nextInt(a.length)]);
			} else if(rand3>=70&&rand3<=89) {
				int[] a = {5,8,11};
				return new ItemStack(VendingMachineItems.sodaBottle, 1, a[new Random().nextInt(a.length)]);
			} else if(rand3>=90&&rand3<=96) {
				int[] a = {0,2,3,4};
				return new ItemStack(VendingMachineItems.sodaBottle, 1, a[new Random().nextInt(a.length)]);
			} else {
				return null;
			}
		default:
	    	return null;
    	}
    }

	public ItemStack getSurvivalCoin(int coinTier) {
		int rand = new Random().nextInt(100);
		if(rand==69||rand==42) {
			return new ItemStack(VendingMachineItems.coin, 1, coinTier);
		} else {
			return null;
		}
	}

    public ItemStack getCreativeLoot() {
		int rand = new Random().nextInt(13);
		return new ItemStack(VendingMachineItems.sodaBottle, 1, rand);
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
}
