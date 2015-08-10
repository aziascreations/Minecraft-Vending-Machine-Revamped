package com.azias.vendingmachine;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import com.google.common.io.ByteStreams;
import com.google.gson.Gson;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.versioning.ArtifactVersion;
import cpw.mods.fml.common.versioning.DefaultArtifactVersion;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.MinecraftForge;

public class VendingMachineVersion {
	public static final int majorVersion = Integer.parseInt(VendingMachineMod.version.split("\\.")[0]);
	public static final int minorVersion = Integer.parseInt(VendingMachineMod.version.split("\\.")[1]);
	public static final int patchVersion = Integer.parseInt(VendingMachineMod.version.split("\\.")[2]);
	private static Status status = Status.PENDING;
	
	public static int getMajorVersion() {
		return majorVersion;
	}

	public static int getMinorVersion() {
		return minorVersion;
	}

	public static int getPatchVersion() {
		return patchVersion;
	}

	public static Status getStatus() {
		return status;
	}

	public static String getVersion() {
		return String.format("%d.%d.%d", majorVersion, minorVersion, patchVersion);
	}
	
	public static enum Status {
		PENDING,
		FAILED,
		UP_TO_DATE,
		OUTDATED,
		AHEAD
	}
	
	@SubscribeEvent
	public void checkUpdate(PlayerEvent.PlayerLoggedInEvent event) {
		if(VendingMachineConfigs.checkUpdate) {
			if(status==Status.OUTDATED) {
				EntityPlayer player = event.player;
				player.addChatComponentMessage(new ChatComponentTranslation("vendingmachine.update"));
				IChatComponent component = IChatComponent.Serializer.func_150699_a(StatCollector.translateToLocal("vendingmachine.update.download"));
				player.addChatMessage(component);
			}
		}
	}
	
	public static void startVersionCheck() {
		try {
			URL url = new URL("https://raw.githubusercontent.com/AziasYur/Minecraft-Vending-Machine-Revamped/develop/version.json");
			InputStream con = url.openStream();
			String data = new String(ByteStreams.toByteArray(con));
			con.close();
 
			Map<String, Object> json = new Gson().fromJson(data, Map.class);
			Map<String, String> versions = (Map<String, String>)json.get("versions");

			String ver;
			if(VendingMachineConfigs.checkBetaUpdate) {
				ver = versions.get(MinecraftForge.MC_VERSION + "-develop");
			} else {
				ver = versions.get(MinecraftForge.MC_VERSION + "-release");
			}
			ArtifactVersion current = new DefaultArtifactVersion(getVersion());

			if(ver!= null) {
				ArtifactVersion latest = new DefaultArtifactVersion(ver);
				int diff = latest.compareTo(current);
				
				if (diff == 0) {
					status = Status.UP_TO_DATE;
				} else if (diff < 0) {
					status = Status.AHEAD;
				} else {
					status = Status.OUTDATED;
				}
			} else {
				status = Status.AHEAD;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			status = Status.FAILED;
		}
		echoStatus();
	}
	
	private static void echoStatus() {
		System.out.println(VendingMachineMod.modName+"'s version statut:");
		if(status==Status.UP_TO_DATE) {
			System.out.println("UpToDate");
		} else if(status==Status.OUTDATED) {
			System.out.println("Outdated");
		} else if(status==Status.AHEAD) {
			System.out.println("Ahead");
		} else if(status==Status.FAILED) {
			System.out.println("ERROR 1");
		} else if(status==Status.PENDING) {
			System.out.println("ERROR 2");
		} else {
			System.out.println("ERROR 3");
		}
	}
}