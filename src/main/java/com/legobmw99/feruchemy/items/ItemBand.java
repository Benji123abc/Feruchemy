package com.legobmw99.feruchemy.items;

import com.legobmw99.feruchemy.Feruchemy;
import com.legobmw99.feruchemy.util.Registry;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import baubles.api.render.IRenderBauble;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBand extends Item implements IBauble{

	public ItemBand() {
		setCreativeTab(Registry.tabFeruchemy);
		setRegistryName(new ResourceLocation(Feruchemy.MODID,"band"));
		setNoRepair();
		setMaxDamage(-1);
		this.setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int meta = itemStack.getItemDamage();
		if ((meta < 0) || (meta >= Registry.METAL_TYPES.length)) {
			meta = 0;
		}
		return "item.band_" + Registry.METAL_TYPES[meta];
	}

    @Override
    public EnumRarity getRarity(ItemStack stack){
        return stack.hasTagCompound() ? EnumRarity.UNCOMMON : EnumRarity.COMMON;
    }

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return BaubleType.RING;
	}
	
	@Override
    public void onWornTick(ItemStack stack, EntityLivingBase player) {
        onUpdate(stack, player.getEntityWorld(), player, 0, false);
    }
	
	@Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected){
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
		// TODO: impl
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel(){
		ModelBakery.registerItemVariants(this,
				new ModelResourceLocation(getRegistryName() + ".band_iron", "inventory"),
				new ModelResourceLocation(getRegistryName() + ".band_steel", "inventory"),
				new ModelResourceLocation(getRegistryName() + ".band_tin", "inventory"),
				new ModelResourceLocation(getRegistryName() + ".band_pewter", "inventory"),
				new ModelResourceLocation(getRegistryName() + ".band_copper", "inventory"),
				new ModelResourceLocation(getRegistryName() + ".band_bronze", "inventory"),
				new ModelResourceLocation(getRegistryName() + ".band_zinc", "inventory"),
				new ModelResourceLocation(getRegistryName() + ".band_brass", "inventory"));
		
		for (int i = 0; i < Registry.METAL_TYPES.length; i++) {        
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(this, i,
					new ModelResourceLocation(getRegistryName() + ".band_" + Registry.METAL_TYPES[i], "inventory"));
		}
	}

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (isInCreativeTab(tab)){ 
        	for (int meta = 0; meta < Registry.METAL_TYPES.length; meta++) {
        		subItems.add(new ItemStack(this, 1, meta));
        	}
        }
    }

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if(!world.isRemote) { 
			IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
			for(int i = 0; i < baubles.getSlots(); i++) 
				if((baubles.getStackInSlot(i) == null || baubles.getStackInSlot(i).isEmpty()) && baubles.isItemValidForSlot(i, player.getHeldItem(hand), player)) {
					baubles.setStackInSlot(i, player.getHeldItem(hand).copy());
					if(!player.capabilities.isCreativeMode){
						player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
					}
					onEquipped(player.getHeldItem(hand), player);
					break;
				}
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}
	
	@Override
	public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
		
		player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, .75F, 1.9f);
	}

	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
		// TODO: stop storing
		player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, .75F, 2f);
	}
}
