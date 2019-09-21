package georgetsak.opcraft.client.render;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.model.npc.ModelKuro;
import georgetsak.opcraft.common.entity.other.EntityKuro;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderKuro extends RenderLiving<EntityKuro> {

    private ResourceLocation texture = new ResourceLocation(OPCraft.MODID, "textures/entity/boss/kuro.png");

    public RenderKuro(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelKuro(), 0.5f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityKuro entity) {
        return texture;
    }
}
