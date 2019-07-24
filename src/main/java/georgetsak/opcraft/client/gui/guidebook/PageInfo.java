package georgetsak.opcraft.client.gui.guidebook;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import georgetsak.opcraft.common.entity.marine.EntityHardMarine;
import georgetsak.opcraft.common.entity.marine.EntityMarine;
import georgetsak.opcraft.common.entity.marine.EntityMorgan;
import georgetsak.opcraft.common.entity.other.*;
import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PageInfo extends Page {

    private final String TITLE;
    private final String[] LINES;
    private final ItemStack ITEMSTACK;

    public PageInfo(JsonObject object) {
        int totalLines = object.get("totalLines").getAsInt();
        TITLE = object.get("title").getAsString();
        LINES = new String[totalLines];
        ITEMSTACK = new ItemStack(Item.getByNameOrId(object.get("icon").getAsString()));

        JsonArray linesArray = object.get("lines").getAsJsonArray();
        for(int i = 0; i < totalLines; i++){
            LINES[i] = linesArray.get(i).getAsString();
        }

    }

    public void draw(GuiScreen screen, FontRenderer fontRenderer) {

        int y = 40;
        GlStateManager.pushMatrix();
        GlStateManager.scale(2F, 2F, 2F);
        screen.drawCenteredString(fontRenderer, TITLE, screen.width / 4, 5, new Color(255, 110, 0).getRGB());
        GlStateManager.popMatrix();

        String info = "";
        for (int i = 0; i < LINES.length; i++) {
            info = info.concat(LINES[i]).concat("\n");
        }

        int currentYPos;
        List<String> finalDes1 = fontRenderer.listFormattedStringToWidth(info, (int) (screen.width * 0.7f));
        for(int i = 0; i < finalDes1.size(); i++) {
            currentYPos = 40 + fontRenderer.FONT_HEIGHT * i;
            screen.drawCenteredString(fontRenderer, finalDes1.get(i), (int) (screen.width - screen.width * 0.45f), currentYPos, Color.WHITE.getRGB());
        }

        GlStateManager.pushMatrix();
        GlStateManager.translate(-120f, -105f, -100f);
        GlStateManager.scale(3F, 3F, 3F);
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        renderItem.renderItemAndEffectIntoGUI(ITEMSTACK, 50, 50);
        GlStateManager.popMatrix();

    }

}
