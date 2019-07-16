package georgetsak.opcraft.dev_notUsed.christos;

import georgetsak.opcraft.OPCraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderChristos extends RenderLiving<EntityChristos> {

    @SideOnly(Side.CLIENT)
    public RenderChristos(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelChristos(), 0.5F);
    }


    ResourceLocation texture = new ResourceLocation(OPCraft.MODID, "textures/entity/other/christos.png");


    @Override
    protected ResourceLocation getEntityTexture(EntityChristos entity) {
        return texture;
    }

}