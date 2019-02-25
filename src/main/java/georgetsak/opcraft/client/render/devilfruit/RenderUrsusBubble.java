package georgetsak.opcraft.client.render.devilfruit;

import georgetsak.opcraft.client.model.devilfruit.ModelUrsusBubble;
import georgetsak.opcraft.common.entity.devilfruit.EntityUrsusBubble;
import georgetsak.opcraft.OPCraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderUrsusBubble extends RenderLiving<EntityUrsusBubble>{

    public RenderUrsusBubble(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
        super(rendermanagerIn, modelbaseIn, shadowsizeIn);
    }


    ResourceLocation texture = new ResourceLocation(OPCraft.MODID, "textures/entity/power/nikyu/ursusbubble.png");

    @SideOnly(Side.CLIENT)
    public RenderUrsusBubble(RenderManager manager) {
        super(manager, new ModelUrsusBubble(), 0.0F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityUrsusBubble entity) {
        return texture;
    }

}

