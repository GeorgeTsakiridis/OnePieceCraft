package georgetsak.opcraft.client.gui.guidebook;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.dev_notUsed.OPLog;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PageManager {

    private static ArrayList<Page> pages = new ArrayList<>();

    public static void addPage(String filePath) {
        if(filePath.equals("null")){
            pages.add(new Page());
        }else {
            try {
                ResourceLocation loc = new ResourceLocation(OPCraft.MODID, "book/" + filePath + ".json");
                InputStream inputStream = Minecraft.getMinecraft().getResourceManager().getResource(loc).getInputStream();
                String jsonString = toString(inputStream);
                JsonObject object = new JsonParser().parse(jsonString).getAsJsonObject();
                String pageType = object.get("type").getAsString();

                switch (pageType) {
                    case "devil_fruit": {
                        pages.add(new PagePower(object));
                        break;
                    }
                    case "simple_recipe": {
                        pages.add(new PageSimpleRecipe(object));
                        break;
                    }
                    case "weapon_recipe": {
                        pages.add(new PageWeaponRecipe(object));
                        break;
                    }
                    case "ship_recipe": {
                        pages.add(new PageShipRecipe(object));
                        break;
                    }
                    case "variety_recipe":{
                        pages.add(new PageVarietyRecipe(object));
                        break;
                    }
                    case "entity_info":{
                        pages.add(new PageEntityInfo(object));
                        break;
                    }
                    case "info":{
                        pages.add(new PageInfo(object));
                        break;
                    }
                }

            } catch (Exception e) {
                OPLog.logWarning("Error when adding page.\n" + e);
            }
        }
    }

    public static void tick(int pageID){
        if(pageID < getTotalPages()){
            pages.get(pageID).tick();
        }
    }

    public static void drawPage(int pageID, GuiScreen screen, FontRenderer fontRenderer) {
        if (pageID < getTotalPages()) {
            pages.get(pageID).draw(screen, fontRenderer);
        }
    }

    public static int getTotalPages(){
        return pages.size();
    }

    private static String toString(InputStream inputStream) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }

        return out.toString();
    }

}
