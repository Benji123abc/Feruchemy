package com.legobmw99.feruchemy.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabFeruchemy extends CreativeTabs {

	public CreativeTabFeruchemy(int index, String label) {
		super(index, label);
	}

	@Override
	public String getTabLabel() {
		return "Feruchemy";
	}

	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(Item.getByNameOrId("feruchemy:itemBand"), 1);
	}

	@Override
	public ItemStack getTabIconItem() {
		return null;
	}
}
