package com.azias.vendingmachine.gui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.azias.vendingmachine.VendingMachineMod;
import com.azias.vendingmachine.items.VendingMachineItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiBookGuide extends GuiScreen {
    private final int bookImageHeight = 192;
    private final int bookImageWidth = 192;
    private int currPage = 0;
	public static final int id = 30;
    private static final int bookTotalPages = 9;
    private static ResourceLocation[] bookPageTextures = new ResourceLocation[5];
    private static int[] pageType = {0,1,2,3,3,4,4,4,4};
    private static String[] stringPageText = new String[bookTotalPages];
    private GuiButton buttonDone;
    private NextPageButton buttonNextPage;
    private NextPageButton buttonPreviousPage;
    protected static final RenderItem itemRenderer = new RenderItem();
    
    public GuiBookGuide() {
        bookPageTextures[0] = new ResourceLocation(VendingMachineMod.modID+":textures/gui/book_cover.png");
        bookPageTextures[1] = new ResourceLocation(VendingMachineMod.modID+":textures/gui/book_main.png");
        bookPageTextures[2] = new ResourceLocation(VendingMachineMod.modID+":textures/gui/book_intro.png");
        bookPageTextures[3] = new ResourceLocation(VendingMachineMod.modID+":textures/gui/book_recipe.png");
        bookPageTextures[4] = new ResourceLocation(VendingMachineMod.modID+":textures/gui/book_userguide.png");
        stringPageText[0] = "";
        stringPageText[1] = "";
        //stringPageText[2] = "This book will guide you trough all the things you need to know about the vending machines.\n\nYou can also craft the second book for more informations about the owner options and more.";
        stringPageText[2] = StatCollector.translateToLocal("gui.vendingmachine.guide.0.2");
        stringPageText[3] = "§lSoda Section:§r\nSoda Vending Machine:\n\n\n\n\n\n\n\n§o*You can use any soda bottle except the §o\"Water Bottle\"§r";
        stringPageText[4] = "Recipes next...";
        stringPageText[5] = "This section of the book will give you usefull informations about the vending machines and their use.";
        stringPageText[6] = "§lCoins:§r\n§o§nClay Coin:§r The clay coin is a §o\"counterfeit\"§r coin and therefore the vending machines will give you lower quality stuff or even nothing.";
        stringPageText[7] = "§o§nIron Coin:§r The iron coin is a standard coin.\nThis coin lets you get average quality stuff and rarely premium quality stuff.\n\n§o§nGold Coin:§r The gold coin is a premium coin that lets you have every drops possible.";
        stringPageText[8] = "§lSodas:§r\n";
    }
    
    @Override
    public void initGui() {
	    buttonList.clear();
	    Keyboard.enableRepeatEvents(true);
	      
	    buttonDone = new GuiButton(0, width / 2 + 2, 4 + bookImageHeight, 98, 20, I18n.format("gui.done", new Object[0]));
	    
	    buttonList.add(buttonDone);
	    int offsetFromScreenLeft = (width - bookImageWidth) / 2;
	    if(pageType[currPage] == 0) {
	    	buttonList.add(buttonNextPage = new NextPageButton(1, offsetFromScreenLeft + 140, 165, true));
	    } else {
	    	buttonList.add(buttonNextPage = new NextPageButton(1, offsetFromScreenLeft + 120, 156, true));
	    }
	    buttonList.add(buttonPreviousPage = new NextPageButton(2, offsetFromScreenLeft + 38, 156, false));
	    
	    
	    buttonList.add(new GuiImageButton(10, VendingMachineMod.modID, "textures/gui/book_parts.png", (this.width-this.bookImageWidth)/2+166, 15, 24, 12, 25, 0));
	    
	    buttonList.add(new GuiImageButton(11, VendingMachineMod.modID, "textures/gui/book_parts.png", (this.width-this.bookImageWidth)/2+166, 15+4+12+12-2, 24, 12, 25, 0));
	    buttonList.add(new GuiImageButton(12, VendingMachineMod.modID, "textures/gui/book_parts.png", (this.width-this.bookImageWidth)/2+166, 15+28+16-5, 24, 14, 50, 26));
	    buttonList.add(new GuiImageButton(13, VendingMachineMod.modID, "textures/gui/book_parts.png", (this.width-this.bookImageWidth)/2+166, 15+28+16+14-4, 24, 14, 50, 26+15));
	    buttonList.add(new GuiImageButton(14, VendingMachineMod.modID, "textures/gui/book_parts.png", (this.width-this.bookImageWidth)/2+166, 15+28+16+14+14-3, 24, 14, 50, 26+30));
	    buttonList.add(new GuiImageButton(15, VendingMachineMod.modID, "textures/gui/book_parts.png", (this.width-this.bookImageWidth)/2+166, 15+28+16+14+14+14-2, 24, 14, 50, 26+45));
	    
	    buttonList.add(new GuiImageButton(16, VendingMachineMod.modID, "textures/gui/book_parts.png", (this.width-this.bookImageWidth)/2+166, 15+28+16+14+14+14-2+4+12+12-2, 24, 12, 25, 0));
	    
	    if(this.currPage==3) {
	    	buttonList.add(new GuiImage(VendingMachineMod.modID, "textures/gui/recipes_01.png", this.width/2-182/4, 55, 182/2, 108/2, 0, 0));
	    }
    }
    
    @Override
    public void updateScreen() {
        buttonDone.visible = (currPage == bookTotalPages - 1);
        buttonNextPage.visible = (currPage < bookTotalPages - 1);
        buttonPreviousPage.visible = currPage > 0; 
    }
    
    @Override
    public void drawScreen(int parWidth, int parHeight, float p_73863_3_) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(bookPageTextures[pageType[currPage]]);
        int offsetFromScreenLeft = (width - bookImageWidth ) / 2;
        drawTexturedModalRect(offsetFromScreenLeft, 2, 0, 0, bookImageWidth, bookImageHeight);
        int widthOfString;
        fontRendererObj.drawSplitString(stringPageText[currPage], offsetFromScreenLeft + 36, 34, 116, 0);
        super.drawScreen(parWidth, parHeight, p_73863_3_);

    }
    
    @Override
    protected void mouseClickMove(int parMouseX, int parMouseY, int parLastButtonClicked, long parTimeSinceMouseClick) {
     
    }

    @Override
    protected void actionPerformed(GuiButton parButton) {
    	if (parButton == buttonDone) {
    		mc.displayGuiScreen((GuiScreen)null);
    	} else if (parButton == buttonNextPage) {
    		if (currPage < bookTotalPages - 1) {
    			++currPage;
    		}
    	} else if (parButton == buttonPreviousPage) {
    		if (currPage > 0) {
    			--currPage;
    		}
    	}
    	if (parButton.id == 10) {
    		this.currPage=2;
    	}
    	if (parButton.id == 11 || parButton.id == 12) {
    		this.currPage=3;
    	}
    	
    	if (parButton.id == 13) {
    		//this.currPage=4;
    	}
    	initGui();
    }

    
    @Override
    public void onGuiClosed() {
     
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    static class NextPageButton extends GuiButton {
        private final boolean isNextButton;

        public NextPageButton(int parButtonId, int parPosX, int parPosY, boolean parIsNextButton) {
            super(parButtonId, parPosX, parPosY, 23, 13, "");
            isNextButton = parIsNextButton;
        }
        
        @Override
        public void drawButton(Minecraft mc, int parX, int parY) {
        	if (visible) {
        		boolean isButtonPressed = (parX >= xPosition && parY >= yPosition && parX < xPosition + width && parY < yPosition + height);
        		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                mc.getTextureManager().bindTexture(bookPageTextures[1]);
                int textureX = 0;
                int textureY = 192;

                if (isButtonPressed) {
                    textureX += 23;
                }

                if (!isNextButton) {
                    textureY += 13;
                }

                drawTexturedModalRect(xPosition, yPosition, textureX, textureY, 23, 13);
            }
        }
    }
}