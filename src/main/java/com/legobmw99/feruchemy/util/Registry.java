package com.legobmw99.feruchemy.util;

import com.legobmw99.feruchemy.Feruchemy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Registry {
	
	public static final String[] METAL_TYPES = { "iron", "steel", "tin", "pewter", "zinc", "brass", "copper",
	"bronze" };
	public static CreativeTabs tabsFeruchemy = new CreativeTabFeruchemy(CreativeTabs.getNextID(), Feruchemy.MODID);

	
	public static void initItems(Register event) {
		// Register bands
		for (int i = 0; i < METAL_TYPES.length; i++) {
			event.getRegistry().register(new Item().setUnlocalizedName("band_" + METAL_TYPES[i]).setCreativeTab(Registry.tabsFeruchemy).setRegistryName(new ResourceLocation(Feruchemy.MODID, "band_" + METAL_TYPES[i])));
		}
		
	}

	@SideOnly(Side.CLIENT)
	public static void registerItemRenders() {
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		// Register flake models
		for (int i = 0; i < METAL_TYPES.length; i++) {
			renderItem.getItemModelMesher().register(new Item().getByNameOrId("feruchemy:" + "band_" + METAL_TYPES[i]),
					0, new ModelResourceLocation("feruchemy:" + "band_" + METAL_TYPES[i], "inventory"));
		}
		

	}

	public static void initKeybindings() {
		// TODO Auto-generated method stub
		
	}

	public static void initRecipies() {
		// TODO Auto-generated method stub
		
	}

	public static void registerPackets() {
		// TODO Auto-generated method stub
		
	}

	



}
