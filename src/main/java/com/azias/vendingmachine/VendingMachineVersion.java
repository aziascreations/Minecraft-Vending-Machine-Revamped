package com.azias.vendingmachine;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import com.google.common.io.ByteStreams;
import com.google.gson.Gson;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.versioning.ArtifactVersion;
import net.minecraftforge.fml.common.versioning.DefaultArtifactVersion;

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
				player.addChatComponentMessage(new TextComponentTranslation("vendingmachine.update"), true);
				ITextComponent component = ITextComponent.Serializer.jsonToComponent(I18n.format("vendingmachine.update.download"));
				player.addChatMessage(component);
				//player.addChatComponentMessage(new ChatComponentTranslation("vendingmachine.update.notification"));
				//IChatComponent component = IChatComponent.Serializer.func_150699_a(I18n.format("vendingmachine.update.download"));
				//player.addChatMessage(component);
			}
		}
	}
	
	public static void startVersionCheck() {
		try {
			URL url = new URL("https://raw.githubusercontent.com/aziascreations/Minecraft-Vending-Machine-Revamped/1.11.2/version.json");
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
		switch(status) {
			case UP_TO_DATE:
				System.out.println("UpToDate");
				break;
			case OUTDATED:
				System.out.println("Outdated");
				break;
			case AHEAD:
				System.out.println("Ahead");
				break;
			case FAILED:
				System.out.println("Failed");
				break;
			case PENDING:
				System.out.println("Pending");
				break;
			default:
				System.out.println("UnknownCase");
				break;
		}
	}
}