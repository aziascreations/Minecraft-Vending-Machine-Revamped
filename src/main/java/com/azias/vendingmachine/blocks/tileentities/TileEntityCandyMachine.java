package com.azias.vendingmachine.blocks.tileentities;

import com.azias.vendingmachine.items.VendingMachineItems;

import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TileEntityCandyMachine extends TileEntity {
	private String ownerName = "";
	public byte facing;
	public boolean isInfinite = true;
	public boolean isCustom = false;

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setString("owner", this.ownerName);
		nbtTagCompound.setByte("facing", this.facing);
		nbtTagCompound.setBoolean("infinite", this.isInfinite);
		nbtTagCompound.setBoolean("custom", this.isCustom);
		return nbtTagCompound;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
		this.ownerName = nbtTagCompound.getString("owner");
		this.facing = nbtTagCompound.getByte("facing");
		this.isInfinite = nbtTagCompound.getBoolean("infinite");
		this.isCustom = nbtTagCompound.getBoolean("custom");
		//Used to create correct values for new NBTTags
		if(this.ownerName==""||this.ownerName==null||this.ownerName.length()<=0) {
			this.ownerName = "null";
			this.isInfinite = true;
			this.isCustom = false;
		}
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound compound = new NBTTagCompound();
        this.writeToNBT(compound);
		return new SPacketUpdateTileEntity(pos, 1, compound);//1 changed from 2
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.getNbtCompound());
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
    
    public byte getFacing() {
        return this.facing;
    }

	public void setFacing(EnumFacing par1) {
		switch(par1) {
		case NORTH:
			this.setFacing((byte)0);
			break;
		case EAST:
			this.setFacing((byte)1);
			break;
		case SOUTH:
			this.setFacing((byte)2);
			break;
		case WEST:
			this.setFacing((byte)3);
			break;
		default:
			this.setFacing((byte)0);
			break;
		}
	}
    
	public void setFacing(byte par1) {
        this.facing = par1;
        System.out.println("Setting rotation at: "+par1);
    }
	
	public void setOwner(String par1) {
		this.ownerName = par1;
	}
	
	public String getOwner() {
		return this.ownerName;
	}
	
	public boolean isOwner(String par1) {
		return this.ownerName == par1;
	}
}
