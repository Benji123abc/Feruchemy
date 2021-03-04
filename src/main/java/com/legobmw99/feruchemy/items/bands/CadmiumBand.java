package com.legobmw99.feruchemy.items.bands;

import com.legobmw99.feruchemy.items.AbstractItemBand;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class CadmiumBand extends AbstractItemBand {

	public CadmiumBand() {
		super("cadmium_band", 50000);

	}

	@Override
	public void stopEffects(EntityLivingBase player) {


	}

	@Override
	protected void bandDrainEffects(ItemStack stack, EntityLivingBase player, byte power) {

		
	}

	@Override
	protected void bandFillEffects(ItemStack stack, EntityLivingBase player, byte power) {

		
	}

	@Override
	protected void beginFillEffect(EntityLivingBase player, int power) {

		
	}

	@Override
	protected void beginDrainEffect(EntityLivingBase player, int power) {

		
	}

}
