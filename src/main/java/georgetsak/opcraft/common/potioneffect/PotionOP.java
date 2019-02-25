package georgetsak.opcraft.common.potioneffect;

import georgetsak.opcraft.OPCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;


public class PotionOP extends Potion {
	private final ResourceLocation iconTexture;

	public int id;
	

	public PotionOP(boolean isBadEffect, int liquidColor, String name, int id) {
		super(isBadEffect, liquidColor);
		this.id = id;
		setPotionName(this, name);
		iconTexture = insideDome;
	}


	public PotionOP(boolean isBadEffect, int liquidR, int liquidG, int liquidB, String name, int id) {
		this(isBadEffect, new Color(liquidR, liquidG, liquidB).getRGB(), name, id);
	}

	public static void setPotionName(Potion potion, String potionName) {
		potion.setRegistryName(potionName);
		potion.setPotionName("effect." + potion.getRegistryName().toString());
	}

	@Override
	public boolean hasStatusIcon() {
		return false;
	}

	ResourceLocation insideDome = new ResourceLocation(OPCraft.MODID, "textures/items/insidedome.png");

	@SideOnly(Side.CLIENT)
	@Override
	public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
		if (mc.currentScreen != null) {

			mc.getTextureManager().bindTexture(insideDome);
			Gui.drawModalRectWithCustomSizedTexture(x + 6, y + 7, 0, 0, 18, 18, 18, 18);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
		mc.getTextureManager().bindTexture(iconTexture);
		Gui.drawModalRectWithCustomSizedTexture(x + 3, y + 3, 0, 0, 18, 18, 18, 18);
	}
	
}