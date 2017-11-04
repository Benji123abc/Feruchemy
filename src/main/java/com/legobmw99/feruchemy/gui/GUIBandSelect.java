package com.legobmw99.feruchemy.gui;

/**
 * This class was modified from one created by <Vazkii>. The original is
 * distributed as part of the Psi Mod.
 * This code is used under the
 * Psi License: http://psi.vazkii.us/license.php
 * 
 * The code was used as a template for the circular GUI,
 * and was heavily modified
 *
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

import com.google.common.collect.ImmutableSet;
import com.legobmw99.feruchemy.items.AbstractItemBand;
import com.legobmw99.feruchemy.network.packets.ToggleBandPacket;
import com.legobmw99.feruchemy.util.Registry;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class GUIBandSelect extends GuiScreen {

	private static final float[][] colors = { {0.4F}, {0.4F,0.52F}, {0.4F,0.52F,0.64F}, {0.4F,0.52F,0.4F,0.52F}, {0.4F,0.52F,0.64F,0.4F,0.52F}, {0.4F,0.52F,0.4F,0.52F,0.4F,0.52F}, {0.4F,0.52F,0.64F,0.28F,0.4F,0.52F,0.28F}};

	int timeIn = 10; 
	int slotSelected = -1;
	List<Integer> slots;
	IBaublesItemHandler baubles;

	public GUIBandSelect() {
		EntityPlayerSP player;
		player = Minecraft.getMinecraft().player;
		baubles = BaublesApi.getBaublesHandler(player);
		
		slots = new ArrayList();

		for (int i = 0; i < baubles.getSlots(); i++) {
			if(baubles.getStackInSlot(i) != null && baubles.getStackInSlot(i).getItem() instanceof AbstractItemBand){
				slots.add(i);
			}
		}
	}

	@Override
	public void drawScreen(int mx, int my, float partialTicks) {
		super.drawScreen(mx, my, partialTicks);

		GlStateManager.pushMatrix();
		GlStateManager.disableTexture2D();

		int x = width / 2;
		int y = height / 2;
		int maxRadius = 80;

		boolean mouseIn = true;
		float angle = mouseAngle(x, y, mx, my);


		GlStateManager.enableBlend();
		GlStateManager.shadeModel(GL11.GL_SMOOTH);
		int segments = slots.size();
		float totalDeg = 0;
		float degPer = Math.round(360F / segments);

		List<int[]> stringPositions = new ArrayList();

		slotSelected = -1;

		for (int seg = 0; seg < segments; seg++) {
			// I'm so sorry...
			if (segments == 7 && seg == 6){
				degPer = 360 - degPer*6;
			}
			boolean mouseInSector = mouseIn && angle > totalDeg && angle < totalDeg + degPer;
			float radius = Math.max(0F, Math.min((timeIn + partialTicks - seg * 6F / segments) * 40F, maxRadius));

			GL11.glBegin(GL11.GL_TRIANGLE_FAN);
			float gs = colors[segments-1][seg];

			float r = gs;
			float g = gs;
			float b = gs;
			byte status = baubles.getStackInSlot(slots.get(seg)).getTagCompound().getByte(AbstractItemBand.FILL_KEY);
			if(status > 0){
				r = 0.5F + status / 6.0F;
			}
			if(status < 0){
				g = 0.5F + -1* status / 6.0F;
			}
			float a = 0.6F;
			if (mouseInSector) {
				slotSelected = seg;
			}

			GlStateManager.color(r, g, b, a);
			GL11.glVertex2i(x, y);

			for (float i = degPer; i >= 0; i--) {
				float rad = (float) ((i + totalDeg) / 180F * Math.PI);
				double xp = x + Math.cos(rad) * radius;
				double yp = y + Math.sin(rad) * radius;
				if ((int)i == (int)(degPer / 2)) {
					stringPositions.add(new int[] { seg, (int) xp, (int) yp, mouseInSector ? 'n' : 'r' });
					stringPositions.add(new int[] { seg, (int) xp, (int) yp, '7' }); 
				}
				GL11.glVertex2d(xp, yp);
			}
			totalDeg += degPer;

			GL11.glVertex2i(x, y);
			GL11.glEnd();


		}
		GlStateManager.shadeModel(GL11.GL_FLAT);
		GlStateManager.enableTexture2D();

		for (int[] pos : stringPositions) {
			int slot = slots.get(pos[0]);
			int xp = pos[1];
			int yp = pos[2];
			char c = (char) pos[3];

			int xsp = xp - 4;
			int ysp = yp;
			ItemStack item = baubles.getStackInSlot(slots.get(pos[0]));
			String name = "\u00a7" + c + item.getDisplayName().substring(0, item.getDisplayName().length() - 4);
			
			
			int width = fontRenderer.getStringWidth(name);

			double mod = 0.6;
			int xdp = (int) ((xp - x) * mod + x);
			int ydp = (int) ((yp - y) * mod + y);

			if (xsp < x)
				xsp -= width - 8;
			if (ysp < y)
				ysp -= 9;

			fontRenderer.drawStringWithShadow(name, xsp, ysp, 0xFFFFFF);

			mod = 0.8;
			xdp = (int) ((xp - x) * mod + x);
			ydp = (int) ((yp - y) * mod + y);
			mc.renderEngine.bindTexture(new ResourceLocation("feruchemy:textures/gui/metals/" + item.getItem().getRegistryName().getResourcePath() + ".png"));
			drawModalRectWithCustomSizedTexture(xdp - 8, ydp - 8, 0, 0, 16, 16, 16, 16);

		}
		float stime = 5F;
		float fract = Math.min(stime, timeIn + partialTicks) / stime;
		float s = 3F * fract;
		GlStateManager.enableRescaleNormal();
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		RenderHelper.enableGUIStandardItemLighting();

		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableBlend();
		GlStateManager.disableRescaleNormal();

		GlStateManager.popMatrix();
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		boolean reset = GameSettings.isKeyDown(mc.gameSettings.keyBindSneak);
		boolean increment = mouseButton == 0 ? true: false;
		toggleSelected(increment, reset);
	}

	@Override
	public void updateScreen() {
		super.updateScreen();

		if (!GameSettings.isKeyDown(Registry.toggleBands)) {
			mc.displayGuiScreen(null); // toggleSelected(); //probably not necessary to change it on exit anymore
		}

		ImmutableSet<KeyBinding> set = ImmutableSet.of(mc.gameSettings.keyBindForward, mc.gameSettings.keyBindLeft,
				mc.gameSettings.keyBindBack, mc.gameSettings.keyBindRight, mc.gameSettings.keyBindSneak,
				mc.gameSettings.keyBindSprint, mc.gameSettings.keyBindJump);
		for (KeyBinding k : set)
			KeyBinding.setKeyBindState(k.getKeyCode(), GameSettings.isKeyDown(k));

		timeIn++;
	}

	/**
	 * Toggles the metal the mouse is currently over
	 * @param increment 
	 * @param reset 
	 */
	private void toggleSelected(boolean increment, boolean reset) {
		if (slotSelected != -1) {
			int slot = slots.get(slotSelected);
			Registry.network.sendToServer(new ToggleBandPacket(slot, increment, reset));
			Minecraft.getMinecraft().player.playSound(new SoundEvent(new ResourceLocation("ui.button.click")), 0.1F,2.0F);
		}

	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	private static float mouseAngle(int x, int y, int mx, int my) {
		Vector2f baseVec = new Vector2f(1F, 0F);
		Vector2f mouseVec = new Vector2f(mx - x, my - y);

		float ang = (float) (Math.acos(Vector2f.dot(baseVec, mouseVec) / (baseVec.length() * mouseVec.length()))
				* (180F / Math.PI));
		return my < y ? 360F - ang : ang;
	}
}