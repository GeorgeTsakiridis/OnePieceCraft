package georgetsak.opcraft.client.gui.overlay;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.power.PowerHandler;
import georgetsak.opcraft.client.power.PowerSelector;
import georgetsak.opcraft.common.item.devilfruits.DevilFruitAssetsManager;
import georgetsak.opcraft.common.util.MathUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class DevilFruitRenderOverlay {

    private	int resolutionX;
    private int resolutionY;
    private float transparency = 0.25f;
    private static final ResourceLocation BAR_TEXTURE = new ResourceLocation("onepiececraft:textures/gui/cooldownbar.png");
    private static final ResourceLocation FRAME = new ResourceLocation(OPCraft.MODID, "textures/gui/powers_icons/frame.png");

    public void render(int fruitID, int cooldown, int cooldownMax, ScaledResolution scaledResolution, int energy) {
        resolutionY = scaledResolution.getScaledHeight();
        resolutionX = scaledResolution.getScaledWidth();

        int i = (int) (MathUtils.getPercentage(cooldown, cooldownMax)*85f);
        int rx = resolutionX / 200 + (resolutionX / 190);
        int ry = resolutionY - resolutionY / 16;

        Minecraft mc = Minecraft.getMinecraft();

        String fruitName = TextFormatting.BOLD + getPowerName(fruitID);

        mc.fontRenderer.drawString(I18n.format(fruitName), rx + 19, ry - 1, Color.WHITE.getRGB());

        if (!OPCraft.IS_RELEASE_VERSION) {
            mc.fontRenderer.drawString(I18n.format(TextFormatting.RED + " Unreleased version! (" + OPCraft.VERSION + ")" + TextFormatting.RESET), rx + 13, ry - 10 - mc.fontRenderer.FONT_HEIGHT, Color.WHITE.getRGB());
        }

        if (fruitID != 0) {
            mc.getTextureManager().bindTexture(getIcon(fruitID));
            Gui.drawModalRectWithCustomSizedTexture(2, ry - mc.fontRenderer.FONT_HEIGHT, 0, 0, 18, 18, 18, 18);

            if (PowerSelector.getPrevIndex() != 0 && PowerSelector.getNextIndex() != 0) {
                renderPowerSelector(fruitID);
            }

        }

        if (fruitID != 0 && PowerSelector.getSelectedPower() != null) {
            mc.fontRenderer.drawString(PowerSelector.getSelectedPower().getActionName(), rx + 19, ry - mc.fontRenderer.FONT_HEIGHT - 1, Color.WHITE.getRGB());
        }

        drawCooldownBar(ry, cooldown, i);
    }

    private void drawCooldownBar(int height, int cooldown, int i){
        Minecraft mc = Minecraft.getMinecraft();
        mc.getTextureManager().bindTexture(BAR_TEXTURE);
        if (cooldown == 0) {
            mc.ingameGUI.drawTexturedModalRect(22, height + 8, 0, 10, 85, 5);
        } else {
            mc.ingameGUI.drawTexturedModalRect(22, height + 8, 0, 0, 85, 5);
            mc.ingameGUI.drawTexturedModalRect(23, height + 8, i, 5, 85, 5);
        }
    }

    public void drawEnergyBar(ScaledResolution scaledResolution, int energy) {

        int x = scaledResolution.getScaledWidth() / 2 - 91;
        int l = scaledResolution.getScaledHeight() - 32 + 3;

        Minecraft mc = Minecraft.getMinecraft();
        mc.getTextureManager().bindTexture(BAR_TEXTURE);

        mc.ingameGUI.drawTexturedModalRect(x, l, 0, 15, 182, 5);
        mc.ingameGUI.drawTexturedModalRect(x, l, 0, 20, (int)(MathUtils.getPercentage(energy, 200)*182f), 5);
        mc.ingameGUI.drawTexturedModalRect(x, l, 0, 25, 182, 5);
    }

    private void renderPowerSelector(int fruitID){
        Minecraft mc = Minecraft.getMinecraft();
        if(PowerHandler.getTotalPowersForFruit(fruitID) > 1) {
            GL11.glColor4f(1f, 1f, 1f, transparency);
            mc.getTextureManager().bindTexture(FRAME);
            Gui.drawScaledCustomSizeModalRect(resolutionX - 35, resolutionY - 35, 0, 0, 64, 64, 44, 44, 64, 64);
            mc.getTextureManager().bindTexture(PowerSelector.getIcons().get(0));
            Gui.drawScaledCustomSizeModalRect(resolutionX - 32, resolutionY - 32, 0, 0, 64, 64, 40, 40, 64, 64);
            mc.getTextureManager().bindTexture(FRAME);
            Gui.drawScaledCustomSizeModalRect(resolutionX - 69, resolutionY - 69, 0, 0, 64, 64, 44, 44, 64, 64);
            mc.getTextureManager().bindTexture(PowerSelector.getIcons().get(2));
            Gui.drawScaledCustomSizeModalRect(resolutionX - 67, resolutionY - 67, 0, 0, 64, 64, 40, 40, 64, 64);
        }
        float temp = transparency;
        if (transparency * 1.45 > 1) {
            temp = 1f;
        }
        GL11.glColor4f(1f, 1f, 1f, temp);
        mc.getTextureManager().bindTexture(FRAME);
        Gui.drawScaledCustomSizeModalRect(resolutionX - 52, resolutionY - 52, 0, 0, 64, 64, 44, 44, 64, 64);
        mc.getTextureManager().bindTexture(PowerSelector.getIcons().get(1));
        Gui.drawScaledCustomSizeModalRect(resolutionX - 50, resolutionY - 50, 0, 0, 64, 64, 40, 40, 64, 64);
        GL11.glColor4f(1f, 1f, 1f, 1f);

    }

    public void resetTransparency(){
        this.transparency = 1f;
    }

    public void decreaseTransparency() {
        if (transparency > 0.25) {
            this.transparency -= 0.01875f;
        }
    }

    private ResourceLocation getIcon(int id) {
        return DevilFruitAssetsManager.getDevilFruitAsset(id).getResourceLocation();
    }

    private static String getPowerName(int id){
        return DevilFruitAssetsManager.getDevilFruitAsset(id).getName();
    }


}
