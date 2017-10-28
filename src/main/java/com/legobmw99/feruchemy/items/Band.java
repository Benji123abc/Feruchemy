package com.legobmw99.feruchemy.items;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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
    public EnumRarity getRarity(ItemStack stack)
    {
        return stack.hasTagCompound() ? EnumRarity.UNCOMMON : EnumRarity.COMMON;
    }
    

}
