package georgetsak.opcraft.client.render.devilfruit;

import georgetsak.opcraft.client.model.devilfruit.ModelSmokePunch;
import georgetsak.opcraft.common.entity.devilfruit.EntitySmokePunch;
import georgetsak.opcraft.OPCraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderSmokePunch extends RenderLiving<EntitySmokePunch>{

    public RenderSmokePunch(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
        super(rendermanagerIn, modelbaseIn, shadowsizeIn);
    }

    ResourceLocation texture = new ResourceLocation(OPCraft.MODID, "textures/entity/power/mokumoku/smoke.png");

    @SideOnly(Side.CLIENT)
    public RenderSmokePunch(RenderManager manager) {
        super(manager, new ModelSmokePunch(), 0.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntitySmokePunch entity) {
        return texture;
    }

}
