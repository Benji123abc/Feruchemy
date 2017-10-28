package com.legobmw99.feruchemy.util;

import com.legobmw99.feruchemy.Feruchemy;
import com.legobmw99.feruchemy.items.ItemBand;

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
	public static ItemBand itemBand;
	public static CreativeTabs tabFeruchemy = new CreativeTabFeruchemy(CreativeTabs.getNextID(), Feruchemy.MODID);
	
	
	public static void initItems(Register event) {
		// Register bands
		event.getRegistry().registerAll(
		itemBand = new ItemBand());
		
	}

	@SideOnly(Side.CLIENT)
	public static void registerItemRenders() {
		// Register flake models
		itemBand.initModel();
		
		//TODO: investigate other solutions to the variants-not-loading-initially problem
		Minecraft.getMinecraft().refreshResources();
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
