package georgetsak.opcraft.client.render.devilfruit;

import georgetsak.opcraft.client.model.devilfruit.ModelIceSaber;
import georgetsak.opcraft.common.entity.devilfruit.EntityIceSaber;
import georgetsak.opcraft.OPCraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderIceSaber extends RenderLiving<EntityIceSaber>{

    public RenderIceSaber(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
        super(rendermanagerIn, modelbaseIn, shadowsizeIn);
    }


    ResourceLocation texture = new ResourceLocation(OPCraft.MODID, "textures/entity/power/hiehie/icesaber.png");

    @SideOnly(Side.CLIENT)
    public RenderIceSaber(RenderManager manager) {
        super(manager, new ModelIceSaber(), 0.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityIceSaber entity) {
        return texture;
    }

}

