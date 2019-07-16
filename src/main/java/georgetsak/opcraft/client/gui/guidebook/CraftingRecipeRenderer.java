package georgetsak.opcraft.client.gui.guidebook;

import com.google.common.collect.Lists;
import georgetsak.opcraft.OPCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CraftingRecipeRenderer {
    private static final ResourceLocation craftingGUI = new ResourceLocation(OPCraft.MODID, "textures/gui/manual/crafting.png");

    static ArrayList<ItemStack> forcedItemStack;

    public static void prepareCraftingRecipeCrocodileHook(){
        ItemStack poison = new ItemStack(Items.POTIONITEM);
        PotionUtils.addPotionToItemStack(poison, PotionTypes.POISON);
        forcedItemStack = new ArrayList<>();
        forcedItemStack.add(null);
        forcedItemStack.add(poison);
        forcedItemStack.add(new ItemStack(Items.IRON_INGOT));
        forcedItemStack.add(new ItemStack(Blocks.GOLD_BLOCK));
        forcedItemStack.add(new ItemStack(Items.GOLD_INGOT));
        forcedItemStack.add(null);
        forcedItemStack.add(new ItemStack(Items.GOLD_INGOT));
        forcedItemStack.add(new ItemStack(Blocks.GOLD_BLOCK));
        forcedItemStack.add(null);
    }

    public static void drawCraftingRecipe(GuiScreen screen, FontRenderer fontRenderer, String title1, String title2, String des1, String des2, ItemStack result1, ItemStack result2){
        int middleX = screen.width/2;
        int middleY = screen.height/2;
        ArrayList<ItemStack> recipe1 = getItemRecipeItems(result1.getItem());
        ArrayList<ItemStack> recipe2 = null;
        if(forcedItemStack == null) {
            if(result2 != null) {
                recipe2 = getItemRecipeItems(result2.getItem());
            }
        }else {
            recipe2 = forcedItemStack;
            forcedItemStack = null;
        }

        GlStateManager.pushMatrix();
        if(title1 != null) {
            screen.drawCenteredString(fontRenderer, title1, (int) (middleX * 1.35f), middleY - 76, new Color(255, 110, 0).getRGB());
        }
        int currentYPos = 0;
        if(des1 != null) {
            List<String> finalDes1 = fontRenderer.listFormattedStringToWidth(des1, (int) (screen.width * 0.65f - screen.width * 0.03));
            for(int i = 0; i < finalDes1.size(); i++){
                currentYPos = middleY - 67+ fontRenderer.FONT_HEIGHT*i;
                screen.drawCenteredString(fontRenderer, finalDes1.get(i), (int)(screen.width-screen.width*0.35f) , currentYPos, Color.WHITE.getRGB());
            }
        }

        if(title2 !=null) {
            if((middleY + 10) - currentYPos < 9){
                screen.drawCenteredString(fontRenderer, title2, (int) (middleX * 1.35f), currentYPos + 2*fontRenderer.FONT_HEIGHT, new Color(255, 110, 0).getRGB());
            }else {
                screen.drawCenteredString(fontRenderer, title2, (int) (middleX * 1.35f), middleY + 10, new Color(255, 110, 0).getRGB());
            }
        }

        if(des2 != null) {
            List<String> finalDes2 = fontRenderer.listFormattedStringToWidth(des2, (int) (screen.width * 0.65f - screen.width * 0.03));//0.666 this numbers is the result of 2/3
            for (int i = 0; i < finalDes2.size(); i++) {
                if(middleY + 10 - currentYPos < 9){
                    screen.drawCenteredString(fontRenderer, finalDes2.get(i), (int) (screen.width - screen.width * 0.35f), currentYPos + fontRenderer.FONT_HEIGHT * (i+3), Color.WHITE.getRGB());
                    continue;
                }
                screen.drawCenteredString(fontRenderer, finalDes2.get(i), (int) (screen.width - screen.width * 0.35f), middleY + 19 + fontRenderer.FONT_HEIGHT * i, Color.WHITE.getRGB());
            }
        }

        GlStateManager.popMatrix();

        GlStateManager.color(1f, 1f, 1f, 1f);
        Minecraft.getMinecraft().getTextureManager().bindTexture(craftingGUI);
        Gui.drawScaledCustomSizeModalRect((int)(screen.width*0.03f) - 7, middleY - 76, 0, 0, 256, 132, 256/2, 132/2, 256,132);
        if(result2 != null) {
            Gui.drawScaledCustomSizeModalRect((int) (screen.width * 0.03f) - 7, middleY + 10, 0, 0, 256, 132, 256 / 2, 132 / 2, 256, 132);
        }

        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        RenderHelper.enableGUIStandardItemLighting();
        int i = 0;
        GlStateManager.disableLighting();
        GlStateManager.enableCull();
        for(int y = 0; y < 3; y++){
            for(int x = 0; x < 3; x++){
                if(recipe1.size() > i && recipe1.get(i) != null){
                    renderItem.renderItemAndEffectIntoGUI(recipe1.get(i), (int)(screen.width*0.03f) + 2*x + x * 16, middleY-69 + 2*y + y * 16);
                }
                if(recipe2 != null && recipe2.size() > i && recipe2.get(i) != null){
                    renderItem.renderItemAndEffectIntoGUI(recipe2.get(i), (int)(screen.width*0.03f) + 2*x + x * 16, middleY-14 + 31 +2*y + y * 16);
                }
                i++;
            }
        }
        if(result1 != null) {
            renderItem.renderItemAndEffectIntoGUI(result1, (int) (screen.width * 0.03f) + 94, middleY - 51);
        }
        if(result2 != null) {
            renderItem.renderItemAndEffectIntoGUI(result2, (int) (screen.width * 0.03f) + 94, middleY + 35);
        }
        GlStateManager.disableLighting();
    }

    private static ArrayList<ItemStack> getItemRecipeItems(Item item) {

        List<IRecipe> recipes = Lists.newArrayList(CraftingManager.REGISTRY);

        ArrayList<ItemStack> items = new ArrayList<>();
        for (IRecipe recipe : recipes) {
            if (recipe instanceof ShapedRecipes) {
                ShapedRecipes shapedRecipes = (ShapedRecipes) recipe;
                if (shapedRecipes.getRecipeOutput().getItem() == item) {
                    for (int i = 0; i < shapedRecipes.recipeItems.size(); i++) {
                        if(shapedRecipes.recipeItems.get(i).getMatchingStacks().length > 0) {
                            items.add(shapedRecipes.recipeItems.get(i).getMatchingStacks()[0]);
                        }else{
                            items.add(null);
                        }
                    }
                }

            }
        }

        if(items.isEmpty()){
            for(IRecipe recipe : recipes){
                if(recipe instanceof ShapelessRecipes){
                    ShapelessRecipes shapelessRecipes = (ShapelessRecipes)recipe;
                    if(shapelessRecipes.getRecipeOutput().getItem() == item){
                        for(int i = 0; i < shapelessRecipes.recipeItems.size(); i++){
                            items.add(shapelessRecipes.recipeItems.get(i).getMatchingStacks()[0]);
                        }
                    }
                }
            }
        }
        return items;
    }

}
