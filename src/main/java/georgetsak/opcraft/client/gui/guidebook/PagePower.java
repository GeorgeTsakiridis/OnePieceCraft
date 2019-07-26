package georgetsak.opcraft.client.gui.guidebook;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PagePower extends Page{

    private final String TITLE;
    private final String PREFIX;
    private final String[] NAMES;
    private final String[] COOLDOWNS;
    private final String[] DESCRIPTIONS;

    public PagePower(JsonObject object) {
        super();
        JsonArray powersArray = object.get("powers").getAsJsonArray();
        int powersNum = powersArray.get(0).getAsInt();

        TITLE = object.get("title").getAsString();
        PREFIX = object.get("prefix").getAsString();
        NAMES = new String[powersNum];
        COOLDOWNS = new String[powersNum];
        DESCRIPTIONS = new String[powersNum];

        for(int i = 0; i < powersNum; i++){
            JsonObject powerArray = powersArray.get(i+1).getAsJsonObject();
            NAMES[i] = powerArray.get("name").getAsString();
            COOLDOWNS[i] = powerArray.get("cooldown").getAsString();
            DESCRIPTIONS[i] = powerArray.get("description").getAsString();
        }

    }

    @Override
    public void draw(GuiScreen screen, FontRenderer fontRenderer){
        int y = 40;
        GlStateManager.pushMatrix();
        GlStateManager.scale(2F, 2F, 2F);
        screen.drawCenteredString(fontRenderer, TITLE, screen.width/4, 5, new Color(255, 110, 0).getRGB());

        GlStateManager.popMatrix();

        int border = (int)((float)screen.width*0.025f);
        for(int i = 0; i < NAMES.length; i++){
            screen.drawCenteredString(fontRenderer, PREFIX + ": " + NAMES[i], screen.width/2, y, new Color(255, 110, 0).getRGB());
            y+=fontRenderer.FONT_HEIGHT;

            List<String> strings = fontRenderer.listFormattedStringToWidth(DESCRIPTIONS[i], screen.width-border*2);
            for(String line: new ArrayList<>(strings)){
                screen.drawCenteredString(fontRenderer, line, screen.width/2, y, Color.WHITE.getRGB());
                y+=fontRenderer.FONT_HEIGHT;
            }
            String info = "Cooldown: " + COOLDOWNS[i] + "s";
            screen.drawCenteredString(fontRenderer, info, screen.width/2, y, Color.WHITE.getRGB());
            y+=fontRenderer.FONT_HEIGHT*2;
        }

    }

}

