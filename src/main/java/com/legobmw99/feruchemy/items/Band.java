package com.legobmw99.feruchemy.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class Band extends Item {
	public static final String[] unlocalName = { "iron", "steel", "tin", "pewter", "zinc", "brass", "copper",
			"bronze" };

	public Band() {
		this.setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int meta = itemStack.getItemDamage();
		if ((meta < 0) || (meta >= unlocalName.length)) {
			meta = 0;
		}
		return "item.itemBand." + unlocalName[meta];
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> subItems) {
		for (int meta = 0; meta < unlocalName.length; meta++) {
			subItems.add(new ItemStack(item, 1, meta));
		}
	}
}
