package com.legobmw99.feruchemy.network.packets;

import com.legobmw99.feruchemy.items.AbstractItemBand;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ToggleBandPacket implements IMessage {

	private int slot;
	
	public ToggleBandPacket() {}
	
	public ToggleBandPacket(int s){
		slot = s;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		slot = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(slot);
	}

	public static class Handler implements IMessageHandler<ToggleBandPacket, IMessage> {

		@Override
		public IMessage onMessage(ToggleBandPacket message, MessageContext ctx) {
			IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world;
			mainThread.addScheduledTask(new Runnable() {
				@Override
				public void run() {
					EntityPlayerMP player = ctx.getServerHandler().player;
					IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
					if(baubles.getStackInSlot(message.slot) != null && baubles.getStackInSlot(message.slot).getItem() instanceof AbstractItemBand){
						
						ItemStack copy = baubles.getStackInSlot(message.slot).copy();
						
						int status = copy.getTagCompound().getByte(AbstractItemBand.FILL_KEY) + 3 + 1;
						status = (status % 7) - 3;
						
						copy.getTagCompound().setByte(AbstractItemBand.FILL_KEY, (byte) status);
						
						baubles.setStackInSlot(message.slot, copy);
						
						((AbstractItemBand) copy.getItem()).stopEffects(player);
						((AbstractItemBand) copy.getItem()).startEffects(copy, player);
					}
				}
			});
			return null;
		}

	}
}
