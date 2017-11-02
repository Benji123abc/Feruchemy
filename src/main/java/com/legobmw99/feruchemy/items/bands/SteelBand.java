package com.legobmw99.feruchemy.items.bands;

import com.legobmw99.feruchemy.items.AbstractItemBand;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

public class SteelBand extends AbstractItemBand {

	public SteelBand() {
		super("steel_band", 1000);
	}

	@Override
	protected void stopEffect(EntityLivingBase player) {
		player.removePotionEffect(Potion.getPotionById(1)); // Speed
		player.removePotionEffect(Potion.getPotionById(2)); // Slowness
	}

	@Override
	protected void bandTick(ItemStack stack, EntityLivingBase player) {
		// TODO Auto-generated method stub

	}

}
