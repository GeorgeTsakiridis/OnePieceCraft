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
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PageEntityInfo extends Page {

    private final String TITLE;
    private final String[] ENTITIES_TO_RENDER;
    private final int RENDER_SIZE;
    private final String[] LINES;

    public PageEntityInfo(JsonObject object) {
        int totalLines = object.get("totalLines").getAsInt();
        JsonArray entityArray = object.get("entityToDisplay").getAsJsonArray();

        TITLE = object.get("title").getAsString();
        ENTITIES_TO_RENDER = new String[entityArray.size()];
        RENDER_SIZE = object.get("displaySize").getAsInt();
        LINES = new String[totalLines];

        for(int i = 0; i < entityArray.size(); i++){
            ENTITIES_TO_RENDER[i] = entityArray.get(i).getAsString();
        }

        JsonArray linesArray = object.get("lines").getAsJsonArray();
        for(int i = 0; i < totalLines; i++){
                LINES[i] = linesArray.get(i).getAsString();
        }

    }

    private EntityLiving toEntity(String entry){
        World world = Minecraft.getMinecraft().world;

        EntityPirate pirate = new EntityPirate(world);
        pirate.setTexture(1);

        EntityMarine marine = new EntityMarine(world);
        marine.setMarineType(1);

        EntityHardMarine hardMarine = new EntityHardMarine(world);
        hardMarine.setMarineType(1);

        EntitySkypiean skypiean = new EntitySkypiean(world);

        switch (entry){
            case "morgan": return new EntityMorgan(world);
            case "pirateStrong": pirate.setType(EntityPirate.STRONG); return pirate;
            case "pirateThin": pirate.setType(EntityPirate.THIN); return pirate;
            case "pirateFat": pirate.setType(EntityPirate.FAT); return pirate;
            case "bandit": return new EntityBandit(world);
            case "crocodile": return new EntityCrocodile(world);
            case "rayleigh": return new EntityRayleigh(world);
            case "tonta": return new EntityTonta(world);
            case "villager": return new EntityOPVillager(world);
            case "marine": return marine;
            case "hardMarine": return hardMarine;
            case "skypiean1": skypiean.setType(0); return skypiean;
            case "skypiean2": skypiean.setType(1); return skypiean;
            case "skypiean3": skypiean.setType(2); return skypiean;
            case "skypiean4": skypiean.setType(3); return skypiean;
            case "skypiean5": skypiean.setType(4); return skypiean;
        }
        return new EntityZombie(world);
    }

    @Override
    public void tick() {
        super.tick();
        stepTick++;
    }

    int step = 0;
    int stepTick = 0;
    public void draw(GuiScreen screen, FontRenderer fontRenderer) {

        int total = ENTITIES_TO_RENDER.length;
        if(stepTick > 60){
            stepTick = 0;
            step++;
        }
        if (step == total){
            step = 0;
        }


        int y = 40;
        GlStateManager.pushMatrix();
        GlStateManager.scale(2F, 2F, 2F);
        screen.drawCenteredString(fontRenderer, TITLE, screen.width / 4, 5, new Color(255, 110, 0).getRGB());
        GlStateManager.popMatrix();
        GlStateManager.color(1f, 1f, 1f);
        OPUtils.drawEntityOnScreen(60, screen.height/2 + RENDER_SIZE/2, RENDER_SIZE, drawTick, toEntity(ENTITIES_TO_RENDER[step]));

        String info = "";
        for (int i = 0; i < LINES.length; i++) {
            info = info.concat(LINES[i]).concat("\n");
        }

        int currentYPos;
            List<String> finalDes1 = fontRenderer.listFormattedStringToWidth(info, (int) (screen.width * 0.65f - screen.width * 0.03));
            for(int i = 0; i < finalDes1.size(); i++) {
                currentYPos = 40 + fontRenderer.FONT_HEIGHT * i;
                screen.drawCenteredString(fontRenderer, finalDes1.get(i), (int) (screen.width - screen.width * 0.45f), currentYPos, Color.WHITE.getRGB());
            }
            /**
            int border = (int) ((float) screen.width * 0.025f);
        List<String> strings = fontRenderer.listFormattedStringToWidth(info, screen.width - border * 2);
        for (String line : new ArrayList<>(strings)) {
            screen.drawCenteredString(fontRenderer, line, screen.width / 2, y, Color.WHITE.getRGB());
            y += fontRenderer.FONT_HEIGHT;
        }
**/
    }

}
