package com.azias.vendingmachine.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiImageButton extends GuiButton
{
    protected static ResourceLocation textures;
    public int xOffset;
    public int yOffset;

    public GuiImageButton(int id, String modid, String ressourceLocation, int x, int y, int width, int height, int offsetHorizontal, int offsetVertical)
    {
    	super(id, x, y, width, height, "");
        this.textures = new ResourceLocation(modid, ressourceLocation);
        this.xOffset = offsetHorizontal;
        this.yOffset = offsetVertical;
    }

    @Override
    public void drawButton(Minecraft mc, int p_146112_2_, int p_146112_3_)
    {
        if (this.visible)
        {
            FontRenderer fontrenderer = mc.fontRendererObj;
            mc.getTextureManager().bindTexture(this.textures);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = p_146112_2_ >= this.xPosition && p_146112_3_ >= this.yPosition && p_146112_2_ < this.xPosition + this.width && p_146112_3_ < this.yPosition + this.height;
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, this.xOffset, this.yOffset, this.width, this.height);
            this.mouseDragged(mc, p_146112_2_, p_146112_3_);
       }
    }
}