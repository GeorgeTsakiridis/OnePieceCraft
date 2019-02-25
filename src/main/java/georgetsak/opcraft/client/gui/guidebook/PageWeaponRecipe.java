package georgetsak.opcraft.client.gui.guidebook;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import georgetsak.opcraft.common.registry.OPItems;
import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PageWeaponRecipe extends Page {

    private final String[] TITLES;
    private final String[][] LINES;
    private final String[] ITEM_OR_BLOCK;
    private final String[] RESULT;
    private final int[] ATTACK;
    private final int[] DURABILITY;
    private final boolean[] RIGHT_CLICK_TO_REMOVE;
    private final int[] RANGE;
    private final float[] RELOAD_TIME;


    public PageWeaponRecipe(JsonObject object) {
        int size = object.get("size").getAsInt();

        TITLES = new String[size];
        LINES = new String[size][];
        ITEM_OR_BLOCK = new String[size];
        RESULT = new String[size];
        ATTACK = new int[size];
        DURABILITY = new int[size];
        RIGHT_CLICK_TO_REMOVE = new boolean[size];
        RANGE = new int[size];
        RELOAD_TIME = new float[size];

        JsonArray recipesArray = object.get("recipes").getAsJsonArray();

        for(int i = 0; i < size; i++){
            JsonObject recipeObject = recipesArray.get(i).getAsJsonObject();

            TITLES[i] = recipeObject.get("title").getAsString();
            ITEM_OR_BLOCK[i] = recipeObject.get("itemOrBlock").getAsString();
            RESULT[i] = recipeObject.get("result").getAsString();
            ATTACK[i] = recipeObject.get("attack").getAsInt();
            DURABILITY[i] = recipeObject.get("durability").getAsInt();
            RIGHT_CLICK_TO_REMOVE[i] = recipeObject.get("rightClickToRemove").getAsBoolean();
            RANGE[i] = recipeObject.get("range").getAsInt();
            RELOAD_TIME[i] = recipeObject.get("reloadTime").getAsFloat();

            int totalLines = recipeObject.get("totalLines").getAsInt();
            LINES[i] = new String[totalLines];
            for(int j = 0; j < totalLines; j++){
                LINES[i][j] = recipeObject.get("lines").getAsJsonArray().get(j).getAsString();
            }
        }

    }

    public void draw(GuiScreen screen, FontRenderer fontRenderer){
        boolean oneRecipe = TITLES.length == 1;

        if(oneRecipe){
            String line = "";
            ItemStack itemStack;

            for(int i = 0; i < LINES[0].length; i++){
                line = line.concat(LINES[0][i]).concat("\n");
            }
            line = line.concat(extraStringBuilder(0));

            if(ITEM_OR_BLOCK[0].equals("block")){
                itemStack = new ItemStack(Block.getBlockFromName(RESULT[0]));
            }else {
                itemStack = new ItemStack(Item.getByNameOrId(RESULT[0]));
            }
            CraftingRecipeRenderer.drawCraftingRecipe(screen, fontRenderer, TITLES[0], null, line, null, itemStack, null);
        }else {
            String[] lines = new String[]{"", ""};
            ItemStack[] itemStacks = new ItemStack[2];

            for(int i = 0; i < 2; i++){
                for(int j =0; j < LINES[i].length; j++){
                    lines[i] = lines[i].concat(LINES[i][j]).concat("\n");
                }
                lines[i] = lines[i].concat(extraStringBuilder(i));

                if (ITEM_OR_BLOCK[i].equals("block")) {
                    itemStacks[i] = new ItemStack(Block.getBlockFromName(RESULT[i]));
                }else {
                    itemStacks[i] = new ItemStack(Item.getByNameOrId(RESULT[i]));
                }
                if(RESULT[1].equals("$Crocodile")){
                    CraftingRecipeRenderer.prepareCraftingRecipeCrocodileHook();
                    itemStacks[1] = new ItemStack(OPItems.ItemCrocodileHookCased);
                }
            }

            CraftingRecipeRenderer.drawCraftingRecipe(screen, fontRenderer, TITLES[0], TITLES[1], lines[0], lines[1], itemStacks[0], itemStacks[1]);

        }

    }

    private String extraStringBuilder(int i) {
        String string = "";
        boolean firstString = true;
        if(ATTACK[i] != -1){
            string = string.concat("Attack: §a"+ ATTACK[i] +"§r");
            firstString = false;
        }
        if(DURABILITY[i] != -1){
            string = string.concat((firstString ? "" : " / ") + "Durability: §a"+ DURABILITY[i] + "§r");
            firstString = false;
        }
        if(RANGE[i] != -1){
            string = string.concat((firstString ? "" : " / ") + "Range: §a"+ RANGE[i] +"§r Blocks");
            firstString = false;
        }
        if(RELOAD_TIME[i] != -1){
            string = string.concat((firstString ? "" : " / ") + "Reload Time: §a" + RELOAD_TIME[i] + "§r sec.");
        }
        if(RIGHT_CLICK_TO_REMOVE[i]){
            string = string.concat("\nRight Click to remove case");
        }

        return string;
    }

}
