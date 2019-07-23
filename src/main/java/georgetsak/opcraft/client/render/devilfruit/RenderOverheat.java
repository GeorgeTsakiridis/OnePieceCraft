package georgetsak.opcraft.client.render.devilfruit;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.model.devilfruit.ModelOverheat;
import georgetsak.opcraft.common.entity.devilfruit.EntityOverheat;
import georgetsak.opcraft.common.entity.devilfruit.EntityOverheat;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderOverheat extends RenderLiving<EntityOverheat> {
    ResourceLocation texture = new ResourceLocation(OPCraft.MODID, "textures/entity/power/ito/overheat.png");

    @SideOnly(Side.CLIENT)
    public RenderOverheat(RenderManager manager) {
        super(manager, new ModelOverheat(), 0.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityOverheat entity) {
        return texture;
    }
}
