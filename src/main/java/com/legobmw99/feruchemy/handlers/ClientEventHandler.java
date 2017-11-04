package com.legobmw99.feruchemy.handlers;

import com.legobmw99.feruchemy.gui.GUIBandSelect;
import com.legobmw99.feruchemy.util.Registry;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientEventHandler {

	public ClientEventHandler() {

	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		if (Registry.toggleBands.isPressed()) {
			if (FMLClientHandler.instance().getClient().currentScreen == null) {
				if (!Minecraft.getMinecraft().inGameHasFocus) {
					return;
				}
				Minecraft.getMinecraft().displayGuiScreen(new GUIBandSelect());
			}
		}
	}
}
