package com.legobmw99.feruchemy.items.bands;

import com.legobmw99.feruchemy.items.AbstractItemBand;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

public class BrassBand extends AbstractItemBand {

	public BrassBand() {
		super("brass_band", 50000);

	}

	@Override
	public void stopEffects(EntityLivingBase player) {

	}

	@Override
	protected void bandDrainEffects(ItemStack stack, EntityLivingBase player, byte power) {
		if (player.world.isRemote) {
			return;
		}
		PotionEffect effect = new PotionEffect(Potion.getPotionById(12), 10, (int) Math.pow(2, (-1 * power) - 1) - 1, false, true);
		player.addPotionEffect(effect);
		if (player.isBurning() && power == 3) {
			player.extinguish();
		}
	}

	@Override
	protected void bandFillEffects(ItemStack stack, EntityLivingBase player, byte power) {
		if (player.world.isRemote) {
			return;
		}
		if (player.isBurning()) {
			player.attackEntityFrom(DamageSource.ON_FIRE, 0.5F * power);
		}

	}

	@Override
	protected void beginFillEffect(EntityLivingBase player, int power) {

	}

	@Override
	protected void beginDrainEffect(EntityLivingBase player, int power) {

	}

}
