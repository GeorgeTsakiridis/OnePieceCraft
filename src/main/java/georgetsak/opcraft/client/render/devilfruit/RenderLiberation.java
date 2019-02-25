package georgetsak.opcraft.client.render.devilfruit;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.model.devilfruit.ModelLiberation;
import georgetsak.opcraft.common.entity.devilfruit.EntityLiberation;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderLiberation extends RenderLiving<EntityLiberation> {

    final ResourceLocation TEXTURE = new ResourceLocation(OPCraft.MODID, "textures/entity/power/yami/dark.png");

    public RenderLiberation(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelLiberation(), 0f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityLiberation entity) {
        return TEXTURE;
    }
}
