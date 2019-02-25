package georgetsak.opcraft.client.gui.guidebook;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PageVarietyRecipe extends Page {

    private final String[] TITLES;
    private final String[][] LINES;
    private final String[] ITEM_OR_BLOCK;
    private final String[][] RESULT;

    public PageVarietyRecipe(JsonObject object) {
        int size = object.get("size").getAsInt();

        TITLES = new String[size];
        LINES = new String[size][];
        ITEM_OR_BLOCK = new String[size];
        RESULT = new String[size][];

        JsonArray recipesArray = object.get("recipes").getAsJsonArray();

        for(int i = 0; i < size; i++){
            JsonObject recipeObject = recipesArray.get(i).getAsJsonObject();

            TITLES[i] = recipeObject.get("title").getAsString();
            ITEM_OR_BLOCK[i] = recipeObject.get("itemOrBlock").getAsString();
            int results = recipeObject.get("totalResults").getAsInt();
            RESULT[i] = new String[results];
            for(int j = 0; j < results; j++){
                RESULT[i][j] = recipeObject.get("results").getAsJsonArray().get(j).getAsString();
            }

            int totalLines = recipeObject.get("totalLines").getAsInt();
            LINES[i] = new String[totalLines];
            for(int j = 0; j < totalLines; j++){
                LINES[i][j] = recipeObject.get("lines").getAsJsonArray().get(j).getAsString();
            }
        }

    }

    int step = 0;
    public void draw(GuiScreen screen, FontRenderer fontRenderer){

        boolean oneRecipe = TITLES.length == 1;
        if(oneRecipe){
            String line = "";
            ItemStack itemStack;

            for(int i = 0; i < LINES[0].length; i++){
                line = line.concat(LINES[0][i]).concat("\n");
            }
            int results = RESULT[0].length;
            if(drawTick > 20){
                drawTick = 0;
                step++;
            }
            if (step == results){
                step = 0;
            }
            if(ITEM_OR_BLOCK[0].equals("block")){
                itemStack = new ItemStack(Block.getBlockFromName(RESULT[0][step]));
            }else {
                itemStack = new ItemStack(Item.getByNameOrId(RESULT[0][step]));
            }
            CraftingRecipeRenderer.drawCraftingRecipe(screen, fontRenderer, TITLES[0], null, line, null, itemStack, null);
        }else {
            String[] lines = new String[]{"", ""};
            ItemStack[] itemStacks = new ItemStack[2];

            for(int i = 0; i < 2; i++){
                for(int j =0; j < LINES[i].length; j++){
                    lines[i] = lines[i].concat(LINES[i][j]).concat("\n");
                }
                int results = RESULT[0].length;
                if(drawTick > 20){
                    drawTick = 0;
                    step++;
                }
                if (step == results){
                    step = 0;
                }
                if (ITEM_OR_BLOCK[i].equals("block")) {
                    itemStacks[i] = new ItemStack(Block.getBlockFromName(RESULT[i][step]));
                }else {
                    itemStacks[i] = new ItemStack(Item.getByNameOrId(RESULT[i][step]));
                }
            }
            CraftingRecipeRenderer.drawCraftingRecipe(screen, fontRenderer, TITLES[0], TITLES[1], lines[0], lines[1], itemStacks[0], itemStacks[1]);

        }

    }

}
