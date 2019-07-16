package georgetsak.opcraft.client.render.devilfruit;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.model.devilfruit.ModelDark;
import georgetsak.opcraft.common.entity.devilfruit.EntityDark;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderDark extends RenderLiving<EntityDark> {

    final ResourceLocation TEXTURE = new ResourceLocation(OPCraft.MODID, "textures/entity/power/yami/dark.png");

    public RenderDark(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelDark(), 0f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityDark entity) {
        return TEXTURE;
    }
}
