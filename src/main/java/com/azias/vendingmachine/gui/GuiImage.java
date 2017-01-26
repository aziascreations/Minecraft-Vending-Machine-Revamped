package com.azias.vendingmachine.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiImage extends GuiButton {
    protected static ResourceLocation buttonTextures;
    public int width;
    public int height;
    public int xPosition;
    public int yPosition;
    public int xOffset;
    public int yOffset;
    public boolean visible;
    protected boolean field_146123_n;
    private static final String __OBFID = "CL_00000668";
    public int packedFGColour;

    public GuiImage(String modid, String ressourceLocation, int x, int y, int width, int height, int offsetHorizontal, int offsetVertical) {
        super(-1,0,0,0,0,"");
        this.buttonTextures = new ResourceLocation(modid, ressourceLocation);
    	this.width = 200;
        this.height = 20;
        this.visible = true;
        this.xPosition = x;
        this.yPosition = y;
        this.width = width;
        this.height = height;
        this.xOffset = offsetHorizontal;
        this.yOffset = offsetVertical;
    }

	@Override
    public void drawButton(Minecraft mc, int p_146112_2_, int p_146112_3_)
    {
        if (this.visible)
        {
            FontRenderer fontrenderer = mc.fontRendererObj;
            mc.getTextureManager().bindTexture(buttonTextures);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.field_146123_n = p_146112_2_ >= this.xPosition && p_146112_3_ >= this.yPosition && p_146112_2_ < this.xPosition + this.width && p_146112_3_ < this.yPosition + this.height;
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, this.xOffset, this.yOffset, this.width, this.height);
        }
    }

    public int getWidth()
    {
        return this.width;
    }

    public int getHeight()
    {
        return this.height;
    }
}