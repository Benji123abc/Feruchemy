package com.legobmw99.feruchemy.items.bands;

import com.legobmw99.feruchemy.items.AbstractItemBand;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class BronzeBand extends AbstractItemBand {

	public BronzeBand() {
		super("bronze_band", 1000);
	}

	@Override
	protected void stopEffect(EntityLivingBase player) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void bandDrainEffects(ItemStack stack, EntityLivingBase player, byte power) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void bandFillEffects(ItemStack stack, EntityLivingBase player, byte power) {
		// TODO Auto-generated method stub
		
	}

}
