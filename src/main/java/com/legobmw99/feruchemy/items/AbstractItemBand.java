package com.legobmw99.feruchemy.items;

import com.legobmw99.feruchemy.Feruchemy;
import com.legobmw99.feruchemy.util.Registry;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
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
		this.name = name;
		this.maxFill = maxFill;

		setNoRepair();
		setMaxDamage(-1);
		setCreativeTab(Registry.tabFeruchemy);
		setRegistryName(new ResourceLocation(Feruchemy.MODID, name));
		setUnlocalizedName(name);
	}

	public static String name;
	protected static int maxFill;

	protected abstract void stopEffect(EntityLivingBase player);

	protected abstract void bandTick(ItemStack stack, EntityLivingBase player);

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return stack.hasTagCompound() ? EnumRarity.UNCOMMON : EnumRarity.COMMON;
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return BaubleType.RING;
	}

	@Override
	public void onWornTick(ItemStack stack, EntityLivingBase player) {
		bandTick(stack, player);
	}

	protected static void setupBand(ItemStack stack) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setByte("status", (byte) 0);
		nbt.setInteger("amount", 0);
		stack.setTagCompound(nbt);
	}

	@Override
	public void onEquipped(ItemStack stack, EntityLivingBase player) {
		if (!stack.hasTagCompound()) {
			setupBand(stack);
		}

		player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, .75F, 1.9f);
	}

	@Override
	public void onUnequipped(ItemStack stack, EntityLivingBase player) {
		stack.getTagCompound().setByte("status", (byte) 0);
		stopEffect(player);
		player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, .75F, 2f);
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
}
