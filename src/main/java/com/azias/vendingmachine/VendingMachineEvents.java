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
 * @ForgeVersion 1.7 - 10.13.4.1448
 */
public class VendingMachineEvents {
	@SubscribeEvent
	public void checkUpdate(PlayerEvent.PlayerLoggedInEvent event) {
		if(VendingMachineConfigs.checkUpdate) {
			if(isLatestVersionNewer(VendingMachineMod.VERSION, VendingMachineMod.VERSIONOFFICIAL)) {
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
			if(!VendingMachineConfigs.checkBetaUpdate) {
				getNews = new URL("https://raw.githubusercontent.com/AziasYur/Minecraft-Vending-Machine-Revamped/master/version_stable.txt");
			} else {
				getNews = new URL("https://raw.githubusercontent.com/AziasYur/Minecraft-Vending-Machine-Revamped/master/version_dev.txt");
			}
			URLConnection yc = getNews.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				try {
					VendingMachineMod.VERSIONOFFICIAL = inputLine;
					if(isLatestVersionNewer(VendingMachineMod.VERSION, VendingMachineMod.VERSIONOFFICIAL)) {
						System.out.println("A new version for \"Vending Machine Revamped\" is available on curse.com !");
					}
				} catch (Exception e) {
					e.printStackTrace();
					VendingMachineConfigs.checkUpdate=false;
				}
			in.close();
		} catch (IOException e1) {
			e1.printStackTrace();
			VendingMachineConfigs.checkUpdate=false;
			throw new EmptyStackException();
		}
	}
	
	private static boolean isLatestVersionNewer(String versionLocal, String versionOfficial) {
		//System.out.println("Checking Versions...");
		String[] local = versionLocal.split("\\.");
		String[] official = versionOfficial.split("\\.");
		if(Integer.parseInt(local[0])<Integer.parseInt(official[0])) {
			//System.out.println("You don't have the latest Major Version! - "+local[0]+" / "+official[0]);
			return true;
		}
		//System.out.println("Major Version is good! - "+local[0]+" / "+official[0]);
		if(Integer.parseInt(local[1])<Integer.parseInt(official[1])) {
			//System.out.println("You don't have the latest Minor Version! - "+local[1]+" / "+official[1]);
			return true;
		}
		//System.out.println("Minor Version is good! - "+local[1]+" / "+official[1]);
		if(Integer.parseInt(local[2])<Integer.parseInt(official[2])) {
			//System.out.println("You don't have the latest Patch Version! - "+local[2]+" / "+official[2]);
			return true;
		}
		//System.out.println("Patch Version is good! - "+local[2]+" / "+official[2]);
		//System.out.println("You have the latest version");
		return false;
	}
}