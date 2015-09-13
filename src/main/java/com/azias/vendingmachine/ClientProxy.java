package com.azias.vendingmachine;

import com.azias.vendingmachine.blocks.tileentities.*;
import com.azias.vendingmachine.renderers.*;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySodaMachine.class, new TileEntitySodaMachineRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCandyMachine.class, new TileEntityCandyMachineRenderer());
	}
}
