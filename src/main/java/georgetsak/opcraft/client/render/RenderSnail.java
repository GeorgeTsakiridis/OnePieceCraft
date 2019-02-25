package georgetsak.opcraft.client.render;

import georgetsak.opcraft.common.block.tile.SnailTileEntity;
import georgetsak.opcraft.client.model.ModelSnail;
import georgetsak.opcraft.OPCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderSnail extends TileEntitySpecialRenderer<SnailTileEntity> {

    ModelSnail ms = new ModelSnail();

    final ResourceLocation SNAIL = new ResourceLocation(OPCraft.MODID, "textures/entity/other/snail.png");

    @Override
    public void render(SnailTileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        super.render(te, x, y, z, partialTicks, destroyStage, alpha);
        float angles = 0F;
        int i = te.getBlockMetadata();
        Minecraft.getMinecraft().getTextureManager().bindTexture(SNAIL);
//2-5

        switch (i){
            case 2: angles = -90F; break;
            case 3: angles = 90; break;
            case 4: angles = 180F; break;
            case 5: angles = 0F; break;
        }

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        GlStateManager.rotate(180, 1, 0, 1);
        GlStateManager.rotate(angles, 0, 1, 0);
        GlStateManager.translate(0.5, -1.5, 0.5);
        if(i == 2) GlStateManager.translate(0,0,-1);
        if(i == 3) GlStateManager.translate(-1,0,0);
        if(i == 4) GlStateManager.translate(-1,0,-1);

        ms.renderAll();
        GlStateManager.popMatrix();

    }
}
