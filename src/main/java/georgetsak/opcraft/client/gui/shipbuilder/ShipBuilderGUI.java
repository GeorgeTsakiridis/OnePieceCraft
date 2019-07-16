package georgetsak.opcraft.client.gui.shipbuilder;

import georgetsak.opcraft.OPCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.awt.*;

/**
 * Created by GeorgeTsak on 8/2/2017.
 */
public class ShipBuilderGUI extends GuiContainer {

    private ResourceLocation texture = new ResourceLocation(OPCraft.MODID + ":textures/gui/shipbuilder.png");

    public ShipBuilderGUI(EntityPlayer player, World world, int x, int y, int z) {
        super(new ContainerCraftingShipBuilder(player.inventory, world, new BlockPos(x, y, z)));
        this.xSize = 256;
        this.ySize = 203;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String name = "Ship Builder";
        drawString(fontRenderer, name, this.xSize/2 - fontRenderer.getStringWidth(name)/2, 5, Color.WHITE.getRGB());
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
