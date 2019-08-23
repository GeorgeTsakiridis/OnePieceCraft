package georgetsak.opcraft.client.gui.overlay;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.power.Power;
import georgetsak.opcraft.common.power.PowerHandler;
import georgetsak.opcraft.client.power.PowerSelector;
import georgetsak.opcraft.common.capability.devilfruitlevels.DevilFruitLevelsCap;
import georgetsak.opcraft.common.capability.devilfruitlevels.IDevilFruitLevelsCap;
import georgetsak.opcraft.common.item.devilfruits.DevilFruitAssetsManager;
import georgetsak.opcraft.common.util.MathUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.text.DecimalFormat;

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
                renderPowerSelector(scaledResolution, fruitID);
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

    private void renderPowerSelector(ScaledResolution scaledResolution, int fruitID){
        Minecraft mc = Minecraft.getMinecraft();
        IDevilFruitLevelsCap dfl = DevilFruitLevelsCap.get(mc.player);

        int totalPowers = PowerHandler.getTotalPowersForFruit(fruitID);
        int startX = (int)(scaledResolution.getScaledWidth()/2f + 110);
        startX = (scaledResolution.getScaledWidth()+startX)/2;
        int spacing = 42;
        int requiredSpace = spacing * totalPowers;

        for(int i = 0; i < totalPowers; i++) {
            Power selectedPower = PowerSelector.getSelectedPower();

            GL11.glColor4f(1f, 1f, 1f, selectedPower.getKey() == i+1 ? 1f : 0.25f);
            int x = startX + spacing*i - requiredSpace/2;

            mc.getTextureManager().bindTexture(FRAME);
            Gui.drawScaledCustomSizeModalRect(x, scaledResolution.getScaledHeight() - 40, 0, 0, 64, 64, 38, 38, 64, 64);

            mc.getTextureManager().bindTexture(PowerHandler.getPower(fruitID,i+1).getResourceLocation());
            Gui.drawScaledCustomSizeModalRect(x+2, scaledResolution.getScaledHeight() - 38, 0, 0, 64, 64, 34, 34, 64, 64);

        }

        for(int i = 0; i < totalPowers; i++){
            Power power = PowerHandler.getPower(fruitID,i+1);
            int x = startX + spacing*i - requiredSpace/2;

            float percentage = MathUtils.getPercentage(dfl.getPowerCooldown(power.getKey()) - power.getCurrentCooldown(), dfl.getPowerCooldown(power.getKey()));
            Gui.drawRect(x,scaledResolution.getScaledHeight() - 40 + (int)(percentage*38),x + 38,scaledResolution.getScaledHeight()-2, new Color(254,0,0,100).getRGB());

            DecimalFormat decimalFormat = new DecimalFormat("#0.00");
            String string;
            int cooldown = power.getCurrentCooldown();
            if(cooldown > 200){
                string = (int)(power.getCurrentCooldown()/20f) + "s";
            }else if(cooldown > 0){
                string = decimalFormat.format(power.getCurrentCooldown() / 20d) + "s";
            }else{
                string = "Ready!";
            }

            if(mc.player.isSneaking()) {
                mc.fontRenderer.drawStringWithShadow("C:" + dfl.getPowerCooldownLevel(power.getKey()) + "/" + (power.canCooldownBeUpgraded()?"5":"1"), x+1, scaledResolution.getScaledHeight() - 10, Color.WHITE.getRGB());
                mc.fontRenderer.drawStringWithShadow("P:" + (dfl.getPowerLevel(power.getKey())+1) + "/" + (power.canPowerBeUpgraded()?"5":"1"), x+1, scaledResolution.getScaledHeight() - 39, Color.WHITE.getRGB());
                mc.fontRenderer.drawStringWithShadow("C: Cooldown Level", scaledResolution.getScaledWidth() - 200,scaledResolution.getScaledHeight() - 50, Color.WHITE.getRGB());
                mc.fontRenderer.drawStringWithShadow("P: Power Level", scaledResolution.getScaledWidth() - 200,scaledResolution.getScaledHeight() - 60, Color.WHITE.getRGB());
            }else{
                if(cooldown == 0) {
                    String s = decimalFormat.format(dfl.getPowerCooldown(power.getKey()) / 20d) + "s";
                    mc.fontRenderer.drawStringWithShadow(s, x + 37 - mc.fontRenderer.getStringWidth(s), scaledResolution.getScaledHeight() - 39, Color.WHITE.getRGB());
                }
                mc.fontRenderer.drawStringWithShadow(string, x + 1,scaledResolution.getScaledHeight() - 11, cooldown == 0 ? Color.GREEN.darker().getRGB() : Color.WHITE.getRGB());
            }
        }

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
