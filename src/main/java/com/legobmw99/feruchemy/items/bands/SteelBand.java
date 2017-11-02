package com.legobmw99.feruchemy.items.bands;

import com.legobmw99.feruchemy.items.AbstractItemBand;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

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
	protected void bandDrainEffects(ItemStack stack, EntityLivingBase player, byte power) {
		if(power == -1){
			player.addPotionEffect(new PotionEffect(Potion.getPotionById(2), 10, 0, true, false));
		}
		
	}

	@Override
	protected void bandFillEffects(ItemStack stack, EntityLivingBase player, byte power) {
		// TODO Auto-generated method stub
		
	}

}
