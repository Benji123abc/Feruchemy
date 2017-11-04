package com.legobmw99.feruchemy;

import com.legobmw99.feruchemy.handlers.ClientEventHandler;
import com.legobmw99.feruchemy.handlers.CommonTickHandler;
import com.legobmw99.feruchemy.network.packets.ToggleBandPacket;
import com.legobmw99.feruchemy.util.Registry;

import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Feruchemy.MODID, version = Feruchemy.VERSION)
public class Feruchemy {
	public static final String MODID = "feruchemy";
	public static final String VERSION = "@VERSION@";

	
	@Instance(value = "feruchemy")
	public static Feruchemy instance;

	@SidedProxy
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}

	public static class CommonProxy {
		public void preInit(FMLPreInitializationEvent e) {
			MinecraftForge.EVENT_BUS.register(new CommonTickHandler());
			Registry.initPackets();
		}

		public void init(FMLInitializationEvent e) {
			Registry.initRecipies();
		}

		public void postInit(FMLPostInitializationEvent e) {

		}
	}

	public static class ClientProxy extends CommonProxy {
		@Override
		public void preInit(FMLPreInitializationEvent e) {
			super.preInit(e);
			
			OBJLoader.INSTANCE.addDomain(MODID);

			Registry.initKeybindings();
		}

		@Override
		public void init(FMLInitializationEvent e) {
			super.init(e);
			MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
			Registry.registerItemRenders();

		}
	}

	public static class ServerProxy extends CommonProxy {

	}
}