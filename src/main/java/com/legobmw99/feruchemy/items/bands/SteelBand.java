package com.legobmw99.feruchemy.items.bands;

import com.legobmw99.feruchemy.items.AbstractItemBand;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class SteelBand extends AbstractItemBand {

	public SteelBand() {
		super("steel_band", 50000);
	}

	@Override
	public void stopEffects(EntityLivingBase player) {

	}

	@Override
	protected void bandDrainEffects(ItemStack stack, EntityLivingBase player, byte power) {
		PotionEffect effect = new PotionEffect(Potion.getPotionById(1), 10 , (int) Math.pow(2, (-1* power) - 1) - 1, false, true);

		player.addPotionEffect(effect);
	}

	@Override
	protected void bandFillEffects(ItemStack stack, EntityLivingBase player, byte power) {
		PotionEffect effect = new PotionEffect(Potion.getPotionById(2), 10 , (int) Math.pow(2, power - 1) - 1, false, true);

		player.addPotionEffect(effect);
	}

	@Override
	protected void beginFillEffect(EntityLivingBase player, int power) {
		
	}

	@Override
	protected void beginDrainEffect(EntityLivingBase player, int power) {

	}
}
