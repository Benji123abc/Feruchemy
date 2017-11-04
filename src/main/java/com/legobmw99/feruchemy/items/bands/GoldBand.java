package com.legobmw99.feruchemy.items.bands;

import com.legobmw99.feruchemy.items.AbstractItemBand;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class GoldBand extends AbstractItemBand {

	public GoldBand() {
		super("gold_band", 25000);
	}

	@Override
	public void stopEffects(EntityLivingBase player) {
		if (player.world.isRemote) {
			return;
		}
		player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0);
		player.setHealth(20.0F);
	}

	@Override
	protected void beginFillEffect(EntityLivingBase player, int power) {
		double health = 20.0 - Math.pow(2, power + 1);
		player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(health);
		player.setHealth((float) health);
	}

	@Override
	protected void beginDrainEffect(EntityLivingBase player, int power) {
		double health = 20.0 + Math.pow(2, -1*power + 1);
		player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(health);
		player.setHealth((float) health);
	}

	@Override
	protected void bandDrainEffects(ItemStack stack, EntityLivingBase player, byte power) {

	}

	@Override
	protected void bandFillEffects(ItemStack stack, EntityLivingBase player, byte power) {

	}

}
