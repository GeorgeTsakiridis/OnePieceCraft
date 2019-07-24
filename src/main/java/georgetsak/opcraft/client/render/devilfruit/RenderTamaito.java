package georgetsak.opcraft.client.render.devilfruit;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.model.devilfruit.ModelTamaito;
import georgetsak.opcraft.common.entity.devilfruit.EntityTamaito;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderTamaito extends RenderLiving<EntityTamaito> {

    ResourceLocation texture = new ResourceLocation(OPCraft.MODID, "textures/entity/power/ito/tamaito.png");

    @SideOnly(Side.CLIENT)
    public RenderTamaito(RenderManager manager) {
        super(manager, new ModelTamaito(), 0.0F);
    }

    @Override
    public boolean shouldRender(EntityTamaito livingEntity, ICamera camera, double camX, double camY, double camZ) {
        return true;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityTamaito entity) {
        return texture;
    }

}
