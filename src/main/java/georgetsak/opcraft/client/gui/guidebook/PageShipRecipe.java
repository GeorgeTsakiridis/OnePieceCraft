package georgetsak.opcraft.client.gui.guidebook;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import georgetsak.opcraft.OPCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PageShipRecipe extends Page{

    private static final ResourceLocation CRAFTING_GUI = new ResourceLocation(OPCraft.MODID, "textures/gui/manual/shipbuild.png");

    private final String TITLE;
    private final String DESCRIPTION;
    private final String RESULT;

    public PageShipRecipe(JsonObject object){
        TITLE = object.get("title").getAsString();
        DESCRIPTION = object.get("description").getAsString();
        RESULT = object.get("result").getAsString();
    }

    public void draw(GuiScreen screen, FontRenderer fontRenderer){
        int middleX = screen.width/2;
        int middleY = screen.height/2;
        int marginX = (int)(screen.width * 0.035f);
        int marginY = (int)(screen.height * 0.035f);
        int posY = marginY;

        ItemStack itemStack = new ItemStack(Item.getByNameOrId(RESULT));

        GlStateManager.pushMatrix();

        screen.drawCenteredString(fontRenderer, TITLE, middleX, posY, new Color(255, 110, 0).getRGB());

        List<String> shipDes = fontRenderer.listFormattedStringToWidth(DESCRIPTION, (int)(screen.width - 2*marginX));
        posY += fontRenderer.FONT_HEIGHT * 3;
        for(int i = 0; i < shipDes.size(); i++){
            screen.drawCenteredString(fontRenderer, shipDes.get(i), middleX , posY, Color.WHITE.getRGB());
            posY += fontRenderer.FONT_HEIGHT;
        }
        Minecraft.getMinecraft().getTextureManager().bindTexture(CRAFTING_GUI);
        Gui.drawScaledCustomSizeModalRect(middleX - 256/2, posY, 0, 0, 256, 122, 256, 122, 256,122);

        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        ArrayList<ItemStack> items = getShipRecipe(itemStack);

        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.enableCull();
        int i = 0;
        for(int y = 0; y < 5; y++){
            for(int x = 0; x < 10; x++) {
                if (items.get(i) != null) {
                    renderItem.renderItemAndEffectIntoGUI(items.get(i), middleX + 6 - 18 * 7 + x * 18, y * 18 + posY + 17);
                }
                i++;
            }
        }
        renderItem.renderItemAndEffectIntoGUI(itemStack, middleX + 100, posY + 44);
        GlStateManager.popMatrix();
    }

    private static ArrayList<ItemStack> getShipRecipe(ItemStack itemStack){
        List<IRecipe> recipes = Lists.newArrayList(CraftingManager.REGISTRY);

        ArrayList<ItemStack> items = new ArrayList<>();
        for (IRecipe recipe : recipes) {
            if (recipe instanceof ShapedRecipes) {
                ShapedRecipes shapedRecipes = (ShapedRecipes) recipe;
                if (shapedRecipes.getRecipeOutput().getItem() == itemStack.getItem()) {
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
        return items;
    }


}
