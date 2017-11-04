package com.legobmw99.feruchemy.items.bands;

import com.legobmw99.feruchemy.items.AbstractItemBand;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PewterBand extends AbstractItemBand {

	public PewterBand() {
		super("pewter_band", 25000);

	}

	@Override
	protected void bandDrainEffects(ItemStack stack, EntityLivingBase player, byte power) {

		PotionEffect effect = new PotionEffect(Potion.getPotionById(5), 10 , (int) Math.pow(2, (-1*power) - 1) - 1, false, true);

		player.addPotionEffect(effect);		
	}

	@Override
	protected void bandFillEffects(ItemStack stack, EntityLivingBase player, byte power) {
		PotionEffect effect = new PotionEffect(Potion.getPotionById(18), 10 , (int) Math.pow(2, power - 1) - 1, false, true);

		player.addPotionEffect(effect);		
	}

	@Override
	public void stopEffects(EntityLivingBase player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void beginFillEffect(EntityLivingBase player, int power) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void beginDrainEffect(EntityLivingBase player, int power) {
		// TODO Auto-generated method stub
		
	}

}
