package com.wesserboy.overlays.helpers;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.Vec3d;

public class ModRenderHelper {
	
	public static void translateToWorldCoords(float partialTicks){
		Entity player = Minecraft.getInstance().getRenderViewEntity();
		Vec3d camPos = Minecraft.getInstance().gameRenderer.getActiveRenderInfo().getProjectedView();
		GlStateManager.rotatef(player.getPitchYaw().x, 1, 0, 0);
		GlStateManager.rotatef(player.getPitchYaw().y, 0, 1, 0);
		GlStateManager.rotatef(180, 0, 1, 0);

		// translate draw origin to 0,0,0. This allows the use of world coords in draw calls.
		GL11.glTranslated(-camPos.x, -camPos.y, -camPos.z);

	}

}
