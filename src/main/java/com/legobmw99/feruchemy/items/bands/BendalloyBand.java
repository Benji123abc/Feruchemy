package com.legobmw99.feruchemy.items.bands;

import com.legobmw99.feruchemy.items.AbstractItemBand;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.FoodStats;

public class BendalloyBand extends AbstractItemBand {

	public BendalloyBand() {
		super("bendalloy_band", 50000);
	}

	@Override
	public void stopEffects(EntityLivingBase player) {


	}

	@Override
	protected void bandDrainEffects(ItemStack stack, EntityLivingBase player, byte power) {
		PotionEffect effect = new PotionEffect(Potion.getPotionById(23), 10 , (int) Math.sqrt(power) / 5, false, true);
		player.addPotionEffect(effect);
	}

	@Override
	protected void bandFillEffects(ItemStack stack, EntityLivingBase player, byte power) {
		PotionEffect effect = new PotionEffect(Potion.getPotionById(23), 10 , (int) Math.sqrt(power) / 5, false, true);
		player.addPotionEffect(effect);
	}

	@Override
	protected void beginFillEffect(EntityLivingBase player, int power) {

		
	}

	@Override
	protected void beginDrainEffect(EntityLivingBase player, int power) {

		
	}

}
