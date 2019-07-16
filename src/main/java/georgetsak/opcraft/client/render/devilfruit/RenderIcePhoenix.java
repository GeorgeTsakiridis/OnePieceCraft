package georgetsak.opcraft.client.render.devilfruit;

import georgetsak.opcraft.client.model.devilfruit.ModelIcePhoenix;
import georgetsak.opcraft.common.entity.devilfruit.EntityIcePhoenix;
import georgetsak.opcraft.OPCraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderIcePhoenix extends RenderLiving<EntityIcePhoenix>{

    public RenderIcePhoenix(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
        super(rendermanagerIn, modelbaseIn, shadowsizeIn);
    }


    ResourceLocation texture = new ResourceLocation(OPCraft.MODID, "textures/entity/power/hiehie/icephoenix.png");

    @SideOnly(Side.CLIENT)
    public RenderIcePhoenix(RenderManager manager) {
        super(manager, new ModelIcePhoenix(), 0.0F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityIcePhoenix entity) {
        return texture;
    }

}

