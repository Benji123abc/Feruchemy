package com.legobmw99.feruchemy.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class FeruchemyUtils {
	public static final int IRON = 0, STEEL = 1, TIN = 2, PEWTER = 3, ZINC = 4, BRASS = 5, COPPER = 6, BRONZE = 7;
	public static final byte FILLING = 1, DRAINING = -1, NEUTRAL = 0;

	private static final int[] MAX_STORAGE = { 1800, 1800, 3600, 600, 1800, 1800, 2400, 1600 };

	public static void bandTick(ItemStack stack, EntityLivingBase player) {
		if (stack.hasTagCompound()) {
			switch (stack.getTagCompound().getByte("status")) {
			case FILLING:
				bandFill(stack, player);
				break;
			case DRAINING:
				bandDrain(stack, player);
				break;
			default:
				break;
			}

		} else {
			setupBand(stack);
		}
	}

	private static void bandDrain(ItemStack stack, EntityLivingBase player) {
		int bandType = stack.getItemDamage();

		if (stack.getTagCompound().getInteger("amount") > 0) {
			stack.getTagCompound().setInteger("amount", stack.getTagCompound().getInteger("amount") - 1);
		} else {
			stack.getTagCompound().setByte("status", NEUTRAL);
		}

		switch (bandType) {
		case IRON:
			break;
		case STEEL:
			break;
		case TIN:
			break;
		case PEWTER:
			break;
		case ZINC:
			break;
		case BRASS:
			break;
		case COPPER:
			break;
		case BRONZE:
			break;
		}
	}

	private static void bandFill(ItemStack stack, EntityLivingBase player) {
		int bandType = stack.getItemDamage();

		if (stack.getTagCompound().getInteger("amount") < MAX_STORAGE[bandType]) {
			stack.getTagCompound().setInteger("amount", stack.getTagCompound().getInteger("amount") + 1);
		} else {
			stack.getTagCompound().setByte("status", NEUTRAL);
		}

		switch (bandType) {
		case IRON:
			break;
		case STEEL:
			break;
		case TIN:
			break;
		case PEWTER:
			break;
		case ZINC:
			break;
		case BRASS:
			break;
		case COPPER:
			break;
		case BRONZE:
			break;
		}
	}

	private static void setupBand(ItemStack stack) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setByte("status", (byte) 0);
		nbt.setInteger("amount", 0);
		stack.setTagCompound(nbt);
	}
}
