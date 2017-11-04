package com.legobmw99.feruchemy.items.bands;

import com.legobmw99.feruchemy.items.AbstractItemBand;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class ZincBand extends AbstractItemBand {

	public ZincBand() {
		super("zinc_band", 50000);

	}

	@Override
	public void stopEffects(EntityLivingBase player) {
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

	@Override
	protected void beginFillEffect(EntityLivingBase player, int power) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void beginDrainEffect(EntityLivingBase player, int power) {
		// TODO Auto-generated method stub
		
	}

}
