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
	private boolean increment;
	private boolean reset;
	
	public ToggleBandPacket() {}
	
	public ToggleBandPacket(int s, boolean inc, boolean r){
		slot = s;
		increment = inc;
		reset = r;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		slot = buf.readInt();
		increment = buf.readBoolean();
		reset = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(slot);
		buf.writeBoolean(increment);
		buf.writeBoolean(reset);
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
						
						int status = copy.getTagCompound().getByte(AbstractItemBand.FILL_KEY);
						status = message.reset ? 0 : (message.increment ? (status < 3 ? status + 1 : status) : (status > -3 ? status - 1 : status));
						
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
