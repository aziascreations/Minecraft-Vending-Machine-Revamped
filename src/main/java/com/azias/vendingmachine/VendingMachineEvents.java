package com.azias.vendingmachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.EmptyStackException;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

/**
 * @author Azias
 * @version 0.2
 * @ForgeVersion 1.7 - 10.13.4.1448
 */
public class VendingMachineEvents {
	@SubscribeEvent
	public void checkUpdate(PlayerEvent.PlayerLoggedInEvent event) {
		if(VendingMachineConfigs.checkUpdate) {
			if(!(Float.valueOf(VendingMachineMod.VERSION)>=Float.valueOf(VendingMachineMod.VERSIONOFFICIAL))) {
				EntityPlayer player = event.player;
				player.addChatComponentMessage(new ChatComponentTranslation("vendingmachine.update"));
				IChatComponent component = IChatComponent.Serializer.func_150699_a(StatCollector.translateToLocal("vendingmachine.update.download"));
				player.addChatMessage(component);
			}
		}
	}
	
	public static void getOfficialVersionNumber() {
		try {
			URL getNews;
			getNews = new URL("https://raw.githubusercontent.com/AziasYur/Minecraft-Vending-Machine-Revamped/master/version.txt");
			URLConnection yc = getNews.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				try {
					VendingMachineMod.VERSIONOFFICIAL = inputLine;
				} catch (Exception e) {
					e.printStackTrace();
				}
			in.close();
			
			if(!(Float.valueOf(VendingMachineMod.VERSION)>=Float.valueOf(VendingMachineMod.VERSIONOFFICIAL))) {
				System.out.println("A new version for \"Vending Machine Revamped\" is available on curse.com !");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			throw new EmptyStackException();
		}
	}
}