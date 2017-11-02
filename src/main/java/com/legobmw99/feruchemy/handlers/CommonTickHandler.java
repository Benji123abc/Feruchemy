package com.legobmw99.feruchemy.handlers;

import com.legobmw99.feruchemy.util.Registry;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommonTickHandler {
	@SubscribeEvent
	public void onRegisterItems(RegistryEvent.Register<Item> event) {
		Registry.initItems(event);
	}

}
