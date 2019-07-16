package georgetsak.opcraft.client.render.devilfruit;

import georgetsak.opcraft.client.model.devilfruit.ModelSmokeSnake;
import georgetsak.opcraft.common.entity.devilfruit.EntitySmokeSnake;
import georgetsak.opcraft.OPCraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderSmokeSnake extends RenderLiving<EntitySmokeSnake>{

    ResourceLocation texture = new ResourceLocation(OPCraft.MODID, "textures/entity/power/mokumoku/smoke.png");

    @SideOnly(Side.CLIENT)
    public RenderSmokeSnake(RenderManager manager) {
        super(manager, new ModelSmokeSnake(), 0.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntitySmokeSnake entity) {
        return texture;
    }

}
