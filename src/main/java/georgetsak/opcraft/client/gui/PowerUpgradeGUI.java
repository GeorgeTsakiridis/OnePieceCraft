package georgetsak.opcraft.client.gui;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.power.PowerHandler;
import georgetsak.opcraft.common.capability.devilfruitlevels.DevilFruitLevelsCap;
import georgetsak.opcraft.common.capability.devilfruitlevels.IDevilFruitLevelsCap;
import georgetsak.opcraft.common.capability.devilfruits.DevilFruitCap;
import georgetsak.opcraft.common.network.packets.common.PacketDevilFruitLevels;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

import java.awt.*;
import java.io.IOException;

public class PowerUpgradeGUI extends GuiScreen {

    int totalPowers;
    String[] names;

    private GuiButton[] upgradeButtons;
    private final ResourceLocation BACKGROUND = new ResourceLocation(OPCraft.MODID, "textures/gui/upgrade_powers.png");

    @Override
    public void initGui() {

        int fruitID = DevilFruitCap.get(Minecraft.getMinecraft().player).getPower();
        totalPowers = PowerHandler.getTotalPowersForFruit(fruitID);

        names = new String[totalPowers];
        upgradeButtons = new GuiButton[totalPowers];

        for (int i = 0; i < totalPowers; i++) {
            names[i] = PowerHandler.getPower(fruitID, i+1).getActionName();
        }

        for (int i = 0; i < totalPowers; i++) {
            int y = height/2 + (i-totalPowers/2)*40;
            upgradeButtons[i] = this.addButton(new GuiButton(i, width-70, y, "Upgrade"));
            upgradeButtons[i].width = fontRenderer.getStringWidth("Upgrade") + 10;
            upgradeButtons[i].y -= upgradeButtons[i].height/4 - 6;
        }

        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        Minecraft.getMinecraft().getTextureManager().bindTexture(BACKGROUND);
        drawModalRectWithCustomSizedTexture((this.width - 370) / 2, (this.height - 203)/2,0,0,370,203, 370, 256);
        IDevilFruitLevelsCap dfl = DevilFruitLevelsCap.get(Minecraft.getMinecraft().player);

        int middleX = width/2;
        int lastY = 0;

        drawCenteredString(fontRenderer,"Power Level Upgrade",middleX + 25,height/2 - (totalPowers/2)*40 - fontRenderer.FONT_HEIGHT*2 + 4,Color.WHITE.getRGB());

        for (int i = 0; i < totalPowers; i++) {
            int y = height/2 + (i-totalPowers/2)*40 + 6;

            int cost = getCost(dfl.getPowerLevel(i+1)+1);
            boolean isMax = dfl.getPowerLevel(i+1) == 4;
            boolean canUpgrade = (dfl.getXP() - cost) >= 0 && !isMax;
            TextFormatting textFormatting = canUpgrade ? TextFormatting.GREEN : TextFormatting.RED;

            String powerLevelString = TextFormatting.GOLD + "Power Level: " + (dfl.getPowerLevel(i+1)+1) + "/5";
            String upgradeCostString = "";
            if(!isMax) {
                upgradeCostString = TextFormatting.GOLD + "Cost to Upgrade: " + textFormatting + cost + TextFormatting.GOLD + "XP";
            }else{
                upgradeCostString = TextFormatting.GOLD + "Cost to Upgrade: " + TextFormatting.GREEN + "Maxed!";
            }
            drawCenteredString(fontRenderer, names[i], middleX - fontRenderer.getStringWidth(upgradeCostString) + 5, y, Color.WHITE.getRGB());
            drawCenteredString(fontRenderer, powerLevelString, middleX+25, y - fontRenderer.FONT_HEIGHT/2-2, Color.WHITE.getRGB());
            drawCenteredString(fontRenderer, upgradeCostString, middleX+25, y + fontRenderer.FONT_HEIGHT/2+2, Color.WHITE.getRGB());
            upgradeButtons[i].enabled = canUpgrade;
            upgradeButtons[i].x = middleX + fontRenderer.getStringWidth(upgradeCostString)/2 + 45;
            lastY = y + fontRenderer.FONT_HEIGHT/2+2;
        }

        drawCenteredString(fontRenderer,"Available XP: " + dfl.getXP() + "XP",width/2 + 25,lastY + fontRenderer.FONT_HEIGHT + 6, Color.GREEN.getRGB());

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        IDevilFruitLevelsCap dfl = DevilFruitLevelsCap.get(Minecraft.getMinecraft().player);

        switch (button.id){
            case 0:
                dfl.addXP(-getCost(dfl.getPowerLevel(1)+1));
                dfl.addPowerLevel(1);
                break;
            case 1:
                dfl.addXP(-getCost(dfl.getPowerLevel(2)+1));
                dfl.addPowerLevel(2);
                break;
            case 2:
                dfl.addXP(-getCost(dfl.getPowerLevel(3)+1));
                dfl.addPowerLevel(3);
                break;
            case 3:
                dfl.addXP(-getCost(dfl.getPowerLevel(4)+1));
                dfl.addPowerLevel(4);
                break;

        }

        PacketDispatcher.sendToServer(new PacketDevilFruitLevels(dfl));

    }

    public int getCost(int level){
        return (int)(500 + 30 * Math.pow(level-1,1.5));
    }

}
