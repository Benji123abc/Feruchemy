package com.legobmw99.feruchemy.items.bands;

import com.legobmw99.feruchemy.items.AbstractItemBand;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class TinBand extends AbstractItemBand {

	public TinBand() {
		super("tin_band", 50000);

	}

	@Override
	public void stopEffects(EntityLivingBase player) {
		if(player.getActivePotionEffect(Potion.getPotionById(16)) != null && player.getActivePotionEffect(Potion.getPotionById(16)).getAmplifier() == 10){
			player.removePotionEffect(Potion.getPotionById(16));
		}
	}

	@Override
	protected void bandDrainEffects(ItemStack stack, EntityLivingBase player, byte power) {
		PotionEffect effect = new PotionEffect(Potion.getPotionById(16), Short.MAX_VALUE , 10, false, true);

		player.addPotionEffect(effect);
		
		if (player.isPotionActive(Potion.getPotionById(15))) {
			player.removePotionEffect(Potion.getPotionById(15));
		}
	}

	@Override
	protected void bandFillEffects(ItemStack stack, EntityLivingBase player, byte power) {
		PotionEffect effect = new PotionEffect(Potion.getPotionById(15), 40 , 0, false, true);
		player.addPotionEffect(effect);
		
		if(power == 3 && !player.isPotionActive(Potion.getPotionById(9))){
			PotionEffect effect2 = new PotionEffect(Potion.getPotionById(9), 100 , 0, false, true);
			player.addPotionEffect(effect2);
		}
	}

	@Override
	protected void beginFillEffect(EntityLivingBase player, int power) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void beginDrainEffect(EntityLivingBase player, int power) {

	}

}
