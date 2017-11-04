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
import com.legobmw99.feruchemy.network.packets.ToggleBandPacket;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Registry {

	public static AbstractItemBand ironBand, steelBand, tinBand, pewterBand, zincBand, brassBand, copperBand,
			bronzeBand, goldBand;
	public static KeyBinding toggleBands, secondBand;
	public static CreativeTabs tabFeruchemy = new CreativeTabFeruchemy(CreativeTabs.getNextID(), Feruchemy.MODID);

	public static SimpleNetworkWrapper network;

	public static void initItems(Register event) {
		// Register bands
		event.getRegistry().registerAll(ironBand = new IronBand(), steelBand = new SteelBand(), tinBand = new TinBand(),
				pewterBand = new PewterBand(), zincBand = new ZincBand(), brassBand = new BrassBand(),
				copperBand = new CopperBand(), bronzeBand = new BronzeBand(), goldBand = new GoldBand());

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
		goldBand.initModel();

	}

	public static void initKeybindings() {
		toggleBands = new KeyBinding("key.toggleBands", Keyboard.KEY_F, "key.categories.feruchemy");
		ClientRegistry.registerKeyBinding(toggleBands);

	}

	public static void initRecipies() {
		// TODO Auto-generated method stub

	}

	public static void initPackets() {
		network = NetworkRegistry.INSTANCE.newSimpleChannel("btm");
		network.registerMessage(ToggleBandPacket.Handler.class, ToggleBandPacket.class, 0, Side.SERVER);		
	}

}
