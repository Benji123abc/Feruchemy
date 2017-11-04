package com.legobmw99.feruchemy.items.bands;

import com.legobmw99.feruchemy.items.AbstractItemBand;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class IronBand extends AbstractItemBand {

	public IronBand() {
		super("iron_band", 50000);
	}

	@Override
	public void stopEffects(EntityLivingBase player) {
		player.setNoGravity(false);
	}

	@Override
	protected void bandDrainEffects(ItemStack stack, EntityLivingBase player, byte power) {
		player.motionY += (0.04D * power / 3.0) ;
		player.velocityChanged = true;
		
	}

	@Override
	protected void bandFillEffects(ItemStack stack, EntityLivingBase player, byte power) {
		player.fallDistance = 0.0F;

	}

	@Override
	protected void beginFillEffect(EntityLivingBase player, int power) {
		if(power == 3){
			player.setNoGravity(true);
		}
	}

	@Override
	protected void beginDrainEffect(EntityLivingBase player, int power) {

	}
}
