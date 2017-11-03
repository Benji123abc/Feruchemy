package com.legobmw99.feruchemy.util;

import org.lwjgl.input.Keyboard;

import com.legobmw99.feruchemy.Feruchemy;
import com.legobmw99.feruchemy.items.AbstractItemBand;
import com.legobmw99.feruchemy.items.bands.BrassBand;
import com.legobmw99.feruchemy.items.bands.BronzeBand;
import com.legobmw99.feruchemy.items.bands.CopperBand;
import com.legobmw99.feruchemy.items.bands.IronBand;
import com.legobmw99.feruchemy.items.bands.PewterBand;
import com.legobmw99.feruchemy.items.bands.SteelBand;
import com.legobmw99.feruchemy.items.bands.TinBand;
import com.legobmw99.feruchemy.items.bands.ZincBand;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Registry {

	public static AbstractItemBand ironBand, steelBand, tinBand, pewterBand, zincBand, brassBand, copperBand,
			bronzeBand;
	public static KeyBinding firstBand, secondBand;
	public static CreativeTabs tabFeruchemy = new CreativeTabFeruchemy(CreativeTabs.getNextID(), Feruchemy.MODID);

	public static void initItems(Register event) {
		// Register bands
		event.getRegistry().registerAll(ironBand = new IronBand(), steelBand = new SteelBand(), tinBand = new TinBand(),
				pewterBand = new PewterBand(), zincBand = new ZincBand(), brassBand = new BrassBand(),
				copperBand = new CopperBand(), bronzeBand = new BronzeBand());

	}

	@SideOnly(Side.CLIENT)
	public static void registerItemRenders() {
		// Register band models
		ironBand.initModel();
		steelBand.initModel();
		tinBand.initModel();
		pewterBand.initModel();
		zincBand.initModel();
		brassBand.initModel();
		copperBand.initModel();
		bronzeBand.initModel();

	}

	public static void initKeybindings() {
		firstBand = new KeyBinding("key.firstBand", Keyboard.KEY_F, "key.categories.feruchemy");
		ClientRegistry.registerKeyBinding(firstBand);
		secondBand = new KeyBinding("key.secondBand", Keyboard.KEY_G, "key.categories.feruchemy");
		ClientRegistry.registerKeyBinding(secondBand);
	}

	public static void initRecipies() {
		// TODO Auto-generated method stub

	}

}
