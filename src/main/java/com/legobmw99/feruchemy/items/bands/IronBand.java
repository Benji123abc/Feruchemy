package com.legobmw99.feruchemy.items.bands;

import com.legobmw99.feruchemy.items.AbstractItemBand;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class IronBand extends AbstractItemBand {

	public IronBand() {
		super("iron_band", 1000);
	}

	@Override
	protected void stopEffect(EntityLivingBase player) {
		player.setNoGravity(false);
	}

	@Override
	protected void bandTick(ItemStack stack, EntityLivingBase player) {

	}
}
