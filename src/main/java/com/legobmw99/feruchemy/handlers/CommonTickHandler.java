package com.legobmw99.feruchemy.handlers;

import com.legobmw99.feruchemy.items.AbstractItemBand;
import com.legobmw99.feruchemy.util.Registry;

import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommonTickHandler {
	@SubscribeEvent
	public void onRegisterItems(RegistryEvent.Register<Item> event) {
		Registry.initItems(event);
	}
	
	@SubscribeEvent
	public void onItemUse(PlayerInteractEvent.RightClickItem event){
		//TODO make a better way of doing this. probably a wheel like allomancy
		if(event.getItemStack().getItem() instanceof AbstractItemBand){
			if(event.getItemStack().hasTagCompound()){
				if ((event.getEntityPlayer().isSneaking())){
					NBTTagCompound tag = event.getItemStack().getTagCompound();
					int value = tag.getByte(AbstractItemBand.FILL_KEY);
					value = value < 0 ? value - 1: value + 1;
					value = (int) (Math.abs(value) > 3 ? -1 * Math.copySign(1, value): value);
					tag.setByte(AbstractItemBand.FILL_KEY, (byte) value);
					event.setCanceled(true);
				}
			}
		}
	}
}
