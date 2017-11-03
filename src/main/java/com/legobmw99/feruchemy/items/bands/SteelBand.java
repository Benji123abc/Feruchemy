package com.legobmw99.feruchemy.items.bands;

import com.legobmw99.feruchemy.items.AbstractItemBand;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class SteelBand extends AbstractItemBand {

	public SteelBand() {
		super("steel_band", 100000);
	}

	@Override
	protected void stopEffect(EntityLivingBase player) {
		player.removePotionEffect(Potion.getPotionById(1)); // Speed
		player.removePotionEffect(Potion.getPotionById(2)); // Slowness
	}

	@Override
	protected void bandDrainEffects(ItemStack stack, EntityLivingBase player, byte power) {

	}

	@Override
	protected void bandFillEffects(ItemStack stack, EntityLivingBase player, byte power) {
	
	}

	@Override
	protected void beginEffect(EntityLivingBase player, int power) {
		if(power > 0){
			PotionEffect slowness = new PotionEffect(Potion.getPotionById(2), Integer.MAX_VALUE,
					(int) Math.pow(2, (1 * power) - 1) - 1, false, true);
			player.addPotionEffect(slowness);
		} else if (power < 0){
			PotionEffect speed = new PotionEffect(Potion.getPotionById(1), Integer.MAX_VALUE,
					(int) Math.pow(2, (-1 * power) - 1) - 1, false, true);
			player.addPotionEffect(speed);
		}
		
	}

}
