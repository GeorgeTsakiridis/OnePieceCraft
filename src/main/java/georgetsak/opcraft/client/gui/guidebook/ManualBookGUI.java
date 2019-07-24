package georgetsak.opcraft.client.gui.guidebook;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import georgetsak.opcraft.common.network.packets.server.ManualBookPageServerPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

/**
 * Created by GeorgeProgramming on 7/10/2017.
 */
public class ManualBookGUI extends GuiScreen {

    private static final ResourceLocation page0 = new ResourceLocation(OPCraft.MODID, "textures/gui/manual/page0.png");


    private GuiButton prevPage;
    private GuiButton nextPage;
    private EntityPlayer player;

    int page = 0;
    private final int totalPages = PageManager.getTotalPages() - 1;//32;

    private int x = width/2;

    private int value = 1;

    public ManualBookGUI(EntityPlayer player){
        if(player != null){
            this.player = player;
            this.page = player.inventory.getCurrentItem().getTagCompound().getInteger("page");
        }
    }

    @Override
    public void initGui() {
        this.prevPage = this.addButton(new GuiButton(0, this.width / 2 - 100, height - 40, 98, 20, "Previous Page"));
        this.nextPage = this.addButton(new GuiButton(1, this.width / 2 + 2, height - 40, 98, 20, "Next Page"));

        this.prevPage.enabled = this.page != 0;
        if(this.page == totalPages){
            this.nextPage.enabled = false;
        }

        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {

        switch (button.id) {
            case 0:
                page--;
                break;
            case 1:
                page++;
                break;
        }

        if(page == totalPages){
            this.nextPage.enabled = false;
        }
        else if(page == 0){
            this.prevPage.enabled = false;
        }
        else{
            this.nextPage.enabled = true;
            this.prevPage.enabled = true;
        }
        super.actionPerformed(button);
    }

    @Override
    public void onGuiClosed() {
        if(player != null) {
            player.inventory.getCurrentItem().getTagCompound().setInteger("page", page);
            PacketDispatcher.sendToServer(new ManualBookPageServerPacket(page));
        }
        super.onGuiClosed();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        this.x = width/2;
        if(page > totalPages)page = totalPages;//Overflow protect. When resizing game.

        if(page == 0){
            drawCoverPage();
        }else {
            PageManager.drawPage(page, this, fontRenderer);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        PageManager.tick(page);
    }

    public void drawCoverPage(){
        Minecraft.getMinecraft().getTextureManager().bindTexture(page0);
        Gui.drawScaledCustomSizeModalRect(0, 0, 0, 0, 1920, 1080, width, height, 1920,1080);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }

}
