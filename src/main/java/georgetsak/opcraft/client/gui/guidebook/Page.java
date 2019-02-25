package georgetsak.opcraft.client.gui.guidebook;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;

public class Page {
    public int drawTick = 0;

    public Page(){}

    public void draw(GuiScreen screen, FontRenderer fontRenderer){

    }

    public void tick(){
        drawTick++;
    }

}
