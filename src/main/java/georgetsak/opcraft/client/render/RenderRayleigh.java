package georgetsak.opcraft.client.render;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.model.ModelRayleigh;
import georgetsak.opcraft.common.entity.other.EntityRayleigh;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderRayleigh extends RenderLiving<EntityRayleigh> {

    ResourceLocation texture = new ResourceLocation(OPCraft.MODID, "textures/entity/other/rayleigh.png");

    public RenderRayleigh(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelRayleigh(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityRayleigh entity) {
        return texture;
    }
}
