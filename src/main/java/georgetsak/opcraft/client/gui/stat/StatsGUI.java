package georgetsak.opcraft.client.gui.stat;

import com.google.common.collect.Lists;
import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.capability.stats.normal.IStatsNormalCap;
import georgetsak.opcraft.common.capability.stats.normal.StatsNormalCap;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import georgetsak.opcraft.common.network.packets.server.PacketUpdateXPLevelsServer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.io.IOException;

/**
 * Created by GeorgeTsak on 7/14/2017.
 */
public class StatsGUI extends GuiScreen{

    PowerUpButton levelUpHealth;
    PowerUpButton levelUpAttack;
    PowerUpButton levelUpDefence;
    PowerUpButton levelUpSpeed;

    static String insufficientLevels = "Insufficient XP Levels!";
    static ResourceLocation stats_gui = new ResourceLocation(OPCraft.MODID, "textures/gui/stats.png");
    IStatsNormalCap stats = StatsNormalCap.get(Minecraft.getMinecraft().player);

    @Override
    public void initGui() {

        levelUpHealth = this.addButton(new HealthButton(0, this.width/2 - 90 - 18, 130, 1, stats));
        levelUpAttack = this.addButton(new AttackButton(1, this.width/2 - 31 - 18, 130, 1, stats));
        levelUpDefence = this.addButton(new DefenceButton(2, this.width/2 + 31 - 18, 130, 1, stats));
        levelUpSpeed = this.addButton(new SpeedButton(3, this.width/2 + 90 - 18, 130, 1, stats));
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        int i = (this.width - 242) / 2;
        stats = StatsNormalCap.get(Minecraft.getMinecraft().player);

        Minecraft.getMinecraft().getTextureManager().bindTexture(stats_gui);
        drawTexturedModalRect(i, 5, 0, 0, 242, 166);

        String name = "Stats Upgrade";

        drawString(fontRenderer, name, this.width/2 - this.fontRenderer.getStringWidth(name)/2, 10, Color.WHITE.getRGB());

        String power1 = "Health";
        String power2 = "Attack";
        String power3 = "Defence";
        String power4 = "Speed";

        drawString(fontRenderer, power1, this.width/2 - 90 - this.fontRenderer.getStringWidth(power1)/2, 20 + fontRenderer.FONT_HEIGHT, Color.WHITE.getRGB());
        drawString(fontRenderer, power2, this.width/2 - 31 - this.fontRenderer.getStringWidth(power2)/2, 20 + fontRenderer.FONT_HEIGHT, Color.WHITE.getRGB());
        drawString(fontRenderer, power3, this.width/2 + 31 - this.fontRenderer.getStringWidth(power3)/2, 20 + fontRenderer.FONT_HEIGHT, Color.WHITE.getRGB());
        drawString(fontRenderer, power4, this.width/2 + 90 - this.fontRenderer.getStringWidth(power4)/2, 20 + fontRenderer.FONT_HEIGHT, Color.WHITE.getRGB());

        String level1 = stats.getHealthLevel() + " / 10";
        String level2 = stats.getAttackLevel() + " / 10";
        String level3 = stats.getDefenceLevel() + " / 10";
        String level4 = stats.getSpeedLevel() + " / 10";

        if(stats.getHealthLevel() == 10){level1 = "MAX"; this.levelUpHealth.enabled = false;}
        if(stats.getAttackLevel() == 10){level2 = "MAX"; this.levelUpAttack.enabled = false;}
        if(stats.getDefenceLevel() == 10){level3 = "MAX"; this.levelUpDefence.enabled = false;}
        if(stats.getSpeedLevel() == 10){level4 = "MAX"; this.levelUpSpeed.enabled = false;}

        EntityPlayer ep = Minecraft.getMinecraft().player;

        if(!stats.canUpgrade(1, ep)) {
            this.levelUpHealth.mode = 0;
        }
        if(!stats.canUpgrade(2, ep)) {
            this.levelUpAttack.mode = 0;
        }
        if(!stats.canUpgrade(3, ep)) {
            this.levelUpDefence.mode = 0;
        }
        if(!stats.canUpgrade(4, ep)) {
            this.levelUpSpeed.mode = 0;
        }

        drawString(fontRenderer, level1, this.width/2 - 90 - this.fontRenderer.getStringWidth(level1)/2, 100, Color.WHITE.getRGB());
        drawString(fontRenderer, level2, this.width/2 - 31 - this.fontRenderer.getStringWidth(level2)/2, 100, Color.WHITE.getRGB());
        drawString(fontRenderer, level3, this.width/2 + 31 - this.fontRenderer.getStringWidth(level3)/2, 100, Color.WHITE.getRGB());
        drawString(fontRenderer, level4, this.width/2 + 90 - this.fontRenderer.getStringWidth(level4)/2, 100, Color.WHITE.getRGB());

        String curBonus1 = "+ " + TextFormatting.GREEN + (stats.getHealthLevel() * 2) + TextFormatting.RESET + " HP";
        String curBonus3 = "+" + TextFormatting.GREEN + (stats.getDefenceLevel() * 5) + TextFormatting.RESET + "% DEF";
        int speed = stats.getSpeedLevel() * 3;
        float speed2 = speed / 10F;
        int attack = stats.getAttackLevel() * 25;
        float attack2 = attack /100F;
        String curBonus4 = "x" + TextFormatting.GREEN + speed2 + TextFormatting.RESET + " SPEED";
        String curBonus2 = "x" + TextFormatting.GREEN + (attack2) + TextFormatting.RESET + " ATK";

        drawString(fontRenderer, I18n.format(curBonus1), this.width/2 - 90 - this.fontRenderer.getStringWidth(curBonus1)/2, 100 + fontRenderer.FONT_HEIGHT, Color.WHITE.getRGB());
        drawString(fontRenderer, I18n.format(curBonus2), this.width/2 - 31 - this.fontRenderer.getStringWidth(curBonus2)/2, 100 + fontRenderer.FONT_HEIGHT, Color.WHITE.getRGB());
        drawString(fontRenderer, curBonus3, this.width/2 + 31 - this.fontRenderer.getStringWidth(curBonus3)/2, 100 + fontRenderer.FONT_HEIGHT, Color.WHITE.getRGB());
        drawString(fontRenderer, curBonus4, this.width/2 + 90 - this.fontRenderer.getStringWidth(curBonus4)/2, 100 + fontRenderer.FONT_HEIGHT, Color.WHITE.getRGB());

        super.drawScreen(mouseX, mouseY, partialTicks);

        for (GuiButton guibutton : this.buttonList)
        {
            if(guibutton.enabled){
                guibutton.drawButtonForegroundLayer(mouseX, mouseY);
        }
    }

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
        stats = StatsNormalCap.get(Minecraft.getMinecraft().player);
        EntityPlayer ep = Minecraft.getMinecraft().player;

        switch (button.id){
            case 0:
                if(((PowerUpButton)(button)).mode != 0) {
                    ep.addExperienceLevel(-stats.getUpgradeCost(1));
                    stats.setHealthLevel(stats.getHealthLevel() + 1);
                }
                break;
            case 1:
                if(((PowerUpButton)(button)).mode != 0) {
                    ep.addExperienceLevel(-stats.getUpgradeCost(2));
                    stats.setAttackLevel(stats.getAttackLevel() + 1);
                }
                break;
            case 2:
                if(((PowerUpButton)(button)).mode != 0) {
                    ep.addExperienceLevel(-stats.getUpgradeCost(3));
                    stats.setDefenceLevel(stats.getDefenceLevel() + 1);
                }
                break;
            case 3:
                if(((PowerUpButton)(button)).mode != 0) {
                    ep.addExperienceLevel(-stats.getUpgradeCost(4));
                    stats.setSpeedLevel(stats.getSpeedLevel() + 1);
                }
                break;
        }

        PacketDispatcher.sendToServer(new PacketUpdateXPLevelsServer(ep.experienceLevel));
        stats.updateToSever(stats);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }


    @SideOnly(Side.CLIENT)
    class HealthButton extends PowerUpButton{

        IStatsNormalCap stats;

        public HealthButton(int id, int x, int y, int mode, IStatsNormalCap stats) {
            super(id, x, y, mode);
            this.stats = stats;
        }

        @Override
        public void drawButtonForegroundLayer(int mouseX, int mouseY) {
            java.util.List<String> list = Lists.newArrayList();
            if(mode == 0){
                list.add(TextFormatting.RED + StatsGUI.insufficientLevels);
            }
            list.add("Will consume " + TextFormatting.GREEN + stats.getUpgradeCost(1) + TextFormatting.RESET + " XP Levels");
            list.add("After upgrade: +" + TextFormatting.GREEN + ((stats.getHealthLevel() + 1) * 2) + TextFormatting.RESET +" HP");
            if(mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height) {
                StatsGUI.this.drawHoveringText(list, mouseX, mouseY);
            }
            super.drawButtonForegroundLayer(mouseX, mouseY);
        }
    }

    class AttackButton extends PowerUpButton{

        IStatsNormalCap stats;

        public AttackButton(int id, int x, int y, int mode, IStatsNormalCap stats) {
            super(id, x, y, mode);
            this.stats = stats;
        }

        @Override
        public void drawButtonForegroundLayer(int mouseX, int mouseY) {
            java.util.List<String> list = Lists.newArrayList();
            if(mode == 0){
                list.add(TextFormatting.RED + StatsGUI.insufficientLevels);
            }

            int attack = (stats.getAttackLevel() + 1) * 25;
            float attack2 = attack/100F;

            list.add("Will consume " + TextFormatting.GREEN + stats.getUpgradeCost(2) + TextFormatting.RESET + " XP Levels");
            list.add("After upgrade: x" + TextFormatting.GREEN + (attack2) + TextFormatting.RESET +" ATK");
            if(mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height) {
                StatsGUI.this.drawHoveringText(list, mouseX, mouseY);
            }
            super.drawButtonForegroundLayer(mouseX, mouseY);
        }
    }

    class DefenceButton extends PowerUpButton{

        IStatsNormalCap stats;

        public DefenceButton(int id, int x, int y, int mode, IStatsNormalCap stats) {
            super(id, x, y, mode);
            this.stats = stats;
        }

        @Override
        public void drawButtonForegroundLayer(int mouseX, int mouseY) {
            java.util.List<String> list = Lists.newArrayList();
            if(mode == 0){
                list.add(TextFormatting.RED + StatsGUI.insufficientLevels);
            }
            list.add("Will consume " + TextFormatting.GREEN + stats.getUpgradeCost(3) + TextFormatting.RESET + " XP Levels");
            list.add("After upgrade: +" + TextFormatting.GREEN + ((stats.getDefenceLevel() + 1) * 5) + TextFormatting.RESET +" % DEF");
            if(mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height) {
                StatsGUI.this.drawHoveringText(list, mouseX, mouseY);
            }
            super.drawButtonForegroundLayer(mouseX, mouseY);
        }
    }

    class SpeedButton extends PowerUpButton{

        IStatsNormalCap stats;

        public SpeedButton(int id, int x, int y, int mode, IStatsNormalCap stats) {
            super(id, x, y, mode);
            this.stats = stats;
        }

        @Override
        public void drawButtonForegroundLayer(int mouseX, int mouseY) {
            java.util.List<String> list = Lists.newArrayList();
            if(mode == 0){
                list.add(TextFormatting.RED + StatsGUI.insufficientLevels);
            }
            list.add("Will consume " + TextFormatting.GREEN + stats.getUpgradeCost(4) + TextFormatting.RESET + " XP Levels");

            int speed = (stats.getSpeedLevel() + 1) * 3;
            float speed2 = speed / 10F;
            list.add("After upgrade: x" + TextFormatting.GREEN + speed2 + TextFormatting.RESET +" SPEED");
            if(mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height) {
                StatsGUI.this.drawHoveringText(list, mouseX, mouseY);
            }
            super.drawButtonForegroundLayer(mouseX, mouseY);
        }
    }


    @SideOnly(Side.CLIENT)
    class PowerUpButton extends GuiButton
    {
        int mode = 0;

        public PowerUpButton(int id, int x, int y, int mode)
        {
            super(id, x, y, 35, 15, "");
            this.mode = mode;
        }

        /**
         * Draws this button to the screen.
         */
        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
        {
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                mc.getTextureManager().bindTexture(StatsGUI.stats_gui);

            boolean flag = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;

            if(mode != 0) {
                if (flag){this.mode = 2;
                }
                else {
                    this.mode = 1;
                }
            }

            int i = 0;
                int j = 0;

                if(mode == 0 || !enabled) {
                    j = 166;
                }
                if(mode == 1) {
                    j = 181;
                }
                if(mode == 2) {
                    j = 196;
                }

                this.drawTexturedModalRect(this.x, this.y, i, j, 35, 15);

        }

    }
}