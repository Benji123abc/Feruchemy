package com.legobmw99.feruchemy.items;

import java.util.List;

import javax.annotation.Nullable;

import com.legobmw99.feruchemy.Feruchemy;
import com.legobmw99.feruchemy.util.Registry;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class AbstractItemBand extends Item implements IBauble {

	public AbstractItemBand(String name, int maxFill) {
		this.maxFill = maxFill;

		setNoRepair();
		setMaxDamage(-1);
		setMaxStackSize(1);
		setCreativeTab(Registry.tabFeruchemy);
		setRegistryName(new ResourceLocation(Feruchemy.MODID, name));
		setUnlocalizedName(name);
	}

	protected int maxFill;
	public static final String FILL_KEY = "status";
	public static final String STORAGE_KEY = "amount";
	private static final String MODIFIER[] = { "I", "II", "III" };

	protected abstract void stopEffects(EntityLivingBase player);

	protected abstract void beginFillEffect(EntityLivingBase player, int power);

	protected abstract void beginDrainEffect(EntityLivingBase player, int power);

	protected abstract void bandDrainEffects(ItemStack stack, EntityLivingBase player, byte power);

	protected abstract void bandFillEffects(ItemStack stack, EntityLivingBase player, byte power);

	/**
	 * Does most of the heavy lifting
	 * 
	 * @see baubles.api.IBauble#onWornTick(net.minecraft.item.ItemStack,
	 *      net.minecraft.entity.EntityLivingBase)
	 */
	@Override
	public void onWornTick(ItemStack stack, EntityLivingBase player) {
		if (player.world.isRemote) {
			return;
		}
		if (!stack.hasTagCompound()) {
			setupBand(stack);
			return;
		}

		NBTTagCompound tag = stack.getTagCompound();
		byte fill = tag.getByte(FILL_KEY);
		int storage = tag.getInteger(STORAGE_KEY);

		if ((fill > 0 && storage < maxFill) || (fill < 0 && storage > 0)) {
			if (fill > 0) {
				bandFillEffects(stack, player, fill);
			}
			if (fill < 0) {
				bandDrainEffects(stack, player, fill);
			}
			tag.setInteger(STORAGE_KEY, stack.getTagCompound().getInteger(STORAGE_KEY) + fill);

		} else if (fill != 0) {
			stack.getTagCompound().setByte(FILL_KEY, (byte) 0);
			stopEffects(player);
		}

	}

	protected static void setupBand(ItemStack stack) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setByte(FILL_KEY, (byte) 0);
		nbt.setInteger(STORAGE_KEY, 0);
		stack.setTagCompound(nbt);
	}

	@Override
	public void onEquipped(ItemStack stack, EntityLivingBase player) {
		if (!stack.hasTagCompound()) {
			setupBand(stack);
		}
		
		int fill = stack.getTagCompound().getByte(FILL_KEY);

		if (!player.world.isRemote) {
			if (fill > 0) {
				beginFillEffect(player, fill);
			}
			if (fill < 0) {
				beginDrainEffect(player, fill);
			}
		}

		player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, .75F, 1.9f);
	}

	@Override
	public void onUnequipped(ItemStack stack, EntityLivingBase player) {
		if (stack.getTagCompound().getByte(FILL_KEY) != 0) {
			stack.getTagCompound().setByte(FILL_KEY, (byte) 0);
			stopEffects(player);
		}

		player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, .75F, 2f);
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return BaubleType.TRINKET;
	}

	@Override
	public boolean willAutoSync(ItemStack itemstack, EntityLivingBase player) {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(this, 0,
				new ModelResourceLocation(this.getRegistryName(), "inventory"));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (!world.isRemote) {
			IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
			for (int i = 0; i < baubles.getSlots(); i++) {
				if ((baubles.getStackInSlot(i) == null || baubles.getStackInSlot(i).isEmpty())
						&& baubles.isItemValidForSlot(i, player.getHeldItem(hand), player)) {
					baubles.setStackInSlot(i, player.getHeldItem(hand).copy());
					if (!player.capabilities.isCreativeMode) {
						player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
					}
					onEquipped(player.getHeldItem(hand), player);
					break;
				}
			}
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return stack.hasTagCompound() ? EnumRarity.UNCOMMON : EnumRarity.COMMON;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean hasEffect(ItemStack stack) {
		if (stack.hasTagCompound()) {
			if (stack.getTagCompound().getByte(FILL_KEY) != 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> tooltip, ITooltipFlag advanced) {
		if (stack.getTagCompound() != null) {
			byte power = stack.getTagCompound().getByte(FILL_KEY);
			int percentage = (int) ((stack.getTagCompound().getInteger(STORAGE_KEY) / (float) maxFill) * 100);
			tooltip.add(percentage + "% Full");
			if (power != 0) {
				String status = power > 0 ? "Filling" : "Draining";
				tooltip.add(status + " " + MODIFIER[Math.abs(power) - 1]);
			}
		}
	}
}
