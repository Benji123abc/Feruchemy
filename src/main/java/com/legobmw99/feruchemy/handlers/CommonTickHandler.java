package com.legobmw99.feruchemy.handlers;

import com.legobmw99.feruchemy.items.AbstractItemBand;
import com.legobmw99.feruchemy.util.Registry;

import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
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
					int status = tag.getByte(AbstractItemBand.FILL_KEY) + 3 + 1;
					status = (status % 7) - 3;
					tag.setByte(AbstractItemBand.FILL_KEY, (byte) status);
					event.setCanceled(true);
				}
			}
		}
	}

	@SubscribeEvent
	public void onRegisterRecipes(RegistryEvent.Register<IRecipe> event){
		Registry.setupRecipes(event);
	}

}
