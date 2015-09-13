package com.azias.vendingmachine.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import com.azias.vendingmachine.VendingMachineMod;
import com.azias.vendingmachine.blocks.tileentities.TileEntitySodaMachine;
import com.azias.vendingmachine.models.ModelSodaMachine;

public class TileEntitySodaMachineRenderer extends TileEntitySpecialRenderer {
	protected ModelSodaMachine model;
	protected ResourceLocation textureLocation;

	public TileEntitySodaMachineRenderer() {
		this.model = new ModelSodaMachine();
		this.textureLocation = new ResourceLocation(VendingMachineMod.modID+":textures/models/sodamachine.png");
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float p_147500_8_) {
		TileEntitySodaMachine tileEntityMachine = (TileEntitySodaMachine) tileEntity;
		
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		
		//Use this to customize skin ?
		/*if (tileEntityOven.getBurnTime() > 0)
			Minecraft.getMinecraft().renderEngine.bindTexture(activeTexture);
		else*/
		Minecraft.getMinecraft().renderEngine.bindTexture(textureLocation);
		
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		GL11.glPushMatrix();
		
		int metadata;
		metadata = tileEntityMachine.getFacing();
		
		GL11.glRotatef(metadata * (90), 0F, 1F, 0F);
		
		this.model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		
		GL11.glPopMatrix();
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
}
