package com.legobmw99.feruchemy.util;

import com.legobmw99.allomancy.Allomancy;
import com.legobmw99.allomancy.util.AllomancyCapability;
import com.legobmw99.allomancy.util.AllomancyConfig;
import com.legobmw99.allomancy.util.RecipeItemVial;
import com.legobmw99.feruchemy.items.RefinedMetal;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.MetadataCollection;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.lwjgl.input.Keyboard;

import com.legobmw99.feruchemy.Feruchemy;
import com.legobmw99.feruchemy.items.AbstractItemBand;
import com.legobmw99.feruchemy.items.bands.BrassBand;
import com.legobmw99.feruchemy.items.bands.BronzeBand;
import com.legobmw99.feruchemy.items.bands.CopperBand;
import com.legobmw99.feruchemy.items.bands.GoldBand;
import com.legobmw99.feruchemy.items.bands.IronBand;
import com.legobmw99.feruchemy.items.bands.PewterBand;
import com.legobmw99.feruchemy.items.bands.SteelBand;
import com.legobmw99.feruchemy.items.bands.TinBand;
import com.legobmw99.feruchemy.items.bands.ZincBand;
import com.legobmw99.feruchemy.items.bands.BendalloyBand;
import com.legobmw99.feruchemy.items.bands.CadmiumBand;
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
			bronzeBand, goldBand, cadmiumBand, bendalloyBand;
	public static RefinedMetal refinedIron, refinedSteel, refinedTin, refinedPewter, refinedZinc, refinedBrass, refinedCopper,
			refinedBronze, refinedGold;
	public static KeyBinding toggleBands, secondBand;
	public static CreativeTabs tabFeruchemy = new CreativeTabFeruchemy(CreativeTabs.getNextID(), Feruchemy.MODID);

	public static SimpleNetworkWrapper network;

	public static void initItems(Register event) {
		// Register bands
		event.getRegistry().registerAll(ironBand = new IronBand(),
				steelBand = new SteelBand(), tinBand = new TinBand(), pewterBand = new PewterBand(),
				zincBand = new ZincBand(), brassBand = new BrassBand(), copperBand = new CopperBand(),
				bronzeBand = new BronzeBand(), goldBand = new GoldBand(), cadmiumBand = new CadmiumBand(),
				bendalloyBand = new BendalloyBand());
		event.getRegistry().registerAll(refinedIron = new RefinedMetal("refined_iron"),
				refinedSteel = new RefinedMetal("refined_steel"), refinedTin = new RefinedMetal("refined_tin"), refinedPewter = new RefinedMetal("refined_pewter"),
				refinedZinc = new RefinedMetal("refined_zinc"), refinedBrass = new RefinedMetal("refined_brass"), refinedCopper = new RefinedMetal("refined_copper"),
				refinedBronze = new RefinedMetal("refined_bronze"), refinedGold = new RefinedMetal("refined_gold"));

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
		cadmiumBand.initModel();
		bendalloyBand.initModel();

		//Register refined metal models
		refinedIron.initModel();
		refinedSteel.initModel();
		refinedTin.initModel();
		refinedPewter.initModel();
		refinedZinc.initModel();
		refinedBrass.initModel();
		refinedCopper.initModel();
		refinedBronze.initModel();
		refinedGold.initModel();
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

	public static void setupRecipes(Register event) {
		// TODO: Steel band crafting and smelting recipe
		GameRegistry.addSmelting(Items.IRON_INGOT, new ItemStack(refinedIron), 0.1f);
		GameRegistry.addSmelting(Items.GOLD_INGOT, new ItemStack(refinedGold), 0.1f);
		GameRegistry.addSmelting(com.legobmw99.allomancy.util.Registry.itemTinIngot, new ItemStack(refinedTin), 0.1f);
		GameRegistry.addSmelting(com.legobmw99.allomancy.util.Registry.itemBrassIngot, new ItemStack(refinedBrass), 0.1f);
		GameRegistry.addSmelting(com.legobmw99.allomancy.util.Registry.itemCopperIngot, new ItemStack(refinedCopper), 0.1f);
		GameRegistry.addSmelting(com.legobmw99.allomancy.util.Registry.itemZincIngot, new ItemStack(refinedZinc), 0.1f);
		GameRegistry.addSmelting(com.legobmw99.allomancy.util.Registry.itemBronzeIngot, new ItemStack(refinedBronze), 0.1f);
	}
}
