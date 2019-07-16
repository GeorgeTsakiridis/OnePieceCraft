package georgetsak.opcraft.client.gui.stat;

import com.google.common.collect.Lists;
import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.capability.haki.HakiCap;
import georgetsak.opcraft.common.capability.haki.IHakiCap;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import georgetsak.opcraft.common.network.packets.common.HakiPacket;
import georgetsak.opcraft.common.network.packets.server.UpdateXPLevelsServerPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
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
public class HakiGUI extends GuiScreen{

    PowerUpButton levelUpDodge;
    PowerUpButton levelUpEmperor;
    PowerUpButton levelUpAttack;
    PowerUpButton levelUpDefence;
    
    static String insufficientLevels = "Insufficient XP Levels!";
    static ResourceLocation haki_gui = new ResourceLocation(OPCraft.MODID, "textures/gui/haki.png");
    IHakiCap haki = HakiCap.get(Minecraft.getMinecraft().player);

    @Override
    public void initGui() {

        levelUpDodge = this.addButton(new DodgeButton(0, this.width/2 - 90 - 18, 130, 1, haki));
        levelUpEmperor = this.addButton(new EmperorButton(1, this.width/2 - 31 - 18, 130, 1, haki));
        levelUpAttack = this.addButton(new AttackButton(2, this.width/2 + 31 - 18, 130, 1, haki));
        levelUpDefence = this.addButton(new DefenceButton(3, this.width/2 + 90 - 18, 130, 1, haki));

        super.initGui();
    }



    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        int i = (this.width - 256) / 2;
        haki = HakiCap.get(Minecraft.getMinecraft().player);

        Minecraft.getMinecraft().getTextureManager().bindTexture(haki_gui);
        drawTexturedModalRect(i, 5, 0, 0, 256, 166);

        String name = "Haki Upgrade";

        drawString(fontRenderer, name, this.width/2 - this.fontRenderer.getStringWidth(name)/2, 10, Color.WHITE.getRGB());

        String power1 = "Mantra";
        String power2 = "Conqueror";
        String power3a = "Busoshoku";
        String power3b = "(ATK)";
        String power4a = "Busoshoku";
        String power4b = "(DEF)";

        drawString(fontRenderer, power1, this.width/2 - 90 - this.fontRenderer.getStringWidth(power1)/2, 20 + fontRenderer.FONT_HEIGHT, Color.WHITE.getRGB());
        drawString(fontRenderer, power2, this.width/2 - 31 - this.fontRenderer.getStringWidth(power2)/2, 20 + fontRenderer.FONT_HEIGHT, Color.WHITE.getRGB());
        drawString(fontRenderer, power3a, this.width/2 + 31 - this.fontRenderer.getStringWidth(power3a)/2, 20 + fontRenderer.FONT_HEIGHT, Color.WHITE.getRGB());
        drawString(fontRenderer, power3b, this.width/2 + 31 - this.fontRenderer.getStringWidth(power3b)/2, 20 + fontRenderer.FONT_HEIGHT*2, Color.WHITE.getRGB());
        drawString(fontRenderer, power4a, this.width/2 + 90 - this.fontRenderer.getStringWidth(power4a)/2, 20 + fontRenderer.FONT_HEIGHT, Color.WHITE.getRGB());
        drawString(fontRenderer, power4b, this.width/2 + 90 - this.fontRenderer.getStringWidth(power4b)/2, 20 + fontRenderer.FONT_HEIGHT*2, Color.WHITE.getRGB());

        String level1 = haki.getDodgeLevel() + " / 5";
        String level2 = haki.getEmperorLevel() + " / 5";
        String level3 = haki.getAttackLevel() + " / 5";
        String level4 = haki.getDefenceLevel() + " / 5";

        if(haki.getDodgeLevel() == 5){level1 = "MAX"; this.levelUpDodge.enabled = false;}
        if(haki.getEmperorLevel() == 5){level2 = "MAX"; this.levelUpEmperor.enabled = false;}
        if(haki.getAttackLevel() == 5){level3 = "MAX"; this.levelUpAttack.enabled = false;}
        if(haki.getDefenceLevel() == 5){level4 = "MAX"; this.levelUpDefence.enabled = false;}

        EntityPlayer ep = Minecraft.getMinecraft().player;

        if(!haki.canUpgrade(ep, 1)) {
            this.levelUpDodge.mode = 0;
        }
        if(!haki.canUpgrade(ep, 2) || !haki.isEmperorHakiUnlocked()) {
            this.levelUpEmperor.mode = 0;
        }
        if(!haki.canUpgrade(ep,3)) {
            this.levelUpAttack.mode = 0;
        }
        if(!haki.canUpgrade(ep, 4)) {
            this.levelUpDefence.mode = 0;
        }

        drawString(fontRenderer, level1, this.width/2 - 90 - this.fontRenderer.getStringWidth(level1)/2, 100, Color.WHITE.getRGB());
        drawString(fontRenderer, level2, this.width/2 - 31 - this.fontRenderer.getStringWidth(level2)/2, 100, Color.WHITE.getRGB());
        drawString(fontRenderer, level3, this.width/2 + 31 - this.fontRenderer.getStringWidth(level3)/2, 100, Color.WHITE.getRGB());
        drawString(fontRenderer, level4, this.width/2 + 90 - this.fontRenderer.getStringWidth(level4)/2, 100, Color.WHITE.getRGB());

        String curBonus1 = ""+TextFormatting.GREEN  + (haki.getDodgeLevel()*10) + TextFormatting.RESET + "% Chance";
        String curBonus3 = "x" + TextFormatting.GREEN + "1." + (haki.getAttackLevel()) + TextFormatting.RESET + " ATK";
        String curBonus4 = "x" + TextFormatting.GREEN + "1." + (haki.getDefenceLevel()) + TextFormatting.RESET + " DEF";
        //String curBonus2 = "x" + TextFormatting.GREEN + (attack2) + TextFormatting.RESET + " DEF";

        drawString(fontRenderer, curBonus1, this.width/2 - 90 - this.fontRenderer.getStringWidth(curBonus1)/2, 100 + fontRenderer.FONT_HEIGHT, Color.WHITE.getRGB());
        //drawString(fontRenderer, I18n.format(curBonus2), this.width/2 - 31 - this.fontRenderer.getStringWidth(curBonus2)/2, 100 + fontRenderer.FONT_HEIGHT, Color.WHITE.getRGB());
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
        haki = HakiCap.get(Minecraft.getMinecraft().player);
        EntityPlayer ep = Minecraft.getMinecraft().player;

        switch (button.id){
            case 0:
                if(((PowerUpButton)(button)).mode != 0) {
                    ep.addExperienceLevel(-haki.getUpgradeCost(1));
                    haki.setDodgeLevel(haki.getDodgeLevel() + 1);
                }
                break;
            case 1:
                if(((PowerUpButton)(button)).mode != 0) {
                    ep.addExperienceLevel(-haki.getUpgradeCost(2));
                    haki.setEmperorLevel(haki.getEmperorLevel() + 1);
                }
                break;
            case 2:
                if(((PowerUpButton)(button)).mode != 0) {
                    ep.addExperienceLevel(-haki.getUpgradeCost(3));
                    haki.setAttackLevel(haki.getAttackLevel() + 1);
                }
                break;
            case 3:
                if(((PowerUpButton)(button)).mode != 0) {
                    ep.addExperienceLevel(-haki.getUpgradeCost(4));
                    haki.setDefenceLevel(haki.getDefenceLevel() + 1);
                }
                break;
        }

        PacketDispatcher.sendToServer(new UpdateXPLevelsServerPacket(ep.experienceLevel));
        PacketDispatcher.sendToServer(new HakiPacket(haki));
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
    class DodgeButton extends PowerUpButton{

        IHakiCap haki;

        public DodgeButton(int id, int x, int y, int mode, IHakiCap haki) {
            super(id, x, y, mode);
            this.haki = haki;
        }

        @Override
        public void drawButtonForegroundLayer(int mouseX, int mouseY) {
            java.util.List<String> list = Lists.<String>newArrayList();
            if(mode == 0){
                list.add(TextFormatting.RED + HakiGUI.insufficientLevels);
            }
            list.add("Will consume " + TextFormatting.GREEN + haki.getUpgradeCost(1) + TextFormatting.RESET + " XP Levels");
            list.add("After upgrade: +" + TextFormatting.GREEN + ((haki.getDodgeLevel() + 1) * 10) + TextFormatting.RESET +"% Dodge Chance");
            if(mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height) {
                HakiGUI.this.drawHoveringText(list, mouseX, mouseY);
            }
            super.drawButtonForegroundLayer(mouseX, mouseY);
        }
    }

    class EmperorButton extends PowerUpButton{

        IHakiCap haki;

        public EmperorButton(int id, int x, int y, int mode, IHakiCap haki) {
            super(id, x, y, mode);
            this.haki = haki;
        }

        @Override
        public void drawButtonForegroundLayer(int mouseX, int mouseY) {
            java.util.List<String> list = Lists.<String>newArrayList();
            if(!haki.isEmperorHakiUnlocked()){
                list.add(TextFormatting.RED + "You haven't learned this Haki yet!");
                list.add(TextFormatting.RED + "Find and talk to Rayleigh to unlock!");
            }else {
                if (mode == 0) {
                    list.add(TextFormatting.RED + HakiGUI.insufficientLevels);
                }

                list.add("Will consume " + TextFormatting.GREEN + haki.getUpgradeCost(2) + TextFormatting.RESET + " XP Levels");
                list.add("After upgrade: " + TextFormatting.GREEN + (haki.getEmperorLevel() + 1) * 2 + TextFormatting.RESET + " Hearts Damage to Players");
                list.add("" + TextFormatting.GREEN + (10 + (haki.getEmperorLevel() + 1) * 2) + TextFormatting.RESET + " Blocks range");
            }
            if(mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height) {
                HakiGUI.this.drawHoveringText(list, mouseX, mouseY);
            }
            super.drawButtonForegroundLayer(mouseX, mouseY);
        }
    }

    class AttackButton extends PowerUpButton{

        IHakiCap haki;

        public AttackButton(int id, int x, int y, int mode, IHakiCap haki) {
            super(id, x, y, mode);
            this.haki = haki;
        }

        @Override
        public void drawButtonForegroundLayer(int mouseX, int mouseY) {
            java.util.List<String> list = Lists.<String>newArrayList();
            if(mode == 0){
                list.add(TextFormatting.RED + HakiGUI.insufficientLevels);
            }
            list.add("Will consume " + TextFormatting.GREEN + haki.getUpgradeCost(3) + TextFormatting.RESET + " XP Levels");
            list.add("After upgrade: x" + TextFormatting.GREEN + "1." + (haki.getAttackLevel()) + TextFormatting.RESET +" ATK to Devil Fruit users");
            if(mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height) {
                HakiGUI.this.drawHoveringText(list, mouseX, mouseY);
            }
            super.drawButtonForegroundLayer(mouseX, mouseY);
        }
    }

    class DefenceButton extends PowerUpButton{

        IHakiCap haki;

        public DefenceButton(int id, int x, int y, int mode, IHakiCap haki) {
            super(id, x, y, mode);
            this.haki = haki;
        }

        @Override
        public void drawButtonForegroundLayer(int mouseX, int mouseY) {
            java.util.List<String> list = Lists.<String>newArrayList();
            if(mode == 0){
                list.add(TextFormatting.RED + HakiGUI.insufficientLevels);
            }
            list.add("Will consume " + TextFormatting.GREEN + haki.getUpgradeCost(4) + TextFormatting.RESET + " XP Levels");

            list.add("After upgrade: x" + TextFormatting.GREEN + "1." + haki.getDefenceLevel() + TextFormatting.RESET +" DEF from Devil Fruit users");
            if(mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height) {
                HakiGUI.this.drawHoveringText(list, mouseX, mouseY);
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

        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            mc.getTextureManager().bindTexture(HakiGUI.haki_gui);

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