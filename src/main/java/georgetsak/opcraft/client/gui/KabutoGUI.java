package georgetsak.opcraft.client.gui;

import georgetsak.opcraft.OPCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KabutoGUI extends GuiContainer {
    private static final ResourceLocation GUI = new ResourceLocation(OPCraft.MODID,"textures/gui/kabuto_inventory.png");
    private final IInventory upperChestInventory;
    private final ItemStack kabutoStack;

    public KabutoGUI(InventoryPlayer upperInv, ItemStack kabutoStack) {
        super(new ContainerKabuto(upperInv, kabutoStack, Minecraft.getMinecraft().world));
        this.upperChestInventory = upperInv;
        this.kabutoStack = kabutoStack;
        this.allowUserInput = false;
        this.xSize = 182;
        this.ySize = 132;
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRenderer.drawString(kabutoStack.getDisplayName(), 8, 6, 4210752);
        this.fontRenderer.drawString(this.upperChestInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GUI);
        drawModalRectWithCustomSizedTexture((width/2)-(xSize/2), (height/2)-(ySize/2), 0, 0, xSize, ySize, xSize, ySize);
    }
}